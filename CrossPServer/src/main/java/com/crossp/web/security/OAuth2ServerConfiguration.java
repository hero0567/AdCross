//package com.crossp.web.security;
//
//import javax.sql.DataSource;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.boot.autoconfigure.security.SecurityProperties;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.core.annotation.Order;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.config.http.SessionCreationPolicy;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
//import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
//import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
//import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
//import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
//import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
//import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
//import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
//import org.springframework.security.oauth2.provider.token.TokenStore;
//import org.springframework.security.oauth2.provider.token.store.InMemoryTokenStore;
//import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
//
//@Configuration
//public class OAuth2ServerConfiguration {
//
//    private static final String RESOURCE_ID = "restservice";
//
//    @Configuration
//    @EnableWebSecurity
//    static class WebSecurityConfig extends WebSecurityConfigurerAdapter {
//
//		@Autowired
//		private DataSource dataSource;
//		
//		private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
//    	
//        @Override
//        protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        	auth.jdbcAuthentication().passwordEncoder(encoder).dataSource(this.dataSource); 
//        }
//
//        @Override
//        @Bean
//        public AuthenticationManager authenticationManagerBean() throws Exception {
//            return super.authenticationManagerBean();
//        }
//
//    }
//    
//    @Configuration
//    @EnableResourceServer
//    static class ResourceServerConfig extends ResourceServerConfigurerAdapter {
//
//    	@Autowired
//		private CrossPSuccessHandler crossPSuccessHandler;
//    	
//        @Override
//        public void configure(ResourceServerSecurityConfigurer resources) {
//            resources
//                    .resourceId(RESOURCE_ID);
//        }
//
//        @Override
//        public void configure(HttpSecurity http) throws Exception {
//            http.sessionManagement()
//                    .sessionCreationPolicy(SessionCreationPolicy.STATELESS);
//            http.antMatcher("/**").anonymous()
//            	.and()
//                    .authorizeRequests()
//	                    .antMatchers("/api/**").hasRole("REST")	
//				.and()
//					.formLogin()
//							.loginPage("/login")								
//								.defaultSuccessUrl("/")
//								.successHandler(crossPSuccessHandler)
//								.failureUrl("/login?error").permitAll()
//					.and()
//						.logout()
//							.deleteCookies("remove")
//							.invalidateHttpSession(false)
//							.logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
//					.and().exceptionHandling()
//                    		.accessDeniedPage("/forbidden")
//                    .and()
//                    	.csrf().disable();
//        }
//
//    }
//
//    @Configuration
//    @EnableAuthorizationServer
//    static class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {
//
//        private TokenStore tokenStore = new InMemoryTokenStore();
//
//        @Autowired
//        @Qualifier("authenticationManagerBean")
//        private AuthenticationManager authenticationManager;
//
//        @Override
//        public void configure(AuthorizationServerEndpointsConfigurer endpoints)
//                throws Exception {
//            endpoints
//                    .tokenStore(tokenStore)
//                    .authenticationManager(authenticationManager);
//        }
//
//
//        @Override
//        public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
//            clients
//                    .inMemory()
//                    .withClient("clientapp")
//                    .authorizedGrantTypes("password", "refresh_token")
//                    .authorities("USER")
//                    .scopes("read", "write")
//                    .resourceIds(RESOURCE_ID)
//                    .secret("123456");
//        }
//
//        @Bean
//        public DefaultTokenServices tokenServices() {
//            DefaultTokenServices tokenServices = new DefaultTokenServices();
//            tokenServices.setSupportRefreshToken(true);
//            tokenServices.setTokenStore(this.tokenStore);
//            return tokenServices;
//        }
//    }
//}