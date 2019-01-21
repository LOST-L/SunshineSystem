package com.heli.oa.common.service.impl;

import com.heli.oa.common.dao.EmployeeInfoDao;
import com.heli.oa.common.entity.EmployeeInfo;
import com.heli.oa.common.service.EmployeeInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: 白驹
 * Date: 2019/1/16
 * Time: 16:35
 * Description:
 */
@Service
public class EmployeeInfoServiceImpl implements EmployeeInfoService {

    @Autowired
    EmployeeInfoDao employeeInfoDao;

    @Override
    public void addEmployeeInfo(EmployeeInfo eInfo) {
        employeeInfoDao.addEmployeeInfo(eInfo);
    }

    @Override
    public void updateEmployeeInfo(EmployeeInfo eInfo) {
        employeeInfoDao.updateEmployeeInfo(eInfo);
    }

    @Override
    public void deleteEmployeeInfo(Integer employeeInfoId) {
        employeeInfoDao.deleteEmployeeInfo(employeeInfoId);
    }

    @Override
    public List<EmployeeInfo> searchEmployeeInfo(String key) {
        return employeeInfoDao.searchEmployeeInfo(key);
    }

    @Override
    public List<EmployeeInfo> listEmployeeInfo() {
        return employeeInfoDao.listEmployeeInfo();
    }

    @Override
    public Boolean search(String key) {
        List<EmployeeInfo> employeeInfos = employeeInfoDao.search(key);
        if(employeeInfos.size()==0){
            return false;
        }else{
            return true;
        }
    }
}
