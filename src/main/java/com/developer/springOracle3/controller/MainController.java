package com.developer.springOracle3.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class MainController {
    @RequestMapping("/")
    public ModelAndView index() {
        ModelAndView mv = new ModelAndView("indexpage");
        return mv;
    }
    @RequestMapping("/logPage")
    public ModelAndView log() {
        ModelAndView mv = new ModelAndView("loginForm");
        return mv;
    }
    @RequestMapping("/404")
    public ModelAndView accessdenied() {
        ModelAndView mv = new ModelAndView("404");
        return mv;
    }
}
