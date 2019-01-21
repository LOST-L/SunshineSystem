package com.heli.oa.common.dao;

import com.heli.oa.common.entity.CountUser;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CountUserMapper {

    CountUser selectByPrimaryKey(Integer uId);

    Integer insertSelective(CountUser c) throws Exception;

    Integer updateSelective(CountUser c) throws Exception;

    Integer selectPageTotal(CountUser c) throws Exception;

    List<CountUser> selectPage(CountUser c) throws Exception;

    List<CountUser> searchCountUserByInfo(CountUser c) throws Exception;

    List<CountUser> selectUserPage(@Param("type") String type, @Param("pageIndex") Integer pageIndex, @Param("pageSize") Integer pageSize) throws Exception;

    List<CountUser> selectUser(@Param("key") String key, @Param("val") String val) throws Exception;

    Integer updateUserStudy(String userStId, String userStTime, String userStIdWhere) throws Exception;
}
