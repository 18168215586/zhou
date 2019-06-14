package com.lgz.cras.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lgz.cras.mapper.PowerMapper;
import com.lgz.cras.pojo.Power;
import com.lgz.cras.pojo.User;
import com.lgz.cras.service.PowerService;
import com.lgz.cras.util.MyUtils;
import com.lgz.cras.util.ResBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpSession;
import java.util.List;
@Service
@Transactional
public class PowerServiceImpl implements PowerService {
    @Autowired
    private PowerMapper powerMapper;

    @Override
    public List<Power> getAll(Power power) {
        return powerMapper.getAll(power);
    }

    @Override
    public ResBean delete(Integer id) {
        if (powerMapper.deleteByPrimaryKey(id)>0){
            return new ResBean(1,"删除成功");
        }
        return new ResBean(0,"删除失败");
    }

    @Override
    public ResBean getPage(Integer page, Integer limit) {
        PageHelper.startPage(page,limit);
        List<Power> list=powerMapper.getAll(new Power());
        PageInfo<Power> pageInfo=new PageInfo<>(list);
        return new ResBean(0,"暂无数据",pageInfo.getTotal(),list);
    }

    @Override
    public ResBean update(Power power, HttpSession session) {
        int result;
        if (power.getId()==null){
            power.setCreateAdmin(MyUtils.getLoginName(session));
            power.setCreateDate(MyUtils.getNowTime());
            result=powerMapper.insertSelective(power);
        }else {
            power.setUpdateAdmin(MyUtils.getLoginName(session));
            power.setUpdateDate(MyUtils.getNowTime());
            result=powerMapper.updateByPrimaryKeySelective(power);
        }
        if (result>0){
            return new ResBean(1,"更新成功");
        }
        return new ResBean(0,"更新失败");
    }


}
