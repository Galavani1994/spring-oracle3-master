package com.developer.springOracle3.controller;

import com.developer.springOracle3.MyException;
import com.developer.springOracle3.annotation.ControllerViewName;
import com.developer.springOracle3.entity.CPtable;
import com.developer.springOracle3.model.repository.CPRepo;
import com.developer.springOracle3.model.repository.CustomerRepo;
import com.developer.springOracle3.model.repository.ProductionRepo;
import com.developer.springOracle3.model.service.CPService;
import com.developer.springOracle3.model.service.CustomerService;
import com.developer.springOracle3.model.service.ProductionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.text.ParseException;

@RestController
@ControllerViewName("index")
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

    @Autowired
    private CPService cpService;


    /**
     *
     * @param cuid this one
     * @return
     * @throws ParseException
     */
    @RequestMapping(value = "/showCustomer",method = RequestMethod.POST)
    public ModelAndView showCu(@RequestParam("cuid") String cuid) throws ParseException {
        ModelAndView mv = new ModelAndView("index");
        mv.addObject("listcu", customerRepo.findByCuid(cuid));
        mv.addObject("listbycu", cpService.findByCuid(cuid));

        return mv;
    }

    @RequestMapping("/showproduction")
    public ModelAndView showPr(@RequestParam("cuid") String cuid, @RequestParam("prid") String prid) throws ParseException {
        ModelAndView mv = new ModelAndView("index");
        mv.addObject("liremain", prRepo.remainMeter(prid));
        mv.addObject("listcu", customerRepo.findByCuid(cuid));
        mv.addObject("listpr", prRepo.findByPrid(prid));
        mv.addObject("listbycu", cpService.findByCuid(cuid));

        return mv;
    }

    @RequestMapping("/saveCP")
    public ModelAndView saveCu(@ModelAttribute CPtable cPtable,
                               @RequestParam("cuid") String cuid) throws MyException, ParseException {
        ModelAndView mv = new ModelAndView("index");
        cpService.save(cPtable);
        mv.addObject("listcu", customerRepo.findByCuid(cuid));
        mv.addObject("listbycu", cpService.findByCuid(cuid));

        return mv;
    }

    @RequestMapping("/showCPtable")
    public ModelAndView saveCu() {
        ModelAndView mv = new ModelAndView("index");
        mv.addObject("listcp", cpRepo.findAll());
        return mv;
    }
}
