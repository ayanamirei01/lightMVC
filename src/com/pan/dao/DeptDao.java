package com.pan.dao;

import com.pan.dto.QueryVo;
import com.pan.model.Dept;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Author: PanWei
 * @Date: 2019-07-04 15:01
 * @Version 1.0
 */
@Repository
public interface DeptDao {

    public List<Dept> findAll();

    public List<Dept> findByPage(QueryVo queryVo);

    int updateById(Dept dept);
    int insert(Dept dept);
    int deleteById(Integer dept_id);
}
