/*
 * Copyright 2014 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.crossp.web.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.crossp.jpa.domain.Authority;
import com.crossp.jpa.domain.User;
import com.crossp.jpa.service.AuthorityRepository;
import com.crossp.jpa.service.UserRepository;

@Controller
public class HomeController {

	private Logger logger = LoggerFactory.getLogger(getClass()); 
	
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private AuthorityRepository authorityRepository;
	
	@RequestMapping("/")
	public String home() throws Exception {
		return "home";
	}
		
	@RequestMapping("/hello")
	public String hello() throws Exception {
		return "hello";
	}
	
	@RequestMapping(value="/login")
	public String login() {
		return "login";
	}
	
	@RequestMapping(value="/register")
	public String register() {
		return "register";
	}
	
	@RequestMapping(value="/upload")
	public String upload() {
		return "upload";
	}
	
	/**
	 * http://localhost:8080/register?username=11112121&password=1212121
	 * @param user
	 * @return
	 */
	@RequestMapping(value="/register", method=RequestMethod.POST)
	public String registerUser(User user) {
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		user.setPassword(encoder.encode(user.getPassword()));
		Authority auth = new Authority();
		auth.setUsername(user.getUsername());
		auth.setAuthority("ROLE_USER");
		user.setEnabled(true);
		logger.info("Username :{}, Password:{}",user.getUsername(),user.getPassword());
		userRepository.save(user);
		authorityRepository.save(auth);
		return "login";
	}
		
	@RequestMapping(value="/logout")
	public String logout() {
		return "logout";
	}
	
	@RequestMapping(value="/about")
	public String about() {
		return "about";
	}
	
	@RequestMapping(value="/forbidden")
	public String forbidden() {
		return "403";
	}
	
//	
//	@ExceptionHandler(Exception.class)  
//    public String runtimeExceptionHandler(Exception runtimeException) {  
//       return "exception";
//    }  
}
