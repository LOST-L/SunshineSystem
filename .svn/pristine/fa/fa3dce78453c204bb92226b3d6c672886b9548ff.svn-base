package com.heli.oa.sunshine.service;


import com.heli.oa.sunshine.entity.Task;
import org.quartz.SchedulerException;

import java.util.List;

public interface TaskService {

    void addFatherTask(Task t) throws Exception;

    void addSonTask(Task t) throws Exception;

    void acceptOrRefuseTask(Task t) throws Exception;

    void auditTask(Task t);

    void reportTaskByTaskRecordId(Task t) throws Exception;

    void cancelTaskByTaskRecordId(Integer taskRecordId) throws SchedulerException;

    void cancelTaskByTaskId(Integer taskId) throws SchedulerException;

    void appealTask(Integer taskRecordId);

    void auditAppealTask(Task t);

    List<Task> listSonTaskByTaskId(Integer taskId);

    List<Task> listToAcceptTaskByReceiver(String taskReceiver);

    List<Task> listToCreateTaskByReceiver(String taskReceiver);

    List<Task> listDoingTaskByReceiver(String taskReceiver);

    List<Task> listToAuditTaskByCreator(String taskCreator);

    List<Task> listAppealToAuditTaskByCreator(String taskCreator);

    List<Task> listDoneTaskByReceiver(String taskReceiver);

    List<Task> listTimeOutTaskByReceiver(String taskReceiver);

    List<Task> listFatherTaskByCreator(String taskCreator);
}
