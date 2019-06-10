package com.lgz.cras.mapper;

import com.lgz.cras.pojo.Imgs;

public interface ImgsMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Imgs record);

    int insertSelective(Imgs record);

    Imgs selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Imgs record);

    int updateByPrimaryKey(Imgs record);
}