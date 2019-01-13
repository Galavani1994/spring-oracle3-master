package com.developer.springOracle3.model.service;

import com.developer.springOracle3.entity.CPtable;
import com.developer.springOracle3.entity.CPtableDto;
import com.developer.springOracle3.entity.Customer;
import com.developer.springOracle3.entity.Production;
import com.developer.springOracle3.model.repository.CPRepo;
import com.developer.springOracle3.model.repository.CustomerRepo;
import com.developer.springOracle3.util.ObjectConverter;
import com.developer.springOracle3.util.date.Dates;
import com.developer.springOracle3.util.date.FDate;
import com.developer.springOracle3.util.date.PrDates;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.text.ParseException;
import java.text.SimpleDateFormat;
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


    public void save(CPtable cPtable) {
        Customer customer = customerRepo.findByCuid(cPtable.getCuid());
        if (cPtable.getId() == null) {///////////////////////---------NEW RECORD----------////////
            if (cPtable.getFactore().equals("0")) {
                cPtable.setRemain("0");
                cPtable.setMetercp("0");
                cPtable.setDiscount("0");
                cPtable.setPrice("0");
                customer.setMande(customer.getMande() - Long.parseLong(cPtable.getPay()));
                customer.setLastCome(FDate.Farsi_to_Gregorian(cPtable.getKaladate()));
                customerRepo.save(customer);
                cPtable.setKaladate(FDate.Farsi_to_Gregorian(cPtable.getKaladate()));
                cpRepo.save(cPtable);
            } else {
                cPtable.setKaladate(FDate.Farsi_to_Gregorian(cPtable.getKaladate()));
                customer.setLastCome(cPtable.getKaladate());
                customer.setMande(customer.getMande() + Long.parseLong(cPtable.getRemain()));
                cpRepo.save(cPtable);
                customerRepo.save(customer);
            }
        } else if (cPtable.getId() != null)///////////////////////// UPDATE
        {

            if (cPtable.getFactore().equals("0")) {

                cPtable.setRemain("0");
                cPtable.setMetercp("0");
                cPtable.setDiscount("0");
                cPtable.setPrice("0");
                cPtable.setKaladate(FDate.Farsi_to_Gregorian(cPtable.getKaladate()));
                customer.setMande(customer.getMande() - Long.parseLong(cPtable.getPay()));
                customer.setLastCome(cPtable.getKaladate());
                customerRepo.save(customer);
                cpRepo.save(cPtable);
            } else {
                CPtable cPtable1 = cpRepo.findById(cPtable.getId()).get();
                cPtable.setPrName(cPtable1.getPrName());
                cPtable.setPrid(cPtable1.getPrid());
                customer.setMande(customer.getMande() + Integer.parseInt(cPtable.getRemain()));
                cPtable.setKaladate(FDate.Farsi_to_Gregorian(cPtable.getKaladate()));
                customer.setLastCome(cPtable.getKaladate());
                customerRepo.save(customer);
                cpRepo.save(cPtable);
            }
        }
    }

    public List<CPtableDto> findByCuid(String cuid) throws ParseException {

        List<CPtable> list = cpRepo.findByCuid(cuid);
        List<CPtableDto> list1 = new ArrayList<>();

        for (CPtable cPtable : list) {
            CPtableDto cPtableDto = ObjectConverter.instance.getCloneObject(cPtable, CPtableDto.class);
            cPtableDto.setKaladate(FDate.Gregorian_to_Farsi(cPtable.getKaladate()));
            list1.add(cPtableDto);
        }
        return list1;
    }

    public List<Object> reportCp(Dates dates) throws ParseException {
        List<Object> objects = new ArrayList<>();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
        try {

            Date fromDate = FDate.Farsi_to_Gregorian(sdf.parse(dates.getFromDate()));
            Date toDate = FDate.Farsi_to_Gregorian(sdf.parse(dates.getToDatee()));

            List<CPtable> cPtables = cpRepo.findByKaladate(fromDate, toDate);
            List<CPtableDto> cPtables1 = new ArrayList<>();

            for (CPtable cPtable : cPtables) {
                CPtableDto cPtableDto = ObjectConverter.instance.getCloneObject(cPtable, CPtableDto.class);
                cPtableDto.setKaladate(FDate.Gregorian_to_Farsi(cPtable.getKaladate()));
                cPtables1.add(cPtableDto);
            }
            String totalPay = cpRepo.findByPay(fromDate, toDate);
            String totalDiscount = cpRepo.findByDiscount(fromDate, toDate);

            objects.add(cPtables1);
            objects.add(totalPay);
            objects.add(totalDiscount);


        } catch (Exception e) {
            System.out.println(e.getMessage());
        }


        return objects;

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

    public List<CPtableDto> reportCpbyPr(PrDates dates) throws ParseException {


        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");

        Date fromDate = FDate.Farsi_to_Gregorian(sdf.parse(dates.getFromDate()));
        Date toDate = FDate.Farsi_to_Gregorian(sdf.parse(dates.getToDatee()));

        List<CPtable> cPtables =cpRepo.findByKaladateAndPrid(fromDate,toDate, dates.getPrInfo());
        List<CPtableDto> cPtables1 = new ArrayList<>();

        for (CPtable cPtable : cPtables) {
            CPtableDto cPtableDto = ObjectConverter.instance.getCloneObject(cPtable, CPtableDto.class);
            cPtableDto.setKaladate(FDate.Gregorian_to_Farsi(cPtable.getKaladate()));
            cPtables1.add(cPtableDto);
        }


        return cPtables1;
    }
}
