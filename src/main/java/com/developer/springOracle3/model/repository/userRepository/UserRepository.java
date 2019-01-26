package com.developer.springOracle3.model.repository.userRepository;

import com.developer.springOracle3.entity.users.UserTable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserTable,Long> {

    UserTable findByEmail(String username);
    @Query("from UserTable where username=?1 or email=?2")
    UserTable findByEmailOrUsername(String username,String email);


}
