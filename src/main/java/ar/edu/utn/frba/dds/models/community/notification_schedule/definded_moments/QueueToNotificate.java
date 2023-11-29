package ar.edu.utn.frba.dds.models.community.notification_schedule.definded_moments;

import ar.edu.utn.frba.dds.models.community_member.CommunityMember;
import lombok.Getter;
import lombok.Setter;


import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
public class QueueToNotificate {

    private static QueueToNotificate instance;

    List<CommunityMember> communityMembers = new ArrayList<>();

    public static synchronized QueueToNotificate getInstance() {
        if (instance == null) {
            instance = new QueueToNotificate();
        }
        return instance;
    }

    public void sendMessageIfScheduleMatches() {
        communityMembers.parallelStream().forEach(member -> member.getPerson().getUser().getNotificationSchedule().checkAndSend());
    }
}
