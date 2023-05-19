package com.nfeeds.data.auth.filters;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.log4j.Log4j2;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.AbstractRequestLoggingFilter;


@Log4j2
@Component
@Order(1)
public class RequestLoggerFilter extends AbstractRequestLoggingFilter {
	@Override
	protected void beforeRequest(HttpServletRequest request, String message) {
		log.info(request.getMethod() +" - "+request.getRequestURI());
	}
	
	@Override
	protected void afterRequest(HttpServletRequest request, String message) {
		log.debug("Done " + request.getMethod() +" - "+request.getRequestURI());
	}
}