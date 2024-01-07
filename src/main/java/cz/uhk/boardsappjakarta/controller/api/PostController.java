package cz.uhk.boardsappjakarta.controller.api;

import cz.uhk.boardsappjakarta.dto.model.ErrorDTO;
import cz.uhk.boardsappjakarta.dto.model.SuccessDTO;
import cz.uhk.boardsappjakarta.dto.post.NewPostDTO;
import cz.uhk.boardsappjakarta.service.PostService;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Response;

@Path("/posts/")
public class PostController {

    @Inject
    private PostService postService;

    @POST
    @Produces("application/json")
    @Consumes("application/json")
    @Path("/new")
    public Response addNewPost(NewPostDTO newPostDTO) {
        try {
            Long id = postService.addNewPost(newPostDTO);
            return Response.ok().entity(new SuccessDTO("New post with id " + id + " created")).build();
        }
        catch (IllegalStateException e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(new ErrorDTO(e.getMessage())).build();
        }
    }

    @POST
    @Produces("application/json")
    @Consumes("application/json")
    @Path("/update/{id}")
    public Response updatePost(@PathParam("id") Long postId, NewPostDTO newPostDTO) {
        try {
            postService.updatePost(postId, newPostDTO);
            return Response.ok().entity(new SuccessDTO("Post with id " + postId + " successfully updated")).build();
        }
        catch (IllegalStateException e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(new ErrorDTO(e.getMessage())).build();
        }
    }

    @POST
    @Produces("application/json")
    @Path("/remove/{id}")
    public Response removePost(@PathParam("id") Long postId) {
        try {
            postService.removePost(postId);
            return Response.ok().entity(new SuccessDTO("Post with id " + postId + " successfully removed")).build();
        }
        catch (IllegalStateException e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(new ErrorDTO(e.getMessage())).build();
        }
    }

    @GET
    @Produces("application/json")
    @Path("/post/{id}")
    public Response findPost(@PathParam("id") Long id) {
        try {
            return Response.ok().entity(postService.findPost(id)).build();
        }
        catch (IllegalStateException e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(new ErrorDTO(e.getMessage())).build();
        }
    }

    @GET
    @Produces("application/json")
    @Path("/all")
    public Response findVisiblePosts() {
        try {
            return Response.ok().entity(postService.findVisiblePosts()).build();
        }
        catch (IllegalStateException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(new ErrorDTO(e.getMessage())).build();
        }
    }

    @GET
    @Produces("application/json")
    @Path("/all-paged")
    public Response findVisiblePosts(@QueryParam("page") int pageNumber, @QueryParam("size") int pageSize) {
        try {
            return Response.ok().entity(postService.findVisiblePosts(pageNumber, pageSize)).build();
        }
        catch (IllegalStateException e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(new ErrorDTO(e.getMessage())).build();
        }
    }
}
