package ar.edu.utn.frba.dds.handlers;

import io.javalin.Javalin;

public class ServerErrorHandler implements IHandler{
    @Override
    public void setHandle(Javalin app) {
        app.exception(Exception.class, (e, context) -> {

                context.status(500).render("errors/manage_500.hbs");


        });
    }
}
