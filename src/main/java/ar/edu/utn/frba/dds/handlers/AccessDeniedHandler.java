package ar.edu.utn.frba.dds.handlers;

import ar.edu.utn.frba.dds.exceptions.AccessDeniedException;
import io.javalin.Javalin;

public class AccessDeniedHandler implements IHandler{
    @Override
    public void setHandle(Javalin app) {
        app.exception(AccessDeniedException.class, (e, context) -> {
            context.render("errors/no_permission.hbs");
        });
    }

}
