package com.pan.service;

import com.pan.dto.QueryVo;

import com.pan.model.Role;

import java.util.List;

/**
 * @Author: PanWei
 * @Date: 2019-07-04 15:03
 * @Version 1.0
 */
public interface RoleService {

    public List<Role> findAll();

    public List<Role> findByPage(QueryVo queryVo);

    int updateById(Role role);
    int insert(Role role);
    int deleteById(Integer role_id);

    Role findById(Integer role_id);

    int grant(String[] resource_ids,Integer role_id);
}
