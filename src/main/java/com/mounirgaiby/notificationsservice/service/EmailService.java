package com.mounirgaiby.notificationsservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.logging.Level;
import java.util.logging.Logger;

@Service
public class EmailService {

    private static final Logger LOGGER = Logger.getLogger(EmailService.class.getName());

    @Autowired(required = false)
    private JavaMailSender mailSender;

    public boolean sendEmail(String to, String subject, String text) {
        if (!isEmailConfigured()) {
            LOGGER.warning("JavaMailSender not configured, falling back to simulation");
            return sendEmailSimulated(to, subject, text);
        }

        try {
            LOGGER.info("===== ENVOI EMAIL REEL =====");
            LOGGER.info("Destinataire: " + to);
            LOGGER.info("Sujet: " + subject);
            LOGGER.info("Message: " + text);

            SimpleMailMessage message = new SimpleMailMessage();
            message.setTo(to);
            message.setSubject(subject);
            message.setText(text);
            message.setFrom("noreply@notificationsservice.com");

            mailSender.send(message);

            LOGGER.info("EMAIL REEL ENVOYE AVEC SUCCES !");
            LOGGER.info("============================");
            return true;
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "ERREUR lors de l'envoi d'email reel: " + e.getMessage(), e);
            return false;
        }
    }

    public boolean sendEmailSimulated(String to, String subject, String text) {
        try {
            // Simulate email sending delay
            Thread.sleep(100);
            LOGGER.info("===== SIMULATION EMAIL (pas de config SMTP) =====");
            LOGGER.info("Destinataire: " + to);
            LOGGER.info("Sujet: " + subject);
            LOGGER.info("Message: " + text);
            LOGGER.info("Pour recevoir de vrais emails, configurez SMTP dans application.properties");
            LOGGER.info("================================================");
            return true;
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            return false;
        }
    }

    public boolean isEmailConfigured() {
        try {
            // Test simple pour vérifier si le service email est configuré
            return mailSender != null;
        } catch (Exception e) {
            return false;
        }
    }
}
