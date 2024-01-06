package cz.uhk.boardsappjakarta;

import cz.uhk.boardsappjakarta.persistence.dao.UserDAO;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;

import java.util.HashMap;
import java.util.Map;

@Path("/hello-world")
public class HelloResource {

    @Inject
    private UserDAO userDAO;

    @GET
    @Path("/hello")
    @Produces("text/plain")
    public String hello() {
        return "Hello, World!";
    }

    @GET
    @Path("/user")
    @Produces("application/json")
    public Map<String, String> helloUser() {
        Map<String, String> map = new HashMap<>();
        map.put("username", userDAO.findOne("admin").getUsername());
        return map;
    }
}