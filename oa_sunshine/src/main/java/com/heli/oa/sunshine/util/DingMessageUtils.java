package com.heli.oa.sunshine.util;

import com.heli.oa.common.dao.UserDao;
import com.heli.oa.common.util.ExceptionResolverUtil;
import com.heli.oa.sunshine.entity.Sunshine;
import com.heli.oa.sunshine.entity.Task;
import com.heli.oa.common.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;

/**
 *@Author: 白驹 on 2019/1/10 14:12
 *@param:
 *@return:
 *@Description:
 */
@Slf4j
@Service
public class DingMessageUtils {

    @Autowired
    UserDao userDao;

    @Value("${WEBHOOK_TOKEN_dianruan}")
    String webhook_token_dianruan;

    @Value("${WEBHOOK_TOKEN_shichang}")
    String webhook_token_shichang;

    @Value("${WEBHOOK_TOKEN_peixun}")
    String webhook_token_peixun;

    @Value("${WEBHOOK_TOKEN_yunying}")
    String webhook_token_yunying;

    @Value("${WEBHOOK_TOKEN_tuiguang}")
    String webhook_token_tuiguang;

    /**钉钉大群，阳光值增减通知用，测试环境为电软部小群链接*/
    private static String WEBHOOK_TOKEN_ALL;


    @Value("${WEBHOOK_TOKEN_ALL}")
    public void setWEBHOOK_TOKEN_ALL(String webhook_token_all){
        WEBHOOK_TOKEN_ALL = webhook_token_all;
    }

    /**
     * 根据部门设置钉钉机器人api
     */
    private static String WEBHOOK_TOKEN_Dep;

    public void setWEBHOOK_TOKEN_Dep(String department) {
        switch (department){
            case "电软部":
                WEBHOOK_TOKEN_Dep = webhook_token_dianruan;
                break;
            case "市场部":
                WEBHOOK_TOKEN_Dep = webhook_token_shichang;
                break;
            case "培训部":
                WEBHOOK_TOKEN_Dep = webhook_token_peixun;
                break;
            case "运营部":
                WEBHOOK_TOKEN_Dep = webhook_token_yunying;
                break;
            case "市场部-推广":
                WEBHOOK_TOKEN_Dep = webhook_token_tuiguang;
                break;
            default:
                WEBHOOK_TOKEN_Dep = WEBHOOK_TOKEN_ALL;
        }
    }

    /**
     *@Author: 白驹 on 2019/1/10 14:14
     *@param:
     *@return:
     *@Description: 奖励阳光值通知
     */
    public void addSunshine(Sunshine s)   {
        HttpClient httpclient = HttpClients.createDefault();

        HttpPost httppost = new HttpPost(WEBHOOK_TOKEN_ALL);

        httppost.addHeader("Content-Type", "application/json; charset=utf-8");

        String textMsg = "{\n" +
                "     \"msgtype\": \"text\",\n" +
                "     \"text\": {\n" +
                "         \"content\": \"由于  "+s.getSunshineCommentType()+"  "+ s.getUserNickname() + "  被奖励一枚阳光值！\"\n" +
                "     },\n" +
                "     \"at\": {\n" +
                "         \"isAtAll\": true\n" +
                "     }\n" +
                " }";
        StringEntity se = new StringEntity(textMsg, "utf-8");
        httppost.setEntity(se);

        try {
            HttpResponse response = httpclient.execute(httppost);
            if (response.getStatusLine().getStatusCode()== HttpStatus.SC_OK){
                String result= EntityUtils.toString(response.getEntity(), "utf-8");
                log.info(s.getSunshineRecordId()+","+s.getUserNickname()+","+s.getSunshineCommentType()+","+s.getSunshineComment()+"被奖励一枚阳光值！发送结果："+result);
            }
        } catch (IOException e) {
            ExceptionResolverUtil.errorMessageToDing(e);
        }
    }

    /**
     *@Author: 白驹 on 2019/1/10 14:20
     *@param: 
     *@return: 
     *@Description: 扣除阳光值钉钉通知
     */
    public void minusSunshine(Sunshine s)   {

        HttpClient httpclient = HttpClients.createDefault();

        HttpPost httppost = new HttpPost(WEBHOOK_TOKEN_ALL);
        httppost.addHeader("Content-Type", "application/json; charset=utf-8");

        String textMsg = "{\n" +
                "     \"msgtype\": \"text\",\n" +
                "     \"text\": {\n" +
                "         \"content\": \"由于  "+s.getSunshineCommentType()+"，"+ s.getUserNickname() + "  被扣除一枚阳光值！\"\n" +
                "     },\n" +
                "     \"at\": {\n" +
                "         \"atMobiles\": [\n" +
                "             \"18330107406\"\n" +
                "         ], \n" +

                "         \"isAtAll\": true\n" +
                "     }\n" +
                " }";
        StringEntity se = new StringEntity(textMsg, "utf-8");
        httppost.setEntity(se);

        try {
            HttpResponse response = httpclient.execute(httppost);
            if (response.getStatusLine().getStatusCode()== HttpStatus.SC_OK){
                String result= EntityUtils.toString(response.getEntity(), "utf-8");
                log.info(s.getSunshineRecordId()+","+s.getUserNickname()+","+s.getSunshineCommentType()+","+s.getSunshineComment()+"被扣除一枚阳光值！发送结果："+result);
            }
        } catch (IOException e) {
            ExceptionResolverUtil.errorMessageToDing(e);
        }
    }

    /**
     *@Author: 白驹 on 2019/1/10 15:21
     *@param: 
     *@return: 
     *@Description:  扣除多颗阳光值钉钉通知
     */
    public void minusSunshineN(Sunshine s,Task t)   {

        HttpClient httpclient = HttpClients.createDefault();

        HttpPost httppost = new HttpPost(WEBHOOK_TOKEN_ALL);
        httppost.addHeader("Content-Type", "application/json; charset=utf-8");

        String textMsg = "{\n" +
                "     \"msgtype\": \"text\",\n" +
                "     \"text\": {\n" +
                "         \"content\": \"由于  "+s.getSunshineCommentType()+"，"+ s.getUserNickname() + "  被扣除"+ t.getTaskPunishment() +"枚阳光值！\"\n" +
                "     },\n" +
                "     \"at\": {\n" +
                "         \"isAtAll\": true\n" +
                "     }\n" +
                " }";
        StringEntity se = new StringEntity(textMsg, "utf-8");
        httppost.setEntity(se);

        try {
            HttpResponse response = httpclient.execute(httppost);
            if (response.getStatusLine().getStatusCode()== HttpStatus.SC_OK){
                String result= EntityUtils.toString(response.getEntity(), "utf-8");
                log.info(s.getSunshineRecordId()+","+s.getUserNickname()+","+s.getSunshineCommentType()+","+s.getSunshineComment()+"被扣除一枚阳光值！发送结果："+result);
            }
        } catch (IOException e) {
            ExceptionResolverUtil.errorMessageToDing(e);
        }

    }

    /**
     *@Author: 白驹 on 2019/1/10 14:35
     *@param: 
     *@return: 
     *@Description: 新任务通知，带阳光值链接
     */
    public void newTaskMessage(Task t)   {

        String textMsg;

        User receiver = userDao.getUserByNickname(t.getTaskReceiver());
        setWEBHOOK_TOKEN_Dep(receiver.getUserDepartment());

        HttpClient httpclient = HttpClients.createDefault();

        HttpPost httppost = new HttpPost(WEBHOOK_TOKEN_Dep);
        httppost.addHeader("Content-Type", "application/json; charset=utf-8");
        if ("单次任务".equals(t.getTaskType())){
            textMsg = "{\n" +
                    "     \"msgtype\": \"text\",\n" +
                    "     \"text\": {\n" +
                    "         \"content\":\""+ "新任务提醒@" + receiver.getUserPhone()
                    +" \n任务ID："+t.getTaskId()
                    +" \n创建人："+t.getTaskCreator()
                    + "\n任务类型：" + t.getTaskType()
                    + "\n任务标题：" + t.getTaskTitle() +
                    "    \" },\n" +
                    "     \"at\": {\n" +
                    "         \"atMobiles\": [\n" +
                    receiver.getUserPhone() +
                    "         ], \n" +
                    "         \"isAtAll\": false\n" +
                    "     }\n" +
                    " }";
        }else{
            textMsg = "{\n" +
                    "     \"msgtype\": \"text\",\n" +
                    "     \"text\": {\n" +
                    "         \"content\":\""+ "新任务提醒\n请登陆 http://oa.helidianshang.com 查看接收\n@" + receiver.getUserPhone()
                    +" \n任务ID："+t.getTaskId()
                    +" \n创建人："+t.getTaskCreator()
                    + "\n任务类型：" + t.getTaskType()
                    + "\n任务标题：" + t.getTaskTitle() +
                    "    \" },\n" +
                    "     \"at\": {\n" +
                    "         \"atMobiles\": [\n" +
                    receiver.getUserPhone() +
                    "         ], \n" +
                    "         \"isAtAll\": false\n" +
                    "     }\n" +
                    " }";
        }

        StringEntity se = new StringEntity(textMsg, "utf-8");
        httppost.setEntity(se);

        try {
            HttpResponse response = httpclient.execute(httppost);
            if (response.getStatusLine().getStatusCode()== HttpStatus.SC_OK){
                String result= EntityUtils.toString(response.getEntity(), "utf-8");
                log.info("新任务通知成功："+ t.getTaskId()+",接收人："+t.getTaskReceiver());
            }
        } catch (IOException e) {
            ExceptionResolverUtil.errorMessageToDing(e);
        }
    }

    /**
     *@Author: 白驹 on 2019/1/10 14:36
     *@param:
     *@return:
     *@Description: 重复任务新任务发布通知
     */
    public void newRepeatTaskMessage(Task t)   {
        String textMsg;

        User receiver = userDao.getUserByNickname(t.getTaskReceiver());
        setWEBHOOK_TOKEN_Dep(receiver.getUserDepartment());

        HttpClient httpclient = HttpClients.createDefault();

        HttpPost httppost = new HttpPost(WEBHOOK_TOKEN_Dep);
        httppost.addHeader("Content-Type", "application/json; charset=utf-8");

        textMsg = "{\n" +
                "     \"msgtype\": \"text\",\n" +
                "     \"text\": {\n" +
                "         \"content\":\""+ "新任务发布提醒\n请登陆 http://oa.helidianshang.com 查看\n@" + receiver.getUserPhone()
                +" \n任务ID："+t.getTaskId()
                +" \n创建人："+t.getTaskCreator()
                + "\n任务类型：" + t.getTaskType()
                + "\n任务标题：" + t.getTaskTitle() +
                "    \" },\n" +
                "     \"at\": {\n" +
                "         \"atMobiles\": [\n" +
                receiver.getUserPhone() +
                "         ], \n" +
                "         \"isAtAll\": false\n" +
                "     }\n" +
                " }";

        StringEntity se = new StringEntity(textMsg, "utf-8");
        httppost.setEntity(se);

        try {
            HttpResponse response = httpclient.execute(httppost);
            if (response.getStatusLine().getStatusCode()== HttpStatus.SC_OK){
                String result= EntityUtils.toString(response.getEntity(), "utf-8");
                log.info("新任务通知成功："+ t.getTaskId()+",接收人："+t.getTaskReceiver());
            }
        } catch (IOException e) {
            ExceptionResolverUtil.errorMessageToDing(e);
        }
    }

    /**
     *@Author: 白驹 on 2019/1/10 15:12
     *@param:
     *@return:
     *@Description: 任务到期前提醒，按照自定义时间
     */
    public void remindTaskMessage(Task t)   {

        String textMsg;

        User receiver = userDao.getUserByNickname(t.getTaskReceiver());
        setWEBHOOK_TOKEN_Dep(receiver.getUserDepartment());

        HttpClient httpclient = HttpClients.createDefault();

        HttpPost httppost = new HttpPost(WEBHOOK_TOKEN_Dep);
        httppost.addHeader("Content-Type", "application/json; charset=utf-8");
        textMsg = "{\n" +
                "     \"msgtype\": \"text\",\n" +
                "     \"text\": {\n" +
                "         \"content\":\""+ "任务即将到期提醒：\n@" + receiver.getUserPhone()
                +" \n任务ID："+t.getTaskId()
                +" \n创建人："+t.getTaskCreator()
                + "\n任务类型：" + t.getTaskType()
                + "\n任务标题：" + t.getTaskTitle()
                + "\n提交限期：" + new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(t.getTaskLimitTime()) +
                "    \" },\n" +
                "     \"at\": {\n" +
                "         \"atMobiles\": [\n" +
                receiver.getUserPhone() +
                "         ], \n" +
                "         \"isAtAll\": false\n" +
                "     }\n" +
                " }";
        StringEntity se = new StringEntity(textMsg, "utf-8");
        httppost.setEntity(se);

        try {
            HttpResponse response = httpclient.execute(httppost);
            if (response.getStatusLine().getStatusCode()== HttpStatus.SC_OK){
                String result= EntityUtils.toString(response.getEntity(), "utf-8");
                log.info("自定义时间即将到期通知成功："+ t.getTaskId()+",接收人："+t.getTaskReceiver());
            }
        } catch (IOException e) {
            ExceptionResolverUtil.errorMessageToDing(e);
        }

    }


    /**
     *@Author: 白驹 on 2019/1/10 15:16
     *@param: 
     *@return: 
     *@Description: 周一至周四，每天晚上5点30检测，提交期限在当天晚上6点，到次日上午10点前的任务，发出提醒
     */
    public void workdayTaskMessage(Task t)   {

        String textMsg;

        User receiver = userDao.getUserByNickname(t.getTaskReceiver());
        setWEBHOOK_TOKEN_Dep(receiver.getUserDepartment());

        HttpClient httpclient = HttpClients.createDefault();

        HttpPost httppost = new HttpPost(WEBHOOK_TOKEN_Dep);
        httppost.addHeader("Content-Type", "application/json; charset=utf-8");
        textMsg = "{\n" +
                "     \"msgtype\": \"text\",\n" +
                "     \"text\": {\n" +
                "         \"content\":\""+ "任务即将到期提醒：\n任务提交期限为今晚6:00至明天上午10:00，请尽量在下班前提交任务，以防忘记\n@" + receiver.getUserPhone()
                +" \n任务ID："+t.getTaskId()
                +" \n创建人："+t.getTaskCreator()
                + "\n任务类型：" + t.getTaskType()
                + "\n任务标题：" + t.getTaskTitle()
                + "\n提交限期：" + new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(t.getTaskLimitTime()) +
                "    \" },\n" +
                "     \"at\": {\n" +
                "         \"atMobiles\": [\n" +
                receiver.getUserPhone() +
                "         ], \n" +
                "         \"isAtAll\": false\n" +
                "     }\n" +
                " }";
        StringEntity se = new StringEntity(textMsg, "utf-8");
        httppost.setEntity(se);

        try {
            HttpResponse response = httpclient.execute(httppost);
            if (response.getStatusLine().getStatusCode()== HttpStatus.SC_OK){
                String result= EntityUtils.toString(response.getEntity(), "utf-8");
                log.info("工作日任务即将到期通知成功："+ t.getTaskId()+",接收人："+t.getTaskReceiver());
            }
        } catch (IOException e) {
            ExceptionResolverUtil.errorMessageToDing(e);
        }

    }

    /**
     *@Author: 白驹 on 2019/1/10 15:17
     *@param: 
     *@return: 
     *@Description: 周五晚上5:30检测，周五晚上6点至周一早上10点到期的任务，并发出提醒
     */
    public void weekendTaskMessage(Task t)   {

        String textMsg;

        User receiver = userDao.getUserByNickname(t.getTaskReceiver());
        setWEBHOOK_TOKEN_Dep(receiver.getUserDepartment());

        HttpClient httpclient = HttpClients.createDefault();

        HttpPost httppost = new HttpPost(WEBHOOK_TOKEN_Dep);
        httppost.addHeader("Content-Type", "application/json; charset=utf-8");
        textMsg = "{\n" +
                "     \"msgtype\": \"text\",\n" +
                "     \"text\": {\n" +
                "         \"content\":\""+ "任务即将到期提醒：\n任务提交期限为周五晚6:00至周一上午10:00之间，请尽量在今天下班前提交任务,以防忘记\n@" + receiver.getUserPhone()
                +" \n任务ID："+t.getTaskId()
                +" \n创建人："+t.getTaskCreator()
                + "\n任务类型：" + t.getTaskType()
                + "\n任务标题：" + t.getTaskTitle()
                + "\n提交限期：" + new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(t.getTaskLimitTime()) +
                "    \" },\n" +
                "     \"at\": {\n" +
                "         \"atMobiles\": [\n" +
                receiver.getUserPhone() +
                "         ], \n" +
                "         \"isAtAll\": false\n" +
                "     }\n" +
                " }";
        StringEntity se = new StringEntity(textMsg, "utf-8");
        httppost.setEntity(se);

        try {
            HttpResponse response = httpclient.execute(httppost);
            if (response.getStatusLine().getStatusCode()== HttpStatus.SC_OK){
                String result= EntityUtils.toString(response.getEntity(), "utf-8");
                log.info("周末任务即将到期通知成功："+ t.getTaskId()+",接收人："+t.getTaskReceiver());
            }
        } catch (IOException e) {
            ExceptionResolverUtil.errorMessageToDing(e);
        }

    }

    /**
     *@Author: 白驹 on 2019/1/10 15:18
     *@param: 
     *@return: 
     *@Description: 任务第一次超时通知
     */
    public void timeoutTaskMessage(Task t)   {

        String textMsg;

        User receiver = userDao.getUserByNickname(t.getTaskReceiver());
        setWEBHOOK_TOKEN_Dep(receiver.getUserDepartment());

        HttpClient httpclient = HttpClients.createDefault();

        HttpPost httppost = new HttpPost(WEBHOOK_TOKEN_Dep);
        httppost.addHeader("Content-Type", "application/json; charset=utf-8");
        textMsg = "{\n" +
                "     \"msgtype\": \"text\",\n" +
                "     \"text\": {\n" +
                "         \"content\":\""+ "任务超时到期提醒：\n亲，任务已经超时，请在一小时内提交，否则将会扣除一颗阳光值\n@" + receiver.getUserPhone()
                +" \n任务ID："+t.getTaskId()
                +" \n创建人："+t.getTaskCreator()
                + "\n任务类型：" + t.getTaskType()
                + "\n任务标题：" + t.getTaskTitle()
                + "\n提交限期：" + new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(t.getTaskLimitTime()) +
                "    \" },\n" +
                "     \"at\": {\n" +
                "         \"atMobiles\": [\n" +
                receiver.getUserPhone() +
                "         ], \n" +
                "         \"isAtAll\": false\n" +
                "     }\n" +
                " }";
        StringEntity se = new StringEntity(textMsg, "utf-8");
        httppost.setEntity(se);

        try {
            HttpResponse response = httpclient.execute(httppost);
            if (response.getStatusLine().getStatusCode()== HttpStatus.SC_OK){
                String result= EntityUtils.toString(response.getEntity(), "utf-8");
                log.info("任务超时通知成功："+ t.getTaskId()+",接收人："+t.getTaskReceiver());
            }
        } catch (IOException e) {
            ExceptionResolverUtil.errorMessageToDing(e);
        }

    }

    /**
     *@Author: 白驹 on 2019/1/10 15:18
     *@param: 
     *@return: 
     *@Description: 市场部KPI任务，工作日加班通知
     */
    public void marketingDepDayTaskTimeoutMessage(Task t)   {

        String textMsg;

        User receiver = userDao.getUserByNickname(t.getTaskReceiver());
        setWEBHOOK_TOKEN_Dep(receiver.getUserDepartment());

        HttpClient httpclient = HttpClients.createDefault();

        HttpPost httppost = new HttpPost(WEBHOOK_TOKEN_Dep);
        httppost.addHeader("Content-Type", "application/json; charset=utf-8");
        textMsg = "{\n" +
                "     \"msgtype\": \"text\",\n" +
                "     \"text\": {\n" +
                "         \"content\":\""+ "工作日任务未完成通知：\n今日任务未完成，请加班至晚8点\n@" + receiver.getUserPhone()
                +" \n任务ID："+t.getTaskId()
                +" \n创建人："+t.getTaskCreator()
                + "\n任务类型：" + t.getTaskType()
                + "\n任务标题：" + t.getTaskTitle()
                + "\n提交限期：" + new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(t.getTaskLimitTime()) +
                "    \" },\n" +
                "     \"at\": {\n" +
                "         \"atMobiles\": [\n" +
                receiver.getUserPhone() +
                "         ], \n" +
                "         \"isAtAll\": false\n" +
                "     }\n" +
                " }";
        StringEntity se = new StringEntity(textMsg, "utf-8");
        httppost.setEntity(se);

        try {
            HttpResponse response = httpclient.execute(httppost);
            if (response.getStatusLine().getStatusCode()== HttpStatus.SC_OK){
                String result= EntityUtils.toString(response.getEntity(), "utf-8");
                log.info("市场部工作日任务未完成通知成功："+ t.getTaskId()+",接收人："+t.getTaskReceiver());
            }
        } catch (IOException e) {
            ExceptionResolverUtil.errorMessageToDing(e);
        }
    }

    /**
     *@Author: 白驹 on 2019/1/10 15:19
     *@param: 
     *@return: 
     *@Description: 市场部KPI任务，本周考核未完成，周六加班通知
     */
    public void marketingDepWeekTaskTimeoutMessage(Task t)   {

        String textMsg;

        User receiver = userDao.getUserByNickname(t.getTaskReceiver());
        setWEBHOOK_TOKEN_Dep(receiver.getUserDepartment());

        HttpClient httpclient = HttpClients.createDefault();

        HttpPost httppost = new HttpPost(WEBHOOK_TOKEN_Dep);
        httppost.addHeader("Content-Type", "application/json; charset=utf-8");
        textMsg = "{\n" +
                "     \"msgtype\": \"text\",\n" +
                "     \"text\": {\n" +
                "         \"content\":\""+ "本周任务未完成通知：\n本周考核未达成，请周六加班继续努力！\n@" + receiver.getUserPhone()
                +" \n任务ID："+t.getTaskId()
                +" \n创建人："+t.getTaskCreator()
                + "\n任务类型：" + t.getTaskType()
                + "\n任务标题：" + t.getTaskTitle()
                + "\n提交限期：" + new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(t.getTaskLimitTime()) +
                "    \" },\n" +
                "     \"at\": {\n" +
                "         \"atMobiles\": [\n" +
                receiver.getUserPhone() +
                "         ], \n" +
                "         \"isAtAll\": false\n" +
                "     }\n" +
                " }";
        StringEntity se = new StringEntity(textMsg, "utf-8");
        httppost.setEntity(se);

        try {
            HttpResponse response = httpclient.execute(httppost);
            if (response.getStatusLine().getStatusCode()== HttpStatus.SC_OK){
                String result= EntityUtils.toString(response.getEntity(), "utf-8");
                log.info("市场部周任务未完成通知成功："+ t.getTaskId()+",接收人："+t.getTaskReceiver());
            }
        } catch (IOException e) {
            ExceptionResolverUtil.errorMessageToDing(e);
        }

    }

    /**
     *@Author: 白驹 on 2019/1/10 15:20
     *@param: 
     *@return: 
     *@Description: 新任务被接受通知
     */
    public void acceptNewTaskMessage(Task t)   {

        String textMsg;

        User creator = userDao.getUserByNickname(t.getTaskCreator());
        User receiver = userDao.getUserByNickname(t.getTaskReceiver());
        if(creator.getUserId() == 1){
            setWEBHOOK_TOKEN_Dep(receiver.getUserDepartment());
        }else{
            setWEBHOOK_TOKEN_Dep(creator.getUserDepartment());
        }

        HttpClient httpclient = HttpClients.createDefault();

        HttpPost httppost = new HttpPost(WEBHOOK_TOKEN_Dep);
        httppost.addHeader("Content-Type", "application/json; charset=utf-8");
        textMsg = "{\n" +
                "     \"msgtype\": \"text\",\n" +
                "     \"text\": {\n" +
                "         \"content\":\""+ "新任务被接收提醒：\n@" + creator.getUserPhone()
                +" \n任务ID："+t.getTaskId()
                +" \n完成人："+t.getTaskReceiver()
                + "\n任务类型：" + t.getTaskType()
                + "\n任务标题：" + t.getTaskTitle() +
                "    \" },\n" +
                "     \"at\": {\n" +
                "         \"atMobiles\": [\n" +
                creator.getUserPhone() +
                "         ], \n" +
                "         \"isAtAll\": false\n" +
                "     }\n" +
                " }";
        StringEntity se = new StringEntity(textMsg, "utf-8");
        httppost.setEntity(se);

        try {
            HttpResponse response = httpclient.execute(httppost);
            if (response.getStatusLine().getStatusCode()== HttpStatus.SC_OK){
                String result= EntityUtils.toString(response.getEntity(), "utf-8");
                log.info("新任务被接收通知成功："+ t.getTaskId()+",接收人："+t.getTaskReceiver());
            }
        } catch (IOException e) {
            ExceptionResolverUtil.errorMessageToDing(e);
        }

    }

    /**
     *@Author: 白驹 on 2019/1/10 15:20
     *@param: 
     *@return: 
     *@Description: 新任务被拒绝通知
     */
    public void refuseNewTaskMessage(Task t)   {

        String textMsg;

        User creator = userDao.getUserByNickname(t.getTaskCreator());
        User receiver = userDao.getUserByNickname(t.getTaskReceiver());
        if("蚂蚁".equals(creator)){
            setWEBHOOK_TOKEN_Dep(receiver.getUserDepartment());
        }else{
            setWEBHOOK_TOKEN_Dep(creator.getUserDepartment());
        }

        HttpClient httpclient = HttpClients.createDefault();

        HttpPost httppost = new HttpPost(WEBHOOK_TOKEN_Dep);
        httppost.addHeader("Content-Type", "application/json; charset=utf-8");
        textMsg = "{\n" +
                "     \"msgtype\": \"text\",\n" +
                "     \"text\": {\n" +
                "         \"content\":\""+ "新任务被拒绝提醒：\n@" + creator.getUserPhone()
                +" \n任务ID："+t.getTaskId()
                +" \n完成人："+t.getTaskReceiver()
                + "\n任务类型：" + t.getTaskType()
                + "\n任务标题：" + t.getTaskTitle() +
                "    \" },\n" +
                "     \"at\": {\n" +
                "         \"atMobiles\": [\n" +
                creator.getUserPhone() +
                "         ], \n" +
                "         \"isAtAll\": false\n" +
                "     }\n" +
                " }";
        StringEntity se = new StringEntity(textMsg, "utf-8");
        httppost.setEntity(se);

        try {
            HttpResponse response = httpclient.execute(httppost);
            if (response.getStatusLine().getStatusCode()== HttpStatus.SC_OK){
                String result= EntityUtils.toString(response.getEntity(), "utf-8");
                log.info("新任务被拒绝通知成功："+ t.getTaskId()+",接收人："+t.getTaskReceiver());
            }
        } catch (IOException e) {
            ExceptionResolverUtil.errorMessageToDing(e);
        }

    }

    /**
     *@Author: 白驹 on 2019/1/10 15:20
     *@param: 
     *@return: 
     *@Description: 新任务待接收提醒
     */
    public void newTaskNoAcceptMessage(Task t)   {

        String textMsg;

        User receiver = userDao.getUserByNickname(t.getTaskReceiver());

        setWEBHOOK_TOKEN_Dep(receiver.getUserDepartment());

        HttpClient httpclient = HttpClients.createDefault();

        HttpPost httppost = new HttpPost(WEBHOOK_TOKEN_Dep);
        httppost.addHeader("Content-Type", "application/json; charset=utf-8");
        textMsg = "{\n" +
                "     \"msgtype\": \"text\",\n" +
                "     \"text\": {\n" +
                "         \"content\":\""+ "新任务待接收提醒：\n  @" + receiver.getUserPhone()
                +" \n任务ID："+t.getTaskId()
                +" \n发布人："+t.getTaskCreator()
                + "\n任务类型：" + t.getTaskType()
                + "\n任务标题：" + t.getTaskTitle() +
                "    \" },\n" +
                "     \"at\": {\n" +
                "         \"atMobiles\": [\n" +
                receiver.getUserPhone() +
                "         ], \n" +
                "         \"isAtAll\": false\n" +
                "     }\n" +
                " }";
        StringEntity se = new StringEntity(textMsg, "utf-8");
        httppost.setEntity(se);

        try {
            HttpResponse response = httpclient.execute(httppost);
            if (response.getStatusLine().getStatusCode()== HttpStatus.SC_OK){
                String result= EntityUtils.toString(response.getEntity(), "utf-8");
                log.info("新任务催接收通知成功："+ t.getTaskId()+",接收人："+t.getTaskReceiver());
            }
        } catch (IOException e) {
            ExceptionResolverUtil.errorMessageToDing(e);
        }

    }

    /**
     *@Author: 白驹 on 2019/1/10 15:21
     *@param: 
     *@return: 
     *@Description: 待接收任务超时自动接收
     */
    public void taskAcceptedBySystem(Task t)   {

        String textMsg;

        User receiver = userDao.getUserByNickname(t.getTaskReceiver());

        setWEBHOOK_TOKEN_Dep(receiver.getUserDepartment());

        HttpClient httpclient = HttpClients.createDefault();

        HttpPost httppost = new HttpPost(WEBHOOK_TOKEN_Dep);
        httppost.addHeader("Content-Type", "application/json; charset=utf-8");
        textMsg = "{\n" +
                "     \"msgtype\": \"text\",\n" +
                "     \"text\": {\n" +
                "         \"content\":\""+ "新任务自动接收提醒：\n由于接受人超过一小时未接收任务，系统已经自动接收任务\n@" + receiver.getUserPhone()
                +" \n任务ID："+t.getTaskId()
                +" \n发布人："+t.getTaskCreator()
                + "\n任务类型：" + t.getTaskType()
                + "\n任务标题：" + t.getTaskTitle() +
                "    \" },\n" +
                "     \"at\": {\n" +
                "         \"atMobiles\": [\n" +
                receiver.getUserPhone() +
                "         ], \n" +
                "         \"isAtAll\": false\n" +
                "     }\n" +
                " }";
        StringEntity se = new StringEntity(textMsg, "utf-8");
        httppost.setEntity(se);

        try {
            HttpResponse response = httpclient.execute(httppost);
            if (response.getStatusLine().getStatusCode()== HttpStatus.SC_OK){
                String result= EntityUtils.toString(response.getEntity(), "utf-8");
                log.info("新任务催接收通知成功："+ t.getTaskId()+",接收人："+t.getTaskReceiver());
            }
        } catch (IOException e) {
            ExceptionResolverUtil.errorMessageToDing(e);
        }

    }

    /**
     *@Author: 白驹 on 2019/1/10 15:19
     *@param:
     *@return:
     *@Description: 任务完成通知
     */
    public void reportTaskMessage(Task t)   {

        String textMsg;

        User creator = userDao.getUserByNickname(t.getTaskCreator());
        User receiver = userDao.getUserByNickname(t.getTaskReceiver());
        if(creator.getUserId() == 1){
            setWEBHOOK_TOKEN_Dep(receiver.getUserDepartment());
        }else{
            setWEBHOOK_TOKEN_Dep(creator.getUserDepartment());
        }

        HttpClient httpclient = HttpClients.createDefault();

        HttpPost httppost = new HttpPost(WEBHOOK_TOKEN_Dep);
        httppost.addHeader("Content-Type", "application/json; charset=utf-8");
        textMsg = "{\n" +
                "     \"msgtype\": \"text\",\n" +
                "     \"text\": {\n" +
                "         \"content\":\""+ "任务完成提醒：\n请登陆 http://oa.helidianshang.com 审核任务报告\n@" + creator.getUserPhone()
                +" \n任务ID："+t.getTaskId()
                +" \n完成人："+t.getTaskReceiver()
                + "\n任务类型：" + t.getTaskType()
                + "\n任务标题：" + t.getTaskTitle() +
                "    \" },\n" +
                "     \"at\": {\n" +
                "         \"atMobiles\": [\n" +
                creator.getUserPhone() +
                "         ], \n" +
                "         \"isAtAll\": false\n" +
                "     }\n" +
                " }";
        StringEntity se = new StringEntity(textMsg, "utf-8");
        httppost.setEntity(se);

        try {
            HttpResponse response = httpclient.execute(httppost);
            if (response.getStatusLine().getStatusCode()== HttpStatus.SC_OK){
                String result= EntityUtils.toString(response.getEntity(), "utf-8");
                log.info("任务完成通知成功："+ t.getTaskId()+",接收人："+t.getTaskReceiver());
            }
        } catch (IOException e) {
            ExceptionResolverUtil.errorMessageToDing(e);
        }

    }

    /**
     *@Author: 白驹 on 2019/1/10 15:19
     *@param:
     *@return:
     *@Description: 提交任务审核通过通知
     */
    public void passTaskMessage(Task t)   {

        String textMsg;

        User receiver = userDao.getUserByNickname(t.getTaskReceiver());

        setWEBHOOK_TOKEN_Dep(receiver.getUserDepartment());

        HttpClient httpclient = HttpClients.createDefault();

        HttpPost httppost = new HttpPost(WEBHOOK_TOKEN_Dep);
        httppost.addHeader("Content-Type", "application/json; charset=utf-8");
        textMsg = "{\n" +
                "     \"msgtype\": \"text\",\n" +
                "     \"text\": {\n" +
                "         \"content\":\""+ "任务审核通过提醒：\n@" + receiver.getUserPhone()
                +" \n任务ID："+t.getTaskId()
                +" \n审核人："+t.getTaskCreator()
                + "\n任务类型：" + t.getTaskType()
                + "\n任务标题：" + t.getTaskTitle() +
                "    \" },\n" +
                "     \"at\": {\n" +
                "         \"atMobiles\": [\n" +
                receiver.getUserPhone() +
                "         ], \n" +
                "         \"isAtAll\": false\n" +
                "     }\n" +
                " }";
        StringEntity se = new StringEntity(textMsg, "utf-8");
        httppost.setEntity(se);

        try {
            HttpResponse response = httpclient.execute(httppost);
            if (response.getStatusLine().getStatusCode()== HttpStatus.SC_OK){
                String result= EntityUtils.toString(response.getEntity(), "utf-8");
                log.info("任务审核通过通知成功："+ t.getTaskId()+",接收人："+t.getTaskReceiver());
            }
        } catch (IOException e) {
            ExceptionResolverUtil.errorMessageToDing(e);
        }

    }

    /**
     *@Author: 白驹 on 2019/1/10 15:19
     *@param:
     *@return:
     *@Description: 系统自动审核任务通过通知
     */
    public void autoPassTaskMessage(Task t)   {

        String textMsg;

        User receiver = userDao.getUserByNickname(t.getTaskReceiver());

        setWEBHOOK_TOKEN_Dep(receiver.getUserDepartment());

        HttpClient httpclient = HttpClients.createDefault();

        HttpPost httppost = new HttpPost(WEBHOOK_TOKEN_Dep);
        httppost.addHeader("Content-Type", "application/json; charset=utf-8");
        textMsg = "{\n" +
                "     \"msgtype\": \"text\",\n" +
                "     \"text\": {\n" +
                "         \"content\":\""+ "任务审核通过提醒：\n由于审核人超过一小时未审核，此任务自动通过\n@" + receiver.getUserPhone()
                +" \n任务ID："+t.getTaskId()
                +" \n发布人："+t.getTaskCreator()
                + "\n任务类型：" + t.getTaskType()
                + "\n任务标题：" + t.getTaskTitle() +
                "    \" },\n" +
                "     \"at\": {\n" +
                "         \"atMobiles\": [\n" +
                receiver.getUserPhone() +
                "         ], \n" +
                "         \"isAtAll\": false\n" +
                "     }\n" +
                " }";
        StringEntity se = new StringEntity(textMsg, "utf-8");
        httppost.setEntity(se);

        try {
            HttpResponse response = httpclient.execute(httppost);
            if (response.getStatusLine().getStatusCode()== HttpStatus.SC_OK){
                String result= EntityUtils.toString(response.getEntity(), "utf-8");
                log.info("任务超时未审核自动完成通知成功："+ t.getTaskId()+",接收人："+t.getTaskReceiver());
            }
        } catch (IOException e) {
            ExceptionResolverUtil.errorMessageToDing(e);
        }

    }

    /**
     *@Author: 白驹 on 2019/1/10 15:20
     *@param:
     *@return:
     *@Description: 提交任务审核被拒绝通知
     */
    public void refuseTaskMessage(Task t)   {

        String textMsg;

        User receiver = userDao.getUserByNickname(t.getTaskReceiver());

        setWEBHOOK_TOKEN_Dep(receiver.getUserDepartment());

        HttpClient httpclient = HttpClients.createDefault();

        HttpPost httppost = new HttpPost(WEBHOOK_TOKEN_Dep);
        httppost.addHeader("Content-Type", "application/json; charset=utf-8");
        textMsg = "{\n" +
                "     \"msgtype\": \"text\",\n" +
                "     \"text\": {\n" +
                "         \"content\":\""+ "任务未通过审核提醒：\n任务未通过审核，请登陆 http://oa.helidianshang.com 查看原因\n@" + receiver.getUserPhone()
                +" \n任务ID："+t.getTaskId()
                +" \n审核人："+t.getTaskCreator()
                + "\n任务类型：" + t.getTaskType()
                + "\n任务标题：" + t.getTaskTitle() +
                "    \" },\n" +
                "     \"at\": {\n" +
                "         \"atMobiles\": [\n" +
                receiver.getUserPhone() +
                "         ], \n" +
                "         \"isAtAll\": false\n" +
                "     }\n" +
                " }";
        StringEntity se = new StringEntity(textMsg, "utf-8");
        httppost.setEntity(se);

        try {
            HttpResponse response = httpclient.execute(httppost);
            if (response.getStatusLine().getStatusCode()== HttpStatus.SC_OK){
                String result= EntityUtils.toString(response.getEntity(), "utf-8");
                log.info("任务审核未通过通知成功："+ t.getTaskId()+",接收人："+t.getTaskReceiver());
            }
        } catch (IOException e) {
            ExceptionResolverUtil.errorMessageToDing(e);
        }

    }

    /**
     *@Author: 白驹 on 2019/1/17 15:00
     *@param:
     *@return:
     *@Description: 申诉通过
     */
    public void passAppealTaskMessage(Task t)   {
        String textMsg;

        User receiver = userDao.getUserByNickname(t.getTaskReceiver());

        setWEBHOOK_TOKEN_Dep(receiver.getUserDepartment());

        HttpClient httpclient = HttpClients.createDefault();

        HttpPost httppost = new HttpPost(WEBHOOK_TOKEN_Dep);
        httppost.addHeader("Content-Type", "application/json; charset=utf-8");
        textMsg = "{\n" +
                "     \"msgtype\": \"text\",\n" +
                "     \"text\": {\n" +
                "         \"content\":\""+ "申诉通过提醒：\n@" + receiver.getUserPhone()
                +" \n任务ID："+t.getTaskId()
                +" \n审核人："+t.getTaskCreator()
                + "\n任务类型：" + t.getTaskType()
                + "\n任务标题：" + t.getTaskTitle() +
                "    \" },\n" +
                "     \"at\": {\n" +
                "         \"atMobiles\": [\n" +
                receiver.getUserPhone() +
                "         ], \n" +
                "         \"isAtAll\": false\n" +
                "     }\n" +
                " }";
        StringEntity se = new StringEntity(textMsg, "utf-8");
        httppost.setEntity(se);
        try {
            HttpResponse response = httpclient.execute(httppost);
            if (response.getStatusLine().getStatusCode()== HttpStatus.SC_OK){
                String result= EntityUtils.toString(response.getEntity(), "utf-8");
                log.info("申诉通过提醒："+ t.getTaskId()+",接收人："+t.getTaskReceiver());
            }
        } catch (IOException e) {
            ExceptionResolverUtil.errorMessageToDing(e);
        }
    }

    /**
     *@Author: 白驹 on 2019/1/17 15:00
     *@param:
     *@return:
     *@Description: 申诉未通过
     */
    public void refuseAppealTaskMessage(Task t)   {

        String textMsg;

        User receiver = userDao.getUserByNickname(t.getTaskReceiver());

        setWEBHOOK_TOKEN_Dep(receiver.getUserDepartment());

        HttpClient httpclient = HttpClients.createDefault();

        HttpPost httppost = new HttpPost(WEBHOOK_TOKEN_Dep);
        httppost.addHeader("Content-Type", "application/json; charset=utf-8");
        textMsg = "{\n" +
                "     \"msgtype\": \"text\",\n" +
                "     \"text\": {\n" +
                "         \"content\":\""+ "申诉被拒绝提醒：\n申诉未通过，请登陆 http://oa.helidianshang.com 查看原因\n@" + receiver.getUserPhone()
                +" \n任务ID："+t.getTaskId()
                +" \n审核人："+t.getTaskCreator()
                + "\n任务类型：" + t.getTaskType()
                + "\n任务标题：" + t.getTaskTitle() +
                "    \" },\n" +
                "     \"at\": {\n" +
                "         \"atMobiles\": [\n" +
                receiver.getUserPhone() +
                "         ], \n" +
                "         \"isAtAll\": false\n" +
                "     }\n" +
                " }";
        StringEntity se = new StringEntity(textMsg, "utf-8");
        httppost.setEntity(se);

        try {
            HttpResponse response = httpclient.execute(httppost);
            if (response.getStatusLine().getStatusCode()== HttpStatus.SC_OK){
                String result= EntityUtils.toString(response.getEntity(), "utf-8");
                log.info("申诉被拒绝提醒："+ t.getTaskId()+",接收人："+t.getTaskReceiver());
            }
        } catch (IOException e) {
            ExceptionResolverUtil.errorMessageToDing(e);
        }

    }

    public void taskAppealMessage(Task t)   {

        String textMsg;

        User creator = userDao.getUserByNickname(t.getTaskCreator());
        User receiver = userDao.getUserByNickname(t.getTaskReceiver());
        if(creator.getUserId() == 1){
            setWEBHOOK_TOKEN_Dep(receiver.getUserDepartment());
        }else{
            setWEBHOOK_TOKEN_Dep(creator.getUserDepartment());
        }

        HttpClient httpclient = HttpClients.createDefault();

        HttpPost httppost = new HttpPost(WEBHOOK_TOKEN_Dep);
        httppost.addHeader("Content-Type", "application/json; charset=utf-8");
        textMsg = "{\n" +
                "     \"msgtype\": \"text\",\n" +
                "     \"text\": {\n" +
                "         \"content\":\""+ "任务申诉提醒：\n请登陆 http://oa.helidianshang.com 任务申诉详情\n@" + creator.getUserPhone()
                +" \n任务ID："+t.getTaskId()
                +" \n完成人："+t.getTaskReceiver()
                + "\n任务类型：" + t.getTaskType()
                + "\n任务标题：" + t.getTaskTitle() +
                "    \" },\n" +
                "     \"at\": {\n" +
                "         \"atMobiles\": [\n" +
                creator.getUserPhone() +
                "         ], \n" +
                "         \"isAtAll\": false\n" +
                "     }\n" +
                " }";
        StringEntity se = new StringEntity(textMsg, "utf-8");
        httppost.setEntity(se);

        try {
            HttpResponse response = httpclient.execute(httppost);
            if (response.getStatusLine().getStatusCode()== HttpStatus.SC_OK){
                String result= EntityUtils.toString(response.getEntity(), "utf-8");
                log.info("任务申诉通知成功："+ t.getTaskId()+",接收人："+t.getTaskReceiver());
            }
        } catch (IOException e) {
            ExceptionResolverUtil.errorMessageToDing(e);
        }

    }
}
