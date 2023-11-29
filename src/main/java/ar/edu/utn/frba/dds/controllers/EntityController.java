package ar.edu.utn.frba.dds.controllers;

import ar.edu.utn.frba.dds.models.entities_establishment.Entity;
import ar.edu.utn.frba.dds.models.users.User;
import ar.edu.utn.frba.dds.repositories.entities.EntityRepository;
import ar.edu.utn.frba.dds.server.utils.ICrudViewsHandler;
import io.javalin.http.Context;

import java.util.*;

import static ar.edu.utn.frba.dds.models.users.RolType.*;

public class EntityController extends Controller implements ICrudViewsHandler {

    private EntityRepository entityRepository;

    public EntityController(EntityRepository repositorioDeEntidades) {
        this.entityRepository = repositorioDeEntidades;
    }

    @Override
    public void index(Context context) {

    }

    @Override
    public void show(Context context) {
        User user = super.usuarioLogueado(context);

        Map<String, Object> model = new HashMap<>();
        List<Entity> entities = this.entityRepository.readAll();
        model.put("openIncident", entities);
        model.put("Entity", entities);
        context.render("entities/Entities.hbs", model);
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

    public void showToUploadEntities(Context context) {
        User user = super.usuarioLogueado(context);


        super.checkRolType2(user, CONTROL_ORGANISM, PROVIDER);


        context.render("/entities/MassiveUploadProviderEntities.hbs");
    }

    public void showToUploadOrganismControl(Context context) {
        User user = super.usuarioLogueado(context);

        super.checkRolType2(user, CONTROL_ORGANISM, PROVIDER);
        context.render("/entities/MassiveUploadOrganismControl.hbs");
    }

    public void upload(Context context) {
        //TODO PORQUE ESTA VACIO??????
     }

    }


