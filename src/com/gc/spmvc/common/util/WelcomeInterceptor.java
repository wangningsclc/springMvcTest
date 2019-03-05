package com.gc.spmvc.common.util;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Calendar;

public class WelcomeInterceptor implements HandlerInterceptor {
	private int hour;

	@Override
	public void afterCompletion(HttpServletRequest httpservletrequest,
			HttpServletResponse httpservletresponse, Object obj,
			Exception exception) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public void postHandle(HttpServletRequest httpservletrequest,
			HttpServletResponse httpservletresponse, Object obj,
			ModelAndView modelandview) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object obj)
			throws Exception {
		Calendar calendar = Calendar.getInstance();
		int hour = calendar.get(Calendar.HOUR_OF_DAY);
		if(hour >= this.hour){
			response.sendRedirect("http://localhost:8080/springMvcTest/error.jsp");
			return false;
		}else{
			return true;
		}
	}

	public int getHour() {
		return hour;
	}

	public void setHour(int hour) {
		this.hour = hour;
	}
}
