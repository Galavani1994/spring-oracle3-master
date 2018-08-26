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
    private int meterPr;
    @Transient
    private  int remainMeter;

    public Production() {
    }

    public Production(String prid, String prName, int meterPr, int remainMeter) {
        this.prid = prid;
        this.prName = prName;
        this.meterPr = meterPr;
        this.remainMeter = remainMeter;
    }

    public int getId() {
        return id;
    }

    public Production setId(int id) {
        this.id = id;
        return this;
    }

    public String getPrid() {
        return prid;
    }

    public Production setPrid(String prid) {
        this.prid = prid;
        return this;
    }

    public String getPrName() {
        return prName;
    }

    public Production setPrName(String prName) {
        this.prName = prName;
        return this;
    }

    public int getMeterPr() {
        return meterPr;
    }

    public Production setMeterPr(int meterPr) {
        this.meterPr = meterPr;
        return this;
    }

    public int getRemainMeter() {
        return remainMeter;
    }

    public Production setRemainMeter(int remainMeter) {
        this.remainMeter = remainMeter;
        return this;
    }
}
