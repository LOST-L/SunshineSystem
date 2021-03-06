package com.heli.oa.common.controller;

import com.heli.oa.common.entity.EmployeeInfo;
import com.heli.oa.common.service.EmployeeInfoService;
import com.heli.oa.common.util.CodeUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * @author 白驹
 * Date: 2019/1/16
 * Time: 16:21
 * Description:
 */
@Slf4j
@Controller
public class EmployeeInfoController {

    @Autowired
    EmployeeInfoService employeeInfoService;

    @ResponseBody
    @RequestMapping("/addEmployeeInfo")
    public CodeUtils addEmployeeInfo(EmployeeInfo eInfo) throws Exception{
        CodeUtils codeUtils = new CodeUtils();

        employeeInfoService.addEmployeeInfo(eInfo);
        codeUtils.setCode(200);

        return codeUtils;
    }

    @ResponseBody
    @RequestMapping("/updateEmployeeInfo")
    public CodeUtils updateEmployeeInfo(EmployeeInfo eInfo) throws Exception {
        CodeUtils codeUtils = new CodeUtils();

            employeeInfoService.updateEmployeeInfo(eInfo);
            codeUtils.setCode(200);

        return codeUtils;
    }

    @ResponseBody
    @RequestMapping("/deleteEmployeeInfo")
    public CodeUtils deleteEmployeeInfo(Integer employeeInfoId) throws Exception{
        CodeUtils codeUtils = new CodeUtils();

        employeeInfoService.deleteEmployeeInfo(employeeInfoId);
        codeUtils.setCode(200);

        return codeUtils;
    }

    @ResponseBody
    @RequestMapping("/searchEmployeeInfo")
    public CodeUtils searchEmployeeInfo(String employeeInfoNickname) throws Exception{
        CodeUtils codeUtils = new CodeUtils();

        List<EmployeeInfo> employeeInfos = employeeInfoService.searchEmployeeInfo(employeeInfoNickname);
        codeUtils.setCode(200);
        codeUtils.setParamList(employeeInfos);

        return codeUtils;
    }

    @ResponseBody
    @RequestMapping("/listEmployeeInfo")
    public CodeUtils listEmployeeInfo(){
        CodeUtils codeUtils = new CodeUtils();

        List<EmployeeInfo> employeeInfos = employeeInfoService.listEmployeeInfo();
        codeUtils.setCode(200);
        codeUtils.setParamList(employeeInfos);

        return codeUtils;
    }

    @ResponseBody
    @RequestMapping("/search")
    public CodeUtils searchEmployeeInfo(String key,HttpServletResponse response){
        CodeUtils codeUtils = new CodeUtils();
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Methods", "POST");
        response.setHeader("Access-Control-Max-Age", "3600");
        response.setHeader("Access-Control-Allow-Headers", "x-requested-with");

        if(employeeInfoService.search(key)){
            codeUtils.setCode(1);
        }else{
            codeUtils.setCode(0);
        }

        return codeUtils;
    }
}
