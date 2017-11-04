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
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value="/setting")
public class SettingController {

	private Logger logger = LoggerFactory.getLogger(getClass()); 
	@RequestMapping(value="/home")
	public String home() {
		return "setting/home";
	}
	
	@RequestMapping(value="/app/my")
	public String my() {
		return "setting/application";
	}
	
	@RequestMapping(value="/app/myCp")
	public String myCp() {
		return "setting/application_cp";
	}
	
	@RequestMapping(value="/app/myCpSpace")
	public String myCpSpace() {
		return "setting/application_space";
	}
	
	@RequestMapping(value="/app/myCpItem")
	public String myCpItem() {
		return "setting/application_item";
	}
	
	@RequestMapping(value="/app/crossp")
	public String cp() {
		return "setting/crossp";
	}
	
	@RequestMapping(value="/app/myAppBind")
	public String appBind() {
		return "setting/application_bind";
	}
	
	@RequestMapping(value="/app/mySpaceBind")
	public String appSpaceBind() {
		return "setting/application_space_bind";
	}
	
}
