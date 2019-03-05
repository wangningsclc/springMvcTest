package com.gc.spmvc.action;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;


public class HelloWorldAction implements Controller{

	private static Logger log = Logger.getLogger(HelloWorldAction.class);
	private String name;
	private String viewPage;
	@Override
	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		log.info("加载第一个action。。。");
		Map model = new HashMap();
		model.put("skill", request.getParameter("skill"));
		model.put("name", getName());
		return new ModelAndView(getViewPage(), model);
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	public String getViewPage() {
		return viewPage;
	}
	public void setViewPage(String viewPage) {
		this.viewPage = viewPage;
	}

}
