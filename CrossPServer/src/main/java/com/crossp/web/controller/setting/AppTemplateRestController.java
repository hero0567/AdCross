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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.crossp.jpa.domain.AppTemplate;
import com.crossp.jpa.service.AppTemplateRepository;

@Controller
@RequestMapping(value="/setting/app/template")
public class AppTemplateRestController {
	
	
	private Logger logger = LoggerFactory.getLogger(getClass()); 
	@Autowired
	private AppTemplateRepository appTemplateRepository;	
	
	@RequestMapping(value="/all")
	public @ResponseBody Iterable<AppTemplate> findAppTemplates() {
		return appTemplateRepository.findAll();
	}
	
	@RequestMapping(value="/add", method=RequestMethod.POST)
	public @ResponseBody void add(@RequestBody AppTemplate appTemplate) {
		appTemplateRepository.save(appTemplate);
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.DELETE)
	public @ResponseBody void removeAppSpace(@PathVariable("id") Long id) {
		appTemplateRepository.delete(id);
	}
		
}
