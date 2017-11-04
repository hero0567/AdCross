package com.crossp.configure;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

@Configuration
public class WebMvcConfigure {
	
	@Bean
	public LocaleResolver localeResolver() {
		return new SessionLocaleResolver();
	}
}
