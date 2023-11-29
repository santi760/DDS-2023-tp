package ar.edu.utn.frba.dds.models.community.notification_channel.email;

import ar.edu.utn.frba.dds.models.community_member.CommunityMember;
import ar.edu.utn.frba.dds.models.community.NotificationMessage;
import jakarta.mail.*;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class AdapterJakartaEmail implements AdapterEmailSender {

    Properties properties = new Properties();
    String userName;
    String password;
    String port;

    String pathConfigFile = "config.properties";

    public AdapterJakartaEmail(){
        try (InputStream fileInputStream = getClass().getClassLoader().getResourceAsStream(pathConfigFile)) {
            properties.load(fileInputStream);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        this.userName = properties.getProperty("user");
        this.password = properties.getProperty("password");
        this.port = properties.getProperty("port");

        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.port", port);
    }

    Session session = Session.getDefaultInstance(properties, new Authenticator() {
        @Override
        protected PasswordAuthentication getPasswordAuthentication() {
            return new PasswordAuthentication(userName, password);
        }
    });

    @Override
    public void notificate(NotificationMessage notification , CommunityMember member) {
        MimeMessage message = new MimeMessage(session);
        try {
            message.addRecipients(Message.RecipientType.TO, new InternetAddress[]{new InternetAddress(member.getPerson().getEmail(), true)});
            //asunto
            message.setSubject(notification.getTitle());
            //texto en el mail
            message.setText(notification.getBody());

            Transport.send(message);

        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }

}
