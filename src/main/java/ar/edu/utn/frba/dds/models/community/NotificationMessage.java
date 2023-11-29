package ar.edu.utn.frba.dds.models.community;

import ar.edu.utn.frba.dds.builders.NotificationMessageBuilder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@ToString
@Entity
@Table(name = "notificationMessage")
public class NotificationMessage {

  @Id
  @GeneratedValue
  private Long id;

  @Column(name = "title")
  private String title;

  @Column(name = "body" , columnDefinition = "text")
  private String body;

  @ManyToOne
  @JoinColumn(name = "incident_id", referencedColumnName ="id")
  private Incident incident;

  @Column(name = "sended")
  private Boolean sended;

  @Column(name = "timeSended")
  private LocalDateTime timeSended;
  
  @Column(name = "messageOfSummarizing", columnDefinition = "longtext")
  private String messageOfSummarizing = "Resumen de tus notificaciones";


  //* ----------------- CONSTRUCTORS ----------------- *//

  public NotificationMessage(){

  }

  //* ----------------- METHODS ----------------- *//

  public NotificationMessage resume(List<NotificationMessage> notificationMessages){

    NotificationMessage notificationMessageSend = new NotificationMessageBuilder().withTitle(messageOfSummarizing).build();

    StringBuilder resume = new StringBuilder();
    for(NotificationMessage notificationMessage : notificationMessages){
      resume.append(notificationMessage.getTitle() + " " + notificationMessage.getBody());
      resume.append("\n");
    }
    notificationMessageSend.setBody(resume.toString());

    return notificationMessageSend;
  }
}
