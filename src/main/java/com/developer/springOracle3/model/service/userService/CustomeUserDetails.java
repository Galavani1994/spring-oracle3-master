package com.developer.springOracle3.model.service.userService;

import com.developer.springOracle3.entity.users.UserTable;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.stream.Collectors;

@Data
public class CustomeUserDetails extends UserTable implements UserDetails {
    private UserTable userTable;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return userTable.getRoles().stream()
                .map(role -> new SimpleGrantedAuthority("ROLE_"+role.getRole()))
                .collect(Collectors.toList());
    }

    @Override
    public String getPassword() {
        return userTable.getPassword();
    }

    @Override
    public String getUsername() {
        return userTable.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
