package cz.uhk.boardsappjakarta.dto.mapper;

import cz.uhk.boardsappjakarta.dto.comment.CommentDTO;
import cz.uhk.boardsappjakarta.dto.comment.NewCommentDTO;
import cz.uhk.boardsappjakarta.persistence.entity.Comment;
import org.mapstruct.Mapper;

@Mapper
public interface CommentDTOMapper {
    CommentDTO commentToCommentDTO(Comment comment);
    Comment commentDTOToComment(CommentDTO commentDTO);
    NewCommentDTO commentToNewCommentDTO(Comment comment);
    Comment newCommentDTOToComment(NewCommentDTO newCommentDTO);
}
