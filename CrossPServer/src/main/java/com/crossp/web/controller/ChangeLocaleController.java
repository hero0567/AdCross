package com.crossp.web.controller;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.LocaleResolver;

@Controller
@RequestMapping("/locale")
public class ChangeLocaleController {
	
	@Autowired
	private LocaleResolver localeResolver;

	@RequestMapping(value = "/{locale}", method=RequestMethod.POST)
	public void home(HttpServletRequest request, HttpServletResponse response, @PathVariable("locale") String locale) throws Exception {
		Locale l = null;
		if (locale.equals("zh_CN")){
			l =Locale.CHINA;
		}else{
			l = Locale.ENGLISH;
		}			
		localeResolver.setLocale(request, response, l);
	}
}
