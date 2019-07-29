package com.pan.util;

import com.pan.model.Resource;
import com.pan.model.Tree;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: PanWei
 * @Date: 2019-07-17 11:43
 * @Version 1.0
 */
@Component
public class GetTree {

    public List<Tree> parentTree(List<Resource> list) {
        List<Tree> pListTree = new ArrayList<Tree>();
        for(Resource re : list) {
            if(re.getResource_pid() == 0) {
                Tree pTree = new Tree();
                pTree.setId(re.getResource_id().toString());
                pTree.setText(re.getResource_name());
                pTree.setAttributes(re);
                pTree.setIconCls(re.getResource_icon());

                pTree.setChildren(childrenTree(list,re.getResource_id()));
                pListTree.add(pTree);
            }
        }
        return pListTree;

    }

    private List<Tree> childrenTree(List<Resource> list,Integer id) {
        List<Tree> cListTree = new ArrayList<Tree>();
        List<Resource> temp = new ArrayList<Resource>();
        for(Resource re : list) {
            if(re.getResource_pid()==id) {
                temp.add(re);
            }
        }

        for(Resource re : temp) {

            Tree pTree = new Tree();
            pTree.setId(re.getResource_id().toString());
            pTree.setText(re.getResource_name());
            pTree.setAttributes(re);
            pTree.setIconCls(re.getResource_icon());
            pTree.setChildren(childrenTree(list, re.getResource_id()));
            if(pTree.getChildren() == null) {
                pTree.setState("open");
            }
            pTree.setPid(re.getResource_pid().toString());
            cListTree.add(pTree);

        }

        if(cListTree.isEmpty()) {
            return null;
        }

        return cListTree;
    }
}
