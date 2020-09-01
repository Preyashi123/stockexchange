package com.wellsfargo.stockmarket.loginsignup.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.wellsfargo.stockmarket.loginsignup.service.UserService;


@RestController
public class UserController {

	@Autowired
	UserService userService;
	

	@RequestMapping("/login")
	public String login(@RequestParam (required = false) String username, @RequestParam (required = false) String password) {
		
		return userService.findbyID(username, password);

	}

	@RequestMapping("/newUser")
	public String addUser(@RequestParam (required = false) String username, @RequestParam String (required = false) password, @RequestParam (required = false) String email,
			@RequestParam boolean isAdmin) {
		return userService.addUser(username, password, email, isAdmin) ;

	}

	@RequestMapping("/update")
	public String update(@RequestParam (required = false) String username, @RequestParam (required = false) String password, @RequestParam (required = false) String email) {
		return userService.updateUser(username, password, email) ;

	}
}
