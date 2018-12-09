package com.developer.springOracle3.entity;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "CP_table")
public class CPtable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @Column
    private String cuid;
    @Column
    private String prid;
    @Column
    private String prName;
    @Column
    private String metercp;
    @Column
    private String price;
    @Column
    private String discount;
    @Column
    private String pay;
    @Column
    private String factore;
    @Column
    private String remain;
    @Column
    private Date kaladate;

    public CPtable() {
    }

    public CPtable(String cuid, String prid, String prName, String meter, String price, String discount, String pay, String factore,  String remain, Date date) {
        this.cuid = cuid;
        this.prid = prid;
        this.prName = prName;
        this.metercp = meter;
        this.price = price;
        this.discount = discount;
        this.pay = pay;
        this.factore = factore;
        this.remain = remain;
        this.kaladate = date;
    }

    public Integer getId() {
        return id;
    }

    public CPtable setId(Integer id) {
        this.id = id;
        return this;
    }

    public String getCuid() {
        return cuid;
    }

    public CPtable setCuid(String cuid) {
        this.cuid = cuid;
        return this;
    }

    public String getPrid() {
        return prid;
    }

    public CPtable setPrid(String prid) {
        this.prid = prid;
        return this;
    }

    public String getPrName() {
        return prName;
    }

    public CPtable setPrName(String prName) {
        this.prName = prName;
        return this;
    }

    public String getMetercp() {
        return metercp;
    }

    public CPtable setMetercp(String metercp) {
        this.metercp = metercp;
        return this;
    }

    public String getPrice() {
        return price;
    }

    public CPtable setPrice(String price) {
        this.price = price;
        return this;
    }

    public String getDiscount() {
        return discount;
    }

    public CPtable setDiscount(String discount) {
        this.discount = discount;
        return this;
    }

    public String getPay() {
        return pay;
    }

    public CPtable setPay(String pay) {
        this.pay = pay;
        return this;
    }

    public String getFactore() {
        return factore;
    }

    public CPtable setFactore(String factore) {
        this.factore = factore;
        return this;
    }


    public String getRemain() {
        return remain;
    }

    public CPtable setRemain(String remain) {
        this.remain = remain;
        return this;
    }

    public Date getKaladate() {
        return kaladate;
    }

    public CPtable setKaladate(Date kaladate) {
        this.kaladate = kaladate;
        return this;
    }
}
