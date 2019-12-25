package cn.tedu.home.mapper;

import java.util.Date;
import java.util.List;
import java.util.Map;

import cn.tedu.home.pojo.User;

public interface UserMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);
    
    List<User> selectAll();

	List<User> selectAssociation();
	
	int updateStatusByPrimaryKey(User record);

	User selectByUsername(String username);

	int deleteByPrimaryKeyList(List<Integer> list);
	
	List<User> selectAssociationAndSearch(Date start, Date end, String username);
	//List<User> selectAssociationAndSearch(String start, String end, String username);
}