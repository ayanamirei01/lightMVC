package com.pan.service;

import com.pan.dto.QueryVo;
import com.pan.model.Dept;
import com.pan.model.Tree;

import java.util.List;

/**
 * @Author: PanWei
 * @Date: 2019-07-04 15:03
 * @Version 1.0
 */
public interface DeptService {

    public List<Dept> findAll();

    public List<Dept> findByPage(QueryVo queryVo);

    int updateById(Dept dept);
    int insert(Dept dept);
    int deleteById(Integer dept_id);

    List<Tree> tree();
}
