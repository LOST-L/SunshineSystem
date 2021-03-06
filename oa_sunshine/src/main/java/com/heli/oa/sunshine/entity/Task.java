package com.heli.oa.sunshine.entity;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * 任务实体类
 * @author 白驹
 * */

@Data
public class Task {

    private Integer taskRecordId;
    private Integer taskId;
    private String taskCreator;
    private String taskReceiver;

    /**任务创建时间 */
    private Date taskCreateTime;

    /**任务报告内容 */
    private String taskTitle;

    /**任务内容 */
    private String taskContent;

    /**重复任务要求提交的时间，从前台传过来的值，单位毫秒，和creattime做和，放入limittime*/
    private Integer repeatTaskLimitTime;

    /**重复任务结束发布的时间 */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss" )
    private Date repeatTaskEndTime;

    /**任务报告内容 */
    private String taskReport;

    /**提交任务报告的时间 */
    private Date taskReportTime;

    /**任务发布人审核任务的时间 */
    private Date taskAuditTime;
    
    /**任务状态:单次任务，进行中，已完成，未完成 */
    private String taskStatus;

    /**任务类型：单次、重复、限期 */
    private String taskType;

    /**任务提交期限，限期任务与重复任务共用 */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss" )
    private Date taskLimitTime;

    /**重复任务发布周期，要转换为cron使用 */
    private String taskRepeatWay;

    /**任务附件地址 */
    private String taskFilePath;

    /**父任务，子任务*/
    private String taskFatherSon;

    /**重复任务发布过的次数 */
    private Integer sonTaskRepeatNum;

    /**伪删除标记，1 为正常，0 为伪删除状态，数据库设置为默认值为1*/
    private Integer taskDelFlag;

    /**循环方式中文，前台任务列表用*/
    private String taskRepeatChinese;

    /**任务过期处理方式，工作日加班：1、周六加班：2、扣一颗星：3、扣多颗星：N*/
    private Integer taskPunishment;

    /**任务到期前提示的时间*/
    private Date taskRemindTime;

    /**任务到期前多久提示，前台接的值*/
    private Integer taskRemindHour;

    /**是否已经提醒过*/
    private Integer taskReminded;

    /**任务接收人拒绝接受任务原因*/
    private String taskRefuseComment;

    /**任务创建人拒绝通过原因*/
    private String taskNoPassComment;

    /**任务逾期时间戳，每逾期一天扣一颗星，并更新此时间戳，每小时扫描一次*/
    private Date taskTimeoutTime;

    /** 重复任务用cron表达式*/
    private String taskCron;
}
