package com.lgz.cras.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lgz.cras.mapper.CarMapper;
import com.lgz.cras.mapper.ImgsMapper;
import com.lgz.cras.pojo.Car;
import com.lgz.cras.pojo.Imgs;
import com.lgz.cras.service.CarService;
import com.lgz.cras.util.MyUtils;
import com.lgz.cras.util.ResBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class CarServiceImpl implements CarService {
    @Autowired
    private CarMapper carMapper;

    @Autowired
    private ImgsMapper imgsMapper;

    @Override
    public ResBean getPage(int page, int limit, Car car,HttpSession session) {
        int powerid=MyUtils.getLoginPowerId(session);
        /*powerid>0表示不是管理员登录*/
        if (powerid>2){
            /*不是管理员用户只能看到可用并且可借的车辆信息*/
            car.setStatus(1);//可借
            car.setIsborrow(2);//可用
        }
        PageHelper.startPage(page, limit);
        List<Car> list = carMapper.getAll(car);
        PageInfo<Car> pi = new PageInfo<>(list);
        return new ResBean(0, "暂无数据", pi.getTotal(), list);
    }

    @Override
    public ResBean update(Car car, String[] carimgs, HttpSession session) {
        int result;
        if (car.getId() == null) {
            /*获取封装值*/
            car.setCreateAdmin(MyUtils.getLoginName(session));
            car.setCreateDate(MyUtils.getNowTime());
            if (carMapper.insertSelective(car) > 0) {
                /*判断是否有图片*/
                if (carimgs.length > 0) {
                    List<Imgs> list = new ArrayList<>();
                    for (String s : carimgs) {
                        /*遍历图片*/
                        Imgs imgs = new Imgs();
                        imgs.setCarid(car.getId());
                        imgs.setImg(s);
                        /*获取session值*/
                        imgs.setCreateAdmin(MyUtils.getLoginName(session));
                        imgs.setCreateDate(MyUtils.getNowTime());
                        list.add(imgs);
                    }
                    result = imgsMapper.batchInsert(list);
                    /*插入图片与数据库图片不一致手动抛出异常   回滚*/
                    if (result != list.size()) {
                        throw new Error("图片个数有误");
                    }
                }
                return new ResBean(1, "信息更新成功");
            } else {
                return new ResBean(0, "信息更新失败");
            }
        } else {
            car.setUpdateAdmin(MyUtils.getLoginName(session));
            car.setCreateDate(MyUtils.getNowTime());
            //汽车修改信息
            if (carMapper.updateByPrimaryKeySelective(car) > 0) {
               if (car.getStatus()==null){
                   List<Imgs> imgsList = imgsMapper.getByCarId(car.getId());
                   //汽车图片是否存在
                   if (imgsList.size() > 0) {
                       //存在图片则删除
                       if (imgsMapper.deleteByCarId(car.getId()) != imgsList.size()) {
                           throw new Error("图片更新异常");
                       }
                   }
                   /*如果汽车有图片进入判断*/
                   if (carimgs.length > 0) {

                       List<Imgs> list = new ArrayList<>();
                       /*遍历Imgs数组长度*/
                       for (String s : carimgs) {
                           /*遍历数组*/
                           Imgs imgs = new Imgs();
                           imgs.setCarid(car.getId());
                           imgs.setImg(s);
                           imgs.setCreateAdmin(MyUtils.getLoginName(session));
                           imgs.setCreateDate(MyUtils.getNowTime());
                           list.add(imgs);
                       }
                        /*如果插入图片不等于img数组长度  回滚事物*/
                       if (imgsMapper.batchInsert(list)!=list.size()) {
                           throw new Error("图片更新异常");
                       }
                   }
               }
                return new ResBean(1, "信息更新成功");
            } else {
                return new ResBean(0, "信息更新失败");
            }
        }
    }

    @Override
    public Car getById(Integer id) {
        return carMapper.getById(id);
    }

    @Override
    public ResBean delete(Integer id) {
        if (carMapper.deleteByPrimaryKey(id)>0){
            List<Imgs> list=imgsMapper.getByCarId(id);
            if (list.size()>0){

                if (imgsMapper.deleteByCarId(id)!=list.size()){
                    throw new Error("删除图片异常");
                }
            }
            return new ResBean(1,"删除成功");
        }
        return new ResBean(0,"删除失败");
    }
}
