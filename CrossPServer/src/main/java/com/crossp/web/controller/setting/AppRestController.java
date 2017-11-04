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

package com.crossp.web.controller.setting;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.crossp.jdbc.service.AppJDBCService;
import com.crossp.jpa.domain.App;
import com.crossp.jpa.domain.User;
import com.crossp.jpa.service.AppRepository;
import com.crossp.jpa.service.UserRepository;

@Controller
@RequestMapping(value="/setting/app")
@SessionAttributes("user")
public class AppRestController {
	
	private Logger logger = LoggerFactory.getLogger(getClass()); 
	@Autowired
	private AppRepository appRepository;
	@Autowired
	private AppJDBCService applicationJDBC;
	@Autowired
	private UserRepository userRepository;
	
	@RequestMapping(value="/all")
	public @ResponseBody Iterable<App> findAll() {
		return appRepository.findAll();
	}
	
	@RequestMapping(value="/{id}")
	public @ResponseBody Iterable<App> findALLAppsById(@PathVariable("id") Long id) {
		return appRepository.findByUserId(id);
	}
	
	@RequestMapping(value="/user")
	public @ResponseBody Iterable<App> findUserALLApps(@ModelAttribute("user") User user) {
		return appRepository.findByUser(user);
	}
	
	@RequestMapping(value="/add", method=RequestMethod.POST)
	public @ResponseBody void add(@ModelAttribute("user") User user, @RequestBody App app) {
		app.setUser(user);
		appRepository.save(app);
	}
	
	@RequestMapping(value="/update", method=RequestMethod.PUT)
	public @ResponseBody void update(@RequestBody App app) {
		appRepository.save(app);
	}
	
	@RequestMapping(value="/cp/{id}")
	public @ResponseBody Iterable<App> findALLCPApps(@PathVariable("id") int id) {
		return appRepository.findByApplicationId(id);
	}
	
	@RequestMapping(value="/cp/unjoin/{id}")
	public @ResponseBody Iterable<App> findUnJoinCPApps(@PathVariable("id") int id) {
		return appRepository.findUnJoinByApplicationId(id);
	}
	@RequestMapping(value="/cp/{wid}/{rid}", method=RequestMethod.POST)
	public @ResponseBody void joinCP(@PathVariable("wid") int wid, @PathVariable("rid") int rid) {
		applicationJDBC.joinCP(wid, rid);
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.DELETE)
	public @ResponseBody void removeApp(@PathVariable("id") Long id) {
		appRepository.delete(id);
	}
	
	@RequestMapping(value="/cp/{wid}/{rid}", method=RequestMethod.DELETE)
	public @ResponseBody void breakCP(@PathVariable("wid") int wid, @PathVariable("rid") int rid) {
		applicationJDBC.breakCP(wid, rid);
	}
		
}
