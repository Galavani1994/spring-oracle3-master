package com.developer.springOracle3.entity.users;


import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Table
@Data
public class UserTable {
    @Id
    private Long id;
    @Column
    private String username;
    @Column
    private String password;
    @Column
    private String email;

    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    @JoinColumn(name = "userId")
    private List<Role> roles;

}
