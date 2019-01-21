package com.heli.dao.impl;

import com.heli.dao.TaskDao;
import com.heli.model.Task;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author 白驹
 */
@Service
public class TaskDaoImpl implements TaskDao {
    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public List<Task> listTask() {
        Session session = sessionFactory.openSession();
        session.beginTransaction();

        Query query = session.createQuery("from Task ");
        List<Task> list = query.list();

        session.getTransaction().commit();
        session.close();
        if (list.size() > 0){
            return list;
        }else{
            return null;
        }
    }

    @Override
    public Task getLastTask() {
        Session session = sessionFactory.openSession();
        session.beginTransaction();

        Query query = session.createQuery("from Task where del_flag = 1 ORDER BY task_id");
        List<Task> list = query.list();

        session.getTransaction().commit();
        session.close();
        if(list.size()>1){
            return list.get(list.size()-1);
        }else{
            return null;
        }
    }

    /**
     * 根据任务完成状态列出任务
     */
    @Override
    public List<Task> listTaskByStatus(String status) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();

        Query query = session.createQuery("from Task t where t.taskStatus=:status and t.delFlag = 1");
        query.setString("status",status);
        List<Task> list = query.list();

        session.getTransaction().commit();
        session.close();
        System.out.println("dao的长度"+list.size());
        return list;
    }
    /**
     * 根据创建者列出任务
     * @param
     * @return
     */

    @Override
    public List<Task> listTaskByCreater(String creater) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();

        Query query = session.createQuery("from Task t where t.creater=:creater and t.taskFatherSon = 'father' and t.delFlag = 1 ");
        query.setString("creater",creater);
        List<Task> list = query.list();

        session.getTransaction().commit();
        session.close();

        if (list.size() > 0){
            return list;
        }else{
            return null;
        }
    }

    /**
     * 更新任务
     * @param t
     */
    @Override
    public void updateTask(Task t) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();

        System.out.println(t);
        session.update(t);
        System.out.println("任务更新成功");

        session.getTransaction().commit();
        session.close();
    }

    /**
     * 通过员工花名列出任务记录，用于员工在个人任务中心查看任务
     * @param receiver
     */
    @Override
    public List<Task> listTaskByReceiver(String receiver) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();

        Query query = session.createQuery("from Task t where t.receiver=:receiver and t.delFlag = 1");
        query.setString("receiver",receiver);
        List<Task> list = query.list();

        session.getTransaction().commit();
        session.close();

        if (list.size() > 0){
            return list;
        }else{
            return null;
        }
    }

    @Override
    public void addTask(Task t) {
        Session session = sessionFactory.openSession();
        session.save(t);
        session.close();
    }

    @Override
    public List<Task> listSonTaskByReceiver(String receiver) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();

        Query query1 = session.createQuery("from Task t where t.receiver=:receiver and t.taskFatherSon='son' and t.taskStatus = '进行中'  and t.delFlag = 1  ");
        Query query2 = session.createQuery("from Task t where t.receiver=:receiver and t.taskFatherSon='son' and t.taskStatus = '待处理' and t.delFlag = 1");
        Query query3 = session.createQuery("from Task t where t.receiver=:receiver and t.taskFatherSon='son' and t.taskStatus = '单次任务' and t.delFlag = 1");
        Query query4 = session.createQuery("from Task t where t.receiver=:receiver and t.taskFatherSon='son' and t.taskStatus = '已逾期' and t.delFlag = 1");
        query1.setString("receiver",receiver);
        query2.setString("receiver",receiver);
        query3.setString("receiver",receiver);
        query4.setString("receiver",receiver);
        List<Task> list1 = query1.list();
        List<Task> list2 = query2.list();
        List<Task> list3 = query3.list();
        List<Task> list4 = query4.list();
        list1.addAll(list2);
        list1.addAll(list3);
        list1.addAll(list4);

        session.getTransaction().commit();
        session.close();

        if (list1.size() > 0){
            return list1;
        }else{
            return null;
        }
    }

    @Override
    public List<Task> listFatherTaskByCreater(String creater) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();

        Query query = session.createQuery("from Task t where t.creater=:creater and t.taskFatherSon='father'  and t.delFlag = 1 ORDER BY create_time DESC");
        query.setString("creater",creater);
        List<Task> list = query.list();

        session.getTransaction().commit();
        session.close();
        if (list.size() > 0){
            return list;
        }else{
            return null;
        }
    }

    @Override
    public List<Task> listSonDoneTaskByReceiver(String receiver) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();

        Query query = session.createQuery("from Task t where t.receiver=:receiver and t.taskFatherSon='son' and t.taskStatus = '已完成' and t.delFlag = 1 ORDER BY report_time DESC");
        Query query1 = session.createQuery("from Task t where t.receiver=:receiver and t.taskFatherSon='son' and t.taskStatus = '逾期完成' and t.delFlag = 1 ORDER BY report_time DESC");
        Query query2 = session.createQuery("from Task t where t.receiver=:receiver and t.taskFatherSon='son' and t.taskStatus = '已通知加班' and t.delFlag = 1 ORDER BY report_time DESC");
        query.setString("receiver",receiver);
        query1.setString("receiver",receiver);
        query2.setString("receiver",receiver);
        List<Task> list = query.list();
        List<Task> list1 = query1.list();
        List<Task> list2 = query2.list();
        list.addAll(list1);
        list.addAll(list2);

        session.getTransaction().commit();
        session.close();
        if (list.size() > 0){
            return list;
        }else{
            return null;
        }
    }

    @Override
    public Task getTaskById(Integer recordId) {
        Session session = sessionFactory.openSession();
        Task t = session.get(Task.class,recordId);
        session.close();
        return t;
    }

    @Override
    public Task getTaskByTaskId(Integer taskId) {
        Session session = sessionFactory.openSession();
        Query query = session.createQuery("from Task t where t.taskId=:taskId  and t.delFlag = 1");
        query.setString("taskId",taskId.toString());
        List<Task> list = query.list();
        session.close();
        if (list.size() > 0){
            return list.get(0);
        }else{
            return null;
        }
    }

    @Override
    public List<Task> listSonByTaskId(Integer taskId) {
        Session session = sessionFactory.openSession();
        Query query = session.createQuery("from Task t where t.taskId=:taskId and t.taskFatherSon='son' and t.delFlag = 1 ORDER BY create_time DESC");
        query.setString("taskId",taskId.toString());
        List<Task> list = query.list();
        session.close();
        if (list.size() > 0){
            return list;
        }else{
            return null;
        }
    }

    @Override
    public List<Task> listTaskByTaskId(Integer taskId) {
        Session session = sessionFactory.openSession();
        Query query = session.createQuery("from Task t where t.taskId=:taskId and t.delFlag = 1");
        query.setString("taskId",taskId.toString());
        List<Task> list = query.list();
        session.close();
        if (list.size() > 0){
            return list;
        }else{
            return null;
        }
    }

    @Override
    public Task getLastSameFatherTask(Integer taskId) {
        Session session = sessionFactory.openSession();
        Query query = session.createQuery("from Task t where t.delFlag = 1 and t.taskId=:taskId ");
        query.setString("taskId",taskId.toString());
        List<Task> list = query.list();
        session.close();
        if(list.size()>1){
            return list.get(list.size()-1);
        }else{
            return null;
        }
    }
}
