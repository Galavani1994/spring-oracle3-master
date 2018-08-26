package com.developer.springOracle3.controller;

import com.developer.springOracle3.model.repository.CPRepo;
import com.developer.springOracle3.model.service.CPService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.text.ParseException;

@Controller
public class ReportController {
    @Autowired
    CPRepo cpRepo;
    @Autowired
    CPService cpService;

    @RequestMapping("/reportPage")
    public ModelAndView reportpage() {
        ModelAndView model = new ModelAndView("report");
        return model;
    }

    @RequestMapping("/resultreport")
    public ModelAndView resultreport(@RequestParam("fromdate") String kaladate, @RequestParam("todate") String todate) throws ParseException {
        ModelAndView mv = new ModelAndView("report");
        if (!kaladate.isEmpty() && !todate.isEmpty()) {
            mv.addObject("listcpbydate", cpService.findbykaladate(kaladate, todate));
            mv.addObject("salevalue",cpService.findsale(kaladate, todate));
            return mv;
        } else {
            return mv;
        }
    }


}
