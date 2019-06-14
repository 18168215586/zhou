package com.lgz.cras.service;

import com.lgz.cras.pojo.Power;
import com.lgz.cras.util.ResBean;

import javax.servlet.http.HttpSession;
import java.io.Reader;
import java.util.List;

public interface PowerService {
    List<Power> getAll(Power power);

    ResBean delete(Integer id);

    ResBean getPage(Integer page,Integer limit);
    ResBean update(Power power,HttpSession session);
}