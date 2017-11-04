package com.crossp.web.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import com.crossp.jpa.domain.User;
import com.crossp.jpa.service.UserRepository;

@Component
public class CrossPSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler{

	@Autowired
	private UserRepository userRepository;
	
	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {
		HttpSession session = request.getSession(false);
		User user = userRepository.findByUsername(authentication.getName());
		session.setAttribute("user", user);
		super.onAuthenticationSuccess(request, response, authentication);
	
	}

}
