package com.developer.springOracle3.controller;

import com.developer.springOracle3.MyException;
import com.developer.springOracle3.annotation.ControllerViewName;
import com.developer.springOracle3.entity.Customer;
import com.developer.springOracle3.model.repository.CustomerRepo;
import com.developer.springOracle3.model.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@RestController
@ControllerAdvice
@ControllerViewName("customer")
public class CustomerController {

    @Autowired
    private CustomerService customerService;
    @Autowired
    private CustomerRepo customerRepo;



    @RequestMapping("/customerPage")
    public ModelAndView doHOme() {
        ModelAndView mv = new ModelAndView("customer");
        mv.addObject("lists", customerService.findAll());
        return  mv;
    }

    @RequestMapping("/saveCu")
    public ModelAndView saveCu(
            @RequestParam("id") String id,
            @RequestParam("cuid") String cuid,
            @RequestParam("firstname") String firstName,
            @RequestParam("lastname") String lastName,
            @RequestParam("addressname") String addressname) throws MyException {
        ModelAndView mv = new ModelAndView("redirect:/customerPage"
        );

        Customer customer;

        if (!id.isEmpty()) {
            customer = customerRepo.getOne(Integer.parseInt(id));
            if (!customer.getCuid().equals(cuid)) {
                throw new MyException("کدشخص قابل تغییر نمی باشد","1");
            }

        } else {
            customer = new Customer();

            customer.setCuid(cuid);
        }

        customer.setFirstName(firstName);
        customer.setLastName(lastName);
        customer.setAddressname(addressname);
        customerService.save(customer);

        return mv;
    }

    @RequestMapping(value = "/deleteCu/{id}", method = RequestMethod.GET)
    public ModelAndView deleteCu(@PathVariable("id") int id) {
        ModelAndView mv = new ModelAndView("redirect:/customerPage");
        Customer customer = new Customer();
        customer.setId(id);
        customerService.delete(customer);

        return mv;
    }

    @RequestMapping(value = "/resultCu", method = RequestMethod.GET)
    public ModelAndView showCu(@RequestParam("names") String firstName, @RequestParam("names") String lastName) {
        ModelAndView mv = new ModelAndView("customer");
        mv.addObject("lists", customerRepo.findByFirstNameORLastNameLike(firstName, lastName));
        return mv;
    }
}
