package ar.edu.utn.frba.dds.controllers;

import ar.edu.utn.frba.dds.models.services.Service;
import ar.edu.utn.frba.dds.models.users.User;
import ar.edu.utn.frba.dds.repositories.entities.ServiceRepository;
import ar.edu.utn.frba.dds.server.utils.ICrudViewsHandler;
import io.javalin.http.Context;

import java.util.*;

import static ar.edu.utn.frba.dds.models.users.RolType.*;

public class ServiceController extends Controller implements ICrudViewsHandler {



    public ServiceController(ServiceRepository repository) {

    }

    @Override
    public void index(Context context) {
        // este es para traer todos los
    }

    @Override
    public void show(Context context) {

    }

    public void showAll(Context context) {
        User user = super.usuarioLogueado(context);

        super.checkRolType(user, COMMUNITY_MEMBER);


        List<Service> services = ServiceRepository.getInstance().readAll();
        Map<String, Object> model = new HashMap<>();
        model.put("services", services);
        context.render("/community_member/services/AgregarServicioDeInteres.hbs", model);
    }



    @Override
    public void create(Context context) {

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
}