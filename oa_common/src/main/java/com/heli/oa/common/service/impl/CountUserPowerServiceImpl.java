package com.heli.oa.common.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.heli.oa.common.dao.CountUserPowerMapper;
import com.heli.oa.common.entity.CountUserPower;
import com.heli.oa.common.service.CountUserPowerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CountUserPowerServiceImpl implements CountUserPowerService {
    @Autowired
    private CountUserPowerMapper cupm;

    @Override
    public Integer editUserPower(Integer userId, Integer powerTypeId, String powers) throws Exception {
        Integer delNum = cupm.deleteLinkSource(userId, powerTypeId);

        JSONArray powersArray = JSONObject.parseArray(powers);
        Integer editNum = 0;
        for (int i = 0; i < powersArray.size(); i++) {
            Integer powerId = powersArray.getInteger(i);
            editNum += cupm.editLinkSource(userId, powerId);
        }


        if("[]".equals(powers) && delNum > 0){
            editNum = 1;
        }
        return editNum;
    }

    @Override
    public List<CountUserPower> selectAllByPowerType(CountUserPower c) throws Exception {
        return cupm.selectAllByPowerType(c);
    }
}
