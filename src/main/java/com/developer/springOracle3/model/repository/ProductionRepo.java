package com.developer.springOracle3.model.repository;

import com.developer.springOracle3.entity.Customer;
import com.developer.springOracle3.entity.Production;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;


@Repository
@Transactional
public interface ProductionRepo extends JpaRepository<Production,Integer> {

    @Query("from Production where prName like %?1% ")
    List<Production> findByFirstNameORLastNameLike(String prName);

    @Query("from Production where prid=?1")
    Production findByPrid(String prid);

    @Query("from Production where meterPr=?1")
    Production findByMeterPr(String meterPr);

    @Query("SELECT pr.meterPr - nvl((select SUM(cp.metercp)from  CPtable cp where pr.prid=cp.prid ),0) FROM Production pr where pr.prid=?1")
    int remainMeter(String prid);
}
