package com.heli.oa.sunshine.dao;

import com.heli.oa.sunshine.entity.SunshineAction;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author 白驹
 */
@Repository
public interface SunshineActionDao {
    void addSunshineAction(SunshineAction a);
    List<SunshineAction> listSunshineAction();
}
