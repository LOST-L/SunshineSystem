package com.heli.oa.common.service;

import com.heli.oa.common.entity.CountUserPower;

import java.util.List;

public interface CountUserPowerService {

    Integer editUserPower(Integer userId, Integer powerTypeId, String powers) throws Exception;

    List<CountUserPower> selectAllByPowerType(CountUserPower c) throws Exception;
}
