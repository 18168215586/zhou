package com.lgz.cras.controller;

import com.lgz.cras.pojo.Brand;
import com.lgz.cras.service.BrandService;
import com.lgz.cras.util.ResBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/brand")
public class BrandController {

    @Autowired
    private BrandService brandService;

    @RequestMapping("/toList")
    public String toList(){
        return "brand/brand-list";
    }

    @RequestMapping("/getPage")
    @ResponseBody
    public List<Brand> getPage(Brand brand){
        return brandService.getAll(brand);
    }

    @RequestMapping("/getChildren")
    @ResponseBody
    public ResBean getChildren(Brand brand){
        return brandService.getChildren(brand);
    }

    @RequestMapping("/delete")
    @ResponseBody
    public ResBean deleteByKey(Brand brand){
        return brandService.deleteByKey(brand.getId());
    }

    @RequestMapping("/doEdit")
    @ResponseBody
    public ResBean update(Brand brand, HttpSession session){
        return brandService.update(brand,session);
    }


    @RequestMapping("/updSta")
    @ResponseBody
    public ResBean updSta(Brand brand,HttpSession session){
        return brandService.updatSatatus(brand,session);
    }

    @RequestMapping("/toEdit")
    public String toEdit(HttpServletRequest request,Brand brand){
        if (brand.getId()!=null){
            request.setAttribute("br",brandService.getById(brand.getId()));
        }else {
            request.setAttribute("pid",brand.getPid());
        }
        return "brand/brand-edit";
    }

}
