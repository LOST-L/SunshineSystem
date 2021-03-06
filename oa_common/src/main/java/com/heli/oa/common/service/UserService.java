package com.heli.oa.common.service;

import com.heli.oa.common.entity.User;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @author 白驹
 **/

public interface UserService {
    User userLogin(String sign) throws Exception;
    void addUser(User u);
    void deleteUser(Integer userId);
    void updateUser(User u);
    User getUserByUserId(Integer userId);
    List<User> listUserBySunshine();
    List<User> listUserByCity(String city);
    List<User> listUserByDepartment(String dep);
    Map<String,List<User>> listAllUserByDep();
    List<User> listAllUser();
    List<User> listAllUserByStar();

}
