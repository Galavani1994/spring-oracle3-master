package com.developer.springOracle3.controller;

import com.developer.springOracle3.MyException;
import com.developer.springOracle3.annotation.ControllerViewName;
import com.developer.springOracle3.entity.Customer;
import com.developer.springOracle3.model.repository.CPRepo;
import com.developer.springOracle3.model.repository.CustomerRepo;
import com.developer.springOracle3.model.service.CustomerService;
import com.developer.springOracle3.util.CustomerInfoPdf;
import com.itextpdf.text.DocumentException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.io.*;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

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


    @GetMapping("/resultCu/{value}")
    @CrossOrigin(origins = "http://localhost:4200")
    public List<Customer> showCu(@PathVariable("value") String value) {

        return customerRepo.findByFirstNameORLastNameOrCuid(value, value, value);
    }

    @GetMapping(value = "/customerInfoPrint/{id}",produces = MediaType.APPLICATION_PDF_VALUE)
    @CrossOrigin(origins = "http://localhost:4200")
    public ResponseEntity<InputStreamResource> customerInfoPrint(@PathVariable("id") int id) throws DocumentException, ParseException, IOException {

        Customer customer=customerRepo.findById(id).get();
        ByteArrayInputStream bis=CustomerInfoPdf.customerPrint(customer);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "attachment; filename=customerInfo.pdf");

        return ResponseEntity
                .ok()
                .headers(headers)
                .contentType(MediaType.APPLICATION_PDF)
                .body(new InputStreamResource(bis));
    }


}
