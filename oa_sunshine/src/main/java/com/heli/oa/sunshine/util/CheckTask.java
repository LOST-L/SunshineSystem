package com.heli.oa.sunshine.util;

import com.heli.oa.common.dao.UserDao;
import com.heli.oa.common.entity.EmployeeInfo;
import com.heli.oa.common.util.ExceptionResolverUtil;
import com.heli.oa.sunshine.dao.SunshineDao;
import com.heli.oa.sunshine.dao.TaskDao;
import com.heli.oa.sunshine.entity.Sunshine;
import com.heli.oa.sunshine.entity.Task;
import com.heli.oa.sunshine.service.SunshineService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Date;
import java.util.List;

/**
 * @desc 定时检查任务状态，超时第一次邮件通知，第二次扣星
 * @author 白驹
 */
@Slf4j
@Component
@EnableScheduling
public class CheckTask {

    @Autowired
    UserDao userDao;
    @Autowired
    TaskDao taskDao;
    @Autowired
    SunshineDao sunshineDao;
    @Autowired
    SunshineService sunshineService;
    @Autowired
    MailUtil mailUtil;
    @Autowired
    DingMessageUtils dingMessageUtils;

    /**
     * 每5分钟扫描，当前时间大于提醒时间就发通知
     * 超过一小时未审核，自动通过
     * */
    @Scheduled(cron = "0 0/5 * * * ? ")    //cron =
    public void remindCheck(){
        List<Task> taskList = taskDao.listTaskByStatus("进行中");
        List<Task> taskList1 = taskDao.listTaskByStatus("待审核");
        Long nowTime = System.currentTimeMillis();
        log.info(taskList.toString());
        if(taskList.size()>0){
            for (Task t : taskList) {
                //t.getReminded通知发送状态值，未发送未0，已发送设1
                if ((nowTime - t.getTaskRemindTime().getTime()>0) && t.getTaskReminded() == 0) {
                    dingMessageUtils.remindTaskMessage(t);
                    t.setTaskReminded(1);
                    taskDao.updateTask(t);
                }
                if((taskList.indexOf(t) % 17 == 0) && (taskList.indexOf(t) > 0)){
                    try
                    {
                        Thread.sleep(65 * 1000);
                    }
                    catch (InterruptedException e)
                    {
                        e.printStackTrace();
                    }
                }
            }
        }

        if (taskList1.size()>0){
            for (Task t : taskList1) {
                if (nowTime - t.getTaskReportTime().getTime()>60*60*1000) {
                    t.setTaskStatus("已完成");
                    taskDao.updateTask(t);
                    dingMessageUtils.autoPassTaskMessage(t);
                }
                if((taskList.indexOf(t) % 17 == 0) && (taskList.indexOf(t) > 0)){
                    try
                    {
                        Thread.sleep(65 * 1000);
                    }
                    catch (InterruptedException e)
                    {
                        e.printStackTrace();
                    }
                }
            }
        }

        log.info("任务提醒扫描结束");
    }

    /**
     * 周一至周四，每天晚上5点30检测，提交期限在当天晚上6点，到次日上午10点前的任务，发出提醒
     * */
    @Scheduled(cron = "0 45 17 ? * MON-THU")    //cron =
    public void workdayRemindCheck(){
        List<Task> taskList = taskDao.listTaskByStatus("进行中");
        Long nowTime = System.currentTimeMillis();
        Long startTime = Long.valueOf(30 * 60 * 1000);
        Long endTime = Long.valueOf(16 * 60 * 60 * 1000);

        for (Task t : taskList) {
            Long timeDifference = t.getTaskLimitTime().getTime() - nowTime;
            if (startTime <= timeDifference && timeDifference <=endTime) {
                dingMessageUtils.workdayTaskMessage(t);
            }
            if((taskList.indexOf(t) % 17 == 0) && (taskList.indexOf(t) > 0)){
                try
                {
                    Thread.sleep(65 * 1000);
                }
                catch (InterruptedException e)
                {
                    e.printStackTrace();
                }
            }
        }
        log.info("任务状态检测-->工作日任务状态检测-->结束。。。。");
    }

    /**
     * 周五晚上5点30检测，周五晚上6点至周一早上10点的任务，并发出提醒
     * */
    @Scheduled(cron = "0 45 17 ? * FRI")  //cron =
    public void weekendRemindCheck() {
        List<Task> taskList = taskDao.listTaskByStatus("进行中");
        Long nowTime = System.currentTimeMillis();
        //周五晚5:30-周五晚6点
        Long startTime = Long.valueOf(30 * 60 * 1000);
        //周五晚5:30-周一早10点
        Long endTime = Long.valueOf((24 + 24 + 16) * 60 * 60 * 1000);

        for (Task t : taskList) {
            Long timeDifference = t.getTaskLimitTime().getTime() - nowTime;
            boolean existed =  (startTime <= timeDifference && timeDifference <=endTime) && t.getTaskPunishment() != 2;
            if (existed) {
                dingMessageUtils.weekendTaskMessage(t);
            }

            //由于钉钉机器人限制每分钟最多发送20条消息，所以加上延时
            if((taskList.indexOf(t) % 17 == 0) && (taskList.indexOf(t) > 0)){
                try
                {
                    Thread.sleep(65 * 1000);
                }
                catch (InterruptedException e)
                {
                    e.printStackTrace();
                }
            }
        }
        log.info("任务状态检测-->周末任务状态检测-->结束。。。。");
    }

    /**
     * 周一至周五，每天晚上17点55检测，检测市场部当天任务
     * */
    @Scheduled(cron = "0 55 17 ? * MON-FRI")    //cron =
    public void marketingDepDayTaskTimeoutCheck(){
        log.info("任务状态检测-->市场部工作日任务状态检测-->开始。。。。");

        List<Task> taskList = taskDao.listTaskByStatus("进行中");
        Long nowTime = System.currentTimeMillis();
        Long startTime = Long.valueOf(30 * 60 * 1000);
        Long endTime = Long.valueOf(16 * 60 * 60 * 1000);

        for (Task t : taskList) {
            Long timeDifference = t.getTaskLimitTime().getTime() - nowTime;
            boolean existed = (startTime <= timeDifference && timeDifference <=endTime ) && t.getTaskPunishment() == 1;
            if (existed) {
                dingMessageUtils.marketingDepDayTaskTimeoutMessage(t);
                t.setTaskStatus("已通知加班");
                taskDao.updateTask(t);
            }
            if((taskList.indexOf(t) % 17 == 0) && (taskList.indexOf(t) > 0)){
                try
                {
                    Thread.sleep(65 * 1000);
                }
                catch (InterruptedException e)
                {
                    e.printStackTrace();
                }
            }
        }
        log.info("任务状态检测-->市场部工作日任务状态检测-->结束。。。。");
    }

    /**
     * 周五，每天晚上17点55检测，检测市场部本周任务，未完成则提示周末加班
     * */
    @Scheduled(cron = "0 55 17 ? * FRI")    //cron =
    public void marketingDepWeekTaskTimeoutCheck(){
        log.info("任务状态检测-->市场部周任务状态检测-->开始。。。。");

        List<Task> taskList = taskDao.listTaskByStatus("进行中");
        Long nowTime = System.currentTimeMillis();
        Long startTime = Long.valueOf(30 * 60 * 1000);
        Long endTime = Long.valueOf(16 * 60 * 60 * 1000);

        for (Task t : taskList) {
            Long timeDifference = t.getTaskLimitTime().getTime() - nowTime;
            boolean existed = (startTime <= timeDifference && timeDifference <=endTime ) && t.getTaskPunishment() == 2;
            if (existed) {
                dingMessageUtils.marketingDepWeekTaskTimeoutMessage(t);
                t.setTaskStatus("已通知加班");
                taskDao.updateTask(t);
            }
            if((taskList.indexOf(t) % 17 == 0) && (taskList.indexOf(t) > 0)){
                try
                {
                    Thread.sleep(65 * 1000);
                }
                catch (InterruptedException e)
                {
                    e.printStackTrace();
                }
            }
        }
        log.info("任务状态检测-->市场部周任务状态检测-->结束。。。。");
    }

    /**
     *
     * */
    @Scheduled(cron = "0 55 17 ? * FRI")    //cron =
    public void minusNSunshineTaskTimeoutCheck(){
        log.info("任务状态检测-->扣除多颗阳光值任务状态检测-->开始。。。。");

        List<Task> taskList = taskDao.listTaskByStatus("进行中");
        Long nowTime = System.currentTimeMillis();
        Long startTime = Long.valueOf(30 * 60 * 1000);
        Long endTime = Long.valueOf(16 * 60 * 60 * 1000);

        if (taskList.size()>0){
            for (Task t : taskList) {
                Long timeDifference = t.getTaskLimitTime().getTime() - nowTime;
                boolean existed = (startTime <= timeDifference && timeDifference <=endTime ) && t.getTaskPunishment() == 2;
                if (existed) {
                    dingMessageUtils.marketingDepWeekTaskTimeoutMessage(t);
                }
                if((taskList.indexOf(t) % 17 == 0) && (taskList.indexOf(t) > 0)){
                    try
                    {
                        Thread.sleep(65 * 1000);
                    }
                    catch (InterruptedException e)
                    {
                        e.printStackTrace();
                    }
                }
            }
        }
        log.info("任务状态检测-->扣除多颗阳光值任务状态检测-->结束。。。。");
    }

    /**
     * 每天9-21点每小时检测，是否有任务超时，第一次通知，第二次扣星
     * @throws Exception
     */
    @Scheduled(cron = "0 0 9-20 * * ?") //
    public void checkTimeout(){
        log.info("任务状态检测-->超时任务状态检测-->开始。。。。");
        List<Task> list1 = taskDao.listTaskByStatus("进行中");
        List<Task> list2 = taskDao.listTaskByStatus("待处理");

        Long nowTime = System.currentTimeMillis();
        for (Task t : list1) {
            boolean existed = (nowTime >= t.getTaskLimitTime().getTime()) && (t.getTaskPunishment() != 1 && t.getTaskPunishment() != 2);
            if (existed) {
                t.setTaskStatus("待处理");
                taskDao.updateTask(t);
                dingMessageUtils.timeoutTaskMessage(t);
            }

            //由于钉钉机器人限制每分钟最多发送20条消息，所以加上延时
            if((list1.indexOf(t) % 17 == 0) && (list1.indexOf(t) > 0)){
                try
                {
                    Thread.sleep(65 * 1000);
                }
                catch (InterruptedException e)
                {
                    e.printStackTrace();
                }
            }
        }

        for (Task t : list2) {
            t.setTaskStatus("已逾期");
            t.setTaskTimeoutTime(new Date());
            taskDao.updateTask(t);

            Sunshine sunshine = new Sunshine();
            sunshine.setUserNickname(t.getTaskReceiver());
            //将任务记录ID存入扣除原因，申诉通过的时候取用。
            sunshine.setSunshineComment(t.getTaskRecordId().toString());
            sunshine.setSunshineCommentType("任务超时");
            sunshine.setAdminNickname("系统");
            try {
                if(t.getTaskPunishment() == 0 ){
                    sunshineService.minusSunshine(sunshine);
                }else{
                    sunshineService.minusSunshineN(sunshine,t);
                }
            } catch (IOException e) {
                ExceptionResolverUtil.errorMessageToDing(e);
            }

            //由于钉钉机器人限制每分钟最多发送20条消息，所以加上延时
            if((list2.indexOf(t) % 17 == 0) && (list2.indexOf(t) > 0)){
                try
                {
                    Thread.sleep(65 * 1000);
                }
                catch (InterruptedException e)
                {
                    e.printStackTrace();
                }
            }
        }
        log.info("任务状态检测-->超时任务状态检测-->结束。。。。");
    }

    /**
     * 每天9-21点每小时检测，是否有未被接受任务
     * @throws Exception
     */
    @Scheduled(cron = "0 0/30 9-20 * * ?") // @Scheduled(cron = "0 0 9-20 * * ?")
    public void checkNoAccept(){
        log.info("待接受任务状态检测-->开始。。。。");
        List<Task> list1 = taskDao.listTaskByStatus("待接受");

        if(list1.size()>0){
            for (Task t : list1) {
                if(System.currentTimeMillis()-t.getTaskCreateTime().getTime()>65*60*1000){
                    if(t.getTaskType().equals("重复任务")){
                        t.setTaskStatus("待发布");
                    }else{
                        t.setTaskStatus("进行中");
                    }
                    taskDao.updateTask(t);
                    dingMessageUtils.taskAcceptedBySystem(t);
                }else{
                    dingMessageUtils.newTaskNoAcceptMessage(t);
                }

                //由于钉钉机器人限制每分钟最多发送20条消息，所以加上延时
                if((list1.indexOf(t) % 17 == 0) && (list1.indexOf(t) > 0)){
                    try
                    {
                        Thread.sleep(65 * 1000);
                    }
                    catch (InterruptedException e)
                    {
                        e.printStackTrace();
                    }
                }
            }
        }

        log.info("待接受任务状态检测-->结束。。。。");
    }

    /**
     * 每天8-22点每小时检测，是否有逾期任务，时间差超过24小时就扣星
     * @throws Exception
     */
    @Scheduled(cron = "0 0 8-22 * * ?" ) //
    public void checkTimeoutNum() throws Exception {
        log.info("任务状态检测-->每逾期24小时扣星-->开始。。。。");
        List<Task> list = taskDao.listTaskByStatus("已逾期");

        Long nowTime = System.currentTimeMillis();

        for (Task t : list) {
            if(nowTime - t.getTaskTimeoutTime().getTime() >= 24 * 60 * 60 * 1000){
                t.setTaskTimeoutTime(new Date());
                taskDao.updateTask(t);

                Sunshine sunshine = new Sunshine();
                sunshine.setUserNickname(t.getTaskReceiver());
                sunshine.setSunshineComment(t.getTaskRecordId() + "  任务再次逾期一天");
                sunshine.setSunshineCommentType("任务超时");
                sunshine.setAdminNickname("系统");
                sunshineService.minusSunshine(sunshine);
            }

            //由于钉钉机器人限制每分钟最多发送20条消息，所以加上延时
            if((list.indexOf(t) % 17 == 0) && (list.indexOf(t) > 0)){
                try
                {
                    Thread.sleep(65 * 1000);
                }
                catch (InterruptedException e)
                {
                    e.printStackTrace();
                }
            }
        }
        log.info("任务状态检测-->每逾期24小时扣星-->结束。。。。");
    }
}
