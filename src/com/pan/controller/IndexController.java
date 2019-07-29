package com.pan.controller;

import com.pan.model.SessionInfo;
import com.pan.model.Tree;
import com.pan.model.User;
import com.pan.service.IndexService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @Author: PanWei
 * @Date: 2019-07-05 8:46
 * @Version 1.0
 */
@Controller
@RequestMapping("/index")
public class IndexController {

    @Resource
    private IndexService indexService;

    @RequestMapping("/tree")
    @ResponseBody
    public List<Tree> tree(HttpSession session) {
        SessionInfo si = (SessionInfo) session.getAttribute("sessionInfo");
        User user = si.getUser();
        List<Tree> list = indexService.tree(user.getUser_id());
        System.out.println(list.size());
        return list;
    }
}
