package com.developer.springOracle3.entity;

import lombok.Data;

import java.util.Date;

@Data
public class ProductionDto {

    private int id;

    private String prid;

    private String prName;

    private int meterPr;

    private  int remainMeter;

    private Date tarikh;
}
