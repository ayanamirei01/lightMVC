package com.pan.dao;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * @Author: PanWei
 * @Date: 2019-07-12 21:56
 * @Version 1.0
 */
@Repository
public interface UserRoleDao {

    int insert(@Param("user_id")Integer user_id,@Param("role_id")Integer role_id);
}
