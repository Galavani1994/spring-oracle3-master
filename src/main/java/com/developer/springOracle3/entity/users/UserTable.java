package com.developer.springOracle3.entity.users;


import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Table
@Data
public class UserTable {
    @Id
    @SequenceGenerator(name = "userSeq",sequenceName = "userSeq",allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.AUTO,generator = "userSeq")
    private Long id;
    @Column
    private String username;
    @Column
    private String password;
    @Column
    private String email;
    @Column
    private String passwordString;

    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    @JoinColumn(name = "userId")
    private List<Role> roles;

}
