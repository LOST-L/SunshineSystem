package com.heli.oa.plan.service;

import com.heli.oa.common.entity.CountUser;
import com.heli.oa.plan.entity.MyShop;

import java.util.List;

public interface MyShopService {

    int deleteByPrimaryKey(Integer shopId);

    int insert(MyShop record);

    int insertSelective(MyShop record);

    MyShop selectByPrimaryKey(Integer shopId);

    int updateByPrimaryKeySelective(MyShop record);

    int updateByPrimaryKey(MyShop record);

    List<MyShop> selectByUserId(Integer userId);

    boolean bindingShop(String url, Integer userId, String linkType)throws Exception;

}