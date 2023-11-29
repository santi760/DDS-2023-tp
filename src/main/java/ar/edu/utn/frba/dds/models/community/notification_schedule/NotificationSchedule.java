package ar.edu.utn.frba.dds.models.community.notification_schedule;

import ar.edu.utn.frba.dds.models.community_member.CommunityMember;
import ar.edu.utn.frba.dds.models.community.NotificationMessage;

public interface NotificationSchedule {

    public void sendNotificationIfScheduleMatches(NotificationMessage notification, CommunityMember member);
    public void checkAndSend();
}
