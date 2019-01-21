package com.heli.oa.sunshine.service;

import com.heli.oa.sunshine.entity.Task;
import org.quartz.SchedulerException;

import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: 白驹
 * Date: 2019/1/15
 * Time: 18:02
 * Description:
 */
public interface QuartzService{
    void add(String startTime, Date endTime, Integer recordId, Integer taskId) throws SchedulerException;
    void setTime(Task t) throws SchedulerException;
}
