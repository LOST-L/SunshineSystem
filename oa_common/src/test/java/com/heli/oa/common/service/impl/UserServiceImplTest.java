package com.heli.oa.common.service.impl;

import com.heli.oa.common.service.DepartmentService;
import com.heli.oa.common.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

import static org.junit.Assert.*;

/**
 * Created with IntelliJ IDEA.
 * User: 白驹
 * Date: 2019/1/10
 * Time: 10:44
 * Description:
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring-common.xml")
public class UserServiceImplTest {
    @Autowired
    UserService userService ;

}