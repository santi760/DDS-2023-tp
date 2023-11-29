package ar.edu.utn.frba.dds.models.community.notification_channel.whatsapp;

import ar.edu.utn.frba.dds.models.community_member.CommunityMember;
import ar.edu.utn.frba.dds.models.community.NotificationMessage;

public interface AdapterWhatsAppSender {
    void notificate(NotificationMessage notification, CommunityMember member);
}
