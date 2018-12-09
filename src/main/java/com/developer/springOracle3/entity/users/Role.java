package com.developer.springOracle3.entity.users;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table
@Data
public class Role {
    @Id
    @GeneratedValue
    private Integer id;
    @Column
    private String role;
}
