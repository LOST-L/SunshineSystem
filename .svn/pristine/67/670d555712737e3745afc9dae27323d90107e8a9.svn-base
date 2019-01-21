package com.heli.oa.sunshine.dao;

import com.heli.oa.sunshine.entity.Task;
import org.springframework.stereotype.Repository;

import java.util.List;

/**  * @author 白驹  */
@Repository
public interface TaskDao {

    int addTask(Task t);

    void deleteTaskByTaskId(Integer taskId);

    void deleteTaskByRecordId(Integer taskRecordId);

    void updateTask(Task t);

    Task selectLastTask();

    Task selectTaskByRecordId(Integer taskRecordId);

    Task selectFatherTaskByTaskId(Integer taskId);

    Task selectLastSameFatherTask(Integer taskId);

    List<Task> listTaskByTaskId(Integer taskId);

    List<Task> listTaskByStatus(String taskStatus);

    List<Task> listTaskByReceiver(String receiver);

    List<Task> listSonTaskByReceiver(String receiver);

    List<Task> listSonTaskByTaskId(Integer taskId);

    List<Task> listToAuditTaskByCreator(String creator);

    List<Task> listAppealToAuditTaskByCreator(String taskCreator);

    List<Task> listToAcceptTaskByReceiver(String receiver);

    List<Task> listToCreateTaskByReceiver(String taskReceiver);

    List<Task> listDoingTaskByReceiver(String taskReceiver);

    List<Task> listDoneTaskByReceiver(String receiver);

    List<Task> listTimeOutTaskByReceiver(String taskReceiver);

    List<Task> listFatherTaskByCreator(String creator);


}
