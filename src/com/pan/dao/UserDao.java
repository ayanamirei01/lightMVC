package com.pan.dao;

import com.pan.dto.QueryVo;
import com.pan.model.User;
import com.pan.model.UserVo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Author: PanWei
 * @Date: 2019-07-04 15:01
 * @Version 1.0
 */
@Repository
public interface UserDao {

    List<User> findAll();
    List<User> findAllByPage(QueryVo queryVo);

    User findById(Integer user_id);

    int updateById(UserVo user);
    int deleteById(Integer user_id);
    int insert(UserVo user);

    User login(@Param("login_name") String login_name,@Param("password") String password);
}
