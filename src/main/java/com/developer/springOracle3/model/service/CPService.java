package com.developer.springOracle3.model.service;

import com.developer.springOracle3.entity.CPtable;
import com.developer.springOracle3.entity.CPtableDto;
import com.developer.springOracle3.entity.Production;
import com.developer.springOracle3.model.repository.CPRepo;
import com.developer.springOracle3.util.FDate;
import com.developer.springOracle3.util.ObjectConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Service
public class CPService {

    @Autowired
    private CPService cpService;
    @Autowired
    private CPRepo cpRepo;
    @Autowired
    private Production prRepo;


    public void save(CPtable cPtable) throws ParseException {
        Date date = FDate.farsi_to_grogerian(cPtable.getKaladate());
        cPtable.setKaladate(date);
        CPtable save = cpRepo.save(cPtable);
    }


    public List<CPtable> findByCuid(String cuid) throws ParseException {

        List<CPtable> list = cpRepo.findByCuid(cuid);

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd");
        Calendar calendar = Calendar.getInstance();
        for (CPtable cPtable : list) {
            Date date = cPtable.getKaladate();
            calendar.setTime(date);
            String strdate = FDate.Farsi_from_Georgian(date);
            cPtable.setKaladate(formatter.parse(strdate));
        }
        return list;
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
        List<CPtableDto> list1=new ArrayList<>();
        for (CPtable cPtable : list) {
            CPtableDto cPtableDto=ObjectConverter.instance.getCloneObject(cPtable,CPtableDto.class);
            cPtableDto.setKaladate(FDate.gro_to_farsi(cPtable.getKaladate()));
            list1.add(cPtableDto);
        }
        return list1;
    }

    public String findsale(String kaladate, String todate) throws ParseException {
        Date date=FDate.formatter_to_date(kaladate);
        Date date1=FDate.farsi_to_grogerian(date);
        Date date2=FDate.formatter_to_date(todate);
        Date date3=FDate.farsi_to_grogerian(date2);
        return cpRepo.findByPay(date1, date3);
    }
}
