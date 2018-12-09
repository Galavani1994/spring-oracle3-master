package com.developer.springOracle3.entity;


import lombok.Data;

import javax.persistence.*;

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
    private int mande;


}
