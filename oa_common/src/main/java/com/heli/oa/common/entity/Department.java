package com.heli.oa.common.entity;

import lombok.Data;

import javax.persistence.*;

/**
 * @Author:loong
 * @Descriction:
 * @Date:Created in 15:22 2018/10/29
 */

@Data
public class Department {

    private Integer departmentId;

    private String departmentName;
}
