package com.heli.oa.sunshine.util;

import com.heli.oa.sunshine.dao.SunshineDao;
import com.heli.oa.sunshine.entity.Sunshine;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

/**
 * @desc 定时检查任务状态，超时第一次邮件通知，第二次扣星
 * @author 白驹
 */
@Slf4j
@Component
@EnableScheduling
public class CheckSunshineMoney {
    /**
     * 工作时间，每小时扫描一次，
     * list by status
     * if "进行中" ，对比 SingleTime, if 超时，set status 待处理
     * 如果为待处理，设置为未完成，并扣星*/
    @Autowired
    SunshineDao sunshineDao;
    @Autowired
    MailUtil mailUtil;
    @Scheduled(cron = "0 4 8-19 ? * MON-SAT") //上线前修改 cron = "0 0 8-18 ? * 2-7" 周一到周六每天8-18点每小时扫描一次
    public void check() throws Exception {
        log.info("开始扣除阳光值付款状态检测。。");
        List<Sunshine> list1 = sunshineDao.listSunshineByMoneyStatus("尚未付款");
        List<Sunshine> list2 = sunshineDao.listSunshineByMoneyStatus("已催款");
        Date now = new Date();
        if(!("".equals(list1))){
            for (int i = 0; i < list1.size(); i++) {
                Sunshine sunshine = list1.get(i);
                if (now.getTime() > sunshine.getSunshineTime().getTime() + 86400000) {
                    mailUtil.sendReminderMail(sunshine);
                    sunshine.setSunshineMoneyStatus("已催款");
                    sunshineDao.updateSunshineRecord(sunshine);
                    log.info("超时尚未付款员工："+sunshine.getUserNickname()+",已发送催款邮件。");
                }
            }
        }

        if(!("".equals(list2))){
            for (int i = 0; i < list2.size(); i++) {
                Sunshine sunshine = list2.get(i);
                System.out.println(sunshine.getSunshineMoney());
                sunshine.setSunshineOverdueFine(sunshine.getSunshineMoney() * 0.05);
                sunshine.setSunshineTotalMoney(sunshine.getSunshineMoney() + sunshine.getSunshineOverdueFine());
                sunshine.setSunshineMoneyStatus("已产生滞纳金");
                sunshineDao.updateSunshineRecord(sunshine);
                mailUtil.sendOverdueFineMail(sunshine);
                log.info("产生滞纳金员工："+sunshine.getUserNickname()+",已再次发送催款邮件。");
            }
            log.info("扣除阳光值付款状态检测结束。。。。。。");
        }

    }
}
