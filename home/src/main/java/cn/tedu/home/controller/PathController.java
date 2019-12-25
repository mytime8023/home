package cn.tedu.home.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.tedu.home.interceptor.PathInterceptorAnnotation;

@Controller
public class PathController {

	@RequestMapping(value = {"/login","/login.html","/login.jsp"})
	public String login() {
		return "login";
	}
	
	@PathInterceptorAnnotation
	@RequestMapping("/index")
	public String index() {
		return "index";
	}
}
