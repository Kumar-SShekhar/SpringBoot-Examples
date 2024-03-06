package com.kss.SpringSecurityDemo2.utils;

import com.kss.SpringSecurityDemo2.service.OtpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMailMessage;
import org.springframework.stereotype.Component;

@Component
public class EmailUtil {

    @Autowired
    private JavaMailSender mailSender;
    @Autowired
    private OtpGenerator otpGenerator;
    @Autowired
    private OtpService otpService;


    public String sendEmail(String email){
        SimpleMailMessage message = new SimpleMailMessage();
        message.setCc(email);
        message.setBcc(email);
        message.setTo(email);
        message.setSubject("Testing mail service");
        message.setText("This mail is send for testing purpose.");
        mailSender.send(message);
        return "Email send Successfully";
    }

    public String sendEmailOtp(String email){

        String generatedOtp = otpGenerator.generateOtp();
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setTo(email);
        simpleMailMessage.setSubject("Otp for verification");
        simpleMailMessage.setText("Otp for your verification is: " + generatedOtp );
        mailSender.send(simpleMailMessage);
        otpService.saveOtp(email,generatedOtp);
        return "email sent successfully";

    }
}
