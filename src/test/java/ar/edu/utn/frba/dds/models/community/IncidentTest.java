package ar.edu.utn.frba.dds.models.community;

import static org.junit.jupiter.api.Assertions.*;

import ar.edu.utn.frba.dds.models.community_member.CommunityMember;
import ar.edu.utn.frba.dds.models.locations.Province;

import static org.mockito.Mockito.*;

import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.time.LocalDateTime;

class IncidentTest {
  CommunityMember openMember = new CommunityMember();


  @Test
  public void calculoDuracionAperturaCierreIncidente(){
    /*
      Incident incident = new Incident();
      Duration duration = calculateDuration(openingDate, closingDate); PARA TESTEAR


     long days = duration.toDays();
     long hours = duration.toHours() % 24;
     long minutes = duration.toMinutes() % 60;
     long seconds = duration.getSeconds() % 60;

     System.out.println("Diferencia: " + days + " días, " + hours + " horas, " + minutes + " minutos, " + seconds + " segundos.");

    Incident incident = new Incident();
    */
  }
  @Test
  public void consultaDeEstadoDeIncidente(){

    Incident incident = new Incident();
    LocalDateTime inicioIncidente = LocalDateTime.of(2023,7,11,0,0);
    LocalDateTime finalIncidente = LocalDateTime.of(2023,7,12,0,0);
    CommunityMember communityMember = new CommunityMember();

    incident.setOpeningDate(inicioIncidente);
    incident.setClosingDate(finalIncidente);

    Duration duracion =Duration.ofDays(1);
    assertEquals(true,incident.getOpen());


    assertEquals(duracion,incident.calculateDuration(inicioIncidente,finalIncidente));


    incident.close(communityMember, "razon desconocida");
    assertEquals(false, incident.getOpen());

    incident = mock(Incident.class);
    Province province = new Province();
    when(incident.getProvince()).thenReturn(province);


    assertEquals(province, incident.getProvince());


  }


  // mas tests:
  /*
  *  mockear notificacion de incidentes para ver si notifican, hay un video en el aula virtual de como mockear
  *
  *
  *
  *
  * */


}