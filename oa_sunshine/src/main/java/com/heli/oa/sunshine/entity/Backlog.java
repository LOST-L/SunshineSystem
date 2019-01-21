package com.heli.oa.sunshine.entity;

import lombok.Data;

import java.util.Date;

/**
 * @author 白驹
 * 待办事项实体类
 */

@Data
public class Backlog {

    private Integer backlogRecordId;

    /**用户ID */
    private Integer userId;

    /**待办事项优先级 */
    private String backlogPriority;

    /**待办事项内容 */
    private String backlogContent;

    /**待办事项创建时间 */
    private Date backlogCreateTime;

    /**任务状态:完成，已定时，取消，待定时 */
    private String backlogStatus;

    /**定时开始时间*/
    private Integer backlogStartTime;

    /**定时开始时间*/
    private Integer backlogEndTime;

    /**任务完成时间*/
    private Date backlogDoneTime;


    private String backlogTimeSlot;

    /**伪删除标记，1 为正常，0 为伪删除状态，数据库设置为默认值为1*/
    private Integer backlogDelFlag;

    /**开始时间字符串*/
    private String backlogStartTimeStr;

    /**结束时间字符串*/
    private String backlogEndTimeStr;

    private String createTimeForSearch;
    private String createTimeForSearch1;
    private String createTimeForSearch2;

    private String doneTimeForSearch;
    private String doneTimeForSearch1;
    private String doneTimeForSearch2;


}