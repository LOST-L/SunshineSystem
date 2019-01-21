package com.heli.oa.common.service.impl;

import com.heli.oa.common.dao.RolePermissionDao;
import com.heli.oa.common.entity.Permission;
import com.heli.oa.common.entity.Role;
import com.heli.oa.common.service.RolePermissionService;
import org.apache.ibatis.builder.ResultMapResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
/**
 *@Author: 白驹
 *@Date: 2019/1/2 10:42
 *@Description: shiro 自定义realm service
 */
@Service
public class RolePermissionServiceImpl implements RolePermissionService {

    @Autowired
    RolePermissionDao rolePermissionDao;

    @Override
    public Set<String> listRoles(String userNickname) {
        List<Role> roles = rolePermissionDao.listRolesByUserName(userNickname);
        Set<String> result = new HashSet<>();
        try{
            for (int i = 0; i <roles.size() ; i++) {
                result.add(roles.get(i).getRoleName());
            }
        }catch (Exception e){
            result.add("employee");
        }
        return result;
    }

    @Override
    public Set<String> listPermissions(String userNickname) {
        List<Permission> permissions = rolePermissionDao.listPermissionsByUserName(userNickname);
        Set<String> result = new HashSet<>();
        for (Permission permission: permissions) {
            result.add(permission.getPerName());
        }
        return result;
    }
}

