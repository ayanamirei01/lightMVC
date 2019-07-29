package com.pan.controller;

import com.pan.model.Dept;
import com.pan.model.Tree;
import com.pan.service.DeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @Author: PanWei
 * @Date: 2019-07-04 15:02
 * @Version 1.0
 */
@Controller
@RequestMapping("/dept")
public class DeptController {

    @Autowired
    private DeptService deptService;

    @RequestMapping("/manager")
    public String manager() {
        return "admin/dept";
    }

    @RequestMapping("/showAll")
    @ResponseBody
    public List<Dept> showAllDept() {
        return deptService.findAll();
    }

    @RequestMapping("/tree")
    @ResponseBody
    public List<Tree> tree() {
        return deptService.tree();
    }
}
