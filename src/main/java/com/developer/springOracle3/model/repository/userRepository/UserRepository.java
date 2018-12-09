package com.developer.springOracle3.model.repository.userRepository;

import com.developer.springOracle3.entity.users.UserTable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserTable,Long> {

    UserTable findByEmail(String username);
}
