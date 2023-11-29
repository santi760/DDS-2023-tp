package ar.edu.utn.frba.dds.models.examples_of_use;

import ar.edu.utn.frba.dds.builders.NotificationMessageBuilder;
import ar.edu.utn.frba.dds.models.community_member.CommunityMember;
import ar.edu.utn.frba.dds.models.community.NotificationMessage;
import ar.edu.utn.frba.dds.models.community.notification_channel.whatsapp.WhatsAppSender;
import ar.edu.utn.frba.dds.models.users.User;

public class EjemploDeUsoWpp {
        // Find your Account SID and Auth Token at twilio.com/console
        // and set the environment variables. See http://twil.io/secure

    public static void main(String[] args) {
        CommunityMember communityMember = new CommunityMember();
        User user = new User("1","2");
       // communityMember.setUser(user);
        WhatsAppSender whatsAppSender = new WhatsAppSender();
        communityMember.getPerson().setPhoneNumber("5491123185162");
        //communityMember.getUser().setNotificationChannel(whatsAppSender);


        NotificationMessage notificationMessage = new NotificationMessageBuilder()
            .withTitle("Titulo de ejemplo")
            .withBody("con cuerpo de ejemplo")
            .build();

        //communityMember.getUser().getNotificationChannel().notificate(notificationMessage,communityMember);
    }
}

