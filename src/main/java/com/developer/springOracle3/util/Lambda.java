package com.developer.springOracle3.util;

import java.util.ArrayList;
import java.util.List;

public class Lambda {
    public static void main(String[] args) {
        List<String> items=new ArrayList<>();

        items.add("A");
        items.add("B");
        items.add("C");
        items.add("D");

        System.out.println(items.stream().count());
        items.stream()
                .filter(s->s.contains("B"))
                .forEach(item-> System.out.println(item));

    }
}
