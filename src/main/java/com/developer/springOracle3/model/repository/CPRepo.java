package com.developer.springOracle3.model.repository;

import com.developer.springOracle3.entity.CPtable;
import com.developer.springOracle3.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CPRepo extends JpaRepository<CPtable,Integer> {



}
