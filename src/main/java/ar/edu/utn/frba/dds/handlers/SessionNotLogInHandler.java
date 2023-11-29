package ar.edu.utn.frba.dds.handlers;

import ar.edu.utn.frba.dds.exceptions.SessionNotLogInException;
import io.javalin.Javalin;

public class SessionNotLogInHandler implements IHandler {
    @Override
    public void setHandle(Javalin app) {
        app.exception(SessionNotLogInException.class, (e, context) -> {
            context.render("errors/no_loged.hbs");
        });
    }
}
