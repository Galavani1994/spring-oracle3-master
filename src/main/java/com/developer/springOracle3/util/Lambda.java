package com.developer.springOracle3.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Lambda {
    public static void main(String[] args) {
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

        LocalDateTime now = LocalDateTime.now();

        System.out.println("Before : " + now);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");

        String formatDateTime = now.format(formatter);

        System.out.println("After : " + formatDateTime);


    }
}
