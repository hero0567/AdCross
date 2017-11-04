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

import java.util.ArrayList;
import java.util.List;

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

import com.crossp.jpa.domain.AppItemArea;
import com.crossp.jpa.domain.AppSpace;
import com.crossp.jpa.domain.AppTemplateConf;
import com.crossp.jpa.domain.User;
import com.crossp.jpa.service.AppSpaceRepository;
import com.crossp.jpa.service.UserRepository;

@Controller
@RequestMapping(value = "/setting/app/space")
@SessionAttributes("user")
public class AppSpaceRestController {

	private Logger logger = LoggerFactory.getLogger(getClass()); 
	@Autowired
	private AppSpaceRepository appSpaceRepository;
	@Autowired
	private UserRepository userRepository;

	@RequestMapping(value = "/all")
	public @ResponseBody Iterable<AppSpace> findUserAppSpaces(@ModelAttribute("user") User user) {
		return appSpaceRepository.findByUser(user);
	}

	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public @ResponseBody void add(@RequestBody AppSpace appSpace, @ModelAttribute("user") User user) {
		appSpace.setUser(user);
		createAppItemArea(appSpace);
		appSpaceRepository.save(appSpace);
	}

	@RequestMapping(value = "/update", method = RequestMethod.PUT)
	public @ResponseBody void edit(@RequestBody AppSpace appSpace) {
		appSpaceRepository.save(appSpace);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public @ResponseBody void removeAppSpace(@PathVariable("id") Long id) {
		appSpaceRepository.delete(id);
	}

	private void createAppItemArea(AppSpace appSpace) {
		AppTemplateConf templateConf = appSpace.getAppTemplate() == null ? null
				: appSpace.getAppTemplate().getAppTconf();
		if (templateConf == null){
			return;
		}
		List<AppItemArea> saveAreas = new ArrayList<AppItemArea>();
		List<AppItemArea> postAreas = appSpace.getAppTemplate().getItemAreas();
		int templateSize = templateConf.getSize();
		int postSize = postAreas == null ? 0 : postAreas.size();
				
		for (int i = 0; i < templateSize; i++) {
			AppItemArea area = null;
			if (postSize > i){
				area = postAreas.get(i);
			}else {
				area = new AppItemArea(i);
			}
			saveAreas.add(area);
		}
		appSpace.getAppTemplate().setItemAreas(saveAreas);
	}
}
