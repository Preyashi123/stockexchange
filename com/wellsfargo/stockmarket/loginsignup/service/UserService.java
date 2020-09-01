package com.wellsfargo.stockmarket.loginsignup.service;


import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wellsfargo.stockmarket.loginsignup.entity.User;
import com.wellsfargo.stockmarket.loginsignup.repository.UserRepository;


@Service
public class UserService {
	@Autowired
	UserRepository userRepository;
	User user;
	

	public String findbyID(String username,String password) {
		
		
		user = userRepository.findByUserName(username);
		if(user != null) {
			
			if(user.getPassword().equals(password))
				return "<h1>SUCCESS</h1>";
			else
				return "<h1>WRONG PASSWORD</h1>";
		}
		else
			return "<h1>USER NOT PRESENT</h1>" ;
		 
	}

	public String addUser(String username, String password, String email, boolean isAdmin) {
		// TODO Auto-generated method stub
		user =  userRepository.findByUserName(username);
			
		if(user == null){
			user = userRepository.findByEmail(String email);
			if(user == null){
				User user = new  User (username, password, email, isAdmin);
				user.setVerified(true);
				userRepository.save(user);
				return "<h1>USER ADDED</h1>";
			}
			else
				return "<h1>EMAIL ALREADY PRESENT</h1>";
		}
		else
			return "<h1>USER ALREADY PRESENT</h1>";
	}

	
	  public String updateUser(String username, String password, String email) {
	 
		// TODO Auto-generated method stub
		user = userRepository.findByUserName(username);	
		
		if(user!= null) {
			user.setEmail(email);
			user.setPassword(password);
			userRepository.save(user);
			return "<h1> USER UPDATED</h1>";
		}
		else 
			return "<h1> USER NOT PRESENT</h1>";
	}

}
