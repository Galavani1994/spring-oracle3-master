package com.developer.springOracle3.model.repository;

import com.developer.springOracle3.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.List;


@Repository
@CrossOrigin(origins = "http://localhost:4200")
public interface CustomerRepo extends JpaRepository<Customer,Integer> {




    @Query("from Customer where firstName like %?1% or lastName like %?2% or cuid like %?3%")
    List<Customer> findByFirstNameORLastNameOrCuid(String firstName,String lastName,String cuid);

    @Query("from Customer where cuid=?1 ")
    Customer findByCuid(String cuid);






}
