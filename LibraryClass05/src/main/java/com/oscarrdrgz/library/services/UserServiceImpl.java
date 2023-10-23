package com.oscarrdrgz.library.services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.oscarrdrgz.library.models.entities.User;

@Service
public class UserServiceImpl implements UserService{
	
	private static Map<String, User> users= new HashMap<>();
	static {
		User user1= new User("jbeckingham0","Janek","Beckingham");
		users.put(user1.getUsername(), user1);
		User user2= new User("jcorbally1","Jill","Corbally");
		users.put(user2.getUsername(), user2);
		User user3= new User("vworham2","Vassili","Worham");
		users.put(user3.getUsername(), user3);
		User user4= new User("gdominighi3","Germaine","Dominighi");
		users.put(user4.getUsername(), user4);
		User user5= new User("bdauby4","Byrann","Dauby");
		users.put(user5.getUsername(), user5);
		
		
	}
	@Override
	public void insert(User user) {
		//primero deberia de verificarxe si existe o no
		users.put(user.getUsername(), user);
		
	}

	@Override
	public void delete(String username) {
		users.remove(username);
		
	}

	@Override
	public User getOneById(String username) {
		return users.get(username);
	}

	@Override
	public List<User> getAll() {
		
		return new ArrayList<>(users.values());
	}

}
