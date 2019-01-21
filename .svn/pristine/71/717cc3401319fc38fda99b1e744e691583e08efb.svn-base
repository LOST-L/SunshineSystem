package com.heli.oa.sunshine.dao;

import com.heli.oa.sunshine.entity.Sunshine;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author 白驹
 */
@Repository
public interface SunshineDao {

    /** 增加阳光值记录*/
    void addSunshineRecord(Sunshine s);

    void delSunshineRecord(Integer sunshineRecordId);

    void updateSunshineRecord(Sunshine s);

    Double sumMoney();

    Double sumReceivedMoney();

    Double sumMoneyById(Sunshine s);

    Sunshine selectSunshineById(Integer sunshineRecordId);

    Sunshine selectSunshineByTaskRecordId(String taskRecordId);

    List<Sunshine> listSunshineByUserId(Integer userId);

    List<Sunshine> listMinusSunshine();

    List<Sunshine> listPlusSunshine();

    List<Sunshine> listSunshineMoney();

    List<Sunshine> listSunshineByMoneyStatus(String moneyStatus);
}
