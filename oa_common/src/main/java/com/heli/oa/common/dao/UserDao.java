package com.heli.oa.common.dao;

import com.heli.oa.common.entity.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository("UserDao")
public interface UserDao {

    int addUser(User user);

    int deleteUser(Integer userId);

    int updateUser(User user);

    User getUserByUserId(int id);

    User getUserByNickname(String userNickname);

    List<User> listUserBySunshine();

    List<User> listUserByCity(String city);

    List<User> listUserByDepartment(String departmentName);

    List<User> listAllUserByStar();

    List<User> listAllUser();
}