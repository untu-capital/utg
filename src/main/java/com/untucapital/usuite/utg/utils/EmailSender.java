package com.untucapital.usuite.utg.utils;

import com.untucapital.usuite.utg.exception.EmailException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Component;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.File;

/**
 * @author Chirinda Nyasha Dell 23/11/2021
 */

@Component
public class EmailSender {
    private static final Logger log = LoggerFactory.getLogger(EmailSender.class);

    private final JavaMailSender mailSender;
    private final SimpleMailMessage preConfiguredMessage;

    @Value("${untu.confirm-account.link}")
    private String confirmAccountUrl;

    @Autowired
    public EmailSender(JavaMailSender mailSender, SimpleMailMessage preConfiguredMessage) {
        this.mailSender = mailSender;
        this.preConfiguredMessage = preConfiguredMessage;
    }

    public void send(String recipient, String subject, String msg) {

        MimeMessage mimeMsg = mailSender.createMimeMessage();

        try {
            // true = multipart html message
            MimeMessageHelper mimeMsgHelper = new MimeMessageHelper(mimeMsg, true, "utf-8");

            mimeMsgHelper.setTo(recipient);
            mimeMsgHelper.setSubject(subject);
            mimeMsgHelper.setFrom("credit.application@untu-capital.com");
            mimeMsgHelper.setText(msg, true);

            mailSender.send(mimeMsg);
        } catch (MessagingException e) {
            throw new EmailException("Error sending Confirmation email", e);
        }
    }

    public void sendMailWithAttachment(String to, String subject, String body, String fileToAttach) {
        MimeMessagePreparator preparator = mimeMessage -> {
            mimeMessage.setRecipient(Message.RecipientType.TO, new InternetAddress(to));
            mimeMessage.setFrom(new InternetAddress("credit.application@untu-capital.com"));
            mimeMessage.setSubject(subject);
            mimeMessage.setText(body);

            FileSystemResource file = new FileSystemResource(new File(fileToAttach));
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
            helper.addAttachment("logo.jpg", file);
        };

        try {
            mailSender.send(preparator);
        } catch (MailException ex) {
            // simply log it and go on...
            System.err.println(ex.getMessage());
        }
    }

    public void sendMailWithInlineResources(String to, String subject, String fileToAttach) {
        MimeMessagePreparator preparator = mimeMessage -> {
            mimeMessage.setRecipient(Message.RecipientType.TO, new InternetAddress(to));
            mimeMessage.setFrom(new InternetAddress("credit.application@untu-capital.com"));
            mimeMessage.setSubject(subject);

            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);

            helper.setText("<html><body><img src='cid:untulogo'></body></html>", true);

            FileSystemResource fileResource = new FileSystemResource(new File(fileToAttach));
            helper.addInline("untulogo", fileResource);
        };

        try {
            mailSender.send(preparator);
        } catch (MailException ex) {
            // simply log it and go on...
            System.err.println(ex.getMessage());
        }
    }

    public String buildConfirmationEmail(String firstName, String username, String token) {
        return "<div style=\"font-family:Helvetica,Arial,sans-serif;font-size:14px;margin:0;color:#0b0c0c\">\n" +
                "\n" +
                "<span style=\"display:none;font-size:1px;color:#fff;max-height:0\"></span>\n" +
                "\n" +
                "  <table role=\"presentation\" width=\"100%\" style=\"border-collapse:collapse;min-width:100%;width:100%!important\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\">\n" +
                "    <tbody><tr>\n" +
                "      <td width=\"100%\" height=\"53\" bgcolor=\"#ce2525\">\n" +
                "        \n" +
                "        <table role=\"presentation\" width=\"100%\" style=\"border-collapse:collapse;max-width:580px\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" align=\"center\">\n" +
                "          <tbody><tr>\n" +
                "            <td width=\"70\" bgcolor=\"#ce2525\" valign=\"middle\">\n" +
                "                <table role=\"presentation\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" style=\"border-collapse:collapse\">\n" +
                "                  <tbody><tr>\n" +
                "                    <td style=\"padding-left:10px\">\n" +
                "                  \n" +
                "                    </td>\n" +
                "                    <td style=\"font-size:26px;line-height:1.315789474;Margin-top:4px;padding-left:10px\">\n" +
                "                      <span style=\"font-family:Helvetica,Arial,sans-serif;font-weight:700;color:#ffffff;text-decoration:none;vertical-align:top;display:inline-block\">Confirm your email</span>\n" +
                "                    </td>\n" +
                "                  </tr>\n" +
                "                </tbody></table>\n" +
                "              </a>\n" +
                "            </td>\n" +
                "          </tr>\n" +
                "        </tbody></table>\n" +
                "        \n" +
                "      </td>\n" +
                "    </tr>\n" +
                "  </tbody></table>\n" +
                "  <table role=\"presentation\" class=\"m_-6186904992287805515content\" align=\"center\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" style=\"border-collapse:collapse;max-width:580px;width:100%!important\" width=\"100%\">\n" +
                "    <tbody><tr>\n" +
                "      <td width=\"10\" height=\"10\" valign=\"middle\"></td>\n" +
                "      <td>\n" +
                "        \n" +
                "                <table role=\"presentation\" width=\"100%\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" style=\"border-collapse:collapse\">\n" +
                "                  <tbody><tr>\n" +
                "                    <td bgcolor=\"#d5cd35\" width=\"100%\" height=\"10\"></td>\n" +
                "                  </tr>\n" +
                "                </tbody></table>\n" +
                "        \n" +
                "      </td>\n" +
                "      <td width=\"10\" valign=\"middle\" height=\"10\"></td>\n" +
                "    </tr>\n" +
                "  </tbody></table>\n" +
                "\n" +
                "\n" +
                "\n" +
                "  <table role=\"presentation\" class=\"m_-6186904992287805515content\" align=\"center\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" style=\"border-collapse:collapse;max-width:580px;width:100%!important\" width=\"100%\">\n" +
                "    <tbody><tr>\n" +
                "      <td height=\"30\"><br></td>\n" +
                "    </tr>\n" +
                "    <tr>\n" +
                "      <td width=\"10\" valign=\"middle\"><br></td>\n" +
                "      <td style=\"font-family:Helvetica,Arial,sans-serif;font-size:19px;line-height:1.315789474;max-width:560px\">\n" +
                "        \n" +
                "            <p style=\"Margin:0 0 20px 0;font-size:19px;line-height:25px;color:#0b0c0c\">Dear " + firstName + ",</p><p style=\"Margin:0 0 20px 0;font-size:19px;line-height:25px;color:#0b0c0c\"> Thank you for registering on Untu Capital Credit Application Portal. Please click on the below link to activate your account: " +
                "               </p><blockquote style=\"Margin:0 0 20px 0;border-left:10px solid #e8b592;padding:15px 0 0.1px 15px;font-size:19px;line-height:25px\"><p style=\"Margin:0 0 20px 0;font-size:19px;line-height:25px;color:#0b0c0c\"> <a href=\"" + confirmAccountUrl + "?username=" + username + "&code=" + token + "\">Confirm Account</a> </p></blockquote> <p>Cheers</br>Untu Credit Team</p>" +
                "        \n" +
                "      </td>\n" +
                "      <td width=\"10\" valign=\"middle\"><br></td>\n" +
                "    </tr>\n" +
                "    <tr>\n" +
                "      <td height=\"30\"><br></td>\n" +
                "    </tr>\n" +
                "  </tbody></table><div class=\"yj6qo\"></div><div class=\"adL\">\n" +
                "\n" +
                "</div></div>";
    }

    private String createRegEmailText(String firstName, String lastName, String username, String code) {
        StringBuilder sb = new StringBuilder();
        sb.append("\n Dear ").append(firstName).append(" ").append(lastName)
                .append(", \n\n Follow the link below to verify your email address \n http://localhost:4200/authentication/account-confirm?username=")
                .append(username)
                .append("&code=").append(code)
                .append("\n\n\n Regards \n Untu Credit Application Team.");
        return sb.toString();
    }

    public void sendPreConfiguredMail(String message) {
        SimpleMailMessage mailMessage = new SimpleMailMessage(preConfiguredMessage);
        mailMessage.setText(message);
        mailSender.send(mailMessage);
    }
}
