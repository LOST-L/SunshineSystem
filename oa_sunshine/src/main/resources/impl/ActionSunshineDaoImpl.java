package com.heli.dao.impl;

import com.heli.dao.ActionSunshineDao;
import com.heli.model.ActionSunshine;
import lombok.Data;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author 白驹
 */
@Data
@Service
public class ActionSunshineDaoImpl implements ActionSunshineDao {
    @Autowired
    private SessionFactory sessionFactory;

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
    @Override
    public void addActionSunshine(ActionSunshine a) {
       Session session =  sessionFactory.openSession();
       session.save(a);
       session.close();
    }

    @Override
    public List<ActionSunshine> listActionSunshine() {
        Session session = sessionFactory.openSession();
        Query query = session.createQuery("from ActionSunshine ORDER BY action_time DESC");
        List<ActionSunshine> list = query.list();
        session.close();
        if (list.size() > 0){
            return list;
        }else{
            return null;
        }
    }
}
