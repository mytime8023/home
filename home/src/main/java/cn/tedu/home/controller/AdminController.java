package cn.tedu.home.controller;

import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import cn.tedu.home.pojo.Admin;
import cn.tedu.home.service.AdminService;

@Controller
@RequestMapping("/admin")
public class AdminController {

	@Autowired
	private AdminService adminService;
	
	@ResponseBody
	@RequestMapping(value = "login", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	public Map<String, Object> login(@RequestBody Admin admin, HttpSession session) {
		
		Map<String, Object> result = adminService.login(admin, session);
		
		return result;
	}
	
}
