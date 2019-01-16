package com.developer.springOracle3.controller.userController;

import com.developer.springOracle3.entity.users.UserTable;
import com.developer.springOracle3.model.repository.userRepository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;


    @PostMapping("/save")
    @CrossOrigin(origins = "http://localhost:4200")
    public void register(@RequestBody UserTable userTable) {

        if (userTable.getId() == null) {
            try {
                userTable.setPasswordString(userTable.getPassword());
                userTable.setPassword(passwordEncoder.encode(userTable.getPassword()));
                userRepository.save(userTable);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        } else {

            userRepository.save(userTable);
        }

    }


    @GetMapping("/getAllUser")
    @CrossOrigin(origins = "http://localhost:4200")
    public List<UserTable> getUser() {

        return userRepository.findAll();
    }


    @GetMapping("/deleteUser/{id}")
    @CrossOrigin(origins = "http://localhost:4200")
    public void dleteUser(@PathVariable("id") Long id) {
        userRepository.deleteById(id);
    }

}
