package com.lgz.cras.service;

import com.lgz.cras.pojo.Car;
import com.lgz.cras.util.ResBean;

import javax.servlet.http.HttpSession;

public interface CarService {
    ResBean getPage(int page, int limit, Car car,HttpSession session);
    ResBean update(Car car, String[] carimgs, HttpSession session);
    Car getById(Integer id);
    ResBean delete(Integer id);
}
