package com.lgz.cras.mapper;

import com.lgz.cras.pojo.Power;

import java.util.List;

public interface PowerMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Power record);

    int insertSelective(Power record);

    Power selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Power record);

    int updateByPrimaryKey(Power record);

    List<Power> getAll(Power power);





}