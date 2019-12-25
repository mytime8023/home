package cn.tedu.home.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpSession;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import cn.tedu.home.interceptor.PathInterceptorAnnotation;
import cn.tedu.home.pojo.User;
import cn.tedu.home.service.UserService;
import cn.tedu.home.util.Code;
import cn.tedu.home.util.Page;

@Controller
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserService userService;
	
	@PathInterceptorAnnotation
	@RequestMapping(value = "static")
	public String getUserList(Model model, @RequestParam(defaultValue = "1", value = "pageNum") Integer pageNum, String start, String end, String username) {
		model.addAttribute("start", start);
		model.addAttribute("end", end);
		model.addAttribute("username", username);
		
		PageHelper.startPage(pageNum, Page.SIZE);
		List<User> list = userService.getAssociation(start, end, username);
		PageInfo<User> data = new PageInfo<User>(list);
		model.addAttribute("users", data);
		return "user/user-list";
	}
	
	//@ResponseBody
	//@RequestMapping(value = "dynamic")
	public Map<String, Object> getUserMap() {
		List<User> list = userService.getAssociation();
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("code",0);
		map.put("msg",null);
		map.put("count", list.size());
		map.put("data", list);
		return map;
	}
	
	@ResponseBody
	@PathInterceptorAnnotation
	@RequestMapping(value = "edit/status/{id}")
	public Map<String, Object> editStatus(@PathVariable("id") Integer id) {
		
		Map<String, Object> result = userService.editStatus(id);
		
		return result;
	}
	
	@PathInterceptorAnnotation
	@RequestMapping(value = "info/{id}/{pageNum}")
	public String editGetInfo(@PathVariable("id") Integer id, @PathVariable("pageNum") Integer pageNum, Model model) {
		
		User user = userService.getUserById(id);
		model.addAttribute("user", user);
		model.addAttribute("pageNum", pageNum);
		return "user/user-edit";
	}
	
	@ResponseBody
	@PathInterceptorAnnotation
	@RequestMapping(value = "edit/info/{pageNum}", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	public Map<String, Object> editInfo(@RequestBody User user, @PathVariable("pageNum") Integer pageNum) {
		Map<String, Object> map = userService.editInfo(user,pageNum);
		return map;
	}
	
	@PathInterceptorAnnotation
	@RequestMapping(value = "pass/{id}")
	public String toPassEdit(@PathVariable("id") Integer id, Model model) {
		
		User user = userService.getUserById(id);
		model.addAttribute("user", user);
		return "user/user-password";
	}
	
	@ResponseBody
	@PathInterceptorAnnotation
	@RequestMapping(value = "edit/pass", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	public Map<String, Object> editPass(@RequestBody JSONObject param) {
		User user = new User();
		user.setId(param.getInteger("id"));
		user.setPassword(param.getString("password"));
		String newpass = param.getString("newpass");
		Map<String, Object> map = userService.editPass(user, newpass);
		return map;
	}
	
	@ResponseBody
	@PathInterceptorAnnotation
	@RequestMapping(value = "delete/{id}/{pageNum}", method = RequestMethod.POST)
	public Map<String, Object> delete(@PathVariable("id") Integer id,@PathVariable("pageNum") Integer pageNum) {
		
		Map<String, Object> result = userService.deleteById(id, pageNum);
		return result;
	}
	
	@PathInterceptorAnnotation
	@RequestMapping(value = "open/add")
	public String toAdd(Model model) {
		return "user/user-add";
	}
	
	@ResponseBody
	@PathInterceptorAnnotation
	@RequestMapping(value = "add", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	public Map<String, Object> add(@RequestBody User u) {
		Map<String, Object> map = userService.add(u);
		return map;
	}
	
	@ResponseBody
	@PathInterceptorAnnotation
	@RequestMapping(value = "delete/all/{pageNum}", method = RequestMethod.POST)
	public Map<String, Object> deleteAll(@PathVariable("pageNum") Integer pageNum, String ids) {
		Map<String, Object> result = userService.deleteByIds(ids, pageNum);
		return result;
	}
}
