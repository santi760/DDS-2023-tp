package ar.edu.utn.frba.dds.models.community.notification_channel.email;

import ar.edu.utn.frba.dds.models.community_member.CommunityMember;
import ar.edu.utn.frba.dds.models.community.NotificationMessage;
import ar.edu.utn.frba.dds.models.community.notification_channel.NotificationChannel;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EmailSender implements NotificationChannel {

    AdapterEmailSender emailAdapter = new AdapterJakartaEmail();

    public EmailSender(AdapterEmailSender adapter) {
      this.emailAdapter = adapter;
    }
    public EmailSender(){}

    @Override
    public void notificate(NotificationMessage notificationMessage, CommunityMember member) {
        emailAdapter.notificate(notificationMessage, member);
    }
}
