package ar.edu.utn.frba.dds.models.crontask;

import ar.edu.utn.frba.dds.builders.NotificationMessageBuilder;
import ar.edu.utn.frba.dds.models.community_member.CommunityMember;
import ar.edu.utn.frba.dds.models.community.NotificationMessage;
import java.util.TimerTask;

//! queda para el ejemplo de uso de enviar Mail
public class NotificadorTemporizado extends TimerTask  {

        private CommunityMember communityMember;
        public NotificadorTemporizado(CommunityMember communityMember){
            this.communityMember=communityMember;
        }
        @Override
        public void run() {
            // Coloca aquí el código que deseas ejecutar en la tarea programada
            NotificationMessage notification = new NotificationMessageBuilder().withTitle("titulo de ejemplo").withBody("Cuerpo notificacion").build();
            this.communityMember.notificate(notification);
            System.out.println("Se ejecutó la tarea programada");
        }

}

