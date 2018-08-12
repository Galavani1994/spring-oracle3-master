package com.developer.springOracle3.entity;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Column;
import javax.persistence.Table;
import javax.persistence.Entity;

@Entity
@Table(name = "CP_table")
public class CPtable
{
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
    private String total;
    @Column
    private String remain;
    @Column
    private String kaladate;

    public CPtable() {
    }

    public CPtable(String cuid, String prid, String prName, String meter, String price, String discount, String pay, String factore, String total, String remain, String date) {
        this.cuid = cuid;
        this.prid = prid;
        this.prName = prName;
        this.metercp = meter;
        this.price = price;
        this.discount = discount;
        this.pay = pay;
        this.factore = factore;
        this.total = total;
        this.remain = remain;
        this.kaladate = date;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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

    public String getMetercp() {
        return metercp;
    }

    public void setMetercp(String metercp) {
        this.metercp = metercp;
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

    public String getPay() {
        return pay;
    }

    public void setPay(String pay) {
        this.pay = pay;
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

    public String getKaladate() {
        return kaladate;
    }

    public void setKaladate(String kaladate) {
        this.kaladate = kaladate;
    }
}
