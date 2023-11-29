package ar.edu.utn.frba.dds.models.examples_of_use;

import ar.edu.utn.frba.dds.builders.NotificationMessageBuilder;
import ar.edu.utn.frba.dds.models.community_member.CommunityMember;
import ar.edu.utn.frba.dds.models.community.NotificationMessage;
import ar.edu.utn.frba.dds.models.community.notification_channel.email.EmailSender;
import ar.edu.utn.frba.dds.models.users.User;

import java.util.Timer;
import java.util.Calendar;
import java.util.Date;
public class EjemploUsoEnviarMail {
    public static void main(String[] args) {
        Timer timer = new Timer();

        // Obtiene el horario personal deseado
        int horarioPersonal = 1547; // Por ejemplo, 9:00 AM

        // Calcula los valores de hora y minuto
        int hora = horarioPersonal / 100; // Divide por 100 para obtener la hora
        int minuto = horarioPersonal % 100; // Obtiene el resto para obtener los minutos

        CommunityMember communityMember = new CommunityMember();
        User user = new User("1","2");
        //communityMember.setUser(user);
        EmailSender emailSender = new EmailSender();
        communityMember.getPerson().setEmail("nicogalfione@gmail.com");
        //communityMember.getUser().setNotificationChannel(emailSender);
        // Crea una instancia de la tarea programada
        //NotificadorTemporizado tarea = new NotificadorTemporizado(communityMember);

        NotificationMessage notification = new NotificationMessageBuilder().withTitle("titulo de ejemplo").withBody("Cuerpo notificacion").build();

        //communityMember.getUser().getNotificationChannel().notificate(notification,communityMember);
        // Programa la tarea para que se ejecute en el horario deseado todos los días
        //timer.schedule(tarea, getHora(hora, minuto));
    }

    private static Date getHora(int hora, int minuto) {
        Calendar calendario = Calendar.getInstance();
        calendario.set(Calendar.HOUR_OF_DAY, hora);
        calendario.set(Calendar.MINUTE, minuto);
        calendario.set(Calendar.SECOND, 0);

        // Si deseas ejecutar la tarea inmediatamente, descomenta la siguiente línea
        // calendario.add(Calendar.DAY_OF_MONTH, 1);

        return calendario.getTime();
    }
}
