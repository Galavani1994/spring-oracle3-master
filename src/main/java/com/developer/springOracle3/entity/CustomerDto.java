package com.developer.springOracle3.entity;

import lombok.Data;

import java.util.List;

@Data
public class CustomerDto {

    private Integer id;
    private String cuid;
    private String firstName;
    private String lastName;
    private String addressname;
    private Long mande;
    private String mobileNum;
    private String phoneNum;
    private String descreption;
    private String registerDate;
    private String lastCome;
    private List<Zamen> zamen;
}
