package com.heli.oa.common.controller;

import com.heli.oa.common.entity.User;
import com.heli.oa.common.service.UserService;
import com.heli.oa.common.util.CodeUtils;
import com.heli.oa.common.util.GetIPandMACUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.List;
import java.util.Map;

/**
 *@Author: 白驹
 *@Date: 2019/1/2 10:44
 *@Description:
 */
@Slf4j
@Controller
public class UserController {

    @Autowired
    UserService userService;

    @RequestMapping("/{path1}/{path2}")
    protected String forward(@PathVariable("path1") String path1,@PathVariable("path2") String path2) {
         return path1+"/"+path2;
    }
    @RequestMapping("/{path}")
    protected String forward(@PathVariable("path") String path) {
        return path;
    }

    @ResponseBody
    @RequestMapping(value = "/login",produces = "application/json;charset=utf-8")
    public CodeUtils login(HttpServletResponse response,HttpSession session,String sign) throws Exception {
        CodeUtils codeUtils = new CodeUtils();
        
        User user = userService.userLogin(sign);
        if(user!=null){
            session.setAttribute("user", user);
            Cookie userNickname = new Cookie("userNickname",URLEncoder.encode(user.getUserNickname(), "utf-8"));
            userNickname.setMaxAge(60*60*24);
            response.addCookie(userNickname);
            codeUtils.setCode(200);
        }else{
            codeUtils.setCode(500);
        }    
        return codeUtils;
    }

    @ResponseBody
    @RequestMapping(value = "/logout")
    public void logout(HttpServletRequest request,HttpServletResponse response) throws IOException {
        request.getSession().invalidate();
        response.getWriter().println("再见！");
        response.sendRedirect("login.jsp");
    }

    @ResponseBody
    @RequestMapping(value = "/addUser")
    public CodeUtils addUser(User u) throws Exception{
        CodeUtils codeUtils = new CodeUtils();
        
        userService.addUser(u);
        codeUtils.setCode(200);
        
        return codeUtils;
    }

    @ResponseBody
    @RequestMapping(value = "/deleteUser")
    public CodeUtils deleteUser(Integer userId) throws Exception{
        CodeUtils codeUtils = new CodeUtils();
        
        userService.deleteUser(userId);
        codeUtils.setCode(200);
        
        return codeUtils;
    }

    @ResponseBody
    @RequestMapping(value = "/updateUser")
    public CodeUtils updateUser(User u) throws Exception{
        CodeUtils codeUtils = new CodeUtils();

        userService.updateUser(u);
        codeUtils.setCode(200);

        return codeUtils;
    }

    @ResponseBody
    @RequestMapping(value = "/getUserByUserId")
    public CodeUtils getUserByUserId(Integer userId) throws Exception{
        CodeUtils codeUtils = new CodeUtils();

        User user = userService.getUserByUserId(userId);
        if(user!=null){
            codeUtils.setCode(200);
            codeUtils.setParamObject(user);
        }else{
            codeUtils.setCode(500);
        }
        
        return codeUtils;
    }

    @ResponseBody
    @RequestMapping(value = "/listUserBySunshine")
    public CodeUtils listUserBySunshine() throws Exception{
        CodeUtils codeUtils = new CodeUtils();

        List<User> users = userService.listUserBySunshine();
        if(users!=null){
            codeUtils.setCode(200);
            codeUtils.setParamList(users);
        }else{
            codeUtils.setCode(500);
        }
        
        return codeUtils;
    }

    @ResponseBody
    @RequestMapping(value = "/listUserByCity")
    public CodeUtils listUserByCity(String userCity) throws Exception{
        CodeUtils codeUtils = new CodeUtils();

        List<User> users = userService.listUserByCity(userCity);
        if(users!=null){
            codeUtils.setCode(200);
            codeUtils.setParamList(users);
        }else{
            codeUtils.setCode(500);
        }
        
        return codeUtils;
    }


    @ResponseBody
    @RequestMapping(value = "/listUserByDepartment")
    public CodeUtils listUserByDepartment(String userDepartment) throws Exception{
        CodeUtils codeUtils = new CodeUtils();

        List<User> users  = userService.listUserByDepartment(userDepartment);
        if(users!=null){
            codeUtils.setCode(200);
            codeUtils.setParamList(users);
        }else{
            codeUtils.setCode(500);
        }
        
        return codeUtils;
    }

    @ResponseBody
    @RequestMapping(value = "/listAllUserByStar")
    public CodeUtils listAllUserByStar() throws Exception{
        CodeUtils codeUtils = new CodeUtils();

        List<User> users = userService.listAllUserByStar();
        if(users!=null){
            codeUtils.setCode(200);
            codeUtils.setParamList(users);
        }else{
            codeUtils.setCode(500);
        }
        
        return codeUtils;
    }

    @ResponseBody
    @RequestMapping(value = "/listAllUserByDep")
    public CodeUtils listAllUserByDep() throws Exception{
        CodeUtils codeUtils = new CodeUtils();

        Map<String,List<User>> users = userService.listAllUserByDep();
        if(users!=null){
            codeUtils.setCode(200);
            codeUtils.setParamObject(users);
        }else{
            codeUtils.setCode(500);
        }
        
        return codeUtils;
    }

    @ResponseBody
    @RequestMapping(value = "/listAllUser")
    public CodeUtils listAllUser() throws Exception{
        CodeUtils codeUtils = new CodeUtils();

        List<User> users = userService.listAllUser();
        if(users!=null){
            codeUtils.setCode(200);
            codeUtils.setParamList(users);
        }else{
            codeUtils.setCode(500);
        }
        
        return codeUtils;
    }

    @ResponseBody
    @RequestMapping(value = "/getIP")
    public String getIP(HttpServletRequest request,HttpServletResponse response){
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Methods", "POST");
        response.setHeader("Access-Control-Max-Age", "3600");
        response.setHeader("Access-Control-Allow-Headers", "x-requested-with");
        return GetIPandMACUtils.getIpAddr(request);
    }
}
