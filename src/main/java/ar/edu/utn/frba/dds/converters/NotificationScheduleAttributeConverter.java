package ar.edu.utn.frba.dds.converters;

import ar.edu.utn.frba.dds.models.community.notification_schedule.NotificationSchedule;
import ar.edu.utn.frba.dds.models.community.notification_schedule.definded_moments.DefinedMoments;
import ar.edu.utn.frba.dds.models.community.notification_schedule.right_now.RightNow;
import javax.persistence.AttributeConverter;
import javax.persistence.Converter;


@Converter(autoApply = true)
public class NotificationScheduleAttributeConverter implements AttributeConverter<NotificationSchedule, String> {


    @Override
    public String convertToDatabaseColumn(NotificationSchedule notificationSchedule) {
        if(notificationSchedule == null){
            return null;
        }

        return notificationSchedule.getClass().getSimpleName();
    }

    @Override
    public NotificationSchedule convertToEntityAttribute(String s) {
        String notificationNow = RightNow.class.getSimpleName();
        String notificationDifinedMoments = DefinedMoments.class.getSimpleName();

        if (notificationNow.equals(s)){
            return new RightNow();
        }
        else {
            if (notificationDifinedMoments.equals(s)){
                return new DefinedMoments();
            }

        }

        return null;
    }
}
