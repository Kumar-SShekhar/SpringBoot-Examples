package com.kss.SpringSecurityDemo2.repository;

import com.kss.SpringSecurityDemo2.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Long> {

    Optional<User> findByUserName(String userName);
    Optional<User> findByEmail(String email);
    Boolean existsByEmail(String email);
    Boolean existsByUserName(String userName);

}
