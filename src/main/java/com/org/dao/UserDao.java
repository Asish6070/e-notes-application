package com.org.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import com.mysql.cj.protocol.x.ResultMessageListener;
import com.org.dto.User;
import com.org.utilities.Helper;

public class UserDao {
	
	public void saveUser(User user) {
		
		EntityManagerFactory emf = Helper.getEMF();
		EntityManager em = emf.createEntityManager();
		EntityTransaction et = em.getTransaction();
		
		et.begin();
		em.merge(user);
		et.commit();
		
	}
	public User loginUser(String email,String password) {
		
		EntityManagerFactory emf = Helper.getEMF();
		EntityManager em = emf.createEntityManager();
		EntityTransaction et = em.getTransaction();

		String jpql = "select t from User t where t.email=?1 and t.password=?2";
		Query query = em.createQuery(jpql);

		query.setParameter(1, email);
		query.setParameter(2, password);

		List<User> list = query.getResultList();
		if (list.isEmpty()) {
			return null;
		} 
		else {

			User user = list.get(0);
			return user;
		}
		
	}
	
	public void deleteUserById(int id) {
		
	}
	
	public User fetchUserById() {
		return null;
		
	}

}
