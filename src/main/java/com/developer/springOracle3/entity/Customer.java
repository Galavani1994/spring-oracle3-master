package com.developer.springOracle3.entity;


import lombok.Data;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "customer")
@Data
public class Customer {
    @Id
    @GeneratedValue
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
    private Long mande;
    @Column
    private String mobileNum;
    @Column
    private String phoneNum;
    @Column
    private String descreption;
    @Column
    private Date registerDate;
    @Column
    private Date lastCome;
    @OneToMany(fetch = FetchType.EAGER, cascade =CascadeType.ALL)
    @JoinColumn(name = "customerId")
    private List<Zamen> zamen;


}
