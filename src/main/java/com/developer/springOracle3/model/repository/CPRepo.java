package com.developer.springOracle3.model.repository;

import com.developer.springOracle3.entity.CPtable;
import com.developer.springOracle3.entity.Customer;
import oracle.sql.DATE;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Repository
public interface CPRepo extends JpaRepository<CPtable,Integer> {

    @Query("from CPtable where cuid=?1")
    List<CPtable> findByCuid(String cuid);

    @Query("from CPtable where kaladate between ?1 and ?2")
    List<CPtable> findByKaladate(Date kaladate, Date todate);

    @Query("SELECT nvl(SUM(pay),0) from CPtable where kaladate between ?1 and ?2")
    String findByPay(Date kaladate,Date todae);


    @Query("select nvl(sum (remain),0) from CPtable where cuid=?1")
    List<CPtable> findByMande(String cuid);
}
