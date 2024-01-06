package cz.uhk.boardsappjakarta;

import cz.uhk.boardsappjakarta.persistence.dao.UserDAO;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;

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
    public String helloUser() {
        return userDAO.findOne("admin").getUsername();
    }
}