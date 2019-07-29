package com.pan.service;

import com.github.pagehelper.PageHelper;
import com.pan.dto.QueryVo;
import com.pan.model.Tree;
import com.pan.model.User;
import com.pan.model.UserVo;

import java.util.List;

/**
 * @Author: PanWei
 * @Date: 2019-07-04 15:04
 * @Version 1.0
 */
public interface UserService {
    List<User> findAll();
    PageHelper findAllByPage(QueryVo queryVo);

    User findById(Integer user_id);
    User login(String login_name,String password);
    int updateById(UserVo user);
    int deleteById(Integer user_id);
    int insert(UserVo user);
    List<Tree> tree();
}
