package cn.tedu.home.service.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.apache.commons.collections.map.HashedMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import cn.tedu.home.mapper.UserMapper;
import cn.tedu.home.pojo.User;
import cn.tedu.home.service.UserService;
import cn.tedu.home.util.Code;
import cn.tedu.home.util.Util;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserMapper userMapper;
	
	@Override
	public List<User> getUserList() {
		List<User> list = userMapper.selectAll();
		return list;
	}
	
	@Override
	public List<User> getAssociation() {
		List<User> list = userMapper.selectAssociation();
		return list;
	}

	@Override
	public Map<String, Object> editStatus(Integer id) {
		Map<String, Object> map = new HashMap<String, Object>();
		
		map.put("code", Code.ERROR);
		map.put("message", "request failed");
		
		User record = userMapper.selectByPrimaryKey(id);
		User u = new User();
		u.setId(id);
		u.setStatus((byte) (record.getStatus() == 0 ? 1 : 0));
		
		int n = userMapper.updateStatusByPrimaryKey(u);
		if(n > 0) {
			map.put("code", Code.SUCCESS);
			map.put("message", "success");
			return map;
		}
		return map;
	}

	@Override
	public User getUserById(Integer id) {
		User user = userMapper.selectByPrimaryKey(id);
		user.setPassword(null);
		return user;
	}

	@Override
	public Map<String, Object> editInfo(User u, Integer pageNum) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("pageNum", pageNum);
		
		if(u == null) {
			map.put("code", Code.INVALID);
			map.put("message", "invalid request parameter");
			return map;
		}
		
		if(u.getGender() == null) {
			u.setGender("男");
		}
		if("on".equals(u.getGender())) {
			u.setGender("女");
		}
		
		map.put("code", Code.ERROR);
		map.put("message", "request failed");
		int n = userMapper.updateByPrimaryKeySelective(u);
		if(n > 0) {
			map.put("code", Code.SUCCESS);
			map.put("message", "success");
		}
		return map;
	}

	@Override
	public Map<String, Object> editPass(User user, String newpass) {
		Map<String, Object> map = new HashMap<String, Object>();
		
		User u = userMapper.selectByPrimaryKey(user.getId());
		newpass = DigestUtils.md5DigestAsHex(newpass.getBytes());
		String oldpass = DigestUtils.md5DigestAsHex(user.getPassword().getBytes());
		if(!u.getPassword().equals(oldpass)) {
			map.put("code", Code.PASS_ERROR);
			map.put("message", "password error");
			return map;
		}
		
		user.setPassword(newpass);
		map.put("code", Code.ERROR);
		map.put("message", "request failed");
		int n = userMapper.updateByPrimaryKeySelective(user);
		if(n > 0) {
			map.put("code", Code.SUCCESS);
			map.put("message", "success");
		}
		
		return map;
	}

	@Override
	public Map<String, Object> deleteById(Integer id, Integer pageNum) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("pageNum", pageNum);
		map.put("code", Code.ERROR);
		map.put("message", "request failed");
		
		int n = userMapper.deleteByPrimaryKey(id);
		if(n > 0) {
			map.put("code", Code.SUCCESS);
			map.put("message", "success");
		}
		
		return map;
	}

	@Override
	public Map<String, Object> add(User u) {
		Map<String, Object> map = new HashMap<String, Object>();
		
		User user = userMapper.selectByUsername(u.getUsername());
		
		if(user != null) {
			map.put("code", Code.USERNAME_INVALID);
			map.put("message", "username already exists");
			return map;
		}
		
		map.put("code", Code.ERROR);
		map.put("message", "insert failed");
		
		u.setGender("男");
		u.setAvatar("avatar/default.jpg");
		u.setPassword(DigestUtils.md5DigestAsHex(u.getPassword().getBytes()));
		u.setJointime(new Date());
		u.setLevel(0);
		byte status = 1;
		u.setStatus(status);
		u.setCity("未知");
		u.setLogins(0);
		u.setEduid(9);
		if("on".equals(u.getGender())) {
			u.setGender("女");
		}
		
		int n = userMapper.insertSelective(u);
		
		if(n > 0) {
			map.put("code", Code.SUCCESS);
			map.put("message", "insert success");
		}
		
		return map;
	}

	@Override
	public Map<String, Object> deleteByIds(String ids, Integer pageNum) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("pageNum", pageNum);
		
		String[] arr = ids.split(",");
		List<Integer> list = new ArrayList<Integer>();
		for (String id : arr) {
			list.add(Integer.parseInt(id));
		}
		map.put("code", Code.ERROR);
		map.put("message", "delete failed");
		
		int n = userMapper.deleteByPrimaryKeyList(list);
		
		if(n > 0) {
			map.put("code", Code.SUCCESS);
			map.put("message", "delete success, " + n + " data deleted");
		}
		return map;
	}

	@Override
	public List<User> getAssociation(String start, String end, String username) {
		Date start_date = null;
		Date end_date = null;
		if(!isEmpty(start)) {
			start_date = Util.parseTime(start, "yyyy-MM-dd");
		}
		if(!isEmpty(end)) {
			end_date = Util.parseTime(end, "yyyy-MM-dd");
			Calendar c = Calendar.getInstance();
			c.setTime(end_date);
			c.set(Calendar.HOUR, 23);
			c.set(Calendar.MINUTE, 59);
			c.set(Calendar.SECOND, 59);
			end_date = c.getTime();
		}
		if(isEmpty(username)) {
			username = null;
		} else {
			username = "%" + username + "%";
		}
		List<User> list = userMapper.selectAssociationAndSearch(start_date, end_date, username);
		return list;
	}
	
	public boolean isEmpty(String s) {
		if(s == null || "null".equals(s) || "".equals(s)) {
			return true;
		}
		return false;
	}

	
}
