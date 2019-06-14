package com.lgz.cras.service;

import com.lgz.cras.pojo.Brand;
import com.lgz.cras.util.ResBean;

import javax.servlet.http.HttpSession;
import java.util.List;

public interface BrandService {
    List<Brand> getAll(Brand brand);

    ResBean getChildren(Brand brand);

    ResBean deleteByKey(Integer id);

    ResBean update(Brand brand, HttpSession session);

    ResBean updatSatatus(Brand brand,HttpSession session);

    Brand getById(Integer id);

}
