package com.developer.springOracle3.entity;


import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "customer")
@Data
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @Column
    private String cuid;
    @Column
    private String firstName;
    @Column
    private String lastName;
    @Column
    private String addressname;
    @Column
    private Integer mande;
    @Column
    private String mobileNum;
    @Column
    private String phoneNum;
    @Column
    private String descreption;
    @Column
    private String zamen;
    @Column
    private Date registerDate;
    @Column
    private Date lastCome;


}
