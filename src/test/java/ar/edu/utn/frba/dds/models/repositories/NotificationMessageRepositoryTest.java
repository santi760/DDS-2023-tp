package ar.edu.utn.frba.dds.models.repositories;

import static org.junit.jupiter.api.Assertions.assertEquals;

import ar.edu.utn.frba.dds.models.community.NotificationMessage;
import ar.edu.utn.frba.dds.repositories.entities.NotificationMessageRepository;

import org.junit.jupiter.api.Test;

public class NotificationMessageRepositoryTest {

  @Test
  public void notificationMessageTest(){
    Long n = 2L;
    NotificationMessage message_01 = NotificationMessageRepository.getInstance().read(n);
    assertEquals("cuerpoNotificacion2",message_01.getBody());

    n = 3L;
    NotificationMessage message_02 = NotificationMessageRepository.getInstance().read(n);
    assertEquals("cuerpoNotificacion3",message_02.getBody());


  }
}
