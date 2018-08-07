package com.developer.springOracle3.entity;


import com.sun.javafx.beans.IDProperty;

import javax.persistence.*;

@Entity
@Table(name="CPtable")
public class CPtable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @Column
    private String cuid;
    @Column
    private String prid;
    @Column
    private String prName;
    @Column
    private String meter;
    @Column
    private String price;
    @Column
    private String discount;
    @Column
    private String pay;
    @Column
    private String factore;
    @Column
    private String total;
    @Column
    private String remain;
    @Column
    private String date;

    public CPtable() {
    }

    public CPtable(String cuid, String prid, String prName, String meter, String price, String discount, String pay, String factore, String total, String remain) {
        this.cuid = cuid;
        this.prid = prid;
        this.prName = prName;
        this.meter = meter;
        this.price = price;
        this.discount = discount;
        this.pay = pay;
        this.factore = factore;
        this.total = total;
        this.remain = remain;

    }

    public int getId() {
        return id;
    }

    public String getPay() {
        return pay;
    }

    public void setPay(String pay) {
        this.pay = pay;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCuid() {
        return cuid;
    }

    public void setCuid(String cuid) {
        this.cuid = cuid;
    }

    public String getPrid() {
        return prid;
    }

    public void setPrid(String prid) {
        this.prid = prid;
    }

    public String getPrName() {
        return prName;
    }

    public void setPrName(String prName) {
        this.prName = prName;
    }

    public String getMeter() {
        return meter;
    }

    public void setMeter(String meter) {
        this.meter = meter;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getDiscount() {
        return discount;
    }

    public void setDiscount(String discount) {
        this.discount = discount;
    }

    public String getFactore() {
        return factore;
    }

    public void setFactore(String factore) {
        this.factore = factore;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public String getRemain() {
        return remain;
    }

    public void setRemain(String remain) {
        this.remain = remain;
    }
}
