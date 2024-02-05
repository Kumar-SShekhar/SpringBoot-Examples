package com.kss.SpringSecurityDemo2.repository;

import com.kss.SpringSecurityDemo2.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Integer> {
    Optional<Role> findByName(String name);
}
