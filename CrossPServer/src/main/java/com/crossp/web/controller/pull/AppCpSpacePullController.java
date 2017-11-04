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

package com.crossp.web.controller.pull;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.crossp.beans.DeliveredItem;
import com.crossp.beans.DeliveredSpace;
import com.crossp.jdbc.service.AppJDBCService;
import com.crossp.jpa.domain.App;
import com.crossp.jpa.domain.AppItem;
import com.crossp.jpa.domain.AppItemArea;
import com.crossp.jpa.domain.AppSpace;
import com.crossp.jpa.domain.AppTemplate;
import com.crossp.jpa.domain.AppTemplateConf;
import com.crossp.jpa.service.AppItemAreaRepository;
import com.crossp.jpa.service.AppItemRepository;
import com.crossp.jpa.service.AppMessageRepository;
import com.crossp.jpa.service.AppRepository;

@Controller
@RequestMapping(value = "/pull")
@SessionAttributes("user")
public class AppCpSpacePullController {

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

	
	@RequestMapping(value = "/space/{token}")
	public @ResponseBody DeliveredSpace getSpace(@PathVariable("token") String token) {
		logger.info("Pull template with token : {}", token);
		if (StringUtils.isEmpty(token)){
			return null;
		}
		App app = appRepository.findByToken(token);		
		DeliveredSpace deliver = getDeliveredSpace(app);
		deliver.setAppId(app.getAppId());
		return deliver;
	}
	
	private DeliveredSpace getDeliveredSpace(App app){
		DeliveredSpace deliver = new DeliveredSpace();
		List<DeliveredItem> dItems = new ArrayList<DeliveredItem>();
		List<AppSpace> spaces =  app.getAppSpaces();
		if (spaces == null){
			return deliver;
		}
		for (AppSpace space : spaces){
			AppTemplate template = space.getAppTemplate();
			if (template != null){
				AppTemplateConf conf = template.getAppTconf();
				if (conf == null){
					continue;
				}
				deliver.setPosition(space.getPosition());
				deliver.setShowType(space.getShowType());
				deliver.setTransparency(space.getTransparency());
				deliver.setDownloadLink(conf.getZipPath());
				List<AppItemArea>  areas = template.getItemAreas();
				for (AppItemArea area : areas){
					AppItem item = area.getAppItem();
					if (item != null){
						DeliveredItem ditem = new DeliveredItem();
						ditem.setDescription(item.getDescription());
						ditem.setIcon(item.getIcon());
						ditem.setName(item.getName());
						ditem.setType(item.getType());
						ditem.setAppId(item.getAppId());
						dItems.add(ditem);
					}
				}
				if (dItems.size() > 0){
					deliver.setItems(dItems);
					return deliver;
				}
			}
		}
		return deliver;
	}

}
