package cn.tedu.home.service;

import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Service;

import cn.tedu.home.pojo.Admin;

public interface AdminService {

	Map<String, Object> login(Admin admin, HttpSession session);
	
	Admin findAdminByAdminname(String adminname);
	
}
