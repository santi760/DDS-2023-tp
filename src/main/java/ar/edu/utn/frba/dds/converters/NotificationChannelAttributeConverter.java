package ar.edu.utn.frba.dds.converters;

import ar.edu.utn.frba.dds.models.community.notification_channel.NotificationChannel;
import ar.edu.utn.frba.dds.models.community.notification_channel.email.EmailSender;
import ar.edu.utn.frba.dds.models.community.notification_channel.whatsapp.WhatsAppSender;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter(autoApply = true)
public class NotificationChannelAttributeConverter implements AttributeConverter<NotificationChannel, String> {

    @Override
    public String convertToDatabaseColumn(NotificationChannel notificationChannel) {
        if (notificationChannel == null) {
            return null;
        }
        return notificationChannel.getClass().getSimpleName();
    }

    @Override
    public NotificationChannel convertToEntityAttribute(String s) {
        String email = EmailSender.class.getSimpleName();
        String whatsapp = WhatsAppSender.class.getSimpleName();

        if (email.equals(s)){
            return new EmailSender();
        }
        else {
            if (whatsapp.equals(s)){
                return new WhatsAppSender();
            }

        }

        return null;
    }
}
