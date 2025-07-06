using these two files make this service into a api its a small micro service that just sends these notification simulated only email is real.
its should have swagger doc a quick html ui for sending email nothing fancy like the email we want to send and text , number we want and text and add v1 and v2 v1 all simulated and v2 only email is real and add a difference between them and say first are deprecated. like in sms the second like allows maybe loinger text or something like that etc easier.
package com.example.etudiantapi.service;

import com.example.etudiantapi.entity.Etudiant;
import com.example.etudiantapi.util.LanguageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;
import java.util.logging.Level;
import java.util.logging.Logger;

@Service
public class NotificationService {

    private static final Logger LOGGER = Logger.getLogger(NotificationService.class.getName());

    @Autowired
    private LanguageUtil languageUtil;

    @Autowired
    private EmailService emailService;

    public enum NotificationType {
        CREATION, MODIFICATION, SUPPRESSION
    }

    public enum NotificationChannel {
        EMAIL, SMS, PUSH
    }

    public CompletableFuture<Boolean> sendNotificationAsync(Etudiant etudiant, NotificationType type, String language) {
        LOGGER.info("===== DEMARRAGE NOTIFICATIONS ASYNCHRONES =====");
        LOGGER.info("Etudiant: " + etudiant.getPrenom() + " " + etudiant.getNom());
        LOGGER.info("Email: " + etudiant.getEmail());
        LOGGER.info("Type: " + type);

        return CompletableFuture.supplyAsync(() -> {
            try {
                // Envoi sur tous les canaux disponibles
                boolean emailSent = sendNotification(etudiant, type, NotificationChannel.EMAIL, language);
                boolean smsSent = sendNotification(etudiant, type, NotificationChannel.SMS, language);
                boolean pushSent = sendNotification(etudiant, type, NotificationChannel.PUSH, language);

                LOGGER.info("===== RESULTATS NOTIFICATIONS =====");
                LOGGER.info("EMAIL: " + (emailSent ? "ENVOYE AVEC SUCCES" : "ECHEC"));
                LOGGER.info("SMS: " + (smsSent ? "ENVOYE AVEC SUCCES (SIMULATION)" : "ECHEC"));
                LOGGER.info("PUSH: " + (pushSent ? "ENVOYE AVEC SUCCES (SIMULATION)" : "ECHEC"));
                LOGGER.info("=====================================");

                return emailSent || smsSent || pushSent;
            } catch (Exception e) {
                LOGGER.severe("ERREUR CRITIQUE dans sendNotificationAsync: " + e.getMessage());
                e.printStackTrace();
                return false;
            }
        });
    }

    public boolean sendNotification(Etudiant etudiant, NotificationType type, NotificationChannel channel, String language) {
        try {
            String message = buildMessage(etudiant, type, language);
            String subject = buildSubject(type, language);

            switch (channel) {
                case EMAIL:
                    return sendRealEmail(etudiant.getEmail(), subject, message);
                case SMS:
                    return sendSMS(etudiant.getTelephone(), message);
                case PUSH:
                    return sendPushNotification(etudiant.getId().toString(), message);
                default:
                    LOGGER.warning("Canal de notification non supporte: " + channel);
                    return false;
            }
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Erreur lors de l'envoi de notification", e);
            return false;
        }
    }

    private boolean sendRealEmail(String email, String subject, String message) {
        if (emailService.isEmailConfigured()) {
            // Envoi d'un vrai email
            return emailService.sendEmail(email, subject, message);
        } else {
            // Fallback vers simulation si pas configuré
            LOGGER.info("===== SIMULATION EMAIL (pas de config SMTP) =====");
            LOGGER.info("Destinataire: " + email);
            LOGGER.info("Sujet: " + subject);
            LOGGER.info("Message: " + message);
            LOGGER.info("Pour recevoir de vrais emails, configurez SMTP dans application.properties");
            LOGGER.info("================================================");
            return true;
        }
    }

    private String buildSubject(NotificationType type, String language) {
        switch (type) {
            case CREATION:
                return "Bienvenue - Votre dossier etudiant a ete cree";
            case MODIFICATION:
                return "Mise a jour - Votre dossier etudiant";
            case SUPPRESSION:
                return "Suppression - Votre dossier etudiant";
            default:
                return "Notification - Dossier etudiant";
        }
    }

    private String buildMessage(Etudiant etudiant, NotificationType type, String language) {
        String messageTemplate;

        switch (type) {
            case CREATION:
                messageTemplate = "Bonjour %s %s,\n\nVotre dossier etudiant a ete cree avec succes.\n\nInformations:\n- Email: %s\n- Niveau: %s\n- Telephone: %s\n\nCordialement,\nL'equipe de gestion des etudiants";
                return String.format(messageTemplate, etudiant.getPrenom(), etudiant.getNom(), etudiant.getEmail(), etudiant.getNiveau(), etudiant.getTelephone());
            case MODIFICATION:
                messageTemplate = "Bonjour %s %s,\n\nVotre dossier etudiant a ete mis a jour.\n\nInformations actuelles:\n- Email: %s\n- Niveau: %s\n- Telephone: %s\n\nCordialement,\nL'equipe de gestion des etudiants";
                return String.format(messageTemplate, etudiant.getPrenom(), etudiant.getNom(), etudiant.getEmail(), etudiant.getNiveau(), etudiant.getTelephone());
            case SUPPRESSION:
                messageTemplate = "Bonjour %s %s,\n\nVotre dossier etudiant a ete supprime.\n\nSi vous pensez qu'il s'agit d'une erreur, contactez-nous.\n\nCordialement,\nL'equipe de gestion des etudiants";
                return String.format(messageTemplate, etudiant.getPrenom(), etudiant.getNom());
            default:
                messageTemplate = "Bonjour %s %s,\n\nNotification concernant votre dossier etudiant.\n\nContact: %s\n\nCordialement,\nL'equipe de gestion des etudiants";
                return String.format(messageTemplate, etudiant.getPrenom(), etudiant.getNom(), etudiant.getEmail());
        }
    }

    private boolean sendSMS(String telephone, String message) {
        if (telephone == null || telephone.trim().isEmpty()) {
            LOGGER.warning("Numero de telephone non fourni pour l'envoi de SMS");
            return false;
        }

        LOGGER.info("===== SIMULATION SMS =====");
        LOGGER.info("Destinataire: " + telephone);
        LOGGER.info("Message: " + message);
        LOGGER.info("SMS simule envoye avec succes !");
        LOGGER.info("===========================");
        return true;
    }

    private boolean sendPushNotification(String userId, String message) {
        LOGGER.info("===== SIMULATION NOTIFICATION PUSH =====");
        LOGGER.info("Utilisateur: " + userId);
        LOGGER.info("Message: " + message);
        LOGGER.info("Notification push simulee envoyee avec succes !");
        LOGGER.info("======================================");
        return true;
    }
}

//////////

package com.example.etudiantapi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.logging.Level;
import java.util.logging.Logger;

@Service
public class EmailService {

    private static final Logger LOGGER = Logger.getLogger(EmailService.class.getName());

    @Autowired
    private JavaMailSender mailSender;

    public boolean sendEmail(String to, String subject, String message) {
        try {
            LOGGER.info("===== ENVOI EMAIL REEL =====");
            LOGGER.info("Destinataire: " + to);
            LOGGER.info("Sujet: " + subject);
            LOGGER.info("Message: " + message);

            SimpleMailMessage email = new SimpleMailMessage();
            email.setFrom("noreply@etudiantapi.com");
            email.setTo(to);
            email.setSubject(subject);
            email.setText(message);

            mailSender.send(email);

            LOGGER.info("EMAIL REEL ENVOYE AVEC SUCCES !");
            LOGGER.info("============================");
            return true;

        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "ERREUR lors de l'envoi d'email reel: " + e.getMessage(), e);
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
