package com.developer.springOracle3.controller;

import com.developer.springOracle3.MyException;
import com.developer.springOracle3.annotation.ControllerViewName;
import com.developer.springOracle3.entity.Customer;
import com.developer.springOracle3.entity.CustomerDto;
import com.developer.springOracle3.model.repository.CPRepo;
import com.developer.springOracle3.model.repository.CustomerRepo;
import com.developer.springOracle3.model.service.CPService;
import com.developer.springOracle3.model.service.CustomerService;
import com.developer.springOracle3.util.CustomerInfoPdf;
import com.itextpdf.text.DocumentException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.ByteArrayInputStream;
import java.io.IOException;
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
    @Autowired
    private CPService cpService;


    @GetMapping("/customerPage")
    @CrossOrigin(origins = "http://localhost:4200")
    public List<CustomerDto> doHOmee() throws ParseException {
        return customerService.findAll();
    }

    @PostMapping("/saveCu")
    @CrossOrigin(origins = "http://localhost:4200")
    public void saveCu(@RequestBody Customer customer) {
        try {
            customerService.save(customer);
        } catch (MyException e) {
            e.printStackTrace();
        }
    }

    @RequestMapping(value = "/deleteCu/{id}", method = RequestMethod.GET)
    @CrossOrigin(origins = "http://localhost:4200")
    public void deleteCu(@PathVariable("id") int id) {

        Customer customer = customerRepo.findById(id).get();
        String cuid = customer.getCuid();
        customerRepo.deleteById(id);
        cpRepo.deleteByCuid(cuid);
    }

    @PostMapping("/findOneCustomer")
    @CrossOrigin(origins = "http://localhost:4200")
    public List<Object> findOneCustomer(@RequestBody String id) throws ParseException {
        List<Object> objects = new ArrayList<>();
        objects.add(customerRepo.findByCuid(id));
        objects.add(cpService.findByCuid(id));
        return objects;
    }


    @GetMapping("/resultCu/{value}")
    @CrossOrigin(origins = "http://localhost:4200")
    public List<CustomerDto> searchCustomer(@PathVariable("value") String value) throws ParseException {

        return customerService.searchCustomer(value);
    }

    @GetMapping(value = "/customerInfoPrint/{id}",produces = MediaType.APPLICATION_PDF_VALUE)
    @CrossOrigin(origins = "http://localhost:4200")
    public ResponseEntity<InputStreamResource> customerInfoPrint(@PathVariable("id") int id) throws DocumentException, ParseException, IOException {

        Customer customer=customerRepo.findById(id).get();
        ByteArrayInputStream bis=CustomerInfoPdf.customerPrint(customer);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "inline; filename=customerInfo.pdf");

        return ResponseEntity
                .ok()
                .headers(headers)
                .contentType(MediaType.APPLICATION_PDF)
                .body(new InputStreamResource(bis));
    }


}
