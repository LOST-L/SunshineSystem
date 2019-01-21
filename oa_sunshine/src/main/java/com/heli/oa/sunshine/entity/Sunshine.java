package com.heli.oa.sunshine.entity;

import lombok.Data;

import java.util.Date;

/**
 * @Author:核利-白驹
 * @Descriction: 阳光值实体类
 * @Date:Created in 16:29 2018/11/6
 */
@Data
public class Sunshine {

    private Integer sunshineRecordId;

    private Integer userId;

    private String userNickname;

    private String userDepartment;

    private Integer adminId;

    private String adminNickname;

    private Integer sunshineValue;

    private Double sunshineSumMinus;

    private String sunshineComment;

    private Date sunshineTime;

    /** 单条阳光值增减记录，传图片路径进去*/
    private String sunshineStarHtml;

    private String sunshineActionComment;

    private String sunshineMoneyStatus;

    /** 基础扣除金额*/
    private Double sunshineMoney;

    /** 滞纳金*/
    private Double sunshineOverdueFine;

    /** 应缴纳总金额*/
    private Double sunshineTotalMoney;

    private String sunshineCommentType;

    private String sunshineNewAdminNickname;

    private Double sunshineMoneySumMinus;
}

