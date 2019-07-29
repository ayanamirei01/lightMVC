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
public class Resource {
    private Integer resource_id;
    private String resource_name;
    private String resource_url;
    private String resource_remark;
    private String resource_icon;
    private Integer resource_pid;
    private Integer resource_seq;
    private Integer resource_state;
    private Integer resource_type;
    private Date resource_createTime;
    private List<Role> roles;
}
