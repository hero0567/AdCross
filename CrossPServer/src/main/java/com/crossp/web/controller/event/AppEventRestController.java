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

package com.crossp.web.controller.event;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.crossp.jpa.domain.App;
import com.crossp.jpa.domain.AppEventData;
import com.crossp.jpa.domain.AppItemArea;
import com.crossp.jpa.service.AppEventRepository;
import com.crossp.jpa.service.AppItemAreaRepository;
import com.crossp.jpa.service.AppItemRepository;
import com.crossp.jpa.service.AppMessageRepository;
import com.crossp.jpa.service.AppRepository;

@Controller
@RequestMapping(value = "/event")
public class AppEventRestController {

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
	private AppEventRepository appEventRepository;

	@RequestMapping(value = "/{type}/{appId}/{areaId}/{itemId}", method = RequestMethod.POST)
	public @ResponseBody void removeAppSpace(@PathVariable("appId") Long appId, @PathVariable("areaId") Long areaId,
			@PathVariable("itemId") Long itemId, @PathVariable("type") int type, @RequestBody AppEventData eventData) {
		App app = appRepository.findOne(appId);
		AppItemArea appArea = appItemAreaRepository.findOne(areaId);
		
		eventData.setEventType(type);
		eventData.setAppOwnerUserId(app.getUser().getId());
		eventData.setAppOwnerAppId(appId);
		eventData.setAppOwnerTemplateId(appArea.getAppTemplate().getId());
		eventData.setAppOwnerSpaceId(appArea.getAppTemplate().getAppSpace().getId());
		eventData.setAppOwnerAreaId(areaId);
		eventData.setAppOwnerItemId(itemId);		
		appEventRepository.save(eventData);
	}

}
