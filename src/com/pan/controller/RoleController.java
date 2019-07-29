package com.pan.controller;

import com.pan.dto.Result;
import com.pan.model.Role;
import com.pan.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @Author: PanWei
 * @Date: 2019-07-04 15:02
 * @Version 1.0
 */
@Controller
@RequestMapping("/role")
public class RoleController {

    @Autowired
    private RoleService roleService;

    @RequestMapping("/manager")
    public String manager(){
        return "admin/role";
    }

    @RequestMapping("/showAll")
    @ResponseBody
    public List<Role> showAll() {
        return roleService.findAll();

    }

    @RequestMapping("/grantPage")
    public String grantPage(Integer role_id,Model model) {

        model.addAttribute("role_id",role_id);
        return "admin/roleGrant";
    }

    @RequestMapping("/get")
    @ResponseBody
    public Role get(Integer role_id) {
        return roleService.findById(role_id);

    }

    @RequestMapping("/grant")
    @ResponseBody
    public Result<String> grant(String resources, Integer role_id) {

        String[] ids = null;
        if(resources != "") ids = resources.split(",");
        Result<String> result = null;
        try {
            roleService.grant(ids,role_id);
            result = new Result<String>(true,"授权成功");
        } catch (Exception e) {
            result = new Result<String>(false,"授权失败");
        }

        return result;
    }

    @RequestMapping("/editPage")
    public String editPage(Integer role_id,Model model) {
        Role role = roleService.findById(role_id);
        model.addAttribute("role",role);
        model.addAttribute("action","edit");
        return "admin/roleAdd";
    }

    @RequestMapping("/edit")
    @ResponseBody
    public Result<String> edit(Role role) {
        int n = roleService.updateById(role);

        if(n > 0) {
            return new Result<String>(true,"修改成功");
        } else {
            return new Result<String>(false,"修改失败");
        }

    }
}
