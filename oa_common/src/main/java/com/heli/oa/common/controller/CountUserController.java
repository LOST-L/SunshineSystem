package com.heli.oa.common.controller;

import com.heli.oa.common.entity.CountUser;
import com.heli.oa.common.service.CountUserService;
import com.heli.oa.common.util.CodeUtils;
import com.heli.oa.common.util.PageTable;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Slf4j
@Controller
@RequestMapping("count/user")
public class CountUserController {
    @Autowired
    private CountUserService cusi;

    @ResponseBody
    @RequestMapping("getCountUser")
    public CodeUtils getCountUser(CountUser c, HttpServletResponse response){
        CodeUtils code = new CodeUtils();
        try {
            CountUser countUser = cusi.searchCountUserByUserId(c);
            if(countUser!=null){
                code.setCode(200);
                code.setParamObject(countUser);
            }else{
                code.setCode(501);
                code.setParamObject("暂未获取用户信息！");
            }
        } catch (Exception e) {
            log.info("加载前台综合用户信息失败，"+e.getMessage());
            code.setCode(505);
            code.setParamObject("系统出现异常，请立即联系管理员！");
        } finally {
            return code;
        }
    }

    @ResponseBody
    @RequestMapping("getList")
    public PageTable getListCountUser(CountUser countUser, HttpServletResponse response){
        PageTable pageTable = new PageTable();
        try {
            Integer total = cusi.selectAllCountUserTotal(countUser);
            pageTable.setCount(total);
            if(total != 0){
                List<CountUser> countUserList = cusi.selectAllCountUser(countUser);
                pageTable.setCode(0);
                pageTable.setData(countUserList);
            } else {
                pageTable.setCode(0);
                pageTable.setMsg("暂无数据");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
            pageTable.setCode(0);
            pageTable.setMsg("暂无数据");
        } finally {
            return pageTable;
        }
    }

    @ResponseBody
    @RequestMapping("searchCountUser")
    public CodeUtils searchCountUser(CountUser countUser,HttpServletResponse response){
        CodeUtils code = new CodeUtils();
        try {
            List<CountUser> countUsers = cusi.searchCountUserByInfo(countUser);
            if(countUsers!=null){
                code.setCode(200);
                code.setParamList(countUsers);
            }else{
                code.setCode(501);
                code.setParamObject("暂未获取用户信息！");
            }
        } catch (Exception e) {
            log.info("搜索用户失败，"+e.getMessage());
            code.setCode(505);
            code.setParamObject("查找用户失败，请联系管理员！");
        } finally {
            return code;
        }
    }

}
