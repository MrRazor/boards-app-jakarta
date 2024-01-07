package cz.uhk.boardsappjakarta.dto.post;

import cz.uhk.boardsappjakarta.dto.model.AbstractUserContentDTO;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class PostDTO extends AbstractUserContentDTO {

    private String title;
}
