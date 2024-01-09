package cz.uhk.boardsappjakarta.dto.mapper;

import cz.uhk.boardsappjakarta.dto.user.InformationUserDTO;
import cz.uhk.boardsappjakarta.dto.user.LoginUserDTO;
import cz.uhk.boardsappjakarta.persistence.entity.User;
import org.mapstruct.Mapper;

@Mapper
public interface UserDTOMapper {
    InformationUserDTO userToUserDTO(User user);
    User userDTOToUser(InformationUserDTO informationUserDTO);
    LoginUserDTO userToLoginUserDTO(User user);
    User loginUserDTOToUser(LoginUserDTO loginUserDTO);
}
