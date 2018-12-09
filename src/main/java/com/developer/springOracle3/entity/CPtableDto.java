package com.developer.springOracle3.entity;

import java.util.Date;


public class CPtableDto {

    private Integer id;

    private String cuid;

    private String prid;

    private String prName;

    private String metercp;

    private String price;

    private String discount;

    private String pay;

    private String factore;

    private String total;

    private String remain;

    private Date kaladate;

    public CPtableDto() {
    }


    public Integer getId() {
        return id;
    }

    public CPtableDto setId(Integer id) {
        this.id = id;
        return this;
    }

    public String getCuid() {
        return cuid;
    }

    public CPtableDto setCuid(String cuid) {
        this.cuid = cuid;
        return this;
    }

    public String getPrid() {
        return prid;
    }

    public CPtableDto setPrid(String prid) {
        this.prid = prid;
        return this;
    }

    public String getPrName() {
        return prName;
    }

    public CPtableDto setPrName(String prName) {
        this.prName = prName;
        return this;
    }

    public String getMetercp() {
        return metercp;
    }

    public CPtableDto setMetercp(String metercp) {
        this.metercp = metercp;
        return this;
    }

    public String getPrice() {
        return price;
    }

    public CPtableDto setPrice(String price) {
        this.price = price;
        return this;
    }

    public String getDiscount() {
        return discount;
    }

    public CPtableDto setDiscount(String discount) {
        this.discount = discount;
        return this;
    }

    public String getPay() {
        return pay;
    }

    public CPtableDto setPay(String pay) {
        this.pay = pay;
        return this;
    }

    public String getFactore() {
        return factore;
    }

    public CPtableDto setFactore(String factore) {
        this.factore = factore;
        return this;
    }

    public String getTotal() {
        return total;
    }

    public CPtableDto setTotal(String total) {
        this.total = total;
        return this;
    }

    public String getRemain() {
        return remain;
    }

    public CPtableDto setRemain(String remain) {
        this.remain = remain;
        return this;
    }

    public Date getKaladate() {
        return kaladate;
    }

    public CPtableDto setKaladate(Date kaladate) {
        this.kaladate = kaladate;
        return this;
    }
}
