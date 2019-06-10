package com.lgz.cras.service;

import com.lgz.cras.pojo.User;
import com.lgz.cras.util.ResBean;

import javax.servlet.http.HttpSession;


public interface UserService {
    User getInfoByid(Integer id);

    User login(User user);

    ResBean getPage(int page,int limit,User user);

    ResBean checkUname(String username);

    ResBean update(User user, HttpSession session);

    ResBean delete(Integer id);

    User getById(Integer id);

    ResBean checkPwd(User user);
}
