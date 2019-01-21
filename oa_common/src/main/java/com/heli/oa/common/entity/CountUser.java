package com.heli.oa.common.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Data
public class CountUser {

    private Integer userId;

    private String userNickname;

    private String userAccounts;

    private String userPassword;

    private String userImg;

    private String userType;

    private String userQqId;

    private String userWcId;

    private String userTime;

    private String userPostbox;

    private String userStId;

    private String userStTime;

    private Integer userLockType;

    private String userService;

    private String userStTempTime;

    private Integer userStTempId;

    private Integer page;

    private Integer limit;

    private List<CountUserPower> powerList;

}
