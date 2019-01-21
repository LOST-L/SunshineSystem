package com.heli.oa.sunshine.service;

import com.heli.oa.sunshine.entity.Sunshine;
import com.heli.oa.sunshine.entity.Task;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

/**  * @author 白驹  */
@Service
public interface SunshineService {

    String setPlusStar(Integer star);

    String setMinusStar(Integer star);

    void addSunshine(Sunshine s) throws IOException;

    void minusSunshine(Sunshine s) throws IOException;

    void minusSunshineN(Sunshine s, Task t) throws IOException;

    void minusMoney(Sunshine s);

    void delPlusSunshine(Sunshine s);

    void delMinusSunshine(Sunshine s);

    void delMinusMoneySunshine(Sunshine s);

    void updateSunshineRecord(Sunshine s);

    List<Double>  sumMoney();

    List<Sunshine> listMinusSunshine();

    List<Sunshine> listPlusSunshine();

    List<Sunshine> listSunshineMoney();

    List<Sunshine> listSunshineByUserId(Integer userId);
}
