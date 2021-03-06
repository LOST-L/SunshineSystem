package com.heli.oa.sunshine.service.impl;

import com.heli.oa.sunshine.dao.SunshineActionDao;
import com.heli.oa.sunshine.dao.SunshineDao;
import com.heli.oa.common.dao.UserDao;
import com.heli.oa.sunshine.entity.Sunshine;
import com.heli.oa.sunshine.entity.SunshineAction;
import com.heli.oa.sunshine.entity.Task;
import com.heli.oa.common.entity.User;
import com.heli.oa.sunshine.service.SunshineService;
import com.heli.oa.sunshine.util.DingMessageUtils;
import com.heli.oa.sunshine.util.MailUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @Author:核利-白驹
 * @Descriction:
 * @Date:Created in 18:40 2018/11/6
 */
@Service
public class SunshineServiceImpl implements SunshineService {

    @Autowired
    private UserDao userDao;
    @Autowired
    private SunshineDao sunshineDao;
    @Autowired
    private SunshineActionDao sunshineActionDao;
    @Autowired
    DingMessageUtils dingMessageUtils;

    private User user;
    private String plusStar = "<div class=\"layer-photos\">\n" +
            "<img  layer-src=\"/static/image/star1.png\" src=\"/static/image/star1.png\" alt=\"星星\">\n" +
            "</div>";
    private String minusStar = "<div class=\"layer-photos\">\n" +
            "<img  layer-src=\"/static/image/star2.png\" src=\"/static/image/star2.png\" alt=\"星星\">\n" +
            "</div>";

    /**
     *将增加的阳光值数字转换为图片
     */
    @Override
    public String setPlusStar(Integer star) {
        String starPlusHtml = "";
        String starPlusHtml2 = "";
        String starPlusHtml1 = "";
        String starPlusHtml3 = "";
        if (star > 0) {
            //a为星星个数
            Integer a = star % 4;
            //b为换成太阳前的月亮数，即star%4的整数
            Integer b = (star - a) / 4;
            //c为换算成太阳后，应该输出的月亮数
            Integer c = b % 4;
            // d为太阳数
            Integer d = (b - c) / 4;
            String star1 = "<img  layer-src=\"/static/image/star1.png\" src=\"/static/image/star1.png\" alt=\"星星\">\n";
            String moon1 = "<img  layer-src=\"/static/image/moon1.png\" src=\"/static/image/moon1.png\" alt=\"月亮\">\n";
            String sun1 = "<img  layer-src=\"/static/image/sun1.png\" src=\"/static/image/sun1.png\" alt=\"太阳\">\n";
            for (int i = 0; i < d; i++) {
                starPlusHtml1 = starPlusHtml1 + sun1;
            }
            for (int i = 0; i < c; i++) {
                starPlusHtml2 = starPlusHtml2 + moon1;
            }
            for (int i = 0; i < a; i++) {
                starPlusHtml3 = starPlusHtml3 + star1;
            }
            starPlusHtml = starPlusHtml1 + starPlusHtml2 + starPlusHtml3;
        } else {
            starPlusHtml = "零";

        }
        return starPlusHtml;
    }

    /**
     *将扣除的阳光值数字转换为图片
     */
    @Override
    public String setMinusStar(Integer star){
        String starMinusHtml = "";
        String starMinusHtml1 = "";
        String starMinusHtml2 = "";
        String starMinusHtml3 = "";
        if (star > 0) {
            //a为星星个数
            Integer a = star % 4;
            //b为换成太阳前的月亮数，即star%4的整数
            Integer b = (star - a) / 4;
            //c为换算成太阳后，应该输出的月亮数
            Integer c = b % 4;
            // d为太阳数
            Integer d = (b - c) / 4;
            String star2 = "<img  layer-src=\"/static/image/star2.png\" src=\"/static/image/star2.png\" alt=\"星星\">\n";
            String moon2 = "<img  layer-src=\"/static/image/moon2.png\" src=\"/static/image/moon2.png\" alt=\"月亮\">\n";
            String sun2 = "<img  layer-src=\"/static/image/sun2.png\" src=\"/static/image/sun2.png\" alt=\"太阳\">\n";
            for (int i = 0; i < d; i++) {
                starMinusHtml1 = starMinusHtml1 + sun2;
            }
            for (int i = 0; i < c; i++) {
                starMinusHtml2 = starMinusHtml2 + moon2;
            }
            for (int i = 0; i < a; i++) {
                starMinusHtml3 = starMinusHtml3 + star2;
            }
            starMinusHtml = starMinusHtml1 + starMinusHtml2 + starMinusHtml3;
        } else {
            starMinusHtml = "零";
        }
        return  starMinusHtml;
    }

    @Override
    public void addSunshine(Sunshine s) throws IOException {
        user = userDao.getUserByNickname(s.getUserNickname());
        s.setUserId(user.getUserId());
        s.setSunshineStarHtml(plusStar);
        s.setSunshineMoney(Double.valueOf(0));
        s.setSunshineTime(new Date());
        s.setSunshineValue(1);
        sunshineDao.addSunshineRecord(s);

        Integer star = user.getUserPlusSunshine() + 1;
        user.setUserPlusSunshine(star);
        user.setUserPlusStarHtml(setPlusStar(star));
        userDao.updateUser(user);

        dingMessageUtils.addSunshine(s);
    }

    @Override
    public void minusSunshine(Sunshine s) throws IOException {
        user = userDao.getUserByNickname(s.getUserNickname());
        int role1 = user.getUserRole();
        Double money1;
        switch(role1){
            case 2:
            case 3:
                money1 = Double.valueOf(200);
                break;
            case  4:
            case  5:
                money1 = Double.valueOf(50);
                break;
            case  6:
                money1 = Double.valueOf(20);
                break;
            case  7:
                money1 = Double.valueOf(10);
                break;
            default:
                money1 = Double.valueOf(0);
        }
        s.setUserId(user.getUserId());
        s.setSunshineMoney(money1);
        s.setSunshineStarHtml(minusStar);
        s.setSunshineTime(new Date());
        s.setSunshineValue(-1);
        s.setSunshineMoneyStatus("尚未付款");
        s.setSunshineOverdueFine(Double.valueOf(0));
        s.setSunshineTotalMoney(money1);
        sunshineDao.addSunshineRecord(s);

        Integer star = user.getUserMinusSunshine() + 1;
        user.setUserMinusSunshine(star);
        user.setUserMinusStarHtml(setMinusStar(star));

        Double count = sunshineDao.sumMoneyById(s);
        user.setUserMoney(count);
        userDao.updateUser(user);

        dingMessageUtils.minusSunshine(s);
    }

    @Override
    public void minusSunshineN(Sunshine s , Task t) throws IOException {
        user = userDao.getUserByNickname(s.getUserNickname());
        int role1 = user.getUserRole();
        Double money1;
        switch(role1){
            case 2:
            case 3:
                money1 = Double.valueOf(200);
                break;
            case  4:
            case  5:
                money1 = Double.valueOf(50);
                break;
            case  6:
                money1 = Double.valueOf(20);
                break;
            case  7:
                money1 = Double.valueOf(10);
                break;
            default:
                money1 = Double.valueOf(0);
        }

        int N = t.getTaskPunishment();
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < N; i++) {
            sb.append(minusStar);
        }
        s.setUserId(user.getUserId());
        s.setSunshineMoney(money1 * N);
        s.setSunshineStarHtml(sb.toString());
        s.setSunshineTime(new Date());
        s.setSunshineValue(-1);
        s.setSunshineMoneyStatus("尚未付款");
        s.setSunshineOverdueFine(Double.valueOf(0));
        s.setSunshineTotalMoney(money1 * N);
        sunshineDao.addSunshineRecord(s);
        dingMessageUtils.minusSunshineN(s,t);
    }

    @Override
    public void delPlusSunshine(Sunshine s) {

        Sunshine sunshine = sunshineDao.selectSunshineById(s.getSunshineRecordId());
        //user阳光值增加减1
        user = userDao.getUserByNickname(sunshine.getUserNickname());
        Integer star = user.getUserPlusSunshine() - 1;
        user.setUserPlusSunshine(star);
        user.setUserPlusStarHtml(setPlusStar(star));
        userDao.updateUser(user);

        //sunshineAction表增加一条新纪录
        SunshineAction a =new SunshineAction();
        a.setSunshineActionComment(s.getSunshineActionComment());
        a.setSunshineAction("删除");
        a.setSunshineActionTime(new Date());
        a.setSunshineRecordId(sunshine.getSunshineRecordId());
        a.setSunshineAdminNickname(sunshine.getAdminNickname());
        a.setSunshineAdminId(sunshine.getAdminId());
        a.setUserNickname(sunshine.getUserNickname());
        a.setSunshineNewAdminNickname(s.getSunshineNewAdminNickname());
        a.setSunshineStarHtml(sunshine.getSunshineStarHtml());
        a.setSunshineOldTime(sunshine.getSunshineTime());
        a.setSunshineOldComment(sunshine.getSunshineComment());
        sunshineActionDao.addSunshineAction(a);

        sunshineDao.delSunshineRecord(s.getSunshineRecordId());
    }

    @Override
    public void delMinusSunshine(Sunshine s) {

        Sunshine sunshine = sunshineDao.selectSunshineById(s.getSunshineRecordId());

        //user阳光值扣除减1
        user = userDao.getUserByNickname(sunshine.getUserNickname());
        user.setUserMoney(user.getUserMoney() - sunshine.getSunshineMoney());
        Integer star = user.getUserMinusSunshine() - 1;
        user.setUserMinusSunshine(star);
        user.setUserMinusStarHtml(setMinusStar(star));
        userDao.updateUser(user);

        //sunshineAction表增加一条新纪录
        SunshineAction a =new SunshineAction();
        a.setSunshineActionComment(s.getSunshineActionComment());
        a.setSunshineAction("删除");
        a.setSunshineActionTime(new Date());
        a.setSunshineRecordId(sunshine.getSunshineRecordId());
        a.setSunshineAdminNickname(sunshine.getAdminNickname());
        a.setSunshineAdminId(sunshine.getAdminId());
        a.setUserNickname(sunshine.getUserNickname());
        a.setSunshineNewAdminNickname(s.getSunshineNewAdminNickname());
        a.setSunshineStarHtml(sunshine.getSunshineStarHtml());
        a.setSunshineOldTime(sunshine.getSunshineTime());
        a.setSunshineOldComment(sunshine.getSunshineComment());
        sunshineActionDao.addSunshineAction(a);

        sunshineDao.delSunshineRecord(s.getSunshineRecordId());
    }

    @Override
    public void delMinusMoneySunshine(Sunshine s) {
        Sunshine sunshine = sunshineDao.selectSunshineById(s.getSunshineRecordId());

        //sunshineAction表增加一条新纪录
        SunshineAction a =new SunshineAction();
        a.setSunshineActionComment(s.getSunshineActionComment());
        a.setSunshineAction("删除");
        a.setSunshineActionTime(new Date());
        a.setSunshineRecordId(sunshine.getSunshineRecordId());
        a.setSunshineAdminNickname(sunshine.getAdminNickname());
        a.setSunshineAdminId(sunshine.getAdminId());
        a.setUserNickname(sunshine.getUserNickname());
        a.setSunshineNewAdminNickname(s.getSunshineNewAdminNickname());
        a.setSunshineStarHtml(sunshine.getSunshineTotalMoney().toString());
        a.setSunshineOldTime(sunshine.getSunshineTime());
        a.setSunshineOldComment(sunshine.getSunshineComment());
        sunshineActionDao.addSunshineAction(a);

        sunshineDao.delSunshineRecord(s.getSunshineRecordId());
    }

    @Override
    public void updateSunshineRecord(Sunshine s) {
        Sunshine sunshine = sunshineDao.selectSunshineById(s.getSunshineRecordId());
        sunshine.setSunshineComment(s.getSunshineComment());
        sunshine.setSunshineMoneyStatus(s.getSunshineMoneyStatus());

        //sunshineAction表增加一条新纪录
        SunshineAction a =new SunshineAction();
        a.setSunshineActionComment(s.getSunshineActionComment());
        a.setSunshineAction("编辑");
        a.setSunshineActionTime(new Date());
        a.setSunshineRecordId(sunshine.getSunshineRecordId());
        a.setSunshineAdminNickname(sunshine.getAdminNickname());
        a.setSunshineAdminId(sunshine.getAdminId());
        a.setUserNickname(sunshine.getUserNickname());
        a.setSunshineNewAdminNickname(s.getSunshineNewAdminNickname());
        a.setSunshineStarHtml(sunshine.getSunshineStarHtml());
        a.setSunshineOldTime(sunshine.getSunshineTime());
        a.setSunshineOldComment(sunshine.getSunshineComment());
        sunshineActionDao.addSunshineAction(a);

        sunshineDao.updateSunshineRecord(sunshine);
    }

    @Override
    public void minusMoney(Sunshine s) {
        s.setUserNickname("提--现");
        s.setUserDepartment("提--现");
        s.setSunshineTotalMoney(0 - s.getSunshineMoney());
        s.setSunshineTime(new Date());
        sunshineDao.addSunshineRecord(s); }

    @Override
    public List<Double>  sumMoney() {
        Double sumMoney = sunshineDao.sumMoney();
        Double sumReceivedMoney = sunshineDao.sumReceivedMoney();

        if ("".equals(sumMoney)){
            sumMoney = Double.valueOf(0);
        }
        if ("".equals(sumReceivedMoney)){
            sumReceivedMoney = Double.valueOf(0);
        }

        List<Double> sum = new ArrayList<>();
        sum.add(sumMoney);
        sum.add(sumReceivedMoney);

        return sum;
    }

    @Override
    public List<Sunshine> listMinusSunshine() {
        return sunshineDao.listMinusSunshine();
    }

    @Override
    public List<Sunshine> listPlusSunshine() {
        return sunshineDao.listPlusSunshine();
    }

    @Override
    public List<Sunshine> listSunshineMoney() {
        return sunshineDao.listSunshineMoney();
    }

    @Override
    public List<Sunshine> listSunshineByUserId(Integer userId ) {
        return sunshineDao.listSunshineByUserId(userId);
    }

}
