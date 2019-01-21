package com.heli.oa.common.entity;


import lombok.Data;

import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: 白驹
 * Date: 2019/1/18
 * Time: 17:36
 * Description:
 */
@Data
public class UserAction {

    private Integer userActionId;

    private String userNickname;

    /**
     * 请求IP
     */
    private String ip;

    /**
     * 请求类，方法
     */
    private String classMethod;

    /**
     * 请求参数
     */
    private String reqParams;
    /**
     * 响应参数
     */
    private String respParams;
    /**
     * 响应时间
     */
    private long spendTime;

    /**
     * 记录时间
     */
    private Date logTime;
}
