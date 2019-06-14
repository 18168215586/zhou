package com.lgz.cras.service;

import com.lgz.cras.pojo.Imgs;

import java.util.List;
import java.util.Map;

public interface ImgsService {
    List<Imgs> getByCarId(Integer carId);

    Map<String,Object> getImgsByCarId(Integer carId);
}
