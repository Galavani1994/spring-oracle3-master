package com.developer.springOracle3.controller;

import com.developer.springOracle3.MyException;
import com.developer.springOracle3.annotation.ControllerViewName;
import com.developer.springOracle3.entity.Production;
import com.developer.springOracle3.entity.ProductionDto;
import com.developer.springOracle3.model.repository.ProductionRepo;
import com.developer.springOracle3.model.service.ProductionService;
import com.developer.springOracle3.util.FDate;
import com.developer.springOracle3.util.ObjectConverter;
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
    public List<Production> jsonProductio() throws ParseException {

        List<Production> productions = prRepo.findAll();
        List<Production> productions1 = new ArrayList<>();
        for (Production production : productions) {
            production.setRemainMeter(prRepo.remainMeter(production.getPrid()));
            productions1.add(production);
        }
        return productions1;
    }

    @RequestMapping("/productionPage1")
    public ModelAndView productionPage() throws ParseException {
        ModelAndView mv = new ModelAndView("production");
        List<Production> productions = prService.findAll();
        List<ProductionDto> list = new ArrayList();
        for (Production production : productions) {
            ProductionDto productionDto = ObjectConverter.instance.getCloneObject(production, ProductionDto.class);
            double a = prRepo.remainMeter(productionDto.getPrid());
            productionDto.setRemainMeter(a);
            productionDto.setTarikh(FDate.gro_to_farsi(productionDto.getTarikh()));
            list.add(productionDto);
        }

        mv.addObject("prlists", list);


        return mv;
    }

    @RequestMapping("/savePr")
    @CrossOrigin(origins = "http://localhost:4200")
    public ModelAndView savePr(@RequestBody Production production) throws MyException, ParseException, IOException {

        ModelAndView mv = new ModelAndView("redirect:/pr/productionPage");
        if (production.getId() == null) {
            prRepo.save(production);
        } else {
            /*byte[] fileByte = file.getBytes();
            Path path = Paths.get(uploadFile + production.getPrName() + ".jpg");
            Files.write(path, fileByte);*/
            prService.save(production);
        }


        return mv;
    }

    @RequestMapping("/savePr1")
    public ModelAndView savePr1(@ModelAttribute Production production) throws MyException, ParseException, IOException {

        ModelAndView mv = new ModelAndView("redirect:/pr/productionPage1");

        String str = production.getPrName();

        if (production.getId() != null) {
            Production production1;
            production1 = prRepo.findById(production.getId()).get();
            production1.setPrName(production.getPrName());
            production1.setMeterPr(production.getMeterPr());
            production1.setTarikh(production1.getTarikh());
            prRepo.save(production1);

        } else {
            /*byte[] fileByte = file.getBytes();
            Path path = Paths.get(uploadFile + production.getPrName() + ".jpg");
            Files.write(path, fileByte);*/
            production.setTarikh(FDate.farsi_to_grogerian(production.getTarikh()));
            prService.save(production);
        }


        return mv;
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
    public double findOneProduction(@PathVariable("id") String id) {
        return prRepo.remainMeter(id);
        /*Production production = null;
        production = prRepo.findByPrid(id);
        production.setRemainMeter(prRepo.remainMeter(id));
        return production;*/
    }

    @RequestMapping(value = "/resultPr", method = RequestMethod.GET)
    public ModelAndView showCu(@RequestParam("names") String firstName) {
        ModelAndView mv = new ModelAndView("production");
        mv.addObject("prlists", prRepo.findByFirstNameORLastNameLike(firstName));
        return mv;
    }


}
