package com.developer.springOracle3.model.service;

import com.developer.springOracle3.MyException;
import com.developer.springOracle3.entity.Customer;
import com.developer.springOracle3.model.repository.CPRepo;
import com.developer.springOracle3.model.repository.CustomerRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.ArrayList;
import java.util.List;

@Service
@CrossOrigin(origins = "http://localhost:4200")
public class CustomerService {

    @Autowired
    private CustomerRepo customerRepo;
    @Autowired
    private CPRepo cpRepo;

    public void save(Customer customer) throws MyException {

        
        Customer save = customerRepo.save(customer);

    }

    public void delete(int id) {
        customerRepo.deleteById(id);
    }

    public void update(Customer customer) {
        customerRepo.save(customer);

    }

    public List<Customer> findAll() {
        return customerRepo.findAll();
    }

    public List<Customer> findbylastnaeOrfirstname(String firstname, String lastname) {
        List<Customer> customers = customerRepo.findByFirstNameORLastNameLike(firstname, lastname);
        List<Customer> customers1 = new ArrayList<>();
        for (Customer customer : customers) {
            customer.setFirstName("آقای " + customer.getFirstName());
            customers1.add(customer);
        }
        return customers1;

    }

}
