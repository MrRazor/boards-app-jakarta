package cz.uhk.boardsappjakarta.service;

import cz.uhk.boardsappjakarta.dto.comment.CommentDTO;
import cz.uhk.boardsappjakarta.dto.comment.NewCommentDTO;
import cz.uhk.boardsappjakarta.dto.mapper.CommentDTOMapper;
import cz.uhk.boardsappjakarta.persistence.dao.CommentDAO;
import cz.uhk.boardsappjakarta.persistence.dao.PostDAO;
import cz.uhk.boardsappjakarta.persistence.dao.UserDAO;
import cz.uhk.boardsappjakarta.persistence.entity.Comment;
import cz.uhk.boardsappjakarta.persistence.entity.Post;
import cz.uhk.boardsappjakarta.persistence.entity.User;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

import java.util.List;

@ApplicationScoped
public class CommentServiceImpl implements CommentService {

    @Inject
    private CommentDAO commentDAO;

    @Inject
    private PostDAO postDAO;

    @Inject
    private UserDAO userDAO;

    @Inject
    private UserService userService;

    @Inject
    private CommentDTOMapper commentDTOMapper;

    @Override
    @Transactional
    public Long addNewComment(Long replyPostId, NewCommentDTO newCommentDTO) {
        try {
            Post post = postDAO.findOne(replyPostId);
            if (!post.isRemoved()) {
                Comment comment = commentDTOMapper.newCommentDTOToComment(newCommentDTO);
                comment.setPost(post);
                User userReference = userDAO.getReference(userService.getCurrentUsername());
                comment.setAuthor(userReference);
                return commentDAO.createAndReturnId(comment);
            }
            else {
                throw new IllegalStateException("You cannot add comment, because post with id " + replyPostId + " is already removed");
            }
        }
        catch (Exception e) {
            e.printStackTrace();
            throw new IllegalStateException("Failed to create comment for this post - it is possible post is already deleted");
        }
    }

    @Override
    @Transactional
    public void updateComment(Long commentId, NewCommentDTO newCommentDTO) {
        try {
            Comment comment = commentDAO.findOne(commentId);
            String username = comment.getAuthor().getUsername();
            Post post = comment.getPost();
            if (userService.getCurrentUsername().equals(username) && !post.isRemoved() && !comment.isRemoved()) {
                comment.setContent(newCommentDTO.getContent());
            }
            else {
                throw new IllegalStateException("Post/comment is already deleted, or you are incorrect user");
            }
        }
        catch (Exception e) {
            e.printStackTrace();
            throw new IllegalStateException("Failed to update comment - it is possible post/comment is already deleted, or you are incorrect user");
        }
    }

    @Override
    @Transactional
    public void removeComment(Long commentId) {
        try {
            Comment comment = commentDAO.findOne(commentId);
            String username = comment.getAuthor().getUsername();
            Post post = comment.getPost();
            if ((userService.getCurrentUsername().equals(username) || userService.getCurrentRoles().contains("ROLE_ADMIN")) && !post.isRemoved() && !comment.isRemoved()) {
                comment.setRemoved(true);
            }
            else {
                throw new IllegalStateException("Post/comment is already deleted, or you are not admin");
            }
        }
        catch (Exception e) {
            e.printStackTrace();
            throw new IllegalStateException("Failed to remove comment - it is possible post/comment is already deleted, or you are not admin");
        }
    }

    @Override
    public CommentDTO findComment(Long id) {
        try {
            Comment comment = commentDAO.findOne(id);
            Post post = comment.getPost();
            if(!comment.isRemoved() && !post.isRemoved()) {
                return commentDTOMapper.commentToCommentDTO(comment);
            }
            else {
                throw new IllegalStateException("Post, where is this comment, is already removed");
            }
        }
        catch (Exception e) {
            e.printStackTrace();
            throw new IllegalStateException("Failed to find this comment");
        }
    }

    @Override
    public List<CommentDTO> findVisibleCommentsByPostId(Long postId) {
        try {
            if (postDAO.existsById(postId)) {
                return commentDAO.findVisibleCommentsByPostId(postId).stream().map(comment -> commentDTOMapper.commentToCommentDTO(comment)).toList();
            }
            else {
                throw new IllegalStateException("Post doesn't exist");
            }
        }
        catch (Exception e) {
            e.printStackTrace();
            throw new IllegalStateException("Failed to find comments for this post, or post doesn't exist");
        }
    }

    @Override
    public List<CommentDTO> findVisibleCommentsByPostId(Long postId, int pageNumber, int pageSize) {
        try {
            if (postDAO.existsById(postId)) {
                return commentDAO.findVisibleCommentsByPostId(postId, pageNumber, pageSize).stream().map(comment -> commentDTOMapper.commentToCommentDTO(comment)).toList();
            }
            else {
                throw new IllegalStateException("Post doesn't exist");
            }
        }
        catch (Exception e) {
            e.printStackTrace();
            throw new IllegalStateException("Failed to find comments for this post, or post doesn't exist");
        }
    }
}
