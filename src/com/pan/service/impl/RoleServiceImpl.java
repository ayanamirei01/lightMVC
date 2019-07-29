package com.pan.service.impl;

import com.pan.dao.RoleDao;
import com.pan.dto.QueryVo;
import com.pan.model.Role;
import com.pan.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: PanWei
 * @Date: 2019-07-12 19:03
 * @Version 1.0
 */
@Service("roleService")
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleDao roleDao;

    @Override
    public List<Role> findAll() {
        return roleDao.findAll();
    }

    @Override
    public List<Role> findByPage(QueryVo queryVo) {
        return roleDao.findByPage(queryVo);
    }

    @Override
    public int updateById(Role role) {
        return roleDao.updateById(role);
    }

    @Override
    public int insert(Role role) {
        return roleDao.insert(role);
    }

    @Override
    public int deleteById(Integer role_id) {
        return roleDao.deleteById(role_id);
    }

    @Override
    public Role findById(Integer role_id) {
        return roleDao.findById(role_id);
    }

    @Override
    public int grant(String[] resource_ids, Integer role_id) {
        return roleDao.grant(resource_ids,role_id);
    }
}
