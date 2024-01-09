package cz.uhk.boardsappjakarta.service;

import cz.uhk.boardsappjakarta.dto.user.ChangePasswordUserDTO;
import cz.uhk.boardsappjakarta.dto.user.InformationUserDTO;
import cz.uhk.boardsappjakarta.dto.user.LoginUserDTO;

import java.util.List;

public interface UserService {
    void registerUser(LoginUserDTO loginUserDTO);
    void changePassword(ChangePasswordUserDTO changePasswordUserDTO);
    void disableUser(String username);
    InformationUserDTO login(LoginUserDTO loginUserDTO);
    InformationUserDTO getCurrentUser();
    String getCurrentUsername();
    List<String> getCurrentRoles();
}