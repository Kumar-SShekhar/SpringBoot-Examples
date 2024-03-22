package com.kss.SpringSecurityDemo2.utils;

import com.kss.SpringSecurityDemo2.service.OtpService;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMailMessage;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import java.io.File;
import java.util.Objects;

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

    public String sendEmailWithAttachment(String email) throws MessagingException {
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);
        mimeMessageHelper.setTo(email);
        mimeMessageHelper.setSubject("Email with attachment");
        mimeMessageHelper.setText("Email is sent with attachment.");
        FileSystemResource fileSystemResource = new FileSystemResource(new File("C:\\Users\\Kumar_Shekhar\\Pictures\\Screenshots\\Screenshot3.png"));
//        mimeMessageHelper.addAttachment(fileSystemResource.getFilename(), fileSystemResource);
        mimeMessageHelper.addAttachment(Objects.requireNonNull(fileSystemResource.getFilename()), fileSystemResource);
        mailSender.send(mimeMessage);

        return "Email sent Successfully";
    }
}
