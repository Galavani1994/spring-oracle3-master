package com.developer.springOracle3.controller;

import com.developer.springOracle3.annotation.ControllerViewName;
import com.developer.springOracle3.entity.CPtable;
import com.developer.springOracle3.entity.CPtableDto;
import com.developer.springOracle3.entity.Customer;
import com.developer.springOracle3.model.repository.CPRepo;
import com.developer.springOracle3.model.repository.CustomerRepo;
import com.developer.springOracle3.model.repository.ProductionRepo;
import com.developer.springOracle3.model.service.CPService;
import com.developer.springOracle3.model.service.CustomerService;
import com.developer.springOracle3.model.service.ProductionService;
import com.developer.springOracle3.util.date.Dates;
import com.developer.springOracle3.util.date.PrDates;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.List;
import java.util.Optional;

@RestController
@ControllerViewName("indexpage")
@RequestMapping("/cp")
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


    @PostMapping("/editcp")
    @CrossOrigin(origins = "http://localhost:4200")
    public void edited(@RequestBody  CPtable cPtable) throws ParseException {

        Customer customer = customerRepo.findByCuid(cPtable.getCuid());
        if (cPtable.getFactore().equals("0")) {
            customer.setMande(customer.getMande() + Integer.parseInt(cPtable.getPay()));
        } else {
            customer.setMande(customer.getMande() - Integer.parseInt(cPtable.getRemain()));
        }
        customerRepo.save(customer);
    }

    @PostMapping("/saveCP")
    @CrossOrigin(origins = "http://localhost:4200")
    public void saveCu(@RequestBody CPtable cPtable) throws ParseException {
        cpService.save(cPtable);
    }

    @RequestMapping(value = "/deletCP/{id}", method = RequestMethod.GET)
    @CrossOrigin(origins = "http://localhost:4200")
    public void delet(@PathVariable("id") int id) throws ParseException {
        Optional<CPtable> cPtable = cpRepo.findById(id);
        cpService.deletById(id);
    }

    @PostMapping("/reportCp")
    @CrossOrigin(origins = "http://localhost:4200")
    public List<Object> reportCp(@RequestBody Dates dates) throws ParseException {

        return cpService.reportCp(dates);
    }

    @PostMapping("/reportCpbyPr")
    @CrossOrigin(origins = "http://localhost:4200")
    public List<CPtableDto> reportCpbyPr(@RequestBody PrDates dates) throws ParseException {

        return cpService.reportCpbyPr(dates);
    }


}
