package com.shekhar.ProfilesDemo.repository;

import com.shekhar.ProfilesDemo.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {


}
