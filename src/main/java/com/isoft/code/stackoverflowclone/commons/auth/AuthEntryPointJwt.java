package com.isoft.code.stackoverflowclone.commons.auth;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class AuthEntryPointJwt implements AuthenticationEntryPoint {

	@Override
	public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
		String actualAuthError = (String) request.getAttribute("actual-error");
		if (actualAuthError != null){
			response.sendError(HttpServletResponse.SC_UNAUTHORIZED,actualAuthError);
		}else{
			response.sendError(HttpServletResponse.SC_UNAUTHORIZED,authException.getMessage());
		}
	}

}
