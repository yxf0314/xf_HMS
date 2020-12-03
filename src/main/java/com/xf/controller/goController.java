package com.xf.controller;

import com.xf.dao.TypeMapper;
import com.xf.pojo.RoomType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class goController {
    @Autowired
    TypeMapper typeMapper;
    @RequestMapping("/admin")
    public String toDash(String cur_username,Model model){
        model.addAttribute("cur_username",cur_username);
        return "admin";
    }
    @RequestMapping("/")
    public String toIndex(Model model){
        List<RoomType> typeList = typeMapper.SelectTypeList();
        model.addAttribute("typelist",typeList);
        return "html/index";
    }
    @RequestMapping("/toRoom")
    public String toRoom(){
        return "html/rooms";
    }
    @RequestMapping("/toRegis")
    public String toRegis(){
        return "views/user/reg";
    }
    @RequestMapping("/toLogin")
    public String toLogin(Model model, @RequestParam(value = "msg",required = false)String msg){
        model.addAttribute("msg",msg);
        return "views/user/login";
    }
    @RequestMapping("/toForget")
    public String toForget(){
        return "views/user/forget";
    }
}
