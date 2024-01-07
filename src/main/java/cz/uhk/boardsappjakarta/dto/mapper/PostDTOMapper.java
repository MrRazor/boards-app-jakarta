package cz.uhk.boardsappjakarta.dto.mapper;

import cz.uhk.boardsappjakarta.dto.post.NewPostDTO;
import cz.uhk.boardsappjakarta.dto.post.PostDTO;
import cz.uhk.boardsappjakarta.persistence.entity.Post;
import org.mapstruct.Mapper;

@Mapper
public interface PostDTOMapper {
    PostDTO postToPostDTO(Post post);
    Post postDTOToPost(PostDTO postDTO);
    NewPostDTO postToNewPostDTO(Post post);
    Post newPostDTOToPost(NewPostDTO newPostDTO);
}
