package com.developer.springOracle3.controller;

import com.developer.springOracle3.MyException;
import com.developer.springOracle3.entity.Customer;
import com.developer.springOracle3.entity.Production;
import com.developer.springOracle3.model.repository.ProductionRepo;
import com.developer.springOracle3.model.service.ProductionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class ProductionController {

    @Autowired
    private ProductionService prService;
    @Autowired
    private ProductionRepo prRepo;

    @RequestMapping("/productionPage")
    public ModelAndView productionPage()
    {
        ModelAndView mv=new ModelAndView("production");
        mv.addObject("prlists",prService.findAll());
        return mv;
    }
    @RequestMapping("/savePr")
    public ModelAndView savePr(
            @RequestParam("id") String id,
            @RequestParam("prid") String prid,
            @RequestParam("prname") String prName,
            @RequestParam("meter") String meter) throws MyException {

        ModelAndView mv=new ModelAndView("redirect:/productionPage");

        Production production;
        if(!id.isEmpty())
        {
           production=prRepo.getOne(Integer.parseInt(id));
           if(!production.getPrid().equals(prid)){
               throw new MyException();
           }
        }
        else
        {
            production=new Production();

            production.setPrid(prid);
        }

        production.setPrName(prName);
        production.setMeter(meter);

        prService.save(production);
        return mv;
    }
    @RequestMapping(value = "/deletePr/{id}", method = RequestMethod.GET)
    public ModelAndView deletePr(@PathVariable("id") int id) {
        ModelAndView mv = new ModelAndView("redirect:/productionPage");
        Production production=new Production();
        production.setId(id);
        prService.delete(production);

        return mv;
    }


    @RequestMapping(value = "/resultPr", method = RequestMethod.GET)
    public ModelAndView showCu(@RequestParam("names") String firstName) {
        ModelAndView mv = new ModelAndView("production");
        mv.addObject("prlists", prRepo.findByFirstNameORLastNameLike(firstName));
        return mv;
    }


}
