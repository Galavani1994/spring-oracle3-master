package com.developer.springOracle3.model.repository;

import com.developer.springOracle3.entity.CPtable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.CrossOrigin;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;

@Repository
@CrossOrigin(origins = "http://localhost:4200")
public interface CPRepo extends JpaRepository<CPtable, Integer> {

    @Query("from CPtable where cuid=?1")
    List<CPtable> findByCuid(String cuid);

    @Query("from CPtable where kaladate between ?1 and ?2")
    List<CPtable> findByKaladate(Date kaladate, Date todate);

    @Query("from CPtable where kaladate between ?1 and ?2 and prid=?3")
    List<CPtable> findByKaladateAndPrid(Date kaladate, Date todate, String prid);


    @Query("SELECT nvl(SUM(pay),0) from CPtable where kaladate between ?1 and ?2")
    String findByPay(Date kaladate, Date todae);

    @Query("SELECT nvl(SUM(discount),0) from CPtable where kaladate between ?1 and ?2")
    String findByDiscount(Date fromdate, Date todate);

    @Query("select nvl(sum (remain),0) from CPtable where cuid=?1")
    List<CPtable> findByMande(String cuid);

    @Transactional
    @Modifying
    @Query("delete from CPtable where cuid=?1")
    void deleteByCuid(String cuid);

}
