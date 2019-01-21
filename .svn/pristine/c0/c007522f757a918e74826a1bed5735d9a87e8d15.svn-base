package com.heli.oa.sunshine.service.impl;

import com.heli.oa.sunshine.dao.SunshineActionDao;
import com.heli.oa.sunshine.entity.SunshineAction;
import com.heli.oa.sunshine.service.SunshineActionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SunshineActionServiceImpl implements SunshineActionService {

    @Autowired
    SunshineActionDao sunshineActionDao;

    @Override
    public void addSunshineAction(SunshineAction a) {
        sunshineActionDao.addSunshineAction(a);
    }

    @Override
    public List<SunshineAction> listSunshineAction() {
        return sunshineActionDao.listSunshineAction();
    }
}
