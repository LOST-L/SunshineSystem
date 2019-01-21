package com.heli.oa.common.service.impl;

import com.heli.oa.common.dao.CountUserMapper;
import com.heli.oa.common.dao.CountUserPowerMapper;
import com.heli.oa.common.entity.CountUser;
import com.heli.oa.common.entity.CountUserPower;
import com.heli.oa.common.service.CountUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CountUserServiceImpl implements CountUserService {

    @Autowired
    private CountUserMapper cum;
    @Autowired
    private CountUserPowerMapper cupm;

    @Override
    public Integer addCountUser(CountUser c) throws Exception {
        return cum.insertSelective(c);
    }

    @Override
    public Integer editCountUser(CountUser c) throws Exception {
        return cum.updateSelective(c);
    }

    @Override
    public CountUser searchCountUserByUserId(CountUser c) throws Exception {
        CountUser countUser = cum.selectByPrimaryKey(c.getUserId());
        if(countUser != null){
            List<CountUserPower> countUserPowers = cupm.searchCountUserPowerListByUserId(c.getUserId());
            countUser.setPowerList(countUserPowers);
        }
        return countUser;
    }

    @Override
    public Integer selectAllCountUserTotal(CountUser c) throws Exception {
        return cum.selectPageTotal(c);
    }

    @Override
    public List<CountUser> selectAllCountUser(CountUser c) throws Exception {
        return cum.selectPage(c);
    }

    @Override
    public List<CountUser> searchCountUserByInfo(CountUser c) throws Exception {
        return cum.searchCountUserByInfo(c);
    }
}
