package ar.edu.utn.frba.dds.builders.users_roles;

import ar.edu.utn.frba.dds.converters.PasswordConverter;
import ar.edu.utn.frba.dds.models.community.notification_channel.NotificationChannel;
import ar.edu.utn.frba.dds.models.community.notification_schedule.NotificationSchedule;

import ar.edu.utn.frba.dds.models.users.RolType;
import ar.edu.utn.frba.dds.models.users.User;

import java.time.LocalDateTime;

public class UserBuilder {

    private User user = new User();
    private PasswordConverter converter = new PasswordConverter();

    public UserBuilder() {

    }

    public UserBuilder withname(String name) {
        this.user.setName(name);
        return this;
    }

    public UserBuilder withpassword(String password) {
        this.user.setPassword(password);
        return this;
    }

    public UserBuilder withNotificationSchedule(NotificationSchedule notificationSchedule) {
        this.user.setNotificationSchedule(notificationSchedule);
        return this;
    }

    public UserBuilder withNotificationChannel(NotificationChannel notificationChannel) {
        this.user.setNotificationChannel(notificationChannel);
        return this;
    }

    public UserBuilder withLastLoginAttemp(LocalDateTime lastLoginAttemp) {
        this.user.setLastLoginAttempt(lastLoginAttemp);
        return this;
    }

    public UserBuilder withRol(RolType rol) {
        this.user.setRolType(rol);
        return this;
    }


    public User build(){
        // validaciones si necesitamos
        this.user.setPassword(converter.convertToDatabaseColumn(this.user.getPassword()));
        return this.user;
    }
}