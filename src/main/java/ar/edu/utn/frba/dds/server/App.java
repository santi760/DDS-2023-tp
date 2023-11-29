package ar.edu.utn.frba.dds.server;

import ar.edu.utn.frba.dds.Instanciado;
import ar.edu.utn.frba.dds.models.community.notification_schedule.definded_moments.CronTaskNotificate;

import java.io.IOException;

public class App {
  public static void main(String[] args) throws IOException {
    //Instanciado.main(args);
    //? ESTA COMENTADO PERO ANDA, ES PARA ACTIVAR LA CRONTASK QUE HACE QUE ENVIE LA NOTIFICACION DE INCIDENTE
    //? AL HORARIO ESTABLECIDO POR EL MEMBER
    //CronTaskNotificate.getInstance().start();
    Server.init();

  }
}
