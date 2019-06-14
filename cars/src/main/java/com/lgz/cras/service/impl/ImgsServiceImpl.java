package com.lgz.cras.service.impl;

import com.lgz.cras.mapper.ImgsMapper;
import com.lgz.cras.pojo.Imgs;
import com.lgz.cras.service.ImgsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class ImgsServiceImpl implements ImgsService {
    @Autowired
    private ImgsMapper imgsMapper;
    @Override
    public List<Imgs> getByCarId(Integer carId) {
        return imgsMapper.getByCarId(carId);
    }

    @Override
    public Map<String, Object> getImgsByCarId(Integer carId) {
        List<Imgs> list=getByCarId(carId);
        List<Map<String,String>> imgList=new ArrayList<>();
        for (Imgs i:list){
            Map<String,String> map=new HashMap<>();
            map.put("alt",i.getImg());
            map.put("pid",i.getId().toString());
            map.put("src","static/upload/"+i.getImg());
            map.put("thumb","static/upload/"+i.getImg());
            imgList.add(map);
        }
        Map<String,Object> resultMap=new HashMap<>();
        resultMap.put("title","车况图");
        resultMap.put("id",carId);
        resultMap.put("static",0);
        resultMap.put("data",imgList);
        return resultMap;
    }
}
