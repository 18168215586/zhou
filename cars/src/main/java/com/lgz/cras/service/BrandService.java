package com.lgz.cras.service;

import com.lgz.cras.pojo.Brand;
import com.lgz.cras.util.ResBean;

import java.util.List;

public interface BrandService {
    List<Brand> getAll(Brand brand);

    ResBean getChildren(Brand brand);

    ResBean deleteByKey(Integer id);

}
