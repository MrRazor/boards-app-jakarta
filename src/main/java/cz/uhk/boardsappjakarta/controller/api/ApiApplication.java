package cz.uhk.boardsappjakarta.controller.api;

import cz.uhk.boardsappjakarta.controller.ObjectMapperContextResolver;
import jakarta.ws.rs.ApplicationPath;
import jakarta.ws.rs.core.Application;

import java.util.Set;

@ApplicationPath("/api/")
public class ApiApplication extends Application {
    @Override
    public Set<Class<?>> getClasses() {
        return Set.of(UserController.class, PostController.class, CommentController.class, ObjectMapperContextResolver.class);
    }
}