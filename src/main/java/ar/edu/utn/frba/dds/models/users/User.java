package ar.edu.utn.frba.dds.models.users;

import java.time.LocalDateTime;
import ar.edu.utn.frba.dds.converters.NotificationChannelAttributeConverter;
import ar.edu.utn.frba.dds.converters.NotificationScheduleAttributeConverter;
import ar.edu.utn.frba.dds.models.community.notification_channel.NotificationChannel;
import ar.edu.utn.frba.dds.models.community.notification_schedule.NotificationSchedule;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Getter
@Setter
@ToString
@Entity
@Table(name = "user")
public class User {

  @Id
  @GeneratedValue
  private Long id;

  @Column(name = "name")
  protected String name;

  @Column(name = "password")
  protected String password;

  @Column(name = "lastLoginAttempt")
  protected LocalDateTime lastLoginAttempt;

  @Convert(converter = NotificationScheduleAttributeConverter.class)
  @Column(name = "notificationSchedule")
  protected NotificationSchedule notificationSchedule;

  @Convert(converter = NotificationChannelAttributeConverter.class)
  @Column(name = "notificationChannel")
  protected NotificationChannel notificationChannel;

  @Enumerated(EnumType.STRING)
  @Column(name = "rolType")
  private RolType rolType;


  // * -------------------------------- CONSTRUCTORS -------------------------------- *//

  public User(String name, String password){
    this.name = name;
    this.password = password;
  }


  public User(){

  }

  // * -------------------------------- METHOD -------------------------------- *//


}
