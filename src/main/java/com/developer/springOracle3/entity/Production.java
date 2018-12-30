package com.developer.springOracle3.entity;

import lombok.Data;
import org.springframework.stereotype.Service;

import javax.persistence.*;
import java.util.Date;

/**
 *
 */
@Entity
@Table(name = "production")
@Service
@Data
public class Production {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @Column
    private String prid;
    @Column
    private String prName;
    @Column
    private Integer meterPr;
    @Transient
    private Double remainMeter;
    @Column
    private Date tarikh;


}
