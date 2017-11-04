package com.crossp.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.crossp.jpa.domain.User;
import com.crossp.jpa.service.UserRepository;

@Controller
@RequestMapping("/admin")
public class UserManageController {
	
	@Autowired
	private UserRepository userRepository;

	@RequestMapping(value="/user")
	public @ResponseBody Iterable<User> getAllUsers() {
		return userRepository.findAll();
	}
	
	@RequestMapping(value="/user/{id}")
	public @ResponseBody User getUser(@PathVariable("id") long id) {
		return userRepository.findOne(id);
	}
	
	
	@RequestMapping(value="/user/add")
	public @ResponseBody void addUser(@RequestBody User user) {
		userRepository.save(user);
	}
	
	@RequestMapping(value="/user/delete")
	public @ResponseBody void deleteUser(@RequestBody User user) {
		userRepository.delete(user);;
	}
	
	@RequestMapping(value="/user/delete/{id}")
	public @ResponseBody void deleteUserId(@PathVariable("id") long id) {
		userRepository.delete(id);;
	}	
}
