package com.lgz.cras.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lgz.cras.mapper.UserMapper;
import com.lgz.cras.pojo.User;
import com.lgz.cras.service.UserService;
import com.lgz.cras.util.MD5Util;
import com.lgz.cras.util.MyUtils;
import com.lgz.cras.util.ResBean;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;
    @Override/*自动注入注解*/
    public User getInfoByid(Integer id) {
        return userMapper.selectByPrimaryKey(id);
    }

    @Override
    public User login(User user) {
        return userMapper.login(user);
    }

    @Override
    public ResBean getPage(int page, int limit, User user) {
        Map<String,Object> map=new HashMap<>();
        PageHelper.startPage(page,limit);
        List<User> list=userMapper.getPage(user);
        PageInfo<User> pageInfo=new PageInfo<>(list);
        return new ResBean(0,"暂无数据",pageInfo.getTotal(),list);
    }

    @Override
    public ResBean checkUname(String username) {
        User user=userMapper.checkUname(username);
        if (user==null){
            return new ResBean(0);
        }
        return new ResBean(1);
    }

    @Override
    public ResBean update(User user, HttpSession session) {
        User loginUser= (User) session.getAttribute("loginUser");
        if (StringUtils.isNotEmpty(user.getPassword())){
            user.setPassword(MD5Util.MD5(user.getPassword()));
        }
        int result=0;
        if (user.getId()==null){
            user.setCreateDate(MyUtils.getNowTime());
            user.setCreateAdmin(loginUser.getRealname());
            result=userMapper.insertSelective(user);
        }else {
            user.setUpdateDate(MyUtils.getNowTime());
            user.setCreateAdmin(loginUser.getRealname());
            result=userMapper.updateByPrimaryKeySelective(user);
        }
        if (result>0){
            return new ResBean(1,"更新成功");
        }else {
            return new ResBean(0,"更新失败");
        }
    }

    @Override
    public ResBean delete(Integer id) {
        if (userMapper.deleteByPrimaryKey(id)>0){
            return new ResBean(1,"删除成功");
        }
        return new ResBean(0,"删除失败");
    }

    @Override
    public User getById(Integer id) {
        return userMapper.selectByPrimaryKey(id);
    }
}

