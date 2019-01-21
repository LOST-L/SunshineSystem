package com.heli.oa.common.dao;

import com.heli.oa.common.entity.CountUserPower;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CountUserPowerMapper {

    Integer editLinkSource(@Param("userId") Integer userId, @Param("powerId") Integer powerId) throws Exception;

    Integer deleteLinkSource(@Param("userId") Integer userId, @Param("powerTypeId") Integer powerTypeId) throws Exception;

    List<CountUserPower> selectAllByPowerType(CountUserPower c) throws Exception;

    List<CountUserPower> searchCountUserPowerListByUserId(@Param("userId") Integer userId) throws Exception;
}
