package com.heli.oa.common.util;

import com.alibaba.fastjson.JSONObject;
import com.heli.oa.common.dao.UserActionDao;
import com.heli.oa.common.entity.UserAction;
import org.apache.shiro.SecurityUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: 白驹
 * Date: 2019/1/21
 * Time: 15:22
 * Description: 用户操作记录切面
 */
@Aspect
@Component
public class UserActionAspect {

    @Autowired
    UserActionDao userActionDao;

    @Around(value = "execution(* com.heli.oa..*Controller.*(..))")
    public Object log(ProceedingJoinPoint joinPoint) throws Throwable {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();

        UserAction userAction = new UserAction();

        String userNickname = (String) SecurityUtils.getSubject().getPrincipal();

        userAction.setUserNickname(userNickname);

        String ip = GetIPandMACUtils.getIpAddr(request);

        userAction.setIp(ip);

        //获取目标类名称
        String clazzName = joinPoint.getTarget().getClass().getName();
        //获取目标类方法名称
        String methodName = joinPoint.getSignature().getName();

        //记录请求参数
        userAction.setReqParams(JSONObject.toJSONString(request.getParameterMap()));
        //记录请求类名方法名
        userAction.setClassMethod(clazzName + "." + methodName);
        //记录请求时间戳
        userAction.setLogTime(new Date());

        // 计时并调用目标函数
        long start = System.currentTimeMillis();
        Object result = joinPoint.proceed();
        Long time = System.currentTimeMillis() - start;

        //设置消耗时间
        userAction.setSpendTime(time);

        userAction.setRespParams(JSONObject.toJSONString(result));
        userActionDao.addUserAction(userAction);
        return result;
    }
}
