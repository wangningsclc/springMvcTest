package com.gc.dao;


import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

public class UserDao {
	private DataSource dataSource;
	private PlatformTransactionManager transactionManager;
	private JdbcTemplate jdbcTemplate;
	private String sql;
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}
	public void setTransactionManager(PlatformTransactionManager transactionManager) {
		this.transactionManager = transactionManager;
	}

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
	
	public String setSql(String sql) {
		return this.sql = sql;
	}
	public Object insert(String msg){
		//自定义 事务提交回滚
		Object result = null;
		DefaultTransactionDefinition defaultTransactionDefinition = new DefaultTransactionDefinition();
		TransactionStatus status = transactionManager.getTransaction(defaultTransactionDefinition);
		try{
			jdbcTemplate.execute(sql);
		}catch(Exception e){
			transactionManager.rollback(status);
		}finally{
			transactionManager.commit(status);
		}
		/*
		//配置文件 提交回滚
		TransactionTemplate transactionTemplate = new TransactionTemplate(transactionManager);
		Object result = transactionTemplate.execute(
				 new TransactionCallback() {
					public Object doInTransaction(TransactionStatus status) {
						Object or = null;
						Connection conn = null;
						Statement stm = null;
						try {
							conn = dataSource.getConnection();
							stm = conn.createStatement();
							or = stm.execute("insert into hoyi_user values (1,'jim','football',sysdate)");
						} catch (SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}finally{
							if(conn !=null){
								try {
									conn.close();
								} catch (SQLException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
							}
							if(stm != null){
								try {
									stm.close();
								} catch (SQLException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
							}
						}
						return or;
					}
				}
				);
				*/
		return result;
	}
	
	public String query(){
		List list = jdbcTemplate.queryForList("select * from hoyi_user");
		Iterator it = list.iterator();
		String name = "";
		while(it.hasNext()){
			Map<String, Object> map = (Map<String, Object>) it.next();
			name += ","+ map.get("name");
		}
		return name==""?"":name.substring(1);
	}
}
