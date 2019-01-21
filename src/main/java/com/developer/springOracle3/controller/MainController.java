package com.developer.springOracle3.controller;

import com.developer.springOracle3.entity.users.UserTable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class MainController {
    @RequestMapping("/")
    public ModelAndView index() {
        ModelAndView mv = new ModelAndView("indexpage");
        return mv;
    }
    @RequestMapping("/login")
    public boolean login(@RequestBody UserTable user) {
        return
                user.getUsername().equals("user") && user.getPassword().equals("password");
    }
    @RequestMapping("/404")
    public ModelAndView accessdenied() {
        ModelAndView mv = new ModelAndView("404");
        return mv;
    }
}
