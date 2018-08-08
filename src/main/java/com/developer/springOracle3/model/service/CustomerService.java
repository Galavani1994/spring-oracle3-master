package com.developer.springOracle3.model.service;

import com.developer.springOracle3.MyException;
import com.developer.springOracle3.entity.Customer;
import com.developer.springOracle3.model.repository.CustomerRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepo customerRepo;

    public void save(Customer customer) throws MyException {


        Customer save = customerRepo.save(customer);

    }

    public void delete(Customer customer) {
        customerRepo.deleteById(customer.getId());
    }

    public void update(Customer customer) {
        customerRepo.save(customer);

    }

    public List<Customer> findAll() {
        return customerRepo.findAll();
    }

}
