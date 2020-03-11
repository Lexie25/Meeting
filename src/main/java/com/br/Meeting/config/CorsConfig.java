package com.br.Meeting.config;
import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;


@Component
public class CorsConfig implements Filter{
	
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
	}

	@Override
	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
			throws IOException, ServletException {
		HttpServletResponse response = (HttpServletResponse) servletResponse;
		HttpServletRequest request = (HttpServletRequest) servletRequest;

		response.setHeader("Access-Control-Allow-Origin", "*");
		response.setHeader("Access-Control-Allow-Methods", "POST, GET, PUT, DELETE, OPTIONS, HEAD, TRACE, CONNECT");
		response.setHeader("Access-Control-Max-Age", "3600");
		response.setHeader("Access-Control-Allow-Headers",
				"x-requested-with, X-Auth-Token, Content-Type, Authorization");
		response.setHeader("Access-Control-Expose-Headers",
				"x-requested-with, X-Auth-Token, Content-Type, Authorization");

		if (request.getMethod().equals(HttpMethod.OPTIONS.toString())) {
			response.setStatus(HttpStatus.NO_CONTENT.value());
			return;
		}

		filterChain.doFilter(servletRequest, servletResponse);

	}

	@Override
	public void destroy() {
	}

}
