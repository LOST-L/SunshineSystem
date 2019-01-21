package com.heli.oa.sunshine.service.impl;

import com.heli.oa.sunshine.dao.TaskDao;
import com.heli.oa.sunshine.entity.Task;
import com.heli.oa.sunshine.service.QuartzService;
import com.heli.oa.sunshine.util.DingMessageUtils;
import org.quartz.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

import static org.quartz.CronScheduleBuilder.cronSchedule;
import static org.quartz.TriggerBuilder.newTrigger;

/**
 * Created with IntelliJ IDEA.
 * User: 白驹
 * Date: 2019/1/15
 * Time: 18:03
 * Description:
 */
@Service
public class QuartzServiceImpl implements QuartzService {

    @Autowired
    TaskDao taskDao;

    @Autowired
    DingMessageUtils dingMessageUtils;

    @Autowired
    Scheduler stdScheduler;

    @Override
    public void add(String startTime, Date endTime, Integer recordId, Integer taskId) throws SchedulerException {
        //sf.initialize("common.properties");
        //定义一个触发器
        String jobName = recordId.toString();
        CronTrigger trigger = newTrigger()
                //name根据Task接收人+id
                .withIdentity(jobName + "Trigger", "createRepeatTaskTriggerGroup")
                //循环规则  "0/2 * * * * ?"
                .withSchedule(cronSchedule(startTime))
                //设置循环任务结束时间
                .endAt(endTime)
                .build();

        JobDetail job=JobBuilder.newJob(AddRepeatTaskServiceNew.class)
                .withIdentity(jobName + "Job", "createRepeatTaskJobGroup")
                .usingJobData("recordId",recordId)
                .usingJobData("taskId",taskId)
                .build();

        //添加到任务中
        stdScheduler.scheduleJob(job, trigger);
        stdScheduler.start();
    }

    @Override
    public void setTime(Task t) throws SchedulerException{
        //定义一个触发器
        String jobName = t.getTaskRecordId().toString();
        CronTrigger trigger = newTrigger()
                //name根据Task接收人+id
                .withIdentity(jobName + "Trigger", "createRepeatTaskTriggerGroup")
                //循环规则  "0/2 * * * * ?"
                .withSchedule(cronSchedule(t.getTaskCron()))
                //设置循环任务结束时间
                .endAt(t.getRepeatTaskEndTime())
                .build();

        JobDetail job=JobBuilder.newJob(AddRepeatTaskServiceNew.class)
                .withIdentity(jobName + "Job", "createRepeatTaskJobGroup")
                .usingJobData("recordId",t.getTaskRecordId())
                .usingJobData("taskId",t.getTaskId())
                .build();

        //添加到任务中
        stdScheduler.scheduleJob(job, trigger);
        stdScheduler.start();
    }
}
