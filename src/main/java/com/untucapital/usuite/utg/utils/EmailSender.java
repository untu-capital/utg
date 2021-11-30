package com.untucapital.usuite.utg.utils;

import com.untucapital.usuite.utg.exception.EmailException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.IOException;
import java.util.Properties;

/**
 * @author Chirinda Nyasha Dell 23/11/2021
 */

@Component
public class EmailSender {
    private static final Logger log = LoggerFactory.getLogger(EmailSender.class);

    private final JavaMailSender mailSender;

    @Autowired
    public EmailSender(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    public void send(String recipient, String subject, String msg) {

        final String username = "opensmsservice@gmail.com";
        final String password = "@pen5m5Service";

        Properties prop = new Properties();
        prop.put("mail.smtp.host", "smtp.gmail.com");
        prop.put("mail.smtp.port", "465");
        prop.put("mail.smtp.auth", "true");
        prop.put("mail.smtp.socketFactory.port", "465");
        prop.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");

        /*Properties prop = new Properties();
        prop.put("mail.smtp.host", "smtp.gmail.com");
        prop.put("mail.smtp.port", "587");
        prop.put("mail.smtp.auth", "true");
        prop.put("mail.smtp.starttls.enable", "true"); //TLS*/

        Session session = Session.getInstance(prop,
                new javax.mail.Authenticator() {
                    @Override
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                    }
                });
        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("opensmsservice@gmail.com"));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(recipient));
            message.setSubject(subject);
            message.setText(msg);

            log.info("Sending email From: {}, To: {} Subject: {}, Message: {}", message.getFrom(),
                    message.getRecipients(Message.RecipientType.TO), message.getSubject(),
                    FormatterUtil.toJson(message.getContent()));
            Transport.send(message);
        } catch (MessagingException | IOException e) {
            log.error("Error sending email - {}", e.getMessage(), e);
            throw new RuntimeException("Error sending email", e);
        }
    }

    public void sendMail(String recipient, String subject, String msg) {

        MimeMessage mimeMsg = mailSender.createMimeMessage();

        try {
            // true = multipart html message
            MimeMessageHelper mimeMsgHelper = new MimeMessageHelper(mimeMsg, true, "utf-8");

            mimeMsgHelper.setTo(recipient);
            mimeMsgHelper.setSubject(subject);
            mimeMsgHelper.setFrom("nyasha.chirinda@untu-capital.com");
            mimeMsgHelper.setText(msg, true);

            // true = text/html
            // mimeMsgHelper.setText("\n <h1>Welcome to OpenSMS portal></h1>" + msg, true);

            mailSender.send(mimeMsg);
        } catch (MessagingException e) {
            throw new EmailException("Error sending Confirmation email", e);
        }

    }

}
