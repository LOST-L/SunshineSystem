package com.heli.oa.common.service.impl;


import com.heli.oa.common.dao.UserDao;
import com.heli.oa.common.dao.DepartmentDao;
import com.heli.oa.common.entity.Department;

import com.heli.oa.common.entity.User;
import com.heli.oa.common.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author:loong
 * @Descriction:
 * @Date:Created in 13:28 2018/10/30
 */
@Service
public class DepartmentServiceImpl implements DepartmentService {
    @Autowired
    UserDao userDao;
    @Autowired
    private DepartmentDao departmentDao;

    @Override
    public List<Department> listDepartment() {
        return departmentDao.listDepartment();
    }

    @Override
    public Integer addDepartment(Department dep) {
        departmentDao.addDepartment(dep);
        return dep.getDepartmentId();
    }

    @Override
    public Integer deleteDepartment(Department dep) {
        List<User> users = userDao.listUserByDepartment(dep.getDepartmentName());
        if(users.size() > 0){
            return -1 ;
        }else{
            return departmentDao.deleteDepartment(dep.getDepartmentId());
        }
    }
}
