package com.pan.service.impl;

import com.pan.dao.ResourceDao;
import com.pan.dao.RoleResourceDao;
import com.pan.dto.QueryVo;
import com.pan.model.Resource;
import com.pan.model.Tree;
import com.pan.service.ResourceService;
import com.pan.util.GetTree;
import org.apache.tools.ant.taskdefs.Get;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: PanWei
 * @Date: 2019-07-12 20:22
 * @Version 1.0
 */
@Service("resourceService")
public class ResourceServiceImpl implements ResourceService {

    @Autowired
    private ResourceDao resourceDao;

    @Autowired
    private RoleResourceDao roleResourceDao;

    @Autowired
    private GetTree getTree;

    @Override
    public List<Resource> findAll() {
        return resourceDao.findAll();
    }

    @Override
    public List<Resource> findByPage(QueryVo queryVo) {
        return resourceDao.findByPage(queryVo);
    }

    @Override
    public Resource findByAny(QueryVo queryVo) {
        return resourceDao.findByAny(queryVo);
    }

    @Override
    public List<String> getResourceUrl(QueryVo queryVo) {
        return resourceDao.getResourceUrl(queryVo);
    }

    @Override
    public int updateById(Resource resource) {
        return resourceDao.updateById(resource);
    }

    @Override
    public int insert(Resource resource,Integer roid) {
        resourceDao.insert(resource);

        int n = roleResourceDao.insert(roid,resource.getResource_id());
        return n;
    }

    @Override
    public int deleteById(Integer resource_id) {
        return resourceDao.deleteById(resource_id);
    }

    @Override
    public List<Tree> tree() {
        List<Resource> list = resourceDao.findAll();

        List<Tree> pListTree = getTree.parentTree(list);
        return pListTree;
    }


}
