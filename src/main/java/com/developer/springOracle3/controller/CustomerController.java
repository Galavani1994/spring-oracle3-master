package com.developer.springOracle3.controller;

import com.developer.springOracle3.MyException;
import com.developer.springOracle3.annotation.ControllerViewName;
import com.developer.springOracle3.entity.Customer;
import com.developer.springOracle3.model.repository.CustomerRepo;
import com.developer.springOracle3.model.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
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


    /* @RequestMapping("/customerPage")
     public ModelAndView doHOme() {
         ModelAndView mv = new ModelAndView("customer");
         mv.addObject("lists", customerService.findAll());
         return mv;
     }*/
    @RequestMapping("/customerPage")
    @CrossOrigin(origins = "http://localhost:4200")
    public List<Customer> doHOmee() {

        return customerService.findAll();

    }

    @RequestMapping("/customerPage1")
    public List<Customer> doHOmee1() {

        return customerService.findAll();

    }

    @RequestMapping("/saveCu")
    @CrossOrigin(origins = "http://localhost:4200")
    public ModelAndView saveCu(@RequestBody Customer customer) throws MyException {

        ModelAndView mv = new ModelAndView("redirect:/cu/customerPage");
        List<Customer> te = new ArrayList<>();
        te.add(customer);
        if (customer.getId() != null) {
            customerService.save(customer);
        } else {
            customer.setMande(0);
            customerService.save(customer);
        }

        return mv;
    }

    @RequestMapping("/saveCu1")
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
    public ModelAndView deleteCu(@PathVariable("id") int id, HttpServletRequest request) {
        ModelAndView mv = new ModelAndView("redirect:/cu/customerPage");
        Customer customer = new Customer();
        customer.setId(id);
        customerService.delete(customer);


        return mv;
    }

    @RequestMapping(value = "/deleteCu1/{id}", method = RequestMethod.GET)
    public ModelAndView deleteCu1(@PathVariable("id") int id, HttpServletRequest request) {
        ModelAndView mv = new ModelAndView("redirect:/cu/customerPage");
        Customer customer = new Customer();
        customer.setId(id);
        customerService.delete(customer);


        return mv;
    }

    @RequestMapping(value = "/resultCu", method = RequestMethod.GET)
    public ModelAndView showCu(@RequestParam("names") String firstName, @RequestParam("names") String lastName) {
        ModelAndView mv = new ModelAndView("customer");
        mv.addObject("lists", customerService.findbylastnaeOrfirstname(firstName, lastName));
        return mv;
    }

}
