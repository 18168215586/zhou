package com.lgz.cras.controller;

import com.lgz.cras.mapper.PowerMapper;
import com.lgz.cras.pojo.Power;
import com.lgz.cras.service.PowerService;
import com.lgz.cras.util.ResBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/power")
public class PowerController {

    @Autowired
    private PowerService powerService;


    @RequestMapping("/getAll")
    @ResponseBody
    public Map<String, Object> getAll() {
        List<Power> list=new ArrayList<>();
        Power power=new Power();
        list=powerService.getAll(power);
        Map<String,Object> map=new HashMap<>();
        map.put("code",0);
        map.put("data",list);
        return map;
    }
    @RequestMapping("/delete")
    @ResponseBody
    public ResBean delete(Integer id){
        return powerService.delete(id);
    }
    @RequestMapping("/toList")
    public String toList(){
        return "power/power-list";
    }


    @RequestMapping("/toEdit")
    public  String toEdit(HttpServletRequest request){
        request.setAttribute("pl",powerService.getAll(new Power(1)));
        return "power/power-edit";
    }

    @RequestMapping("/doEdit")
    @ResponseBody
    public ResBean checkUname(Power power, HttpSession session){
        return powerService.checkPower(power,session);
    }


}

