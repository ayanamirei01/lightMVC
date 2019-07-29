package com.pan.dao;

import com.pan.model.Resource;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Author: PanWei
 * @Date: 2019-07-05 8:47
 * @Version 1.0
 */
@Repository
public interface IndexDao {

    List<Resource> findAll();

    List<Resource> findAllByUserId(Integer user_id);
}
