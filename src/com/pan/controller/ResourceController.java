package com.pan.controller;

import com.pan.dto.QueryVo;
import com.pan.dto.Result;
import com.pan.model.Resource;
import com.pan.model.SessionInfo;
import com.pan.model.Tree;
import com.pan.service.ResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @Author: PanWei
 * @Date: 2019-07-04 15:02
 * @Version 1.0
 */
@Controller
@RequestMapping("/resource")
public class ResourceController {

    @Autowired
    private ResourceService resourceService;

    private SessionInfo sessionInfo = null;

    @RequestMapping("/manager")
    public String manager() {
        return "admin/resource";
    }


    @RequestMapping(value="/tree",method= RequestMethod.POST)
    @ResponseBody
    public List<Tree> tree() {
        List<Tree> list = resourceService.tree();

        return list;
    }

    @RequestMapping("/editPage")
    public String editPage(QueryVo queryVo, Model model) {
        Resource resource = resourceService.findByAny(queryVo);
        model.addAttribute("action","edit");
        model.addAttribute("resource",resource);
        return "admin/resourceAdd";
    }

    @RequestMapping("/addPage")
    public String addPage(Model model) {
        model.addAttribute("action","add");
        return "admin/resourceAdd";
    }

    @RequestMapping(value="/add",method=RequestMethod.POST)
    @ResponseBody
    public Result<Resource> add(Resource resource, HttpSession session) {
        sessionInfo = (SessionInfo) session.getAttribute("sessionInfo");
        Integer roid = sessionInfo.getUser().getRole().getRole_id();
        int n = resourceService.insert(resource,roid);
        Result<Resource> result = null;
        if(n > 0) {
            result = new Result<Resource>(true,"添加成功");
        } else {
            result = new Result<Resource>(false,"添加失败");
        }
        return result;
    }

    @PostMapping("/edit")
    @ResponseBody
    public Result<Resource> edit(Resource resource) {

        int n = resourceService.updateById(resource);

        return null;
    }

}
