package com.developer.springOracle3.model.service;

import com.developer.springOracle3.entity.CPtable;
import com.developer.springOracle3.entity.CPtableDto;
import com.developer.springOracle3.entity.Customer;
import com.developer.springOracle3.entity.Production;
import com.developer.springOracle3.model.repository.CPRepo;
import com.developer.springOracle3.model.repository.CustomerRepo;
import com.developer.springOracle3.util.FDate;
import com.developer.springOracle3.util.ObjectConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@CrossOrigin(origins = "http://localhost:4200")
public class CPService {

    @Autowired
    private CPService cpService;
    @Autowired
    private CPRepo cpRepo;
    @Autowired
    private Production prRepo;
    @Autowired
    private CustomerRepo customerRepo;


    public void save(CPtable cPtable) throws  ParseException {


            Customer customer = customerRepo.findByCuid(cPtable.getCuid());
            if (cPtable.getFactore().equals("0")) {
                cPtable.setRemain("0");
                cPtable.setMetercp("0");
                cPtable.setDiscount("0");
                cPtable.setPrice("0");
                customer.setMande(customer.getMande() - Integer.parseInt(cPtable.getPay()));
                customerRepo.save(customer);
                Date date = FDate.farsi_to_grogerian(cPtable.getKaladate());
                cPtable.setKaladate(date);
                CPtable save = cpRepo.save(cPtable);
            }
            if (!(cPtable.getFactore().equals("0"))) {
                customer.setMande(customer.getMande() + Integer.parseInt(cPtable.getRemain()));
                customerRepo.save(customer);
                Date date = FDate.farsi_to_grogerian(cPtable.getKaladate());
                cPtable.setKaladate(date);
                CPtable save = cpRepo.save(cPtable);
            }



    }


    public List<CPtableDto> findByCuid(String cuid) throws ParseException {

        List<CPtable> list = cpRepo.findByCuid(cuid);
        List<CPtableDto> list1 = new ArrayList<>();

        for (CPtable cPtable : list) {
            CPtableDto cPtableDto = ObjectConverter.instance.getCloneObject(cPtable, CPtableDto.class);
            Date date = cPtableDto.getKaladate();
            Date date1 = FDate.gro_to_farsi(date);
            cPtableDto.setKaladate(date1);
            list1.add(cPtableDto);
        }
        return list1;
    }

    public List<CPtableDto> findbykaladate(String kaladate, String todate) throws ParseException {

        /////////////
        Date date = FDate.formatter_to_date(kaladate);
        Date date1 = FDate.farsi_to_grogerian(date);
        //////////
        Date date2 = FDate.formatter_to_date(todate);
        Date date3 = FDate.farsi_to_grogerian(date2);
        /////////////
        List<CPtable> list = cpRepo.findByKaladate(date1, date3);
        List<CPtableDto> list1 = new ArrayList<>();
        for (CPtable cPtable : list) {
            CPtableDto cPtableDto = ObjectConverter.instance.getCloneObject(cPtable, CPtableDto.class);
            cPtableDto.setKaladate(FDate.gro_to_farsi(cPtable.getKaladate()));
            list1.add(cPtableDto);
        }
        return list1;
    }

    public String findsale(String kaladate, String todate) throws ParseException {
        Date date = FDate.formatter_to_date(kaladate);
        Date date1 = FDate.farsi_to_grogerian(date);
        Date date2 = FDate.formatter_to_date(todate);
        Date date3 = FDate.farsi_to_grogerian(date2);
        return cpRepo.findByPay(date1, date3);
    }

    public void deletById(int id) {
        Optional<CPtable> cPtable = cpRepo.findById(id);
        CPtable cPtable1 = cPtable.get();
        Customer customer = customerRepo.findByCuid(cPtable1.getCuid());
        if (cPtable1.getFactore().equals("0")) {
            customer.setMande(customer.getMande() + Integer.parseInt(cPtable1.getPay()));
        } else {
            customer.setMande(customer.getMande() - Integer.parseInt(cPtable1.getRemain()));
        }
        customerRepo.save(customer);
        cpRepo.deleteById(id);
    }
}
