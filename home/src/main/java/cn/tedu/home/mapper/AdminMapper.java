package cn.tedu.home.mapper;

import cn.tedu.home.pojo.Admin;

public interface AdminMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Admin record);

    int insertSelective(Admin record);

    Admin selectByPrimaryKey(Integer id);
    
    Admin selectByAdminName(String adminname);

    int updateByPrimaryKeySelective(Admin record);

    int updateByPrimaryKey(Admin record);
}