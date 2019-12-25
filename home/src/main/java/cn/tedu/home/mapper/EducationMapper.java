package cn.tedu.home.mapper;

import cn.tedu.home.pojo.Education;

public interface EducationMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Education record);

    int insertSelective(Education record);

    Education selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Education record);

    int updateByPrimaryKey(Education record);
}