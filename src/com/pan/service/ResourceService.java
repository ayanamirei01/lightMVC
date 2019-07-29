package com.pan.service;

import com.pan.dto.QueryVo;
import com.pan.model.Resource;
import com.pan.model.Tree;

import java.util.List;

/**
 * @Author: PanWei
 * @Date: 2019-07-04 15:03
 * @Version 1.0
 */
public interface ResourceService {

    List<Resource> findAll();

    List<Resource> findByPage(QueryVo queryVo);

    Resource findByAny(QueryVo queryVo);

    List<String> getResourceUrl(QueryVo queryVo);

    int updateById(Resource resource);

    int insert(Resource resource,Integer roid);

    int deleteById(Integer resource_id);

    List<Tree> tree();


}
