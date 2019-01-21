package com.heli.oa.common.service;

import com.heli.oa.common.entity.CountUser;

import java.util.List;

public interface CountUserService {

    Integer addCountUser(CountUser c) throws Exception;

    Integer editCountUser(CountUser c) throws Exception;

    CountUser searchCountUserByUserId(CountUser c) throws Exception;

    Integer selectAllCountUserTotal(CountUser c) throws Exception;

    List<CountUser> selectAllCountUser(CountUser c) throws Exception;

    List<CountUser> searchCountUserByInfo(CountUser c) throws Exception;
}
