package com.developer.springOracle3.controller;

import com.developer.springOracle3.MyException;
import com.developer.springOracle3.annotation.ControllerViewName;
import com.developer.springOracle3.entity.Customer;
import com.developer.springOracle3.model.repository.CPRepo;
import com.developer.springOracle3.model.repository.CustomerRepo;
import com.developer.springOracle3.model.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@ControllerAdvice
@ControllerViewName("customer")
@RequestMapping("/cu")
public class CustomerController {

    @Autowired
    private CustomerService customerService;
    @Autowired
    private CustomerRepo customerRepo;
    @Autowired
    private CPRepo cpRepo;


    @GetMapping("/customerPage")
    @CrossOrigin(origins = "http://localhost:4200")
    public List<Customer> doHOmee() {

        return customerService.findAll();

    }

    @GetMapping("/customerPage1")
    public List<Customer> doHOmee1() {

        return customerService.findAll();
    }


    @PostMapping("/saveCu")
    @CrossOrigin(origins = "http://localhost:4200")
    public ModelAndView saveCu(@RequestBody Customer customer) throws MyException {

        if (customer.getId() == null) {
            customer.setMande(0l);
            customerService.save(customer);
        } else {
            customerService.save(customer);
        }
        return new ModelAndView("redirect:/cu/customerPage");
    }


    @PostMapping("/saveCu1")
    public ModelAndView saveCu1(@ModelAttribute Customer customer) throws MyException {

        ModelAndView mv = new ModelAndView("redirect:/cu/customerPage");
        if (customer.getId() != null) {
            Optional<Customer> optionalCustomer = customerRepo.findById(customer.getId());
            Customer customer1 = optionalCustomer.get();
            customer1.setFirstName(customer.getFirstName());
            customer1.setLastName(customer.getLastName());
            customer1.setAddressname(customer.getAddressname());
            customer1.setMande(customer.getMande());
            customerService.save(customer1);
        } else {
            customerService.save(customer);
        }

        return mv;
    }

    @RequestMapping(value = "/deleteCu/{id}", method = RequestMethod.GET)
    @CrossOrigin(origins = "http://localhost:4200")
    public void deleteCu(@PathVariable("id") int id) {

        Customer customer = customerRepo.findById(id).get();
        String cuid = customer.getCuid();
        customerRepo.deleteById(id);
        cpRepo.deleteByCuid(cuid);
    }

    @GetMapping("/findOneCustomer/{id}")
    @CrossOrigin(origins = "http://localhost:4200")
    public List<Object> findOneCustomer(@PathVariable("id") String id) {
        List<Object> objects = new ArrayList<>();
        objects.add(customerRepo.findByCuid(id));
        objects.add(cpRepo.findByCuid(id));
        return objects;
    }


    @RequestMapping(value = "/resultCu", method = RequestMethod.GET)
    public ModelAndView showCu(@RequestParam("names") String firstName, @RequestParam("names") String lastName) {
        ModelAndView mv = new ModelAndView("customer");
        mv.addObject("lists", customerService.findbylastnaeOrfirstname(firstName, lastName));
        return mv;
    }


}
