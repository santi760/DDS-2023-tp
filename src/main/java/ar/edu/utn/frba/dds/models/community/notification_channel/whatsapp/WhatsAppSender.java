package ar.edu.utn.frba.dds.models.community.notification_channel.whatsapp;

import ar.edu.utn.frba.dds.models.community_member.CommunityMember;
import ar.edu.utn.frba.dds.models.community.NotificationMessage;
import ar.edu.utn.frba.dds.models.community.notification_channel.NotificationChannel;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class WhatsAppSender implements NotificationChannel {

    AdapterWhatsAppSender whatsAppSender = new AdapterTwilioWhatsApp();

    public WhatsAppSender(AdapterWhatsAppSender adapter){
        this.whatsAppSender = adapter;
    }

    public WhatsAppSender(){

    }


    @Override
    public void notificate(NotificationMessage notificationMessage, CommunityMember member) {
        whatsAppSender.notificate(notificationMessage,member);
    }
}
