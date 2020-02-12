package com.itheima.ssm.controller;

import com.itheima.ssm.domain.Role;
import com.itheima.ssm.domain.UserInfo;
import com.itheima.ssm.service.UserService;
import com.sun.org.apache.xpath.internal.operations.Mod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @RequestMapping("findAll.do")
    public ModelAndView findAllUser() throws Exception{
        List<UserInfo> userList = userService.findAll();
        ModelAndView mv = new ModelAndView();
        mv.addObject("userList",userList);
        mv.setViewName("user-list");
        return mv;
    }


    @RequestMapping("save.do")
    public String saveUser(UserInfo userInfo){
        userService.saveUser(userInfo);
        return "redirect:findAll.do";
    }

    @RequestMapping("/findById.do")
    public ModelAndView findUserById(String id){
        UserInfo user = userService.findUserById(id);
        ModelAndView mv = new ModelAndView();
        mv.addObject("user",user);
        mv.setViewName("user-show");
        return mv;
    }
    @RequestMapping("/findUserByIdAndAllRole")
    public ModelAndView findUserByIdAndAllRole(@RequestParam(name = "id",required = true) String userId){
        ModelAndView mv = new ModelAndView();
        UserInfo userInfo = userService.findUserById(userId);

        List<Role> rolesList = userService.findOtherRoleToUser(userId);

        mv.addObject("user",userInfo);
        mv.addObject("roleList",rolesList);
        mv.setViewName("user-role-add");
        return mv;
    }

    @RequestMapping("/addRoleToUser")
    public String addRoleToUser(@RequestParam(name = "userId",required = true) String userId,
                                @RequestParam(name = "ids") String[] ids){
        userService.addRoleToUser(userId,ids);
        return "redirect:findAll.do";
    }

}
