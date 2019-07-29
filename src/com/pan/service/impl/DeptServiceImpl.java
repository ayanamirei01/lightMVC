package com.pan.service.impl;

import com.pan.dao.DeptDao;
import com.pan.dto.QueryVo;
import com.pan.model.Dept;
import com.pan.model.Tree;
import com.pan.service.DeptService;
import com.pan.util.GetTree;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: PanWei
 * @Date: 2019-07-12 18:38
 * @Version 1.0
 */
@Service("deptService")
public class DeptServiceImpl implements DeptService {

    @Autowired
    private DeptDao deptDao;

    @Autowired
    private GetTree getTree;
    @Override
    public List<Dept> findAll() {
        return deptDao.findAll();
    }

    @Override
    public List<Dept> findByPage(QueryVo queryVo) {
        return deptDao.findByPage(queryVo);
    }

    @Override
    public int updateById(Dept dept) {
        return deptDao.updateById(dept);
    }

    @Override
    public int insert(Dept dept) {
        return deptDao.insert(dept);
    }

    @Override
    public int deleteById(Integer dept_id) {
        return deptDao.deleteById(dept_id);
    }

    @Override
    public List<Tree> tree() {
        List<Dept> list = deptDao.findAll();
        List<Tree> treeList = new ArrayList<Tree>();

        for(Dept dept : list) {
            System.out.println(dept);
            Tree tree = new Tree();
            tree.setId(dept.getDept_id().toString());
            tree.setText(dept.getDept_name());
            tree.setIconCls(dept.getDept_icon());
            tree.setState("open");
            treeList.add(tree);
        }
        return treeList;
    }
}
