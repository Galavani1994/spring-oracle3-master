package com.developer.springOracle3.controller;

import com.developer.springOracle3.MyException;
import com.developer.springOracle3.annotation.ControllerViewName;
import com.developer.springOracle3.entity.Production;
import com.developer.springOracle3.model.repository.ProductionRepo;
import com.developer.springOracle3.model.service.ProductionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
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
    public List<Production> jsonProduction() throws ParseException {

        List<Production> productions = prRepo.findAll();
        List<Production> productions1 = new ArrayList<>();
        for (Production production : productions) {
            production.setRemainMeter(prRepo.remainMeter(production.getPrid()));
            productions1.add(production);
        }
        return productions1;
    }

    @RequestMapping("/savePr")
    @CrossOrigin(origins = "http://localhost:4200")
    public void savePr(@RequestBody Production production) throws MyException, ParseException, IOException {

        if (production.getId() == null) {
            prRepo.save(production);
        } else {
            /*byte[] fileByte = file.getBytes();
            Path path = Paths.get(uploadFile + production.getPrName() + ".jpg");
            Files.write(path, fileByte);*/
            prService.save(production);
        }
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

    @GetMapping("/resultPr/{value}")
    @CrossOrigin(origins = "http://localhost:4200")
    public List<Production> showProduction(@PathVariable("value") String value) {
        return prRepo.findByPrNameOrPrid(value, value);
    }




}
