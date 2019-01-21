package com.heli.dao.impl;

import com.heli.dao.ActionSunshineDao;
import com.heli.dao.SunshineDao;
import com.heli.dao.UserDao;
import com.heli.model.ActionSunshine;
import com.heli.model.Sunshine;
import com.heli.model.User;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @Author:核利-白驹
 * @Descriction:
 * @Date:Created in 18:23 2018/11/6
 */
@Service
public class SunshineDaoImpl{

    @Autowired
    private SessionFactory sessionFactory;
    @Autowired
    private UserDao userDao;
    @Autowired
    private ActionSunshineDao actionSunshineDao;
    private User user;
    private Long sumMoney;
    private Double sumMoneyById;
    private String starPlusHtml = "";
    private String starPlusHtml1 = "";
    private String starPlusHtml2 = "";
    private String starPlusHtml3 = "";
    private String starMinusHtml = "";
    private String starMinusHtml1 = "";
    private String starMinusHtml2 = "";
    private String starMinusHtml3 = "";

    @Override
    public List<Sunshine> listSunshine() {
        Session session = sessionFactory.openSession();
        Query query = session.createQuery("from Sunshine ORDER BY sunshine_time DESC");
        List<Sunshine> list = query.list();
        session.close();
        if (list.size() > 0){
            return list;
        }else{
            return null;
        }
    }

    @Override
    public List<Sunshine> listMiusSunshine() {
        Session session = sessionFactory.openSession();
        Query query = session.createQuery("from Sunshine where sunshine_value = -1 ORDER BY sunshine_time DESC");
        List<Sunshine> list = query.list();
        session.close();
        if (list.size() > 0){
            return list;
        }else{
            return null;
        }
    }

    @Override
    public List<Sunshine> listPlusSunshine() {
        Session session = sessionFactory.openSession();
        Query query = session.createQuery("from Sunshine where sunshine_value = 1 ORDER BY sunshine_time DESC");
        List<Sunshine> list = query.list();
        session.close();
        if (list.size() > 0){
            return list;
        }else{
            return null;
        }
    }

    @Override
    public List<Sunshine> listMoneySunshine() {
        Session session = sessionFactory.openSession();
        Query query = session.createQuery("from Sunshine where total_money < 0  ORDER BY sunshine_time DESC");
        List<Sunshine> list = query.list();
        session.close();
        if (list.size() > 0){
            return list;
        }else{
            return null;
        }
    }

    @Override
    public List<Sunshine> listSunshineById(Sunshine s) {
        Session session = sessionFactory.openSession();
        Query query = session.createQuery("from Sunshine s where s.id =:id");
        query.setString("id",s.getId().toString());
        List<Sunshine> list = query.list();
        session.close();
        if (list.size() > 0){
            return list;
        }else{
            return null;
        }
    }

    @Override
    public Sunshine getSunshine(Sunshine s) {
        Session session = sessionFactory.openSession();
        Sunshine sunshine= session.get(Sunshine.class,s.getId());
        session.close();
        return sunshine;
    }


    /**
     * 给sunshine表增加操作记录进行加星的同时，需要更新user表中的plus_sunshine项
     */
    @Override
    public void addSunshine(Sunshine s) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();

        session.save(s);


        session.getTransaction().commit();
        session.close();
    }

    @Override
    public void delPlusSunshine(Sunshine s) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Sunshine sun = session.get(Sunshine.class,s.getId());

        //user阳光值增加减1
        user = userDao.getUserByNickname(sun.getUserNickName());
        Integer star = user.getPlusSunshine() - 1;
        user.setPlusSunshine(star);
        user.setUserPlusStarHtml(this.setPlusStar(star));
        userDao.updateUser(user);

        //sunshineAction表增加一条新纪录
        ActionSunshine a =new ActionSunshine();
        a.setActionComment(s.getActionComment());
        a.setAction("删除");
        a.setActionTime(new Date());
        a.setSunshineId(sun.getId());
        a.setAdminNickName(sun.getAdminNickName());
        a.setAdminId(sun.getAdminId());
        a.setUserNickName(sun.getUserNickName());
        a.setNewAdminNickName(s.getNewAdminNickName());
        a.setStarHtml(sun.getStarHtml());
        a.setOldTime(sun.getSunshineTime());
        a.setOldComment(sun.getComment());
        actionSunshineDao.addActionSunshine(a);
        session.delete(sun);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public void delMinusSunshine(Sunshine s) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Sunshine sun = session.get(Sunshine.class,s.getId());


        //user阳光值扣除减1
        user = userDao.getUserByNickname(sun.getUserNickName());
        int role1 = user.getUserRole();
        int money2;
        switch(role1){
            case 2:
            case 3:
                money2 = 200;
                break;
            case  4:
            case  5:
                money2 = 50;
                break;
            case  6:
                money2 = 20;
                break;
            case  7:
                money2 = 10;
                break;
             default:
                 money2 = 0;
        }
        user.setMoney(user.getMoney()-money2);
        Integer star = user.getMinusSunshine() - 1;
        user.setMinusSunshine(star);
        user.setUserMinusStarHtml(this.setMinusStar(star));
        userDao.updateUser(user);

        //sunshineAction表增加一条新纪录
        ActionSunshine a =new ActionSunshine();
        a.setActionComment(s.getActionComment());
        a.setAction("删除");
        a.setActionTime(new Date());
        a.setSunshineId(sun.getId());
        a.setAdminNickName(sun.getAdminNickName());
        a.setAdminId(sun.getAdminId());
        a.setUserNickName(sun.getUserNickName());
        a.setNewAdminNickName(s.getNewAdminNickName());
        a.setStarHtml(sun.getStarHtml());
        a.setOldTime(sun.getSunshineTime());
        a.setOldComment(sun.getComment());
        actionSunshineDao.addActionSunshine(a);
        session.delete(sun);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public void delMoneySunshine(Sunshine s) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();

        Sunshine sun = session.get(Sunshine.class,s.getId());
        //sunshineAction表增加一条新纪录
        ActionSunshine a =new ActionSunshine();
        a.setActionComment(s.getActionComment());
        a.setAction("删除");
        a.setUserNickName("取   现");
        a.setStarHtml(sun.getMoney().toString());
        a.setActionTime(new Date());
        a.setSunshineId(sun.getId());
        a.setAdminNickName(sun.getAdminNickName());
        a.setAdminId(sun.getAdminId());
        a.setNewAdminNickName(s.getNewAdminNickName());
        a.setOldTime(sun.getSunshineTime());
        a.setOldComment(sun.getComment());
        actionSunshineDao.addActionSunshine(a);
        session.delete(sun);

        session.getTransaction().commit();
        session.close();
    }

    @Override
    public void minusSunshine(Sunshine s) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();

        session.save(s);


        session1.close();

    }


    @Override
    public void minusMoney(Sunshine s) {
        Session session = sessionFactory.openSession();
        session.save(s);
        session.close();
    }

    @Override
    public List<Double> sumMoney() {
        Session session = sessionFactory.openSession();
        Double sumMoney = (Double) session.createQuery("select sum(s.totalMoney) from Sunshine s").uniqueResult();
        Double sumReceivedMoney = (Double) session.createQuery("select sum(s.totalMoney) from Sunshine s where s.moneyStatus='已缴款' ").uniqueResult();

        if ("".equals(sumMoney)){
            sumMoney = Double.valueOf(0);
        }
        if ("".equals(sumReceivedMoney)){
            sumReceivedMoney = Double.valueOf(0);
        }

        List<Double> sum = new ArrayList<>();
        sum.add(sumMoney);
        sum.add(sumReceivedMoney);

        session.close();
        return sum;
    }

    /**
     * 通过sunshine增加记录中的userId计算该id的扣除的金额总和；
     * 待优化：后期增加按月计算扣除总额；
     * @param s
     * @return
     */
    @Override
    public Double sumMoneyById(Sunshine s) {
        Session session = sessionFactory.openSession();
        Query query = session.createQuery("select sum(s.totalMoney) from Sunshine s where s.userId=:userId");
        query.setString("userId", s.getUserId().toString());
        sumMoneyById = (Double)query.uniqueResult();
        session.close();
        return sumMoneyById;
    }



    @Override
    public void updateSunshine(Sunshine s) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();

        Sunshine sunshine= session.get(Sunshine.class,s.getId());
        sunshine.setMoneyStatus("已缴款");
        session.update(sunshine);

        session.getTransaction().commit();
        session.close();
    }

    @Override
    public void updateUtilSunshine(Sunshine s) {
        Session session = sessionFactory.openSession();
        Transaction ts = session.beginTransaction();

        System.out.println(s.getMoney());
        System.out.println(s.getOverdueFine());
        System.out.println(s.getTotalMoney());

        Sunshine sunshine= session.get(Sunshine.class,s.getId());

        sunshine.setOverdueFine(s.getOverdueFine());
        sunshine.setTotalMoney(s.getTotalMoney());
        sunshine.setMoneyStatus(s.getMoneyStatus());
        session.update(sunshine);

        ts.commit();
        session.close();
    }

    @Override
    public List<Sunshine> listSunshineByMoneyStatus(String moneyStatus) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();

        Query query = session.createQuery("from Sunshine s where s.moneyStatus =:moneyStatus");
        query.setString("moneyStatus",moneyStatus);
        List<Sunshine> list = query.list();

        session.getTransaction().commit();
        session.close();

        return list;
    }

    @Override
    public List<Sunshine> listByUserId(Integer userId) {
        Session session = sessionFactory.openSession();
        Query query = session.createQuery("from Sunshine s where s.userId =:userId order by sunshine_time Desc");
        query.setString("userId",userId.toString());
        List<Sunshine> list = query.list();
        session.close();
        if (list.size() > 0){
            return list;
        }else{
            return null;
        }
    }

    @Override
    public void updatePlusSunshine(Sunshine s) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Sunshine sun = session.get(Sunshine.class,s.getId());

        //sunshineAction表增加一条新纪录
        ActionSunshine a =new ActionSunshine();
        a.setActionComment(s.getActionComment());
        a.setAction("编辑");
        a.setActionTime(new Date());
        a.setSunshineId(sun.getId());
        a.setAdminNickName(sun.getAdminNickName());
        a.setAdminId(sun.getAdminId());
        a.setUserNickName(sun.getUserNickName());
        a.setNewAdminNickName(s.getNewAdminNickName());
        a.setStarHtml(sun.getStarHtml());
        a.setOldTime(sun.getSunshineTime());
        a.setOldComment(sun.getComment());
        actionSunshineDao.addActionSunshine(a);
        session.delete(sun);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public void updateMinusSunshine(Sunshine s) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Sunshine sun = session.get(Sunshine.class,s.getId());

        //sunshineAction表增加一条新纪录
        ActionSunshine a =new ActionSunshine();
        a.setActionComment(s.getActionComment());
        a.setAction("编辑");
        a.setActionTime(new Date());
        a.setSunshineId(sun.getId());
        a.setAdminNickName(sun.getAdminNickName());
        a.setAdminId(sun.getAdminId());
        a.setUserNickName(sun.getUserNickName());
        a.setNewAdminNickName(s.getNewAdminNickName());
        a.setStarHtml(sun.getStarHtml());
        a.setOldTime(sun.getSunshineTime());
        a.setOldComment(sun.getComment());
        actionSunshineDao.addActionSunshine(a);
        session.delete(sun);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public void updateMoneySunshine(Sunshine s) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();

        Sunshine sun = session.get(Sunshine.class,s.getId());
        //sunshineAction表增加一条新纪录
        ActionSunshine a =new ActionSunshine();
        a.setActionComment(s.getActionComment());
        a.setAction("编辑");
        a.setUserNickName("取   现");
        a.setStarHtml(sun.getMoney().toString());
        a.setActionTime(new Date());
        a.setSunshineId(sun.getId());
        a.setAdminNickName(sun.getAdminNickName());
        a.setAdminId(sun.getAdminId());
        a.setNewAdminNickName(s.getNewAdminNickName());
        a.setOldTime(sun.getSunshineTime());
        a.setOldComment(sun.getComment());
        actionSunshineDao.addActionSunshine(a);
        session.delete(sun);

        session.getTransaction().commit();
        session.close();
    }
}
