package com.lgz.cras.controller;

import com.lgz.cras.pojo.Brand;
import com.lgz.cras.pojo.Car;
import com.lgz.cras.pojo.Record;
import com.lgz.cras.service.CarService;
import com.lgz.cras.service.RecordService;
import com.lgz.cras.util.ResBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/record")
public class RecordController {

    @Autowired
    private RecordService recordService;
    @Autowired
    private CarService carService;

    @RequestMapping("/toApply")
    public String toApply(Car car, HttpServletRequest request) {
        request.setAttribute("carInfo",carService.getById(car.getId()));
        return "record/apply-edit";
    }

    @RequestMapping("/doEdit")
    @ResponseBody
    public ResBean update(Record record, HttpSession session){
        return recordService.update(record,session);
    }

    @RequestMapping("/borrowList")
    public String borrowList(Record record,HttpServletRequest request){
        request.setAttribute("apply",record.getApply());
        return "record/record-list";
    }
    @RequestMapping("/getPage")
    @ResponseBody
    public ResBean getPage(Integer page,Integer limit,Record record,HttpSession session){
        return recordService.getPage(page,limit,record,session);
    }

}
