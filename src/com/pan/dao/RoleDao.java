package com.pan.dao;

import com.pan.dto.QueryVo;
import com.pan.model.Role;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Author: PanWei
 * @Date: 2019-07-04 15:01
 * @Version 1.0
 */
@Repository
public interface RoleDao {

    public List<Role> findAll();

    public List<Role> findByPage(QueryVo queryVo);

    int updateById(Role role);
    int insert(Role role);
    int deleteById(Integer role_id);

    int grant(@Param("ids") String[] resource_ids,@Param("role_id") Integer role_id);

    Role findById(Integer role_id);
}
