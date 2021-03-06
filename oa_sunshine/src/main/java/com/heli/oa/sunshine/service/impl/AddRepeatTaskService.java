package com.heli.oa.sunshine.service.impl;

import com.heli.oa.common.util.ExceptionResolverUtil;
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

import java.io.IOException;
import java.util.Date;

/**
 *@Author: 白驹
 *@Date: 2019/1/10 17:54
 *@Description:
 */
@Slf4j
@Component
public class AddRepeatTaskService implements Job {

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
        int taskId = dataMap.getInt("taskId");

        Task t = taskDao.selectFatherTaskByTaskId(taskId);
        log.info("重复任务发布："+ t);
        //设置任务提交期限
        Date limitTime = new Date();
        limitTime.setTime(limitTime.getTime() + t.getRepeatTaskLimitTime());
        t.setTaskLimitTime(limitTime);
        t.setTaskRemindTime(new Date(t.getTaskLimitTime().getTime() - t.getTaskRemindHour() * 60 * 60 * 1000));

        //设置任务状态
        if(t.getTaskCreator().equals(t.getTaskReceiver())){
            t.setTaskStatus("进行中");
        }else{
            t.setTaskStatus("待接受");
        }

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

        //生成receiver数组，循环插入任务记录
        String[] receiver =t.getTaskReceiver().split(",");
        for (int i = 0; i < receiver.length ; i++) {
            t.setTaskReceiver(receiver[i]);
            taskDao.addTask(t);
            if(!t.getTaskCreator().equals(t.getTaskReceiver())){
                try {
                    dingMessageUtils.newTaskMessage(t);
                } catch (Exception e) {
                    ExceptionResolverUtil.errorMessageToDing(e);
                }
            }
            log.info("循环任务发布成功，任务ID:"+t.getTaskId()+",接收人："+receiver[i]);
        }
    }
}

