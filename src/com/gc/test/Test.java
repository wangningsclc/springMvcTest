package com.gc.test;

import java.util.List;

/**
 * Created by wn on 2018/4/4.
 */
public class Test {
    public   static List<String> removeDuplicate(List<String> list)  {
        for  ( int  i  =   0 ; i  <  list.size()  -   1 ; i ++ )  {
            for  ( int  j  =  list.size()  -   1 ; j  >  i; j -- )  {
                if  (list.get(j).equals(list.get(i)))  {
                    list.remove(j);
                }
            }
            System.out.println(list.get(i)+"    ===================");
        }
        return  list;
    }
    public static void main(String[] args) {
        if(true&&true||true&&false){
            System.out.println(111);
        }
    }
}
