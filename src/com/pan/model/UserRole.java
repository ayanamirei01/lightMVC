package com.pan.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @Author: PanWei
 * @Date: 2019-07-04 11:54
 * @Version 1.0
 */
@Getter
@Setter
@ToString
public class UserRole {
    private User user;
    private Role role;
}
