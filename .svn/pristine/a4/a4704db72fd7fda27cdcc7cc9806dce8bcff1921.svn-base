package com.heli.oa.common.service.impl;

import com.heli.oa.common.dao.CountUserPowerTypeMapper;
import com.heli.oa.common.entity.CountUserPowerType;
import com.heli.oa.common.service.CountUserPowerTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CountUserPowerTypeServiceImpl implements CountUserPowerTypeService {
    @Autowired
    private CountUserPowerTypeMapper cuptm;

    @Override
    public List<CountUserPowerType> selectAll() throws Exception {
        return cuptm.selectAll();
    }
}
