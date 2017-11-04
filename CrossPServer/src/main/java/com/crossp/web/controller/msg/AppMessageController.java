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

package com.crossp.web.controller.msg;

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
import com.crossp.jpa.domain.AppItem;
import com.crossp.jpa.domain.AppItemArea;
import com.crossp.jpa.domain.AppMessage;
import com.crossp.jpa.domain.User;
import com.crossp.jpa.service.AppItemAreaRepository;
import com.crossp.jpa.service.AppItemRepository;
import com.crossp.jpa.service.AppMessageRepository;
import com.crossp.jpa.service.AppRepository;

@Controller
@RequestMapping(value = "/msg")
@SessionAttributes("user")
public class AppMessageController {

	private Logger logger = LoggerFactory.getLogger(getClass());  
	@Autowired
	private AppMessageRepository appMessageRepository;
	@Autowired
	private AppRepository appRepository;
	@Autowired
	private AppItemAreaRepository appItemAreaRepository;
	@Autowired
	private AppItemRepository appItemRepository;
	@Autowired
	private AppJDBCService appJDBCService;

	@RequestMapping(value = "/all")
	public @ResponseBody Iterable<AppMessage> findAll() {
		return appMessageRepository.findAll();
	}

	@RequestMapping(value = "/req/{appId}/{areaId}/{itemId}", method = RequestMethod.POST)
	public @ResponseBody void request(@ModelAttribute("user") User user, @PathVariable("appId") Long appId,
			@PathVariable("areaId") Long areaId, @PathVariable("itemId") Long itemId, @RequestBody AppMessage appMessage) {		
		logger.info("App cross request with appId: {}, areaId: {}, itemId {}.", appId, areaId, itemId);
		App app = appRepository.findOne(appId);
		AppItemArea appArea = appItemAreaRepository.findOne(areaId);
		AppItem appItem = appItemRepository.findOne(itemId);
		
		appMessage.setPid(user.getId());
		appMessage.setpName(user.getUsername());;
		appMessage.setCid(app.getUser().getId());
		appMessage.setcName(app.getUser().getUsername());
		appMessage.setAppId(app.getId());
		appMessage.setAppName(app.getName());
		appMessage.setAppAreaId(appArea.getId());
		appMessage.setAppItemId(itemId);
		appMessage.setAppItemName(appItem.getName());
				
		appMessageRepository.save(appMessage);
	}

	@RequestMapping(value = "/read/all", method = RequestMethod.POST)
	public @ResponseBody void readAll(@ModelAttribute("user") User user) {
		appJDBCService.readMsgALL(user.getId());
	}
	
	@RequestMapping(value = "/my/{mid}")
	public @ResponseBody AppMessage findOneMsg(@ModelAttribute("user") User user, @PathVariable("mid") Long mid) {
		return appMessageRepository.findByIdAndCid(mid, user.getId());
	}
	
	@RequestMapping(value = "/my/new")
	public @ResponseBody Iterable<AppMessage> unreadAll(@ModelAttribute("user") User user) {
		return appMessageRepository.findByCidAndStatus(user.getId(), 0);
	}
	
	@RequestMapping(value = "/read/{mid}", method = RequestMethod.POST)
	public @ResponseBody void readOne(@ModelAttribute("user") User user, @PathVariable("mid") Long mid) {
		appJDBCService.readMsgOne(user.getId(), mid);
		
	}

	@RequestMapping(value = "/my/send")
	public @ResponseBody Iterable<AppMessage> findMySendMsg(@ModelAttribute("user") User user) {
		return appMessageRepository.findByPid(user.getId());
	}

	@RequestMapping(value = "/my/rec")
	public @ResponseBody Iterable<AppMessage> findMyRecMsg(@ModelAttribute("user") User user) {
		return appMessageRepository.findByCid(user.getId());
	}
	
	@RequestMapping(value = "/delete/{mid}", method = RequestMethod.DELETE)
	public @ResponseBody void removeMsg(@ModelAttribute("user") User user, @PathVariable("mid") Long mid) {
		AppMessage appMessage = appMessageRepository.findByIdAndCid(mid, user.getId());
		if (appMessage != null){
			appMessageRepository.delete(appMessage);
		}
		
	}
	
	@RequestMapping(value = "/delete/all", method = RequestMethod.DELETE)
	public @ResponseBody void removeAllMyMsg(@ModelAttribute("user") User user) {
		appJDBCService.deteleMsg(user.getId());
	}

}
