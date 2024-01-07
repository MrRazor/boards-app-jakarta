package cz.uhk.boardsappjakarta.controller.index;

import jakarta.ws.rs.ApplicationPath;
import jakarta.ws.rs.core.Application;

import java.util.Set;

@ApplicationPath("")
public class IndexApplication extends Application {
    @Override
    public Set<Class<?>> getClasses() {
        return Set.of(Controller.class);
    }
}