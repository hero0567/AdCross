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

package com.crossp.web.security;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configurers.GlobalAuthenticationConfigurerAdapter;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfiguration extends WebMvcConfigurerAdapter {


	@Bean
	public ApplicationSecurity applicationSecurity() {
		return new ApplicationSecurity();
	}
	@Bean
	public AuthenticationSecurity authenticationSecurity() {
		return new AuthenticationSecurity();
	}
	
    @Order(1)
    public static class IndexSecurityConfig extends WebSecurityConfigurerAdapter {
        @Override
        public void configure(HttpSecurity http) throws Exception {
            http.antMatcher("/**")
            	.antMatcher("/event/**")
            	.antMatcher("/download/**")
            		.anonymous();
        }
    }

	@Order(2)
	protected static class ApplicationSecurity extends WebSecurityConfigurerAdapter {

		@Autowired
		private SecurityProperties security;
		@Autowired
		private CrossPSuccessHandler crossPSuccessHandler;
		
		@Override
		protected void configure(HttpSecurity http) throws Exception {
			http.authorizeRequests()
					.antMatchers("/api/**").hasRole("REST")	
					.antMatchers("/user/**").hasRole("USER")
					.antMatchers("/setting/**").hasRole("USER")
					.antMatchers("/admin/**").hasRole("ADMIN")
					.and()
						.formLogin()
							.loginPage("/login")								
								.defaultSuccessUrl("/")
								.successHandler(crossPSuccessHandler)
								.failureUrl("/login?error").permitAll()
					.and()
						.logout()
							.deleteCookies("remove")
							.invalidateHttpSession(false)
							.logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
					.and().exceptionHandling()
                    		.accessDeniedPage("/forbidden")
                    .and()
                    	.csrf().disable();
		}
	}

	@Order(3)
	protected static class AuthenticationSecurity extends
			GlobalAuthenticationConfigurerAdapter {

		@Autowired
		private DataSource dataSource;
		
		private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

		@Override
		public void init(AuthenticationManagerBuilder auth) throws Exception {
//			auth.jdbcAuthentication().dataSource(this.dataSource)
//			.withUser("admin").password("admin").roles("ADMIN", "USER", "REST").and()
//			.withUser("api").password("api").roles("REST").and()
//			.withUser("user").password("user").roles("USER");
			
			auth.jdbcAuthentication().passwordEncoder(encoder).dataSource(this.dataSource);
		}
	}

}
