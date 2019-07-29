package com.pan.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;

/**
 * @Author: PanWei
 * @Date: 2019-07-04 11:53
 * @Version 1.0
 */
@Getter
@Setter
@ToString
public class Dept {
    private Integer dept_id;
    private String dept_name;
    private String dept_address;
    private String dept_code;
    private String dept_icon;
    private Integer dept_seq;
    private Date dept_createTime;
    private Integer dept_pid;
    private List<User> users;
}
