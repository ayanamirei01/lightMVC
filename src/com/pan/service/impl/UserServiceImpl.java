package com.pan.service.impl;

import com.github.pagehelper.PageHelper;
import com.pan.dao.UserDao;
import com.pan.dao.UserRoleDao;
import com.pan.dto.QueryVo;
import com.pan.model.Role;
import com.pan.model.Tree;
import com.pan.model.User;
import com.pan.model.UserVo;
import com.pan.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author: PanWei
 * @Date: 2019-07-04 16:00
 * @Version 1.0
 */
@Service("userService")
public class UserServiceImpl implements UserService {

    @Resource
    private UserDao userDao;

    @Resource
    private UserRoleDao userRoleDao;

    @Override
    public List<User> findAll() {
        return userDao.findAll();
    }

    @Override
    public PageHelper findAllByPage(QueryVo queryVo) {
        userDao.findAllByPage(queryVo);
        return null;
    }

    @Override
    public User findById(Integer user_id) {
        return userDao.findById(user_id);
    }

    @Override
    public User login(String login_name, String password) {
        return userDao.login(login_name,password);
    }

    @Override
    public int updateById(UserVo user) {
        if(user.getUser().getRole().getRole_id() == 1) {
            user.getUser().setUser_type(1);
        } else {
            user.getUser().setUser_type(0);
        }
        return userDao.updateById(user);
    }

    @Override
    public int deleteById(Integer user_id) {
        return userDao.deleteById(user_id);
    }

    @Override
    public int insert(UserVo userVo) {
        userVo.getUser().setUser_state(1);
        userVo.getUser().setUser_isDefault(1);
        if(userVo.getUser().getRole().getRole_id() == 1) {
            userVo.getUser().setUser_type(1);
        } else {
            userVo.getUser().setUser_type(0);
        }
        int id = userDao.insert(userVo);

        return userRoleDao.insert(userVo.getUser().getUser_id(),userVo.getUser().getRole().getRole_id());
    }

    @Override
    public List<Tree> tree() {
        List<Tree> lt = new ArrayList<Tree>();
        List<Tree> ct = new ArrayList<Tree>();
        List<User> list = userDao.findAll();

        for(User u : list) {
            Tree tree = new Tree();
            tree.setId(u.getUser_id().toString());
            tree.setText(u.getUser_name());

            lt.add(tree);
        }
        return lt;
    }
}
