package com.developer.springOracle3.model.service;

import com.developer.springOracle3.MyException;
import com.developer.springOracle3.entity.Customer;
import com.developer.springOracle3.entity.CustomerDto;
import com.developer.springOracle3.model.repository.CPRepo;
import com.developer.springOracle3.model.repository.CustomerRepo;
import com.developer.springOracle3.util.ObjectConverter;
import com.developer.springOracle3.util.date.FDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

@Service
@CrossOrigin(origins = "http://localhost:4200")
public class CustomerService {

    @Autowired
    private CustomerRepo customerRepo;
    @Autowired
    private CPRepo cpRepo;

    public List<CustomerDto> findAll() throws ParseException {
        List<Customer> customers = customerRepo.findAll();
        List<CustomerDto> customerDtos = new ArrayList<>();
        for (Customer customer : customers) {
            CustomerDto customerDto = ObjectConverter.instance.getCloneObject(customer, CustomerDto.class);
            customerDto.setRegisterDate(FDate.Gregorian_to_Farsi(customer.getRegisterDate()));
            customerDto.setLastCome(FDate.Gregorian_to_Farsi(customer.getLastCome()));
            customerDtos.add(customerDto);
        }
        return customerDtos;
    }

    public void save(Customer customer) throws MyException {

        if (customer.getId()==null){
            customer.setMande(0l);
            customer.setRegisterDate(FDate.Farsi_to_Gregorian(customer.getRegisterDate()));
            customer.setLastCome(customer.getRegisterDate());
            customerRepo.save(customer);
        }else {
            customer.setRegisterDate(FDate.Farsi_to_Gregorian(customer.getRegisterDate()));
            customer.setLastCome(FDate.Farsi_to_Gregorian(customer.getLastCome()));
            customerRepo.save(customer);
        }
    }

    public List<CustomerDto> searchCustomer(String value) throws ParseException {
        List<Customer> customers = customerRepo.findByFirstNameORLastNameOrCuid(value, value, value);;
        List<CustomerDto> customerDtos = new ArrayList<>();
        for (Customer customer : customers) {
            CustomerDto customerDto = ObjectConverter.instance.getCloneObject(customer, CustomerDto.class);
            customerDto.setRegisterDate(FDate.Gregorian_to_Farsi(customer.getRegisterDate()));
            customerDto.setLastCome(FDate.Gregorian_to_Farsi(customer.getLastCome()));
            customerDtos.add(customerDto);
        }
        return customerDtos;

    }
    public void delete(int id) {
        customerRepo.deleteById(id);
    }

}
