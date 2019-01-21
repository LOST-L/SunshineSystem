package com.heli.oa.common.entity;

import lombok.Data;

/**
 * Created with IntelliJ IDEA.
 * User: 白驹
 * Date: 2019/1/16
 * Time: 16:18
 * Description:官网信息查询接口
 */
@Data
public class EmployeeInfo {
    Integer employeeInfoId;
    String employeeInfoNickname;
    String employeeInfoPhone;
    String employeeInfoQQ;
    String employeeInfoWechat;
    String employeeInfoMail;
}
