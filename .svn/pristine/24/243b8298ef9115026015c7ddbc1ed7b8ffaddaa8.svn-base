package com.heli.oa.sunshine.service.impl;

import com.heli.oa.sunshine.dao.SunshineDao;
import com.heli.oa.sunshine.dao.TaskDao;
import com.heli.oa.sunshine.entity.Sunshine;
import com.heli.oa.sunshine.entity.Task;
import com.heli.oa.sunshine.service.QuartzService;
import com.heli.oa.sunshine.service.SunshineService;
import com.heli.oa.sunshine.service.TaskService;
import com.heli.oa.sunshine.util.DingMessageUtils;
import lombok.extern.slf4j.Slf4j;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 *@Author: 白驹
 *@Date: 2019/1/10 17:38
 *@Description:
 */
@Slf4j
@Service
public class TaskServiceImpl implements TaskService {
    /** 任务状态 */
    public static final String TASK_TOACCEPT = "待接受";
    public static final String TASK_ACCEPTED = "已接受";
    public static final String TASK_REFUSED = "已拒绝";
    /** 重复任务 father 任务按接收人拆分为 father_son 任务，被接受后开始quartz定时并更新为“待发布”状态 */
    public static final String TASK_TOCREATE = "待发布";
    public static final String TASK_DOING = "进行中";
    /** 任务超时，第一次提醒，并改为“待处理”，*/
    public static final String TASK_PENDING = "待处理";
    public static final String TASK_TIMEOUT = "已逾期";
    public static final String TASK_TOAUDIT= "待审核";
    public static final String TASK_DONE = "已完成";
    public static final String TASK_NOPASS = "不通过";
    public static final String TASK_CANCELED = "已取消";


    public static final String SINGLE_TASK = "单次任务";
    public static final String REPEAT_TASK = "重复任务";
    public static final String LIMIT_TASK = "限期完成";

    @Autowired
    private TaskDao taskDao;
    @Autowired
    private QuartzService quartzService;
    @Autowired
    private DingMessageUtils dingMessageUtils;
    @Autowired
    private Scheduler stdScheduler;
    @Autowired
    private SunshineDao sunshineDao;
    @Autowired
    private SunshineService sunshineService;

    @Override
    public void addFatherTask(Task t)throws Exception{

        //根据task表最后一条任务taskId给当前任务赋值
        Task getIdTask = taskDao.selectLastTask();
        if(getIdTask == null){
            t.setTaskId(1);
        }else{
            t.setTaskId(getIdTask.getTaskId()+1);
        }

        //todo 惩罚措施，待完善
        if(t.getTaskPunishment() == null){
            t.setTaskPunishment(0);
        }

        t.setTaskReminded(0);
        t.setTaskCreateTime(new Date());
        t.setTaskFatherSon("father");
        t.setTaskDelFlag(1);
        taskDao.addTask(t);
        addSonTask(t);
    }

    @Override
    public void addSonTask(Task t) throws Exception{
        //判断任务类型
        String taskType = t.getTaskType();

        //重复任务
        if(REPEAT_TASK.equals(taskType)){
            addRepeatFatherSonTask(t);
        }else{
            String[] receivers =t.getTaskReceiver().split(",");
            t.setTaskFatherSon("son");
            //单次任务
            if(SINGLE_TASK.equals(taskType)){
                t.setTaskStatus(SINGLE_TASK);
                for (int i = 0; i < receivers.length ; i++) {
                    t.setTaskReceiver(receivers[i]);
                    taskDao.addTask(t);
                    if(!t.getTaskCreator().equals(t.getTaskReceiver())){
                        dingMessageUtils.newTaskMessage(t);
                    }
                }
            //限期任务
            }else if(LIMIT_TASK.equals(taskType)) {

                t.setTaskRemindTime(new Date(t.getTaskLimitTime().getTime() - t.getTaskRemindHour() * 60 * 60 * 1000));

                for (int i = 0; i < receivers.length ; i++) {
                    t.setTaskReceiver(receivers[i]);
                    t.setTaskStatus(TASK_TOACCEPT);
                    if(t.getTaskCreator().equals(t.getTaskReceiver())){
                        t.setTaskStatus(TASK_DOING);
                        taskDao.addTask(t);
                        break;
                    }
                    taskDao.addTask(t);
                    dingMessageUtils.newTaskMessage(t);
                }
            }
        }
    }

    /**
     *@Author: 白驹
     *@Date: 2019/1/10 17:39
     *@Description: 由于增加新需求：重复任务发布前让接收人确认，所以增加 t.setTaskFatherSon("father_son"); 状态，提前对父任务进行拆分
     */
    public void addRepeatFatherSonTask(Task t) throws Exception{
        String cron = null;
        String repeatChinese= null;

        String[] repeatWay =t.getTaskRepeatWay().split(",");
        switch (Integer.parseInt(repeatWay[0])){
            case 1:
                String weekDay = repeatWay[1];
                String hour1 = repeatWay[3].substring(0,2);
                String minute1 = repeatWay[3].substring(3,5);
                repeatChinese = "每周" + weekDay + "," +  repeatWay[3];
                //0 15 10 ? * MON
                cron = "0 "+ minute1 +" " + hour1 + " ? * " + weekDay;
                break;
            case 2:
                String monthDay = repeatWay[2];
                String hour2 = repeatWay[3].substring(0,2);
                String minute2 = repeatWay[3].substring(3,5);
                //0 15 10 15 * ?
                cron = "0 "+ minute2 +" " + hour2 + " " + monthDay +" * ?";
                repeatChinese = "每月" + monthDay + "日，" +  repeatWay[3];
                break;
            case 3:
                String hour3 = repeatWay[3].substring(0,2);
                String minute3 = repeatWay[3].substring(3,5);
                //0 15 10 * * ?
                cron = "0 "+ minute3 + " " + hour3 + " * * ?";
                repeatChinese = "每天："+  repeatWay[3];
                break;
            default:
        }

        t.setTaskCron(cron);
        t.setTaskRepeatChinese(repeatChinese);
        t.setTaskFatherSon("father_son");
        t.setSonTaskRepeatNum(0);

        //生成receiver数组，循环插入任务记录
        String[] receiver =t.getTaskReceiver().split(",");
        for (int i = 0; i < receiver.length ; i++) {
            t.setTaskReceiver(receiver[i]);
            if(t.getTaskCreator().equals(t.getTaskReceiver())){
                t.setTaskStatus(TASK_TOCREATE);
                taskDao.addTask(t);
                quartzService.setTime(t);
            }else{
                t.setTaskStatus(TASK_TOACCEPT);
                taskDao.addTask(t);
                dingMessageUtils.newTaskMessage(t);
            }
        }
    }

    @Override
    public void acceptOrRefuseTask(Task t) throws Exception {
        Task t1 = taskDao.selectTaskByRecordId(t.getTaskRecordId());

        if(TASK_ACCEPTED.equals(t.getTaskStatus())){

            if(LIMIT_TASK.equals(t1.getTaskType())){
                t1.setTaskStatus(TASK_DOING);
            }else if(REPEAT_TASK.equals(t1.getTaskType())){
                t1.setTaskStatus(TASK_TOCREATE);
                quartzService.setTime(t1);
            }
            taskDao.updateTask(t1);
            dingMessageUtils.acceptNewTaskMessage(t1);

        }else if(TASK_REFUSED.equals(t.getTaskStatus())){
            t1.setTaskStatus(TASK_REFUSED);
            t1.setTaskRefuseComment(t.getTaskRefuseComment());
            taskDao.updateTask(t1);
            dingMessageUtils.refuseNewTaskMessage(t1);
        }
    }

    @Override
    public void reportTaskByTaskRecordId(Task t){

        Task t1 = taskDao.selectTaskByRecordId(t.getTaskRecordId());
        t1.setTaskReportTime(new Date());
        t1.setTaskReport(t.getTaskReport());

        if(t1.getTaskCreator().equals(t1.getTaskReceiver())){
            t1.setTaskStatus(TASK_DONE);
        }else{
            t1.setTaskStatus(TASK_TOAUDIT);
            dingMessageUtils.reportTaskMessage(t1);
        }

        taskDao.updateTask(t1);
    }

    @Override
    public void auditTask(Task t) {
        Task t1 = taskDao.selectTaskByRecordId(t.getTaskRecordId());
        t1.setTaskAuditTime(new Date());

        if(TASK_DONE.equals(t.getTaskStatus())){
            t1.setTaskStatus(TASK_DONE);
            taskDao.updateTask(t1);
            dingMessageUtils.passTaskMessage(t1);
        }else if(TASK_NOPASS.equals(t.getTaskStatus())){
            t1.setTaskStatus(TASK_DOING);
            t1.setTaskRefuseComment(t.getTaskRefuseComment());
            taskDao.updateTask(t1);
            dingMessageUtils.refuseTaskMessage(t1);
        }
    }

    @Override
    public void cancelTaskByTaskRecordId(Integer taskRecordId) throws SchedulerException{
        TriggerKey triggerKey = TriggerKey.triggerKey(taskRecordId +"Trigger", "createRepeatTaskTriggerGroup");
        //停止定时器
        stdScheduler.pauseTrigger(triggerKey);
        //移除定时器
        stdScheduler.unscheduleJob(triggerKey);
        stdScheduler.pauseJob(JobKey.jobKey(taskRecordId +"Job","createRepeatTaskJobGroup"));
        taskDao.deleteTaskByRecordId(taskRecordId);
    }

    @Override
    public void cancelTaskByTaskId(Integer taskId) throws SchedulerException {
        List<Task> list = taskDao.listTaskByTaskId(taskId);
        for (Task t:list) {
            cancelTaskByTaskRecordId(t.getTaskRecordId());
        }
    }

    @Override
    public void appealTask(Integer taskRecordId) {
        Task t = taskDao.selectTaskByRecordId(taskRecordId);
        t.setTaskStatus("申诉中");
        taskDao.updateTask(t);
        dingMessageUtils.taskAppealMessage(t);
    }

    @Override
    public void auditAppealTask(Task t) {
        Task t1 = taskDao.selectTaskByRecordId(t.getTaskRecordId());

        if(TASK_DONE.equals(t.getTaskStatus())){
            t1.setTaskStatus(TASK_DONE);
            taskDao.updateTask(t1);
            Sunshine s = sunshineDao.selectSunshineByTaskRecordId(t.getTaskRecordId().toString());
            s.setSunshineNewAdminNickname(t1.getTaskCreator());
            s.setSunshineActionComment("申诉通过");
            sunshineService.delMinusSunshine(s);
            dingMessageUtils.passAppealTaskMessage(t1);
        }else if(TASK_NOPASS.equals(t.getTaskStatus())){
            t1.setTaskNoPassComment(t.getTaskNoPassComment());
            t1.setTaskStatus("申诉被拒绝");
            taskDao.updateTask(t1);
            dingMessageUtils.refuseAppealTaskMessage(t1);
        }
    }

    @Override
    public List<Task> listToAcceptTaskByReceiver(String taskReceiver) {
        return taskDao.listToAcceptTaskByReceiver(taskReceiver);
    }

    @Override
    public List<Task> listToCreateTaskByReceiver(String taskReceiver) {
        return taskDao.listToCreateTaskByReceiver(taskReceiver);
    }

    @Override
    public List<Task> listDoingTaskByReceiver(String taskReceiver) {
        return taskDao.listDoingTaskByReceiver(taskReceiver);
    }

    @Override
    public List<Task> listToAuditTaskByCreator(String taskCreator) {
        return taskDao.listToAuditTaskByCreator(taskCreator);
    }

    @Override
    public List<Task> listAppealToAuditTaskByCreator(String taskCreator) {
        return taskDao.listAppealToAuditTaskByCreator(taskCreator);
    }

    @Override
    public List<Task> listSonTaskByTaskId(Integer taskId) {
        return taskDao.listSonTaskByTaskId(taskId);
    }

    @Override
    public List<Task> listDoneTaskByReceiver(String taskReceiver) {
        return taskDao.listDoneTaskByReceiver(taskReceiver);
    }

    @Override
    public List<Task> listTimeOutTaskByReceiver(String taskReceiver) {
        return taskDao.listTimeOutTaskByReceiver(taskReceiver);
    }

    @Override
    public List<Task> listFatherTaskByCreator(String taskCreator) {
        return taskDao.listFatherTaskByCreator(taskCreator);
    }
}
