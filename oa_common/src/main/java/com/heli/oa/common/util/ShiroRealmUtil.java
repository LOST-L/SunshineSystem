package com.heli.oa.common.util;

import com.heli.oa.common.dao.UserDao;
import com.heli.oa.common.service.RolePermissionService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Set;

/**
 * @Author:核利-白驹
 * @Descriction:
 * @Date:Created in 14:11 2018/11/5
 */

public class ShiroRealmUtil extends AuthorizingRealm {

    @Autowired
    private UserDao userDao;
    @Autowired
    private RolePermissionService rolePermissionService;

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {

        //能进入到这里，表示账号已经通过验证了
        String userNickname =(String) principalCollection.getPrimaryPrincipal();
        //通过DAO获取角色和权限
        Set<String> roles = rolePermissionService.listRoles(userNickname);

        //授权对象
        SimpleAuthorizationInfo s = new SimpleAuthorizationInfo();
        //把通过DAO获取到的角色和权限放进去
        s.setRoles(roles);
        return s;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {

        //获取账号密码
        UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;
        String userNickname= (String)token.getPrincipal();
        String password= String.valueOf(token.getPassword());
        //获取数据库中的密码
        String passwordInDB = userDao.getUserByNickname(userNickname).getUserPassword();

        //如果为空就是账号不存在，如果不相同就是密码错误，但是都抛出AuthenticationException，而不是抛出具体错误原因，免得给破解者提供帮助信息
        if(null==passwordInDB || !passwordInDB.equals(password)) {
            throw new AuthenticationException();
        }

        //认证信息里存放账号密码, getName() 是当前Realm的继承方法,通常返回当前类名 :databaseRealm
        SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(userNickname,passwordInDB,getName());
        return authenticationInfo;
    }
}
