package com.lgz.cras.controller;

import com.lgz.cras.pojo.Power;
import com.lgz.cras.pojo.User;
import com.lgz.cras.service.PowerService;
import com.lgz.cras.service.UserService;
import com.lgz.cras.util.ResBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Map;

@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private PowerService powerService;

    @RequestMapping("/getById")
    @ResponseBody
    public User getUser(){
        return userService.getInfoByid(1);
    }

    /*跳转*/
    @RequestMapping("/toList")
    public String toList(HttpServletRequest request){
        User user= (User) request.getSession().getAttribute("loginUser");
        request.setAttribute("pl",powerService.getAll(new Power(user.getPowerid())));
        return "user/user-list";
    }

    @RequestMapping("/getPage")
    @ResponseBody
    public ResBean getPage(Integer page, Integer limit, User user){
        return userService.getPage(page,limit,user);
    }

    @RequestMapping("/toEdit")
    public  String toEdit(HttpServletRequest request,Integer id){
        if (id!=null){
             request.setAttribute("user",userService.getById(id));
        }
        /*获取当前登录对象*/

        User user= (User) request.getSession().getAttribute("loginUser");
        request.setAttribute("pl",powerService.getAll(new Power(user.getPowerid())));
        return "user/user-edit";
    }


    @RequestMapping("/checkUname")
    @ResponseBody
    public ResBean checkUname(String username){
        return userService.checkUname(username);
    }



    @RequestMapping("/doEdit")
    @ResponseBody
    public ResBean update(User user, HttpSession session){
        return userService.update(user,session);
    }

    @RequestMapping("/delete")
    @ResponseBody
    public ResBean delete(Integer id){
        return userService.delete(id);
    }
}
