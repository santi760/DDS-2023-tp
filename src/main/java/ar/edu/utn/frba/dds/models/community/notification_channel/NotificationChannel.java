package ar.edu.utn.frba.dds.models.community.notification_channel;

import ar.edu.utn.frba.dds.models.community_member.CommunityMember;
import ar.edu.utn.frba.dds.models.community.NotificationMessage;

public interface NotificationChannel {

    public void notificate(NotificationMessage notificationMessage, CommunityMember member);

}
