package com.heli.oa.common.controller;

import com.heli.oa.common.entity.CountUserPower;
import com.heli.oa.common.service.CountUserPowerService;
import com.heli.oa.common.util.CodeUtils;
import com.heli.oa.common.util.ReturnUtils;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Slf4j
@Data
@Controller
@RequestMapping("count/user/power")
public class CountUserPowerController  {

    @Autowired
    private CountUserPowerService cupsi;

    @ResponseBody
    @RequestMapping("edit")
    public CodeUtils edit(String userId, String userPowerTypeId, String powers, HttpServletRequest request, HttpServletResponse response){
        CodeUtils code = new CodeUtils();
        try {
            /*String userId = request.getParameter("userId");
            String userPowerTypeId = request.getParameter("userPowerTypeId");
            String powers = request.getParameter("powers");*/
            Integer editUserPower = cupsi.editUserPower(Integer.valueOf(userId), Integer.valueOf(userPowerTypeId), powers);
            if(editUserPower != 0){
                code.setCode(200);
                code.setParamObject("编辑权限成功");
            }else{
                code.setCode(501);
                code.setParamObject("编辑用户权限失败！");
            }
        } catch (Exception e) {
            log.info("编辑前台综合用户权限失败，"+e.getMessage());
            code.setCode(505);
            code.setParamObject("系统出现异常，请立即联系管理员！");
        } finally {
            return code;
        }
    }

    @ResponseBody
    @RequestMapping("getList")
    public CodeUtils getList(CountUserPower cup, HttpServletResponse response){
        CodeUtils code = new CodeUtils();
        try {
            List<CountUserPower> countUserPowers = cupsi.selectAllByPowerType(cup);
            if(countUserPowers!=null && countUserPowers.size() != 0){
                code.setCode(200);
                code.setParamObject(countUserPowers);
            }else{
                code.setCode(501);
                code.setParamObject("暂未获取权限列表！");
            }
        } catch (Exception e) {
            log.info("加载前台综合用户权限失败，"+e.getMessage());
            code.setCode(505);
            code.setParamObject("系统出现异常，请立即联系管理员！");
        } finally {
            return code;
        }
    }
}
