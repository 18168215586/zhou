package com.lgz.cras.controller;

import com.lgz.cras.pojo.Brand;
import com.lgz.cras.service.BrandService;
import com.lgz.cras.util.ResBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("/brand")
public class brandController {

    @Autowired
    private BrandService brandService;

    @RequestMapping("/toList")
    public String toList(){
        return "brand/brand-list";
    }

    @RequestMapping("/getPage")
    @ResponseBody
    public List<Brand> getPage(){
        return brandService.getAll(new Brand());
    }

    @RequestMapping("/getChildren")
    @ResponseBody
    public ResBean getChildren(Brand brand){
        return brandService.getChildren(brand);
    }

    @RequestMapping("/delete")
    @ResponseBody
    public ResBean deleteByKey(Integer id){
        return brandService.deleteByKey(id);
    }

}
