package com.pan.controller;

import com.pan.dto.QueryVo;
import com.pan.dto.Result;
import com.pan.model.SessionInfo;
import com.pan.model.Tree;
import com.pan.model.User;
import com.pan.model.UserVo;
import com.pan.service.ResourceService;
import com.pan.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @Author: PanWei
 * @Date: 2019-07-04 15:02
 * @Version 1.0
 */
@Controller
@RequestMapping("/user")
public class UserController {

    @Resource
    private UserService userService;

    @Autowired
    private ResourceService resourceService;

    @RequestMapping("/login")
    @ResponseBody
    public Result<User> login(String login_name, String password, HttpSession session) {

        User user = userService.login(login_name,password);
        SessionInfo sessionInfo = (SessionInfo) session.getAttribute("sessionInfo");

        if(sessionInfo == null) {
            sessionInfo = new SessionInfo();
    }

        Result<User> result = null;
        if(user == null) {
            result = new Result<User>(false,"账号密码错误");
        } else {
            result = new Result<User>(true,"登录成功");
            sessionInfo.setUser(user);
            sessionInfo.setResourceList(resourceService.getResourceUrl(new QueryVo(user.getUser_id())));
            session.setAttribute("sessionInfo",sessionInfo);
        }

        return result;
    }

    @RequestMapping("/index")
    public String index() {
        return "index";
    }


    @RequestMapping("/tree")
    @ResponseBody
    public List<Tree> tree() {
        return userService.tree();
    }

    @RequestMapping("/manager")
    public String manager(){
        return "admin/user";
    }

    @RequestMapping("/dataGrid")
    @ResponseBody
    public List<User> dataGrid(){
        List<User> list = userService.findAll();

        return list;
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.POST)
    @ResponseBody
    public Result<User> delete(@PathVariable("id")Integer id) {
        Result<User> result = null;

        if(userService.deleteById(id) > 0) {
           result =  new Result<User>(true,"删除成功");
        } else {
           result = new Result<User>(false,"删除失败");
        }
        return result;
    }


    @RequestMapping(value="/editPage/{uid}")
    public String editPage(@PathVariable("uid")Integer uid, Model model) {

        User user = userService.findById(uid);
        model.addAttribute("u",user);
        model.addAttribute("action","edit");

        return "admin/userAdd";
    }

    @RequestMapping(value = "/edit",method = RequestMethod.PUT)
    @ResponseBody
    public Result<String> edit(UserVo userVo) {

        int i = userService.updateById(userVo);
        Result<String> result = new Result<String>();
        if(i > 0) {
            result.setSuccess(true);
            result.setError("修改成功");
        } else {
            result.setSuccess(false);
            result.setError("修改失败");
        }
        return result;
    }

    @RequestMapping("/addPage")
    public String addPage(Model model) {
        model.addAttribute("action","add");
        return "admin/userAdd";
    }

    //添加用户
    @RequestMapping(value = "/add",method = RequestMethod.POST)
    @ResponseBody
    public Result<String> add(UserVo userVo) {
        int i = userService.insert(userVo);
        Result<String> result = new Result<String>();
        if(i > 0) {
            result.setSuccess(true);
            result.setError("添加成功");
        } else {
            result.setSuccess(false);
            result.setError("添加失败");
        }
        return result;
    }

    @RequestMapping("/logout")
    public String logout(HttpSession session) {
        session.setAttribute("sessionInfo",null);
        return "login";
    }
}
