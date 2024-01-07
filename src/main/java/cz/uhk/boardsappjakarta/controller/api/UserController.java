package cz.uhk.boardsappjakarta.controller.api;

import cz.uhk.boardsappjakarta.dto.model.ErrorDTO;
import cz.uhk.boardsappjakarta.dto.model.SuccessDTO;
import cz.uhk.boardsappjakarta.dto.user.ChangePasswordUserDTO;
import cz.uhk.boardsappjakarta.dto.user.LoginUserDTO;
import cz.uhk.boardsappjakarta.dto.user.UsernameDTO;
import cz.uhk.boardsappjakarta.service.UserService;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Response;


@Path("/user/")
public class UserController {

    @Inject
    private UserService userService;

    @POST
    @Produces("application/json")
    @Consumes("application/json")
    @Path("/register")
    public Response registerUser(LoginUserDTO loginUserDTO) {
        try {
            userService.registerUser(loginUserDTO);
            return Response.ok().entity(new SuccessDTO("Created user with username: " + loginUserDTO.getUsername() + "!")).build();
        }
        catch (IllegalStateException e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(new ErrorDTO(e.getMessage())).build();
        }
    }

    @POST
    @Produces("application/json")
    @Consumes("application/json")
    @Path("/change-password")
    public Response changePassword(ChangePasswordUserDTO changePasswordUserDTO) {
        try {
            userService.changePassword(changePasswordUserDTO);
            return Response.ok().entity(new SuccessDTO("Password for user: " + userService.getCurrentUsername() + " successfully changed!")).build();
        }
        catch (IllegalStateException e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(new ErrorDTO(e.getMessage())).build();
        }
    }

    @POST
    @Produces("application/json")
    @Consumes("application/json")
    @Path("/disable-user")
    public Response disableUser(UsernameDTO usernameDTO) {
        try {
            userService.disableUser(usernameDTO.getUsername());
            return Response.ok().entity(new SuccessDTO("User: " + usernameDTO.getUsername() + " was blocked!")).build();
        }
        catch (IllegalStateException e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(new ErrorDTO(e.getMessage())).build();
        }
    }

    @GET
    @Produces("application/json")
    @Path("/current-user")
    public Response getCurrentUser() {
        try {
            return Response.ok().entity(userService.getCurrentUser()).build();
        }
        catch (IllegalStateException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(new ErrorDTO(e.getMessage())).build();
        }
    }

}
