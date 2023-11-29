package ar.edu.utn.frba.dds.controllers;


import ar.edu.utn.frba.dds.models.entities_establishment.Establishment;
import ar.edu.utn.frba.dds.models.users.User;
import ar.edu.utn.frba.dds.repositories.entities.*;
import ar.edu.utn.frba.dds.server.utils.ICrudViewsHandler;
import io.javalin.http.Context;
import java.util.HashMap;
import java.util.List;
import java.util.Map;



public class EstablishmentController extends Controller implements ICrudViewsHandler {



    public EstablishmentController(EstablishmentRepository repositorioDeEstablecimientos) {

    }

    @Override
    public void index(Context context) {

    }

    @Override
    public void show(Context context) {
        User user = super.usuarioLogueado(context);

        Map<String, Object> model = new HashMap<>();
        List<Establishment> establishments = EstablishmentRepository.getInstance().readAll();
        model.put("openIncident", establishments);
        model.put("Establishment", establishments);
        context.render("/entities/Establishments.hbs", model);
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