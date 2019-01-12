package com.developer.springOracle3.controller;

import com.developer.springOracle3.MyException;
import com.developer.springOracle3.annotation.ControllerViewName;
import com.developer.springOracle3.entity.Production;
import com.developer.springOracle3.entity.ProductionDto;
import com.developer.springOracle3.model.repository.ProductionRepo;
import com.developer.springOracle3.model.service.ProductionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.text.ParseException;
import java.util.List;

@RestController
@ControllerViewName("production")
@RequestMapping("/pr/")
public class ProductionController {

    private String uploadFile = "D:\\productionImage\\";
    @Autowired
    private ProductionService prService;
    @Autowired
    private ProductionRepo prRepo;

    @RequestMapping("/productionPage")
    @CrossOrigin(origins = "http://localhost:4200")
    public List<ProductionDto> jsonProduction() throws ParseException {

        return prService.findAll();
    }

    @RequestMapping("/savePr")
    @CrossOrigin(origins = "http://localhost:4200")
    public void savePr(@RequestBody Production production) throws MyException {
        prService.save(production);
    }


    @RequestMapping(value = "/deletePr/{id}", method = RequestMethod.GET)
    @CrossOrigin(origins = "http://localhost:4200")
    public ModelAndView deletePr1(@PathVariable("id") int id) {
        ModelAndView mv = new ModelAndView("redirect:/pr/productionPage");
        Production production = new Production();
        production.setId(id);
        prService.delete(production);

        return mv;
    }

    @GetMapping("/findOneProduction/{id}")
    @CrossOrigin(origins = "http://localhost:4200")
    public Production findOneProduction(@PathVariable("id") String id) {

        Production production = null;
        production = prRepo.findByPrid(id);
        production.setRemainMeter(prRepo.remainMeter(id));
        return production;
    }
/*
این بخش برای جستجوی کالا با نام یا کد کالا صورت می گیرد
*/
    @GetMapping("/resultPr/{value}")
    @CrossOrigin(origins = "http://localhost:4200")
    public List<ProductionDto> showProduction(@PathVariable("value") String value) throws ParseException {
        return prService.showProduction(value);
    }


}
