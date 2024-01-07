package cz.uhk.boardsappjakarta.service;

import cz.uhk.boardsappjakarta.dto.user.ChangePasswordUserDTO;
import cz.uhk.boardsappjakarta.dto.user.LoginUserDTO;
import cz.uhk.boardsappjakarta.dto.user.UserDTO;

import java.util.List;

public interface UserService {
    void registerUser(LoginUserDTO loginUserDTO);
    void changePassword(ChangePasswordUserDTO changePasswordUserDTO);
    void disableUser(String username);
    UserDTO login(LoginUserDTO loginUserDTO);
    UserDTO getCurrentUser();
    String getCurrentUsername();
    List<String> getCurrentRoles();
}