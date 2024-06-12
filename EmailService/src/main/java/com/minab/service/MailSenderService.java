package com.minab.service;

import com.minab.rabbitmq.model.ActivationMailModel;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
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

    @RabbitListener(queues = "reset.password.queue")
    public void sendPasswordMail(ActivationMailModel model) {
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setTo(model.getEmail());
        simpleMailMessage.setSubject("Burger Store Password Reset");
        simpleMailMessage.setText("Şifre yenilemek için " +
                model.getActivationCode() + " kodu giriniz.");


        javaMailSender.send(simpleMailMessage);
    }


}
