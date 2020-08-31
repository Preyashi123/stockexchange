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
	
	//userRepository.findUser(username, password).equals(username)
	
//	public boolean findinTable(String username, String password) {
//		
//			if(userRepository.findUser(username) != null) {
//				if(userRepository.findUser(username).equals(password))
//					return true;
//				else
//					return false;
//			}
//				
//			else
//				return false;
//	}
	public String findbyID(String username,String password) {
		
		//Optional<User> userEn =  userRepository.findById(username);
		user = userRepository.findByUserName(username);
		if(user != null) {
			//User userEntity = userEn.get();
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
			User user = new  User (username, password, email, isAdmin);
			user.setVerified(true);
			userRepository.save(user);
			return "<h1>USER ADDED</h1>";
		}
		else
			return "<h1>USER ALREADY PRESENT</h1>";
	}

	
	  public String updateUser(String username, String password, String email) {
	 
		// TODO Auto-generated method stub
		user = userRepository.findByUserName(username);	
		
		if(user!= null && user.getUserName().equals(username)) {
			user.setEmail(email);
			user.setPassword(password);
			userRepository.save(user);
			return "<h1> USER UPDATED</h1>";
		}
		else 
			return "<h1> USER NOT PRESENT</h1>";
	}

	/*public String updateUser(User user) {
		 
		// TODO Auto-generated method stub
		String username=user.getUserName();
		Optional<User> userEn = userRepository.findById(username);	
		User u = userEn.get();
		if(u.getUserName().equals(username)) {
			userRepository.save(user);
			return "<h1> USER_UPDATED</h1>";
		}
		else 
			return "<h1> USER NOT UPDATED</h1>";
	
	}*/

	/*public void addUser(boolean isAdmin,String username,String password, String email,boolean verified) {
		User user=new User(isAdmin,username,password,email,verified);
		Optional<User> userEn =  userRepository.findById(username);
		if(userEn == null){
			userRepository.save(user);
		else {
			
		}
		if(userEn != null) {
		    return true;
		}
		else
			return false;
		}
		
	}
	}
	*/
	
	
	// all the functions
	//get
	//update
	//new
	
	
//	public User checkUsername (String username){
//		User xyz =  userRepository.findByUsernameContaining(username);
//		System.out.println(xyz.toString());
//		return xyz;
//	}
//	
//	@PostMapping("/{userId}")
//	public User createUser(@RequestBody User user) {
//		return this.userRepository.save(User);
//	}

}
