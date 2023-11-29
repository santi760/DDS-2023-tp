package ar.edu.utn.frba.dds.controllers;

import ar.edu.utn.frba.dds.builders.PersonBuilder;
import ar.edu.utn.frba.dds.builders.users_roles.UserBuilder;
import ar.edu.utn.frba.dds.converters.PasswordConverter;
import ar.edu.utn.frba.dds.models.community.notification_channel.email.EmailSender;
import ar.edu.utn.frba.dds.models.community.notification_schedule.right_now.RightNow;
import ar.edu.utn.frba.dds.models.community_member.Person;
import ar.edu.utn.frba.dds.models.users.RolType;
import ar.edu.utn.frba.dds.models.users.User;
import ar.edu.utn.frba.dds.models.validators.PasswordValidator;
import ar.edu.utn.frba.dds.repositories.entities.PersonRepository;
import ar.edu.utn.frba.dds.repositories.users.UserRepository;
import ar.edu.utn.frba.dds.server.utils.ICrudViewsHandler;
import io.javalin.http.Context;


import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class AuthenticateController extends Controller implements ICrudViewsHandler  {


    private PasswordConverter converter = new PasswordConverter();

    public AuthenticateController(UserRepository repositorioDeUsuarios) {

    }

    @Override
    public void index(Context context) {
        Map<String, Object> model = new HashMap<>();
        List<User> users  = UserRepository.getInstance().readAll();
        model.put("users", users);
        context.render("public/sign-in.hbs", model);
    }

    @Override
    public void show(Context context) {

    }

    public void showError(Context context) {
        Map<String, Object> model = new HashMap<>();
        context.render("public/login-failed.hbs", model);
    }

    public void session(Context context) {
        Map<String, Object> model = new HashMap<>();

        String username = context.formParam("username");
        String password = context.formParam("password");
        // pongo usario que no existe y me explota.

        User usuarioIniciado = null;
        try {
            usuarioIniciado = UserRepository.getInstance().readByUsername(username);
        } catch (Exception e){
            context.redirect("/login-failed");
            return;
        }

        usuarioIniciado = usuarioIniciado = UserRepository.getInstance().readByUsername(username);

        if (!converter.convertToDatabaseColumn(password).equals(usuarioIniciado.getPassword())) {
            context.redirect("/login-failed");
            return;
        }
        usuarioIniciado.setLastLoginAttempt(LocalDateTime.now());
        UserRepository.getInstance().update(usuarioIniciado);

        context.sessionAttribute("user_id", usuarioIniciado.getId());
        //es context.render() o context.redirect()?????????????
        if(usuarioIniciado.getRolType().equals(RolType.COMMUNITY_MEMBER)){
            context.redirect("/dashboard");
        }
        else if (usuarioIniciado.getRolType().equals(RolType.CONTROL_ORGANISM)){
        
            context.redirect("controlOrganism/providerEntities");

        } else if (usuarioIniciado.getRolType().equals(RolType.PROVIDER)) {

            context.redirect("providerEntity/entities");
        }
    }

    public void showLogin(Context context) {
        Map<String, Object> model = new HashMap<>();
        //User user = this.userRepository.read(1);
        //model.put("user", user);


        if(context.sessionAttribute("user_id") != null) {
            User usuarioIniciado = super.usuarioLogueado(context);
            if(usuarioIniciado.getRolType().equals(RolType.COMMUNITY_MEMBER)){
                context.redirect("/dashboard");
            }
            else if (usuarioIniciado.getRolType().equals(RolType.CONTROL_ORGANISM) || usuarioIniciado.getRolType().equals(RolType.PROVIDER)){

                context.redirect("/providerEntities");

            }
        }else{

        }

        context.render("public/login.hbs", model);
    }

    public void showSignIn(Context context) {
        //User user = this.userRepository.read(1); TODO ver como hacer para que funcione esto
        Map<String, Object> model = new HashMap<>();
        //model.put("user", user);
        context.render("public/sign-in.hbs", model);
    }

    @Override
    public void create(Context context) {

        //? datos que van solamente al user.
        String username = context.formParam("username");
        String password = context.formParam("password");


        //? datos que van a la persona asociada al usuario.

        String name = context.formParam("name");
        String surname = context.formParam("surname");
        String email = context.formParam("email");


        // validamos la contraseña con nuestro validador de la primera entrega! hello there!
        PasswordValidator passwordValidator = new PasswordValidator();
        List<String> listOfValidations = passwordValidator.validate(name,password);

        if(listOfValidations.isEmpty()){

            User newUser = new UserBuilder()
                    .withname(username)
                    .withpassword(password)
                    .withRol(RolType.COMMUNITY_MEMBER)
                    .withNotificationChannel(new EmailSender())
                    .withNotificationSchedule(new RightNow())
                    .withLastLoginAttemp(LocalDateTime.now())
                    .build();

            Person newPerson = new PersonBuilder()
                .withUser(newUser)
                .withName(name)
                .withSurname(surname)
                .withEmail(email)
                .build();

            UserRepository.getInstance().create(newUser);
            PersonRepository personRepository = PersonRepository.getInstance();
            personRepository.create(newPerson);

            context.sessionAttribute("user_id", newUser.getId());

            if(newUser.getRolType().equals(RolType.COMMUNITY_MEMBER)){
                context.redirect("/dashboard");
            }


        } else {
            //! en este caso, la contraseña es insegura, el usuario debe volver a ingresar otra.
            Map<String, Object> model = new HashMap<>();
            model.put("listOfValidations", listOfValidations);
            context.render("public/sign-in.hbs", model);
        }
    }

    @Override
    public void save(Context context) {

    }

    @Override
    public void edit(Context context) {

    }

    @Override
    public void update(Context context) {

    }

    @Override
    public void delete(Context context) {

    }

    public void logOut(Context context){
        context.req().getSession().removeAttribute("user_id");


        context.redirect("/");
    }
}
