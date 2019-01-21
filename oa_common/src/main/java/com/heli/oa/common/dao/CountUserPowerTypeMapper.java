package com.heli.oa.common.dao;

import com.heli.oa.common.entity.CountUserPowerType;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CountUserPowerTypeMapper {
    List<CountUserPowerType> selectAll() throws Exception;
}
