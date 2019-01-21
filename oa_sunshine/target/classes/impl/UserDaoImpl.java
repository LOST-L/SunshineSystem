package com.heli.dao.impl;

import com.heli.dao.UserDao;
import com.heli.model.User;
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
public class UserDaoImpl implements UserDao{

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void addUser(User u) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();

        session.save(u);

        session.getTransaction().commit();
        session.close();
    }

    @Override
    public void deleteUser(User u) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();

        User user = session.get(User.class,u.getUserId());
        session.delete(user);

        session.getTransaction().commit();
        session.close();
    }

    @Override
    public void updateUser(User u){
        Session session = sessionFactory.openSession();
        session.beginTransaction();

        session.update(u);

        session.getTransaction().commit();
        session.close();
    }

    @Override
    public User getUser(Integer userId) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();

        User user = session.get(User.class,userId);

        session.getTransaction().commit();
        session.close();

        return user;
    }

    @Override
    public User getUserByNickname(String userNickName) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();

        Query query = session.createQuery("from User u where u.userNickName=:userNickName");
        query.setString("userNickName",userNickName);
        List<User> list = query.list();

        session.getTransaction().commit();
        session.close();

        if (list.size() > 0){
            return list.get(0);
        }else{
            return null;
        }
    }

    @Override
    public List<User> listUser() {
        Session session = sessionFactory.openSession();
        session.beginTransaction();

        Query query = session.createQuery("from User");
        List<User> list = query.list();

        session.getTransaction().commit();
        session.close();

        if (list.size() > 0){
            return list;
        }else{
            return null;
        }
    }

    @Override
    public List<User> listUserByCity(String city) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();

        Query query = session.createQuery("from User where city =:city");
        query.setString("city",city);
        List<User> list = query.list();

        session.getTransaction().commit();
        session.close();

        if (list.size() > 0){
            return list;
        }else{
            return null;
        }
    }

    @Override
    public List<User> listUserByDepartment(String dep) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();

        Query query = session.createQuery("from User u where u.userDepartment=:department");
        query.setString("department",dep);
        List<User> list = query.list();

        session.getTransaction().commit();
        session.close();

        return list;
    }
}
