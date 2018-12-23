package com.developer.springOracle3.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "zamen")
@Data
public class Zamen {
    @Id
    @GeneratedValue
    private Integer id;
    @Column
    private String zamenName;
    @Column
    private String zamenFamily;
}
