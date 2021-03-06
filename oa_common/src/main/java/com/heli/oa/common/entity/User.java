package com.heli.oa.common.entity;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 *@Author: 白驹
 *@Date: 2019/1/2 10:44
 *@Description:
 */
@Data
public class User {

    /** 移动端登陆接口用token*/
    private String userToken;

    private Integer userId;

    private String userName;

    private String userNickname;

    private String userPassword;

    private String userPhone;

    private String userPhone2;

    private String userQQ;

    private String userWechat;

    private String userMail;

    private String userDepartment;

    private String userCity;

    /** 职位级别编号 */
    private Integer userRole;

    /** 用户职位中文 */
    private String userPositionChinese;

    /** 奖励阳光值数量 */
    private Integer userPlusSunshine;

    /** 扣除阳光值数量 */
    private Integer userMinusSunshine;

    /** 奖励阳光值html拼接 */
    private String userPlusStarHtml;

    /** 扣除阳光值html拼接 */
    private String userMinusStarHtml;

    /** 入职时间 */
    @JSONField(format="yyyy-MM-dd") //fastjson 返回特定格式日期注解
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date userJoinDate;

    /** 在职时间 */
    private String userHowLong;

    /** 在职状态 */
    private String userStatus;

    /** 扣除总金额 */
    private Double userMoney;

    /** shiro加密盐MD5 */
    private String userPasswordSalt;

    /** 伪删除标记 ，0为删除，默认为1 */
    private Integer userDelFlag;
}