package com.heli.oa.common.controller;

import com.heli.oa.common.entity.Department;
import com.heli.oa.common.service.DepartmentService;
import com.heli.oa.common.util.CodeUtils;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @Author:loong
 * @Descriction:
 * @Date:Created in 13:24 2018/10/30
 */
@Data
@Slf4j
@Controller
public class DepartmentController{

    @Autowired
    private DepartmentService departmentService;
    
    @ResponseBody
    @RequestMapping(value = "/addDepartment")
    public CodeUtils addDepartment(Department dep){
        CodeUtils codeUtils = new CodeUtils();

        Integer depId = departmentService.addDepartment(dep);
        if(depId >0){
            codeUtils.setCode(200);
        }else{
            codeUtils.setCode(500);
        }

        return codeUtils;
    }
    
    @ResponseBody
    @RequestMapping(value = "/deleteDepartment")
    public CodeUtils deleteDepartment(Department dep){
        CodeUtils codeUtils = new CodeUtils();

        Integer delFlag = departmentService.deleteDepartment(dep);
        if(delFlag == -1){
            codeUtils.setCode(500);
        }else if(delFlag == 1){
            codeUtils.setCode(200);
        }

        return codeUtils;
    }


    @ResponseBody
    @RequestMapping(value = "/listDepartment")
    public CodeUtils listDepartment(){
        CodeUtils codeUtils = new CodeUtils();

        List<Department> depList = departmentService.listDepartment();
        codeUtils.setCode(200);
        codeUtils.setParamList(depList);

        return codeUtils;
    }
}

