package com.gc.util.mongo;

import com.mongodb.*;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.DateUtils;
import org.bson.Document;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by wn on 2018/5/28.
 */
public class MongoUtils {
    private final static String HOST = "101.132.130.228";//
    private final static String DB_NAME = "jsloan";
    private final static int PORT = 37017;//
    private final static int POOLSIZE = 100;
    private final static int BLOCKSIZE = 100;
    private final static String MONGOUSERNAME = "jsloan";
    private final static String MONGGOPWD = "pwd123";
    private final static String AUTH_DBNAME = "jsloan";
    private final static String UDESK_LIST_COLLECTION = "udesk_message_info";

    private static MongoClient mongo = null;
    private static MongoUtils single = null;

    private MongoUtils() {
    }

    public synchronized static MongoUtils getInstance() {

        if (single == null) {
            single = new MongoUtils();
            initMongoClient();
        }
        return single;
    }

    private static void initMongoClient() {
        ServerAddress serverAddress = new ServerAddress(HOST, PORT);
        List<ServerAddress> seeds = new ArrayList<ServerAddress>();
        seeds.add(serverAddress);
        MongoCredential credentials = MongoCredential.createCredential(MONGOUSERNAME, AUTH_DBNAME,
                MONGGOPWD.toCharArray());
        List<MongoCredential> credentialsList = new ArrayList<MongoCredential>();
        credentialsList.add(credentials);
        mongo = new MongoClient(seeds, credentialsList);
        //MongoClientOptions.Builder options = mongo.getMongoClientOptions().builder();
        MongoClientOptions.Builder options = new MongoClientOptions.Builder();
        options.connectionsPerHost(POOLSIZE);
        options.threadsAllowedToBlockForConnectionMultiplier(BLOCKSIZE);
        options.build();
    }

    public static HashMap getMapParmas() {
        HashMap map =new HashMap();
        Long l = Calendar.getInstance().getTimeInMillis();
        String id = l.toString()+ new Random().nextInt(100);
        map.put("id",id);
        map.put("name","jon"+new Random().nextInt(100));
        map.put("age",new Random().nextInt(20)+3);
        Date date = new Date(l);
        Date d = DateUtils.addDays(date, new Random().nextInt(30));
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String createTime = sdf.format(d);
        map.put("createTime", createTime);
        return map;
    }

    private MongoDatabase getDB() {
        return mongo.getDatabase(DB_NAME);
    }

    public boolean save(String collectionName, Map<String, Object> map, boolean isCreateCollection) {
        MongoDatabase db = getDB();
        MongoCollection dbCollection = db.getCollection(collectionName);

        if (null == dbCollection && isCreateCollection)
        {
            db.createCollection(collectionName);
            dbCollection = db.getCollection(collectionName);
        }

        long num = dbCollection.count();
        Document doc = new Document();

        this.gSaveData(map, doc);

        dbCollection.insertOne(doc);
        if (dbCollection.count() - num > 0) {
            System.out.println("添加数据成功");
            return true;
        }
        return false;
    }
    public void gSaveData(Map<String, Object> map, Document doc)
    {
        if (null != map) {
            doc.putAll(map);
        }
    }

    /**
     * 分页查询
     *
     * @param page
     * @param pageSize
     * @return
     */
    public MongoCursor pageList(String collectionName, String key, List<String> list, String likeKey, String likeVal, String sortKey, int type, int page, int pageSize,Map<String,String> mapDate) {
        MongoCollection table = getDB().getCollection(collectionName);

        BasicDBObject dbObjectSort = new BasicDBObject();
        dbObjectSort.put(sortKey, type);

        List<BasicDBObject> objects = new ArrayList<BasicDBObject>();

        BasicDBObject dbObject = new BasicDBObject();
        BasicDBObject searchQuery = new BasicDBObject(); //条件查询的对象
        BasicDBList condList = new BasicDBList(); //存放查询条件的集合
        BasicDBObject condition= new BasicDBObject(); //最后在将查询结果放到一个查询对象中去
        if (mapDate != null && mapDate.size() > 0) {
            String keyTime = mapDate.get("beginTime");
            searchQuery.put(keyTime, BasicDBObjectBuilder.start("$gte", mapDate.get("eventStartDate")).add("$lte", mapDate.get("eventEndDate")).get());
            condList.add(searchQuery); //将这个查询条件放到条件集合中
            condition.put("$and", condList); //多条件查询使用and
        }
        //这里使用BasicDBObject嵌套来并使用$in预定义名称来实现mongodb的in查询功能
        if (null != list && list.size() > 0) {
            dbObject.put(key, new BasicDBObject("$in", list));
            objects.add(dbObject);
        }

        MongoCursor limit = null;

        if (StringUtils.isNotBlank(likeVal))
        {
            //Pattern p = Pattern.compile(likeVal);
            objects.add(new BasicDBObject(likeKey, likeVal));
        }




        BasicDBObject idObj = new BasicDBObject();
        if (mapDate != null && mapDate.size() > 0) {
            List<BasicDBObject> sidList = new ArrayList<BasicDBObject>();
            sidList.add(condition);
            sidList.add(new BasicDBObject("$or", objects));
            idObj.put("$and", sidList);
        } else {
            idObj.put("$or", objects);
        }
        //mongodb分页查询出游标
        limit = table.find(idObj).skip((page - 1) * pageSize).sort(dbObjectSort).limit(pageSize).iterator();
        return limit;
    }
    public static void main(String[] args) {
        MongoUtils mongoUtils = MongoUtils.getInstance();
//        for(int i=0;i<5;i++){
//            HashMap map = getMapParmas();
//            mongoUtils.save(UDESK_LIST_COLLECTION,map,true);
//        }


        MongoCursor mongoCursor = null;
        MongoCollection table = mongoUtils.getDB().getCollection(UDESK_LIST_COLLECTION);
        Document filter = new Document();
        filter.append("imSubSessionLog.subSessionId", 55423187);
        MongoCursor c1 = table.find(filter).iterator();
//        List<BasicDBObject> conds =new ArrayList<>();
//        BasicDBObject nameCond = new BasicDBObject();
//        Pattern pattern = Pattern.compile("^.*tom.*$", Pattern.CASE_INSENSITIVE);
//        nameCond.put("name", pattern);
//        conds.add(nameCond);
//        BasicDBObject ageCond = new BasicDBObject("age",new BasicDBObject("$gt",10).append("$lt",20));
//        conds.add(ageCond);
//        BasicDBObject timeCond = new BasicDBObject("createTime",new BasicDBObject("$gt","2018-06-13").append("$lt","2018-06-25"));
//        conds.add(timeCond);
//        MongoCursor c1 = table.find().sort(new BasicDBObject("createTime", 1)).iterator();
//        System.out.println("all:");
        while(c1.hasNext()){
            Map map = (Map) c1.next();
            System.out.println(map);
        }
//        System.out.println("------------");
////        mongoCursor = table.find(new BasicDBObject("$and",conds)).iterator();
//
//        Map map1 = new HashMap<>();
//        map1.put("name","jon27");
//        Map map2 = new HashMap<>();
//        map2.put("createTime","null==2018-06-25");
//        map2.put("name","jon23==jon90");
//        mongoCursor = pageList(mongoUtils.mongo,"mt",UDESK_LIST_COLLECTION,"createTime",1,1,100,map1,map2);
//        while(mongoCursor.hasNext()) {
//            Map map = (Map) mongoCursor.next();
//            System.out.println(map);
//        }
    }
    private static String DISTRICT_SEPARATOR = "=="; //区间分隔符
    private static String DISTRICT_NULL = "null"; //区间置空标志
    public static MongoCursor pageList(MongoClient mongoClient, String database, String collectionName, String sortKey, int type, int page, int pageSize,Map<String,String> keyConditions, Map<String, String> districtConditions) {
        MongoCollection table  = mongoClient.getDatabase(database).getCollection(collectionName);
        BasicDBObject dbObjectSort = new BasicDBObject();
        dbObjectSort.put(sortKey, type);
        List<BasicDBObject> conditions = new ArrayList<>();
        if(keyConditions != null && keyConditions.size() > 0){
            for(Map.Entry<String,String> entry:keyConditions.entrySet()){
                BasicDBObject keyCond = new BasicDBObject(entry.getKey(), entry.getValue());
                conditions.add(keyCond);
            }
        }
        if(districtConditions != null && districtConditions.size() > 0){
            for(Map.Entry<String,String> entry:districtConditions.entrySet()){
                BasicDBObject districtCond;
                String key = entry.getKey();
                String value = entry.getValue();
                String []vals = value.split(DISTRICT_SEPARATOR);
                if(vals.length != 2 || (vals[0].equals(DISTRICT_NULL) && vals[1].equals(DISTRICT_NULL))){
                    continue;
                }
                if(vals[0].equals(DISTRICT_NULL)){
                    districtCond  = new BasicDBObject(key,new BasicDBObject("$lt",vals[1]));
                }else if(vals[1].equals(DISTRICT_NULL)){
                    districtCond  = new BasicDBObject(key,new BasicDBObject("$gt",vals[0]));
                }else{
                    districtCond  = new BasicDBObject(key,new BasicDBObject("$gt",vals[0]).append("$lt",vals[1]));
                }
                conditions.add(districtCond);
            }
        }
        BasicDBObject serchCond = new BasicDBObject();
        if(conditions.size() > 0){
            serchCond = new BasicDBObject("$and",conditions);
        }
        return table.find(serchCond).sort(dbObjectSort).skip((page - 1) * pageSize).limit(pageSize).iterator();
    }
}
