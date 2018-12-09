package com.developer.springOracle3.controller.userController;

import com.developer.springOracle3.entity.users.UserTable;
import com.developer.springOracle3.model.repository.userRepository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @PostMapping("/save")
    public String register(@ModelAttribute UserTable userTable){

        userTable.setPassword(passwordEncoder.encode(userTable.getPassword()));
        userRepository.save(userTable);
        return "Lets Go !!!successfully.";
    }

}
