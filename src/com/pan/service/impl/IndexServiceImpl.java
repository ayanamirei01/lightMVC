package com.pan.service.impl;

import com.pan.dao.IndexDao;
import com.pan.model.Resource;
import com.pan.model.Tree;
import com.pan.service.IndexService;
import com.pan.util.GetTree;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @Author: PanWei
 * @Date: 2019-07-05 8:48
 * @Version 1.0
 */
@Service("indexService")
public class IndexServiceImpl implements IndexService {

    @Autowired
    private IndexDao indexDao;

    @Autowired
    private GetTree getTree;
    @Override
    public List<Tree> tree(Integer user_id) {
        List<Resource> list = indexDao.findAllByUserId(user_id);
        List<Tree> pListTree = getTree.parentTree(list);
        return pListTree;
    }



}
