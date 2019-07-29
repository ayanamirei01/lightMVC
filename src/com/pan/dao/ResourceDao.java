package com.pan.dao;

import com.pan.dto.QueryVo;
import com.pan.model.Resource;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Author: PanWei
 * @Date: 2019-07-04 15:01
 * @Version 1.0
 */
@Repository
public interface ResourceDao {

    List<Resource> findAll();

    List<Resource> findByPage(QueryVo queryVo);

    Resource findByAny(QueryVo queryVo);

    List<String> getResourceUrl(QueryVo queryVo);

    int updateById(Resource resource);

    int insert(Resource resource);

    int deleteById(Integer resource_id);
}
