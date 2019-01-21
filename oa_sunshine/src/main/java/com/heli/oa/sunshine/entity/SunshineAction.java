package com.heli.oa.sunshine.entity;

import lombok.Data;

import java.util.Date;
/**
 *@Author: 白驹
 *@Date: 2019/1/9 17:08
 *@Description: 阳光值修改删除记录实体类
 */
@Data
public class SunshineAction {

    private Integer sunshineActionId;

    private Integer sunshineRecordId;

    private String sunshineAction;

    private String sunshineActionComment;

    private Date sunshineActionTime;

    private Integer sunshineAdminId;

    private String sunshineAdminNickname;

    private String sunshineNewAdminNickname;

    private String sunshineOldComment;

    private Date sunshineOldTime;

    private String sunshineStarHtml;

    private String userNickname;
}