package com.pan.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotEmpty;
import java.sql.Date;
import java.sql.Time;
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
public class User {
    private Integer user_id;
    @NotEmpty(message = "用户名不能为空")
    private String login_name;
    @NotEmpty(message = "姓名不能空")
    private String user_name;
    @NotEmpty(message = "密码不能空")
    private String password;
    private Integer user_sex;
    private Integer user_age;
    private Integer user_type;
    private Integer user_isDefault;
    private Integer user_state;
    @JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    private Date user_createTime;
    private String user_phone;
    private Dept dept;
    private Role role;
}
