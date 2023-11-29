package ar.edu.utn.frba.dds.handlers;

import io.javalin.Javalin;
import java.util.Arrays;

public class AppHandlers {
    private IHandler[] handlers = new IHandler[]{
            new AccessDeniedHandler(),
            new SessionNotLogInHandler(),
            new PageNotFoundHandler(),
            new SessionNotLogInHandler(),
            new ServerErrorHandler(),
    };

    public static void applyHandlers(Javalin app) {
        Arrays.stream(new AppHandlers().handlers).toList().forEach(handler -> handler.setHandle(app));
    }
}
