package com.study.rest.webservices.restfulwebservices.user;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Component;

@Component
public class UserDaoService {

	private static List<User> users = new ArrayList<>();
	private Integer usercount = 3;
	
	
	static {
		users.add(new User(1, "Satish", new Date()));
		users.add(new User(2, "Tanav", new Date()));
		users.add(new User(3, "Nethra", new Date()));
	}
	
	public List<User> findAll()
	{
		return users; 
	}
	
	public User findOne(int id) {
		for (User user : users)
		{
			if (user.getId() == id)
				return user;
		}
		return null;
	}
	
	public User save(String name, Date birthdate)
	{
		User user = new User(++usercount, name, birthdate);
		users.add(user);
		return user;
		
	}
	
	public User save(User user)
	{
		if (user.getId() == null)
			user.setId(++usercount);
		users.add(user);
		return user;
		
	}
	
			
}
