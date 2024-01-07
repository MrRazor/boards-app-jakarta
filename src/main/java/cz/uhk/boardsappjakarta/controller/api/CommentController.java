package cz.uhk.boardsappjakarta.controller.api;

import cz.uhk.boardsappjakarta.dto.comment.NewCommentDTO;
import cz.uhk.boardsappjakarta.dto.model.ErrorDTO;
import cz.uhk.boardsappjakarta.dto.model.SuccessDTO;
import cz.uhk.boardsappjakarta.service.CommentService;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Response;

@Path("/comments/")
public class CommentController {

    @Inject
    private CommentService commentService;

    @POST
    @Produces("application/json")
    @Consumes("application/json")
    @Path("/new/{id}")
    public Response addNewComment(@PathParam("id") Long replyPostId, NewCommentDTO newCommentDTO) {
        try {
            Long id = commentService.addNewComment(replyPostId, newCommentDTO);
            return Response.ok().entity(new SuccessDTO("New comment with id " + id + " created")).build();
        }
        catch (IllegalStateException e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(new ErrorDTO(e.getMessage())).build();
        }
    }

    @POST
    @Produces("application/json")
    @Consumes("application/json")
    @Path("/update/{id}")
    public Response updateComment(@PathParam("id") Long commentId, NewCommentDTO newCommentDTO) {
        try {
            commentService.updateComment(commentId, newCommentDTO);
            return Response.ok().entity(new SuccessDTO("Comment with id " + commentId + " successfully updated")).build();
        }
        catch (IllegalStateException e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(new ErrorDTO(e.getMessage())).build();
        }
    }

    @POST
    @Produces("application/json")
    @Path("/remove/{id}")
    public Response removeComment(@PathParam("id") Long commentId) {
        try {
            commentService.removeComment(commentId);
            return Response.ok().entity(new SuccessDTO("Comment with id " + commentId + " successfully removed")).build();
        }
        catch (IllegalStateException e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(new ErrorDTO(e.getMessage())).build();
        }
    }

    @GET
    @Produces("application/json")
    @Path("/comment/{id}")
    public Response findComment(@PathParam("id") Long id) {
        try {
            return Response.ok().entity(commentService.findComment(id)).build();
        }
        catch (IllegalStateException e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(new ErrorDTO(e.getMessage())).build();
        }
    }

    @GET
    @Produces("application/json")
    @Path("/all/{id}")
    public Response findVisibleCommentsByPostId(@PathParam("id") Long postId) {
        try {
            return Response.ok().entity(commentService.findVisibleCommentsByPostId(postId)).build();
        }
        catch (IllegalStateException e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(new ErrorDTO(e.getMessage())).build();
        }
    }

    @GET
    @Produces("application/json")
    @Path("/all-paged/{id}")
    public Response findVisibleCommentsByPostId(@PathParam("id") Long postId, @QueryParam("page") int pageNumber, @QueryParam("size") int pageSize) {
        try {
            return Response.ok().entity(commentService.findVisibleCommentsByPostId(postId, pageNumber, pageSize)).build();
        }
        catch (IllegalStateException e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(new ErrorDTO(e.getMessage())).build();
        }
    }
}
