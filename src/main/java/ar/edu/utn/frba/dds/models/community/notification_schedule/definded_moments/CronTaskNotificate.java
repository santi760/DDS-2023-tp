package ar.edu.utn.frba.dds.models.community.notification_schedule.definded_moments;

import lombok.Getter;
import lombok.Setter;

import java.util.Timer;
import java.util.TimerTask;
@Getter
@Setter
public class CronTaskNotificate extends TimerTask{

    private static CronTaskNotificate instance;
    private Timer timer = new Timer();

  // Programa la tarea para que se ejecute cada 1 hora (3600000 milisegundos)
    private int timeInMillisecondsUntilNextActivation = 3600000;

    private int delay = 0; // esto ni idea que es

    public static synchronized CronTaskNotificate getInstance() {
        if (instance == null) {
            instance = new CronTaskNotificate();
        }
        return instance;
    }

    //lo que va a pasar cada vez que se ejecute la crontask
    @Override
    public void run() {
        // Lógica de la tarea a ejecutar
        this.verificateNotification();
    }

    //este metodo tiene que ser llamado cuando corran las cosas ya que es el que se encagar
    //de ejecutar la crontask para notificar a los members
    public void start(){
        timer.schedule(instance, delay, timeInMillisecondsUntilNextActivation);
    }

    public void cancelCrontask(){
        this.cancel();
    }

    public void verificateNotification(){
        QueueToNotificate.getInstance().sendMessageIfScheduleMatches();

    }

}
