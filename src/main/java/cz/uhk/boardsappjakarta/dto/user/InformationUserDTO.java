package cz.uhk.boardsappjakarta.dto.user;

import cz.uhk.boardsappjakarta.dto.AuthorityDTO;
import lombok.Data;

import java.util.List;

@Data
public class InformationUserDTO {

    private String username;
    private boolean enabled;
    private List<AuthorityDTO> authorities;
}
