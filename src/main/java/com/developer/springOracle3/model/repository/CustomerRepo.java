package com.developer.springOracle3.model.repository;

import com.developer.springOracle3.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;


@Repository
@Transactional
public interface CustomerRepo extends JpaRepository<Customer,Integer> {




    @Query("from Customer where firstName like %?1% or lastName like %?2%")
    List<Customer> findByFirstNameORLastNameLike(String fristName,String lastName);
    @Query("from Customer where cuid=?1 ")
    Customer findByCuid(String cuid);




}
