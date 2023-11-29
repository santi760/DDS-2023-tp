package ar.edu.utn.frba.dds.models.community.notification_schedule.right_now;

import ar.edu.utn.frba.dds.models.community_member.CommunityMember;
import ar.edu.utn.frba.dds.models.community.NotificationMessage;
import ar.edu.utn.frba.dds.models.community.notification_schedule.NotificationSchedule;

public class RightNow implements NotificationSchedule {

    @Override
    public void sendNotificationIfScheduleMatches(NotificationMessage notification, CommunityMember member) {
        member.getPerson().getUser().getNotificationChannel().notificate(notification, member);
    }

    @Override
    public void checkAndSend() {
        //* aca no hacemos nada porque esto es para las notificaciones con horarios establecidos
    }

    public RightNow(){

    }

}
