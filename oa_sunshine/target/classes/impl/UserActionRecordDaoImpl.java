package com.heli.dao.impl;

import com.heli.dao.UserActionRecordDao;
import com.heli.model.UserActionRecordBean;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserActionRecordDaoImpl implements UserActionRecordDao {
    @Autowired
    private SessionFactory sessionFactory;

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void save(UserActionRecordBean uar) {
        Session session = sessionFactory.openSession();
        session.save(uar);
        session.close();
    }
}
