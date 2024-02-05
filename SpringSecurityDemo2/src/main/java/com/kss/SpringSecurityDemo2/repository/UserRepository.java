package com.kss.SpringSecurityDemo2.repository;

import com.kss.SpringSecurityDemo2.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {

    User findByUserName(String userName);
    User findByEmail(String email);
    Boolean existsByEmail(String email);
    Boolean existsByUserName(String userName);

}
