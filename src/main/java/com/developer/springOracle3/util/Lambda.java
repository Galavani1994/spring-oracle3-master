package com.developer.springOracle3.util;

import com.developer.springOracle3.util.date.FDate;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class Lambda {
    public static void main(String[] args) throws ParseException {
        /*List<String> items=new ArrayList<>();

        items.add("A");
        items.add("B");
        items.add("C");
        items.add("D");

        System.out.println(items.stream().count());
        items.stream()
                .filter(s->s.contains("B"))
                .forEach(item-> System.out.println(item));*/

       /* String anotherDate = "09 Apr 2016";

        DateTimeFormatter df = DateTimeFormatter.ofPattern("dd MMM yyyy");
        LocalDate random = LocalDate.parse(anotherDate, df);

        System.out.println(anotherDate + " parses as " + random);*/

       /* LocalDateTime now = LocalDateTime.now();

        System.out.println("Before : " + now);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");

        String formatDateTime = now.format(formatter);

        System.out.println("After : " + formatDateTime);*/


       /* Map<String, Integer> items = new HashMap<>();
        items.put("A", 10);
        items.put("B", 20);
        items.put("C", 30);
        items.put("D", 40);
        items.put("E", 50);
        items.put("F", 60);

        items.forEach((k,v)->System.out.println("Item : " + k + " Count : " + v));

        items.forEach((k,v)->{
            //System.out.println("Item : " + k + " Count : " + v);
            if("E".equals(k)){
                System.out.println("Hello E");
            }
        });*/

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
         /*Calendar calendar = new GregorianCalendar(2018, 10, 8);
        String str = sdf.format(calendar.getTime());
        Date date = sdf.parse(str);
        String string = converter.gregorian_to_jalali(date);
        System.out.println("تاریخ شمسی:"+string);
        Date date1=converter.jalali_to_gregorian(sdf.parse(string));
        System.out.println("تاریخ میلادی:"+sdf.format(date1));*/

///////////////// JalaiToMiladi/////////////////////////

        String strDate = "1397/10/19";
        Date date = sdf.parse(strDate);
        Date date1=FDate.Farsi_to_Gregorian(date);
        System.out.println(sdf.format(date1));

////////////////////////////MiladiToJalai/////////////////////
        String Gstr = "2019/1/9";
        Date date2 = sdf.parse(Gstr);
        Calendar calendar1 = new GregorianCalendar();
        calendar1.setTime(date2);
        String date3=FDate.Gregorian_to_Farsi(calendar1.getTime());
        System.out.println(date3);


    }
}
