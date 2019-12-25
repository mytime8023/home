package cn.tedu.home.service.impl;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import cn.tedu.home.mapper.AdminMapper;
import cn.tedu.home.pojo.Admin;
import cn.tedu.home.service.AdminService;
import cn.tedu.home.util.Code;

@Service
public class AdminServiceImpl implements AdminService{

	@Autowired
	private AdminMapper adminMapper;

	@Override
	public Map<String, Object> login(Admin admin, HttpSession session) {
		Map<String, Object> map = new HashMap<String, Object>();
		
		admin.setPassword(DigestUtils.md5DigestAsHex(admin.getPassword().getBytes()));
		
		Admin a = adminMapper.selectByAdminName(admin.getAdminname());
		
		if(a == null || !a.getPassword().equals(admin.getPassword())) {
			map.put("code", Code.PASS_ERROR);
			map.put("message", "账号或密码有误");
		} else {
			session.setAttribute("login", a);
			map.put("code", Code.SUCCESS);
			map.put("message", "登录成功");
		}
		
		return map;
	}
	
	@Override
	public Admin findAdminByAdminname(String adminname) {
		return adminMapper.selectByAdminName(adminname);
	}
	
}
