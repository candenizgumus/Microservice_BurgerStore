package com.minab.service;

import com.minab.rabbitmq.model.ActivationMailModel;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class MailSenderService {
    private final JavaMailSender javaMailSender;

    public MailSenderService(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    public void sendActivationMail(ActivationMailModel mailModel){

        SimpleMailMessage simpleMailMessage=new SimpleMailMessage();

        simpleMailMessage.setTo(mailModel.getEmail());
        simpleMailMessage.setSubject("Burger Store Aktivasyon İşlemleri");
        simpleMailMessage.setText("Aktivasyon Kodu: "+mailModel.getActivationCode());
        //simpleMailMessage.setCc("");


        javaMailSender.send(simpleMailMessage);
    }


}
