package com.kss.SpringSecurityDemo2.service;

import com.kss.SpringSecurityDemo2.dto.EmailVerificationRequest;
import com.kss.SpringSecurityDemo2.entity.OtpEntity;
import com.kss.SpringSecurityDemo2.entity.User;
import com.kss.SpringSecurityDemo2.repository.OtpRepository;
import com.kss.SpringSecurityDemo2.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OtpService {

    private final OtpRepository otpRepository;
    private final UserRepository userRepository;

    public void saveOtp(String email, String otp){
        OtpEntity otpEntity = otpRepository.findByEmail(email);
        if(otpEntity !=null ){
            otpEntity.setOtp(otp);
            otpEntity.setEmail(email);
            otpEntity.setOtpGeneratedTime(LocalDateTime.now());
            otpRepository.save(otpEntity);
            return;
        }
        OtpEntity newOtp = new OtpEntity();
        newOtp.setOtp(otp);
        newOtp.setEmail(email);
        newOtp.setOtpGeneratedTime(LocalDateTime.now());
        otpRepository.save(newOtp);
    }

    public boolean verifyOtp(String email, String enteredOtp){
        User user = userRepository.findByEmail(email).orElseThrow(()-> new RuntimeException("User not found with email: "+ email));

        String userEmail = user.getEmail();
        OtpEntity otpEntity = otpRepository.findByEmail(userEmail);
        if(otpEntity.getOtp().equals(enteredOtp) && otpEntity.getEmail().equals(email)){

            LocalDateTime currentTime = LocalDateTime.now();
            LocalDateTime otpExpiryTime = otpEntity.getOtpGeneratedTime().plusMinutes(25);
            if( !currentTime.isAfter(otpExpiryTime) ){
                return true;
            }else{
                return false;
            }
        }
        return false;
    }


//    public String verifyOtp(EmailVerificationRequest verificationRequest){
//        User user = userRepository.findByEmail(verificationRequest.getEmail()).orElseThrow(()-> new RuntimeException("User not found with email: "+ verificationRequest.getEmail()));
//
//        String userEmail = user.getEmail();
//        String enteredOtp = verificationRequest.getOtp();
//        OtpEntity otpEntity = otpRepository.findByEmail(userEmail);
//        if(otpEntity.getOtp().equals(enteredOtp) && otpEntity.getEmail().equals(userEmail)){
//
//            LocalDateTime currentTime = LocalDateTime.now();
//            LocalDateTime otpExpiryTime = otpEntity.getOtpGeneratedTime().plusMinutes(5);
//            if( !currentTime.isAfter(otpExpiryTime) ){
//                return "Otp validated Successfully";
//            }else{
//                return "Otp is Expired";
//            }
//        }
//        return "Invalid Otp";
//    }

}
