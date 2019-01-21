package com.heli.oa.plan.dao;

import com.heli.oa.common.entity.CountUser;
import com.heli.oa.plan.entity.MyShop;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface MyShopMapper {
    int deleteByPrimaryKey(Integer shopId);

    int insert(MyShop record);

    int insertSelective(MyShop record);

    MyShop selectByPrimaryKey(Integer shopId);

    int updateByPrimaryKeySelective(MyShop record);

    int updateByPrimaryKey(MyShop record);

    List<MyShop> selectByUserId(Integer userId);

}