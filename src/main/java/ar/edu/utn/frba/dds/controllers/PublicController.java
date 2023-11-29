package ar.edu.utn.frba.dds.controllers;

import ar.edu.utn.frba.dds.models.users.User;
import ar.edu.utn.frba.dds.server.utils.ICrudViewsHandler;
import io.javalin.http.Context;
import io.javalin.http.HttpStatus;
import java.util.HashMap;
import java.util.Map;


public class PublicController extends Controller implements ICrudViewsHandler {

    // private ; Instanciar algun repo vale la pena?

    public PublicController() {

    }

    @Override
    public void index(Context context) {
        Map<String, Object> model = new HashMap<>();
        context.render("public/home.hbs", model);
    }

    @Override
    public void show(Context context) {

        User user = super.usuarioLogueado(context);
        Map<String, Object> model = new HashMap<>();
        context.render("community_member/dashboard.hbs", model);
    }

    @Override
    public void create(Context context) {
        context.status(HttpStatus.NOT_ACCEPTABLE);
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
