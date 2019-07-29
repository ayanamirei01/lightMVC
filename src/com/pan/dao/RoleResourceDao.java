package com.pan.dao;

import com.pan.model.Resource;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * @Author: PanWei
 * @Date: 2019-07-18 9:15
 * @Version 1.0
 */

@Repository
public interface RoleResourceDao {

    int insert(@Param("roid") Integer roid,@Param("reid") Integer reid);
}
