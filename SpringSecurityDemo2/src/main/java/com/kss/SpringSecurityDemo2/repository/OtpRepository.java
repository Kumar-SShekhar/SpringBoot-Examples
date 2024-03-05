package com.kss.SpringSecurityDemo2.repository;

import com.kss.SpringSecurityDemo2.entity.OtpEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface OtpRepository extends JpaRepository<OtpEntity, Long> {

    public OtpEntity findByOtp(String otp);

    public OtpEntity findByEmail(String email);
}
