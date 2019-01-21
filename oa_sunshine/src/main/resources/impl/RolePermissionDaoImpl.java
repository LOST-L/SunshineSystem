package com.heli.dao.impl;

import com.heli.dao.RolePermissionDao;
import com.heli.model.Permission;
import com.heli.model.Role;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RolePermissionDaoImpl implements RolePermissionDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public List<Role> listRolesByUserName(String userNickName) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();

        SQLQuery query = session.createSQLQuery("select r.id, r.name,r.chinese from sunshine_user u " +
                        "left join sunshine_user_role ur " +
                        "on u.user_id = ur.uid " +
                        "left join sunshine_role r " +
                        "on r.id = ur.rid " +
                        "where u.user_nick_name =:userNickName"
        );
        query.setString("userNickName",userNickName);
        query.addEntity(Role.class);
        List<Role> list = (List<Role>)query.list();
        session.getTransaction().commit();
        session.close();

        if (list.size() > 0){
            return list;
        }else{
            return null;
        }
    }

    @Override
    public List<Permission> listPermissionsByUserName(String userName) {
        return null;
    }
}
