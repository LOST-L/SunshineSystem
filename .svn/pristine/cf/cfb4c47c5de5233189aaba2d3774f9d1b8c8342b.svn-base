package com.heli.oa.sunshine.service.impl;

import com.heli.oa.sunshine.dao.TaskDao;
import com.heli.oa.sunshine.entity.Task;
import com.heli.oa.sunshine.util.DingMessageUtils;
import com.heli.oa.sunshine.util.MailUtil;
import lombok.extern.slf4j.Slf4j;
import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import java.util.Date;


/**
 *@Author: 白驹
 *@Date: 2019/1/10 17:21
 *@Description: 增加father_son状态任务
 */
@Slf4j
@Component
public class AddRepeatTaskServiceNew implements Job {

    @Autowired
    TaskDao taskDao;
    @Autowired
    MailUtil mailUtil;
    @Autowired
    DingMessageUtils dingMessageUtils;

    @Override
    public void execute(JobExecutionContext context) {

        //  https://www.cnblogs.com/gongchengshixiaobai/p/7868125.html
        SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);

        JobDataMap dataMap = context.getJobDetail().getJobDataMap();
        Integer recordId = dataMap.getInt("recordId");
        Integer taskId = dataMap.getInt("taskId");

        Task t = taskDao.selectTaskByRecordId(recordId);

        log.info("重复任务发布："+ t);
        //设置任务提交期限
        Date limitTime = new Date();
        limitTime.setTime(limitTime.getTime() + t.getRepeatTaskLimitTime());
        t.setTaskLimitTime(limitTime);
        t.setTaskRemindTime(new Date(t.getTaskLimitTime().getTime() - t.getTaskRemindHour() * 60 * 60 * 1000));

        t.setTaskStatus("进行中");
        t.setTaskFatherSon("son");
        t.setTaskDelFlag(1);
        t.setTaskCreateTime(new Date());

        //设置任务为第几次发布的
        Task getRepeatNumTask = taskDao.selectLastSameFatherTask(taskId);
        if(getRepeatNumTask == null){
            t.setSonTaskRepeatNum(1);
        }else{
            t.setSonTaskRepeatNum(getRepeatNumTask.getSonTaskRepeatNum()+1);
        }

        taskDao.addTask(t);

        dingMessageUtils.newRepeatTaskMessage(t);

    }
}

