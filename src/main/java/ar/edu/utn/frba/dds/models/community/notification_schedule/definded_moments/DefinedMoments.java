package ar.edu.utn.frba.dds.models.community.notification_schedule.definded_moments;



import ar.edu.utn.frba.dds.models.community_member.CommunityMember;
import ar.edu.utn.frba.dds.models.community.NotificationMessage;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import ar.edu.utn.frba.dds.models.community.notification_schedule.NotificationSchedule;
import lombok.Getter;
import lombok.Setter;
@Setter
@Getter
public class DefinedMoments implements NotificationSchedule {

    private CommunityMember member;

    private List<NotificationMessage> unreportedNotifications = new ArrayList<>(); //* aca guardamos todos las notificaciones que aun no han sido notificadas

    //* ---------------------------- CONSTRUCTORS ---------------------------- *//
    public DefinedMoments(){

    }


    //* ---------------------------- METHODS ---------------------------- *//
    @Override
    public void sendNotificationIfScheduleMatches(NotificationMessage notification, CommunityMember member) {
        //me fijo que el miembro este en la lista, y si no esta lo agrego.
        this.member = member;

        if (!QueueToNotificate.getInstance().getCommunityMembers().contains(member)) {
            QueueToNotificate.getInstance().getCommunityMembers().add(member);
        }

        this.unreportedNotifications.add(notification);
    }


    //! error. Es un checkAndSend para refactorizar nombre de la funcion.
    //hecho
    @Override
    public void checkAndSend(){
        // decidimos notificar hora por hora. solo es configurable de 16 |17 en numeros concretos. ignoramos minutos por este momento. a menos q lo pidan
        LocalTime now = LocalTime.now();
        List<NotificationMessage> unreportedNotificationsUnder24Hours;
        unreportedNotificationsUnder24Hours = this.unreportedNotifications.stream().filter(notificationMessage -> notificationMessage.getIncident().isWithin24Hours()).collect(Collectors.toList());
        member.getMomentsNotificate().forEach(horaConfigurada -> {
            if (horaConfigurada == now.getHour() && !unreportedNotifications.isEmpty() ) {
                NotificationMessage notificationMessage =  new NotificationMessage();
                member.getPerson().getUser().getNotificationChannel().notificate(notificationMessage.resume(unreportedNotificationsUnder24Hours), member);
                this.unreportedNotifications.clear();
            }
        });
    }
}
