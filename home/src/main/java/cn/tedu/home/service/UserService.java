package cn.tedu.home.service;

import java.util.List;
import java.util.Map;

import cn.tedu.home.pojo.User;

public interface UserService {

	List<User> getUserList();

	List<User> getAssociation();
	
	Map<String, Object> editStatus(Integer id);
	
	User getUserById(Integer id);
	
	Map<String, Object> editInfo(User u, Integer pageNum);

	Map<String, Object> editPass(User user, String newpass);

	Map<String, Object> deleteById(Integer id, Integer pageNum);

	Map<String, Object> add(User u);

	Map<String, Object> deleteByIds(String ids, Integer pageNum);

	List<User> getAssociation(String start, String end, String username);
}
