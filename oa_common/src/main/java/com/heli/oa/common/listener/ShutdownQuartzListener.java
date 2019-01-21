package com.heli.oa.common.listener;

import org.quartz.Scheduler;
import org.quartz.SchedulerFactory;
import org.quartz.impl.StdSchedulerFactory;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.util.Collection;

public class ShutdownQuartzListener implements ServletContextListener {

    /**
     * tomcat启动初始化
     */
    @Override
    public void contextInitialized(ServletContextEvent event)  {
        System.out.println("tomcat已经启动！");
    }

    /**
     * tomcat关闭
     */
    @Override
    public void contextDestroyed(ServletContextEvent event)  {
        System.out.println("tomcat已经关闭！开始关闭quartz！");
        try {
            //创建新的调度器工厂
            SchedulerFactory sf = new StdSchedulerFactory();

            //获取当前进程的所有定时器线程数据
            Collection<Scheduler> schedulers =  sf.getAllSchedulers();
            System.out.println("获取到quartz线程："+schedulers);
            for(Scheduler scheduler : schedulers){
                scheduler.shutdown(false);
                System.out.println("关闭定时器线程成功！");
            }

            Thread.sleep(1000);

        } catch (Exception e) {
            System.out.println("关闭定时器线程失败！");
            e.printStackTrace();
        }

    }
}
