package com.heli.oa.common.service.impl;

import com.heli.oa.common.dao.DepartmentDao;
import com.heli.oa.common.dao.UserDao;
import com.heli.oa.common.entity.Department;
import com.heli.oa.common.entity.User;
import com.heli.oa.common.util.HeliRSAUtils;
import com.heli.oa.common.service.UserService;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sun.misc.BASE64Decoder;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**  * @author 白驹  */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;
    @Autowired
    private DepartmentDao departmentDao;

    @Override
    public User userLogin(String sign) throws Exception {
        String loginInfo = HeliRSAUtils.decryptByPrivateKey(sign);
        com.alibaba.fastjson.JSONObject jsonObject = com.alibaba.fastjson.JSONObject.parseObject(loginInfo);
        String userNickname = jsonObject.getString("v1");
        String password = jsonObject.getString("v2");

        //base64解密
        BASE64Decoder decoder = new BASE64Decoder();
        byte[] b = decoder.decodeBuffer(userNickname);
        userNickname = new String(b, "utf-8");

        //获取认证主体
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(userNickname,password);
        try{
            subject.login(token);
        }catch (AuthenticationException e){
            e.printStackTrace();
            return null;
        }
        return userDao.getUserByNickname(userNickname);
    }

    @Override
    public void addUser(User u) {
        u.setUserPassword("helidianshang");
        u.setUserPlusSunshine(0);
        u.setUserMinusSunshine(0);
        u.setUserPlusStarHtml("零");
        u.setUserMinusStarHtml("零");
        u.setUserMoney((double) 0);
        u.setUserStatus("在职");
        u.setUserDelFlag(1);
        userDao.addUser(u);
    }

    @Override
    public void deleteUser(Integer userId) {
        userDao.deleteUser(userId);
    }

    @Override
    public void updateUser(User u) {
        userDao.updateUser(u);
    }

    @Override
    public User getUserByUserId(Integer userId) { return userDao.getUserByUserId(userId);}

    @Override
    public List<User> listUserBySunshine() {
        return userDao.listUserBySunshine();
    }

    @Override
    public List<User> listUserByCity(String city) {
        return userDao.listUserByCity(city);
    }

    @Override
    public List<User> listUserByDepartment(String dep) {
        return userDao.listUserByDepartment(dep);
    }

    @Override
    public List<User> listAllUser() {
        return userDao.listAllUser();
    }

    @Override
    public Map<String,List<User>> listAllUserByDep() {
        List<Department> depList = departmentDao.listDepartment();

        Map<String, List<User>> userMap = new HashMap<>(16);
        for (int i = 0; i < depList.size(); i++) {
            String dep = depList.get(i).getDepartmentName();
            List<User> userlist = userDao.listUserByDepartment(dep);
            userMap.put(dep,userlist);
        }
        return userMap;
    }

    @Override
    public List<User> listAllUserByStar() {
        return userDao.listAllUserByStar();
    }
}
