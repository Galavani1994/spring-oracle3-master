package com.developer.springOracle3.controller;

import com.developer.springOracle3.model.repository.CPRepo;
import com.developer.springOracle3.model.repository.CustomerRepo;
import com.developer.springOracle3.model.repository.ProductionRepo;
import com.developer.springOracle3.model.service.CustomerService;
import com.developer.springOracle3.model.service.ProductionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class CPController {

    @Autowired
    private CustomerService customerService;
    @Autowired
    private CustomerRepo customerRepo;
    @Autowired
    private ProductionService prService;
    @Autowired
    private ProductionRepo prRepo;

    @Autowired
    private CPRepo cpRepo;

    @RequestMapping("/showCustomer")
    public ModelAndView showCu(@RequestParam("cuid") String cuid) {
        ModelAndView mv = new ModelAndView("index");
        mv.addObject("listcu", customerRepo.findByCuid(cuid));
        return mv;
    }
    @RequestMapping("/showproduction")
    public ModelAndView showPr(@RequestParam("cuid") String cuid,@RequestParam("prid") String prid)
    {
        ModelAndView mv=new ModelAndView("index");
        mv.addObject("listcu", customerRepo.findByCuid(cuid));
        mv.addObject("listpr",prRepo.findByPrid(prid));

        return mv;
    }


}
