package com.heli.oa.sunshine.service.impl;

import com.heli.oa.common.dao.UserDao;
import com.heli.oa.common.entity.User;
import com.heli.oa.common.service.UserService;
import com.heli.oa.sunshine.dao.SunshineDao;
import com.heli.oa.sunshine.entity.Sunshine;
import com.heli.oa.sunshine.service.SunshineService;
import com.heli.oa.sunshine.service.TaskService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.quartz.SchedulerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: 白驹
 * Date: 2019/1/17
 * Time: 10:38
 * Description:
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring-sunshine.xml")
public class TaskServiceImplTest {

    @Autowired
    TaskService taskService;

    @Autowired
    SunshineService sunshineService;

    @Autowired
    UserService userService;

    @Autowired
    UserDao userDao;

    @Autowired
    SunshineDao sunshineDao;

    @Test
    public void cancelTaskByTaskRecordId() throws SchedulerException {
        /*taskService.cancelTaskByTaskRecordId(525);
        taskService.cancelTaskByTaskRecordId(528);
        taskService.cancelTaskByTaskRecordId(530);
        taskService.cancelTaskByTaskRecordId(538);
        System.out.println("111");*/

        List<Sunshine> sunshines = sunshineService.listMinusSunshine();
        for(Sunshine s:sunshines){
            s.setSunshineStarHtml("<div class=\"layer-photos\">\n" +
                    "<img  layer-src=\"/static/image/star2.png\" src=\"/static/image/star2.png\" alt=\"星星\">\n" +
                    "</div>");
            sunshineDao.updateSunshineRecord(s);
        }
        List<Sunshine> sunshines1 = sunshineService.listPlusSunshine();
        for(Sunshine s:sunshines1){
            s.setSunshineStarHtml("<div class=\"layer-photos\">\n" +
                    "<img  layer-src=\"/static/image/star1.png\" src=\"/static/image/star1.png\" alt=\"星星\">\n" +
                    "</div>");
            sunshineDao.updateSunshineRecord(s);
        }
    }
}