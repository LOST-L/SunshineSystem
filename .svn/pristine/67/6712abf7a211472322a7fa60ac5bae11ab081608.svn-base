package com.heli.oa.common.controller;

import com.heli.oa.common.entity.CountUserPowerType;
import com.heli.oa.common.service.CountUserPowerTypeService;
import com.heli.oa.common.util.CodeUtils;
import com.heli.oa.common.util.ReturnUtils;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Slf4j
@Controller
@RequestMapping("count/user/power/type")
public class CountUserPowerTypeController {

    @Autowired
    private CountUserPowerTypeService cuptsi;

    @ResponseBody
    @RequestMapping("getList")
    public CodeUtils getList(HttpServletResponse response){
        CodeUtils code = new CodeUtils();
        try {
            List<CountUserPowerType> countUserPowers = cuptsi.selectAll();
            if(countUserPowers != null && countUserPowers.size() != 0){
                code.setCode(200);
                code.setParamObject(countUserPowers);
            }else{
                code.setCode(501);
                code.setParamObject("暂未获取权限类型列表！");
            }
        } catch (Exception e) {
            log.info("加载前台综合用户权限类型失败，"+e.getMessage());
            code.setCode(505);
            code.setParamObject("系统出现异常，请立即联系管理员！");
        } finally {
            return code;
        }
    }
}
