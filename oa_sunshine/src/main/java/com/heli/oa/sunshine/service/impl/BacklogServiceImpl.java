package com.heli.oa.sunshine.service.impl;

import com.heli.oa.sunshine.dao.BacklogDao;
import com.heli.oa.sunshine.entity.Backlog;
import com.heli.oa.sunshine.service.BacklogService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.Date;
import java.util.List;

@Slf4j
@Service
public class BacklogServiceImpl implements BacklogService {

    @Autowired
    BacklogDao backlogDao;

    @Override
    public List<Backlog> listBacklogByPriority(Backlog backlog) {
        return backlogDao.listBacklogByPriority(backlog);
    }

    @Override
    public List<Backlog> listByStatus(Backlog backlog) {
        return backlogDao.listByStatus(backlog);
    }

    @Override
    public List<Backlog> listSetTimeBacklogByUserId(Backlog backlog) {
        return backlogDao.listSetTimeBacklogByUserId(backlog);
    }

    @Override
    public List<Backlog> searchDone(Backlog backlog) throws ParseException {

        backlog.setBacklogStartTime(this.timeToMinutes(backlog.getBacklogStartTimeStr()));
        backlog.setBacklogEndTime(this.timeToMinutes(backlog.getBacklogEndTimeStr()));

        String createTimeForSearch[] = backlog.getCreateTimeForSearch().split(" - ");
        String doneTimeForSearch[] = backlog.getDoneTimeForSearch().split(" - ");

        backlog.setCreateTimeForSearch1(createTimeForSearch[0]);
        backlog.setCreateTimeForSearch2(createTimeForSearch[1]);
        backlog.setDoneTimeForSearch1(doneTimeForSearch[0]);
        backlog.setDoneTimeForSearch2(doneTimeForSearch[1]);

        return backlogDao.searchDone(backlog);
    }

    @Override
    public void addBacklog(Backlog backlog) {
        backlog.setBacklogStatus("未定时");
        backlog.setBacklogCreateTime(new Date());
        backlog.setBacklogDelFlag(1);
        backlogDao.addBacklog(backlog);
    }

    @Override
    public void deleteBacklog(Backlog backlog) {
        backlog.setBacklogDelFlag(0);
        backlogDao.updateBacklog(backlog);
    }

    @Override
    public void cancelTime(Backlog backlog) {
        backlog.setBacklogStatus("未定时");
        backlogDao.updateBacklog(backlog);
    }

    @Override
    public void overTime(Backlog backlog) {
        backlog.setBacklogStatus("已完成");
        backlog.setBacklogDoneTime(new Date());
        backlogDao.updateBacklog(backlog);
    }


    @Override
    public String setTime(Backlog backlog) {
        String result;
        Backlog backlog1 = backlogDao.selectBacklog(backlog.getBacklogRecordId());

        Integer startTimeMinutes = this.timeToMinutes(backlog.getBacklogStartTimeStr());
        Integer endTimeMinutes = this.timeToMinutes(backlog.getBacklogEndTimeStr());

        if (ifTimeConflict(startTimeMinutes,endTimeMinutes,backlog1.getUserId())){
            result = "时间冲突";
        }else{
            backlog1.setBacklogStatus("已定时");
            backlog1.setBacklogStartTime(startTimeMinutes);
            backlog1.setBacklogEndTime(endTimeMinutes);
            backlog1.setBacklogStartTimeStr(backlog.getBacklogStartTimeStr());
            backlog1.setBacklogEndTimeStr(backlog.getBacklogEndTimeStr());
            backlog1.setBacklogTimeSlot(backlog.getBacklogStartTimeStr() + "-" + backlog.getBacklogEndTimeStr());

            backlogDao.updateBacklog(backlog1);
            result = "定时成功";
        }

        return result;
    }

    /**
     * 先找到冲突的待办事项，然后计算冲突的时长，然后循环叠加
     * @param backlog
     */
    @Override
    public void insertBacklog(Backlog backlog) {
        Integer startTimeMinutes = this.timeToMinutes(backlog.getBacklogStartTimeStr());
        Integer endTimeMinutes = this.timeToMinutes(backlog.getBacklogEndTimeStr());

        List<Backlog> setBacklog = backlogDao.listSetTimeBacklogByUserId(backlog);

        Backlog backlog1 = backlogDao.selectBacklog(backlog.getBacklogRecordId());
        backlog1.setBacklogStartTime(startTimeMinutes);
        backlog1.setBacklogEndTime(endTimeMinutes);
        backlog1.setBacklogStartTimeStr(backlog.getBacklogStartTimeStr());
        backlog1.setBacklogEndTimeStr(backlog.getBacklogEndTimeStr());
        backlog1.setBacklogTimeSlot(backlog.getBacklogStartTimeStr() + "-" + backlog.getBacklogEndTimeStr());
        backlog1.setBacklogStatus("已定时");

        for (int i = 0; i < setBacklog.size(); i++) {

            //新事项在旧事项内，开始时间重合，结束时间小于等于冲突事项
            if((startTimeMinutes.equals(setBacklog.get(i).getBacklogStartTime())) && (endTimeMinutes <= setBacklog.get(i).getBacklogEndTime())){

                for (int j = i; j < setBacklog.size(); j++){

                    Backlog backlog3 = setBacklog.get(j);
                    backlog3.setBacklogStartTime(backlog3.getBacklogStartTime() + ( endTimeMinutes - startTimeMinutes ));
                    backlog3.setBacklogEndTime(backlog3.getBacklogEndTime() + ( endTimeMinutes - startTimeMinutes ));
                    backlog3.setBacklogStartTimeStr(this.minutesToTime(backlog3.getBacklogStartTime()));
                    backlog3.setBacklogEndTimeStr(this.minutesToTime(backlog3.getBacklogEndTime()));
                    backlog3.setBacklogTimeSlot(backlog3.getBacklogStartTimeStr() + "-" + backlog3.getBacklogEndTimeStr());
                    backlogDao.updateBacklog(backlog3);

                    if(j==setBacklog.size()-1){
                        break;
                    }
                    if(backlog3.getBacklogEndTime()<=setBacklog.get(j+1).getBacklogStartTime()){
                        break;
                    }
                }
                backlogDao.updateBacklog(backlog1);
                break;
            }
            //新事项在旧事项内，开始时间大于冲突事项开始时间，开始时间小于冲突事项结束时间，结束时间无所谓
            else if((startTimeMinutes > setBacklog.get(i).getBacklogStartTime()) &&  (startTimeMinutes < setBacklog.get(i).getBacklogEndTime())) {
                Backlog backlog2 = setBacklog.get(i);

                Backlog backlog4 = new Backlog();
                Backlog backlog5 = new Backlog();
                BeanUtils.copyProperties(backlog2, backlog4);
                BeanUtils.copyProperties(backlog2, backlog5);
                backlog2.setBacklogDelFlag(0);
                backlogDao.updateBacklog(backlog2);

                backlog4.setBacklogEndTime(startTimeMinutes);
                backlog4.setBacklogEndTimeStr(backlog.getBacklogStartTimeStr());
                backlog4.setBacklogTimeSlot(backlog4.getBacklogStartTimeStr() + "-" + backlog4.getBacklogEndTimeStr());
                backlogDao.addBacklog(backlog4);


                backlog5.setBacklogStartTime(startTimeMinutes);
                backlog5.setBacklogStartTimeStr(backlog.getBacklogStartTimeStr());
                backlog5.setBacklogTimeSlot(backlog5.getBacklogStartTimeStr() + "-" + backlog5.getBacklogEndTimeStr());
                backlogDao.addBacklog(backlog5);

                List<Backlog> setBacklog1 = backlogDao.listSetTimeBacklogByUserId(backlog);
                backlogDao.updateBacklog(backlog1);

                for (int j = i +1 ; j < setBacklog1.size()-1; j++){
                    Backlog backlog3 = new Backlog();
                    BeanUtils.copyProperties(setBacklog1.get(j),backlog3);

                    backlog3.setBacklogStartTime(backlog3.getBacklogStartTime() + ( endTimeMinutes - startTimeMinutes ));
                    backlog3.setBacklogEndTime(backlog3.getBacklogEndTime() + ( endTimeMinutes - startTimeMinutes ));
                    backlog3.setBacklogStartTimeStr(this.minutesToTime(backlog3.getBacklogStartTime()));
                    backlog3.setBacklogEndTimeStr(this.minutesToTime(backlog3.getBacklogEndTime()));
                    backlog3.setBacklogTimeSlot(backlog3.getBacklogStartTimeStr() + "-" + backlog3.getBacklogEndTimeStr());

                    backlogDao.updateBacklog(backlog3);

                    if(j==setBacklog.size()-1){
                        break;
                    }
                    if(backlog3.getBacklogEndTime()<=setBacklog.get(j+1).getBacklogStartTime()){ break; }
                }
                break;

            }else if((startTimeMinutes < setBacklog.get(i).getBacklogStartTime()) && (endTimeMinutes > setBacklog.get(i).getBacklogStartTime()) ){

                for (int j = i; j < setBacklog.size(); j++){
                    Backlog backlog3 = new Backlog();
                    BeanUtils.copyProperties(setBacklog.get(j),backlog3);

                    backlog3.setBacklogStartTime(backlog3.getBacklogStartTime() + ( endTimeMinutes - setBacklog.get(i).getBacklogStartTime()));
                    backlog3.setBacklogEndTime(backlog3.getBacklogEndTime() + ( endTimeMinutes - setBacklog.get(i).getBacklogStartTime()));
                    backlog3.setBacklogStartTimeStr(this.minutesToTime(backlog3.getBacklogStartTime()));
                    backlog3.setBacklogEndTimeStr(this.minutesToTime(backlog3.getBacklogEndTime()));
                    backlog3.setBacklogTimeSlot(backlog3.getBacklogStartTimeStr() + "-" + backlog3.getBacklogEndTimeStr());
                    backlogDao.updateBacklog(backlog3);

                    if(j==setBacklog.size()-1){
                        break;
                    }
                    if(backlog3.getBacklogEndTime()<=setBacklog.get(j+1).getBacklogStartTime()){ break; }
                }
                backlogDao.updateBacklog(backlog1);
                break;
            }
        }
    }

    /**
     * 判断新定时待办事项是否与已有定时项目冲突
     * @param startTimeMinutes
     * @param endTimeMinutes
     * @return
     */
    public boolean ifTimeConflict(Integer startTimeMinutes,Integer endTimeMinutes,Integer userId){
        Boolean ifTimeConflict = false;
        List<Backlog> setBacklog = backlogDao.listByStatusAndUserId("已定时",userId);
        if(setBacklog.size() == 0){
            ifTimeConflict = false;
        }else{
            for (int i = 0; i <setBacklog.size() ; i++) {
                if((startTimeMinutes <= setBacklog.get(i).getBacklogStartTime()) && (endTimeMinutes > setBacklog.get(i).getBacklogStartTime())){
                    ifTimeConflict = true;
                    break;
                }else if((startTimeMinutes >= setBacklog.get(i).getBacklogStartTime()) && (endTimeMinutes <= setBacklog.get(i).getBacklogEndTime())) {
                    ifTimeConflict = true;
                    break;
                }else if((startTimeMinutes < setBacklog.get(i).getBacklogEndTime()) && (endTimeMinutes > setBacklog.get(i).getBacklogEndTime())) {
                    ifTimeConflict = true;
                    break;
                }
            }
        }
        return ifTimeConflict;
    }

    public Integer timeToMinutes(String getTime){
        String hour = getTime.substring(0,2);
        Integer hour1 = Integer.valueOf(hour);
        String minutes = getTime.substring(3,5);
        Integer minutes1 = Integer.valueOf(minutes);
        Integer newTime = hour1 * 60 + minutes1;
        return newTime;
    }

    public String minutesToTime(Integer getMinutes){
        String newTime;
        Integer minutes = getMinutes % 60;
        Integer hour = (getMinutes - minutes) / 60;
        if(minutes == 0 ){
            newTime = hour.toString() + ":" + "00";
        }else{
            newTime = hour.toString() + ":" + minutes.toString();
        }

        return newTime;
    }
}
