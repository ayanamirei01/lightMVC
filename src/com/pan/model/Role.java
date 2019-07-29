package com.pan.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

/**
 * @Author: PanWei
 * @Date: 2019-07-04 11:53
 * @Version 1.0
 */
@Setter
@Getter
@ToString
public class Role {
    private Integer role_id;
    private String role_name;
    private Integer role_seq;
    private String role_remark;
    private Integer role_isDefault;

    private List<Resource> resources;
}
