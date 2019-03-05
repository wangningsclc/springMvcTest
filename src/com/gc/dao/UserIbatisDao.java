package com.gc.dao;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import com.gc.dataset.User;

public class UserIbatisDao extends SqlMapClientDaoSupport{
	private PlatformTransactionManager transactionManager;
	public void setTransactionManager(PlatformTransactionManager transactionManager) {
		this.transactionManager = transactionManager;
	}
	
	public boolean insert(User user){
		boolean res = false;
		DefaultTransactionDefinition transactionDefinition = new DefaultTransactionDefinition();
		TransactionStatus status = transactionManager.getTransaction(transactionDefinition);
		try{
			getSqlMapClientTemplate().insert("insertUser", user);
			transactionManager.commit(status);
			res = true;
		}catch(Exception e){
			e.printStackTrace();
			transactionManager.rollback(status);
		}
		return res;
	}
}
