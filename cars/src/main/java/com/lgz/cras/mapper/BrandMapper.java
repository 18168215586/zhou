package com.lgz.cras.mapper;

import com.lgz.cras.pojo.Brand;

import java.util.List;

public interface BrandMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Brand record);

    int insertSelective(Brand record);

    Brand selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Brand record);

    int updateByPrimaryKey(Brand record);

    List<Brand> getAll(Brand brand);

    int updateByPid(Brand brand);
}