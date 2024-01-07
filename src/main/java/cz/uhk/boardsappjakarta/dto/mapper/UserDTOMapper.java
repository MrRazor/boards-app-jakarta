package cz.uhk.boardsappjakarta.dto.mapper;

import cz.uhk.boardsappjakarta.dto.user.LoginUserDTO;
import cz.uhk.boardsappjakarta.dto.user.UserDTO;
import cz.uhk.boardsappjakarta.dto.user.UsernameDTO;
import cz.uhk.boardsappjakarta.persistence.entity.User;
import org.mapstruct.Mapper;

@Mapper
public interface UserDTOMapper {
    UserDTO userToUserDTO(User user);
    User userDTOToUser(UserDTO userDTO);
    LoginUserDTO userToLoginUserDTO(User user);
    User loginUserDTOToUser(LoginUserDTO loginUserDTO);
    UsernameDTO userToUsernameDTO(User user);
    User usernameDTOToUser(UsernameDTO usernameDTO);
}
