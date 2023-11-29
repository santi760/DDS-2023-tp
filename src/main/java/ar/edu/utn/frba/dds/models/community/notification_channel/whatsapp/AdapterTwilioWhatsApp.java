package ar.edu.utn.frba.dds.models.community.notification_channel.whatsapp;

import ar.edu.utn.frba.dds.models.community_member.CommunityMember;
import ar.edu.utn.frba.dds.models.community.NotificationMessage;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class AdapterTwilioWhatsApp implements AdapterWhatsAppSender {
    // Find your Account Sid and Token at twilio.com/console
    Properties properties = new Properties();

    private String ACCOUNT_SID;
    private String AUTH_TOKEN;
    private String sendNumber;

    private String pathConfigFile = "config.properties";

    public AdapterTwilioWhatsApp(){
        try (InputStream fileInputStream = getClass().getClassLoader().getResourceAsStream(pathConfigFile)) {
            properties.load(fileInputStream);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        this.ACCOUNT_SID = properties.getProperty("ACCOUNT_SID");
        this.AUTH_TOKEN = properties.getProperty("AUTH_TOKEN");
        this.sendNumber = properties.getProperty("sendNumber");
    }

    @Override
    public void notificate(NotificationMessage notification, CommunityMember member) {

        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
        Message message = Message.creator(
                new com.twilio.type.PhoneNumber("whatsapp:+"+member.getPerson().getPhoneNumber()),
                new com.twilio.type.PhoneNumber(sendNumber),
                notification.getBody())
            .create();


    }

}
