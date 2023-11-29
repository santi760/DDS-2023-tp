package ar.edu.utn.frba.dds.models.community.notification_channel.email;

import ar.edu.utn.frba.dds.models.community_member.CommunityMember;
import ar.edu.utn.frba.dds.models.community.NotificationMessage;

public interface AdapterEmailSender {
    void notificate(NotificationMessage notification, CommunityMember member);
}
