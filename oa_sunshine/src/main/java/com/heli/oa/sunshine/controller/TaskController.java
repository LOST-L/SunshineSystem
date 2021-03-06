package com.heli.oa.sunshine.controller;

import com.heli.oa.common.util.CodeUtils;
import com.heli.oa.sunshine.entity.Task;
import com.heli.oa.sunshine.service.TaskService;
import lombok.Data;

import org.quartz.SchedulerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
/**
 *@Author: 白驹
 *@Date: 2019/1/21 13:25
 *@Description:
 */
/**
 * @author Loong
 */
@Data
@Controller
public class TaskController {
    @Autowired
    private TaskService taskService;

    @ResponseBody
    @RequestMapping("/addTask")
    public CodeUtils addTask(Task t) throws Exception {
        CodeUtils codeUtils = new CodeUtils();
         
            taskService.addFatherTask(t);
            codeUtils.setCode(200);
         
        return codeUtils;
    }

    @ResponseBody
    @RequestMapping("/reportTaskByTaskRecordId")
    public CodeUtils reportTaskByTaskRecordId(Task t) throws Exception {
        CodeUtils codeUtils = new CodeUtils();
         
            taskService.reportTaskByTaskRecordId(t);
            codeUtils.setCode(200);
         
        return codeUtils;
    }

    @ResponseBody
    @RequestMapping("/cancelTaskByTaskRecordId")
    public CodeUtils cancelTaskByTaskRecordId(Integer taskRecordId) throws SchedulerException {
        CodeUtils codeUtils = new CodeUtils();
         
            taskService.cancelTaskByTaskRecordId(taskRecordId);
            codeUtils.setCode(200);
         
        return codeUtils;
    }

    @ResponseBody
    @RequestMapping("/cancelTaskByTaskId")
    public CodeUtils cancelTaskByTaskId(Integer taskId) throws SchedulerException {
        CodeUtils codeUtils = new CodeUtils();

        taskService.cancelTaskByTaskId(taskId);
        codeUtils.setCode(200);

        return codeUtils;
    }

    /**
     *@Author: 白驹 on 2019/1/16 10:53
     *@param:
     *@return:
     *@Description: 任务接收人，接受或拒绝新任务
     */
    @ResponseBody
    @RequestMapping("/acceptOrRefuseTask")
    public CodeUtils acceptOrRefuseTask(Task t) throws Exception {
        CodeUtils codeUtils = new CodeUtils();

        taskService.acceptOrRefuseTask(t);
        codeUtils.setCode(200);
         
        return codeUtils;
    }

    /**
     *@Author: 白驹 on 2019/1/16 10:53
     *@param:
     *@return:
     *@Description: 审核任务
     */
    @ResponseBody
    @RequestMapping("/auditTask")
    public CodeUtils auditTask(Task t){
        CodeUtils codeUtils = new CodeUtils();

        taskService.auditTask(t);
        codeUtils.setCode(200);
         
        return codeUtils;
    }

    /**
     *@Author: 白驹 on 2019/1/17 13:22
     *@param:
     *@return:
     *@Description: 发起申诉
     */
    @ResponseBody
    @RequestMapping("/appealTask")
    public CodeUtils appealTask(Integer taskRecordId) throws Exception {
        CodeUtils codeUtils = new CodeUtils();

        taskService.appealTask(taskRecordId);
        codeUtils.setCode(200);
         
        return codeUtils;
    }

    /**
     *@Author: 白驹 on 2019/1/17 13:25
     *@param:
     *@return:
     *@Description: 审核申诉状态的任务
     */
    @ResponseBody
    @RequestMapping("/auditAppealTask")
    public CodeUtils auditAppealTask(Task t) throws Exception {
        CodeUtils codeUtils = new CodeUtils();

        taskService.auditAppealTask(t);
        codeUtils.setCode(200);
         
        return codeUtils;
    }

    /**
     *@Author: 白驹 on 2019/1/16 11:04
     *@param:
     *@return:
     *@Description: 查询待接收任务
     */
    @ResponseBody
    @RequestMapping("/listToAcceptTaskByReceiver")
    public CodeUtils listToAcceptTaskByReceiver(String taskReceiver) throws Exception {
        CodeUtils codeUtils = new CodeUtils();

        List<Task> list = taskService.listToAcceptTaskByReceiver(taskReceiver);
        codeUtils.setCode(200);
        codeUtils.setParamList(list);
         
        return codeUtils;
    }

    /**
     *@Author: 白驹 on 2019/1/16 11:04
     *@param:
     *@return:
     *@Description: 查询已接收，待发布重复任务
     */
    @ResponseBody
    @RequestMapping("/listToCreateTaskByReceiver")
    public CodeUtils listToCreateTaskByReceiver(String taskReceiver) throws Exception {
        CodeUtils codeUtils = new CodeUtils();

        List<Task> list = taskService.listToCreateTaskByReceiver(taskReceiver);
        codeUtils.setCode(200);
        codeUtils.setParamList(list);
         
        return codeUtils;
    }

    /**
     *@Author: 白驹 on 2019/1/16 11:04
     *@param:
     *@return:
     *@Description: 列出待审核任务
     */
    @ResponseBody
    @RequestMapping("/listToAuditTaskByCreator")
    public CodeUtils listToAuditTaskByCreator(String taskCreator) throws Exception {
        CodeUtils codeUtils = new CodeUtils();

        List<Task> list = taskService.listToAuditTaskByCreator(taskCreator);
        codeUtils.setCode(200);
        codeUtils.setParamList(list);
         
        return codeUtils;
    }

    /**
     *@Author: 白驹 on 2019/1/17 13:16
     *@param:
     *@return:
     *@Description: 列出申诉的待处理的任务
     */
    @ResponseBody
    @RequestMapping("/listAppealToAuditTaskByCreator")
    public CodeUtils listAppealToAuditTaskByCreator(String taskCreator) throws Exception {
        CodeUtils codeUtils = new CodeUtils();

        List<Task> list = taskService.listAppealToAuditTaskByCreator(taskCreator);
        codeUtils.setCode(200);
        codeUtils.setParamList(list);
         
        return codeUtils;
    }

    @ResponseBody
    @RequestMapping("/listSonTaskByTaskId")
    public CodeUtils listSonTaskByTaskId(Integer taskId) throws Exception {
        CodeUtils codeUtils = new CodeUtils();

        List<Task> list = taskService.listSonTaskByTaskId(taskId);
        codeUtils.setCode(200);
        codeUtils.setParamList(list);
         
        return codeUtils;
    }

    @ResponseBody
    @RequestMapping("/listDoingTaskByReceiver")
    public CodeUtils listDoingTaskByReceiver(String taskReceiver) throws Exception {
        CodeUtils codeUtils = new CodeUtils();

        List<Task> list = taskService.listDoingTaskByReceiver(taskReceiver);
        codeUtils.setCode(200);
        codeUtils.setParamList(list);
         
        return codeUtils;
    }

    @ResponseBody
    @RequestMapping("/listDoneTaskByReceiver")
    public CodeUtils listDoneTaskByReceiver(String taskReceiver) throws Exception {
        CodeUtils codeUtils = new CodeUtils();

        List<Task> list = taskService.listDoneTaskByReceiver(taskReceiver);
        codeUtils.setCode(200);
        codeUtils.setParamList(list);
         
        return codeUtils;
    }

    @ResponseBody
    @RequestMapping("/listTimeOutTaskByReceiver")
    public CodeUtils listTimeOutTaskByReceiver(String taskReceiver) throws Exception {
        CodeUtils codeUtils = new CodeUtils();

        List<Task> list = taskService.listTimeOutTaskByReceiver(taskReceiver);
        codeUtils.setCode(200);
        codeUtils.setParamList(list);
         
        return codeUtils;
    }


    @ResponseBody
    @RequestMapping("/listFatherTaskByCreator")
    public CodeUtils listFatherTaskByCreator(String taskCreator) throws Exception {
        CodeUtils codeUtils = new CodeUtils();

        List<Task> list = taskService.listFatherTaskByCreator(taskCreator);
        codeUtils.setCode(200);
        codeUtils.setParamList(list);
         
        return codeUtils;
    }

/*    @RequestMapping("/taskDetails")
    public String taskDetails(Integer taskId,HttpServletRequest request){
        request.setAttribute("taskId",taskId);
        return "forward:sunshine/task_details";
    }*/
}
