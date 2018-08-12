package com.developer.springOracle3.entity;

import org.springframework.stereotype.Service;

import javax.persistence.*;

/**
 *
 */
@Entity
@Table(name = "production")
@Service
public class Production {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @Column
    private String prid;
    @Column
    private String prName;
    @Column
    private String meterPr;

    public Production() {
    }

    public Production(String prid, String prName, String meter) {
        this.prid = prid;
        this.prName = prName;
        this.meterPr = meter;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getMeterPr() {
        return meterPr;
    }

    public void setMeterPr(String meterPr) {
        this.meterPr = meterPr;
    }
}
