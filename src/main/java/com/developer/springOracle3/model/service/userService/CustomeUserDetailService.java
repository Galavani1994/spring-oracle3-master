package com.developer.springOracle3.model.service.userService;

import com.developer.springOracle3.entity.users.UserTable;
import com.developer.springOracle3.model.repository.userRepository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomeUserDetailService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserTable userTable = userRepository.findByEmail(username);

        CustomeUserDetails userDetails = null;
        if (userTable != null) {
            userDetails= new CustomeUserDetails();
            userDetails.setUserTable(userTable);
        }
        else{
            throw new UsernameNotFoundException("not found "+ username);
        }
        return userDetails;
    }
}
