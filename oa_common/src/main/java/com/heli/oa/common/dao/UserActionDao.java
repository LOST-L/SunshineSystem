package com.heli.oa.common.dao;

import com.heli.oa.common.entity.UserAction;
import org.springframework.stereotype.Repository;

/**
 * Created with IntelliJ IDEA.
 * User: 白驹
 * Date: 2019/1/21
 * Time: 15:44
 * Description:
 */
@Repository
public interface UserActionDao {
    void addUserAction(UserAction userAction);
}
