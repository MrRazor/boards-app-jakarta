package cz.uhk.boardsappjakarta.service;

import cz.uhk.boardsappjakarta.dto.comment.CommentDTO;
import cz.uhk.boardsappjakarta.dto.comment.NewCommentDTO;

import java.util.List;

public interface CommentService {
    Long addNewComment(Long replyPostId, NewCommentDTO newCommentDTO);
    void updateComment(Long commentId, NewCommentDTO newCommentDTO);
    void removeComment(Long commentId);
    CommentDTO findComment(Long id);
    List<CommentDTO> findVisibleCommentsByPostId(Long postId);
    List<CommentDTO> findVisibleCommentsByPostId(Long postId, int pageNumber, int pageSize);
}
