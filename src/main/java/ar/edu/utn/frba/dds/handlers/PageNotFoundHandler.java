package ar.edu.utn.frba.dds.handlers;

import io.javalin.Javalin;
import io.javalin.http.NotFoundResponse;

public class PageNotFoundHandler implements IHandler{
    @Override
    public void setHandle(Javalin app) {
        app.exception(NotFoundResponse.class, (e, context) -> {
            context.render("errors/manage_404.hbs");
        });
    }
}
