package com.developer.springOracle3.model.repository;

import com.developer.springOracle3.entity.CPtable;
import com.developer.springOracle3.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public interface CPRepo extends JpaRepository<CPtable,Integer> {

    @Query("from CPtable where cuid=?1")
    List<CPtable> findByCuid(String cuid);
}
