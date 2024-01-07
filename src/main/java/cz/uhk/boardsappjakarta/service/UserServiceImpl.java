package cz.uhk.boardsappjakarta.service;

import cz.uhk.boardsappjakarta.dto.mapper.UserDTOMapper;
import cz.uhk.boardsappjakarta.dto.user.ChangePasswordUserDTO;
import cz.uhk.boardsappjakarta.dto.user.LoginUserDTO;
import cz.uhk.boardsappjakarta.dto.user.UserDTO;
import cz.uhk.boardsappjakarta.persistence.dao.AuthorityDAO;
import cz.uhk.boardsappjakarta.persistence.dao.UserDAO;
import cz.uhk.boardsappjakarta.persistence.entity.Authority;
import cz.uhk.boardsappjakarta.persistence.entity.User;
import cz.uhk.boardsappjakarta.security.BCryptPasswordHash;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.security.enterprise.SecurityContext;
import jakarta.transaction.Transactional;

import java.util.Arrays;
import java.util.List;

@ApplicationScoped
public class UserServiceImpl implements UserService {

    @Inject
    private UserDTOMapper userDTOMapper;

    @Inject
    private UserDAO userDAO;

    @Inject
    private AuthorityDAO authorityDAO;

    @Inject
    private BCryptPasswordHash bCryptPasswordHash;

    @Inject
    private SecurityContext securityContext;

    @Override
    @Transactional
    public void registerUser(LoginUserDTO loginUserDTO) {
        try {
            loginUserDTO.setPassword(encodePassword(loginUserDTO.getPassword()));
            User user = userDTOMapper.loginUserDTOToUser(loginUserDTO);
            Authority authority = new Authority();
            authority.setAuthorityName("ROLE_USER");
            authority.setUsername(user.getUsername());
            authorityDAO.create(authority);
            userDAO.create(user);
        }
        catch (Exception e) {
            e.printStackTrace();
            throw new IllegalStateException("Failed to create new user, maybe username is already taken");
        }
    }

    @Override
    @Transactional
    public void changePassword(ChangePasswordUserDTO changePasswordUserDTO) {
        try {
            User user = userDAO.findOne(getCurrentUsername());
            if (passwordMatches(changePasswordUserDTO.getOldPassword(), user.getPassword())) {
                user.setPassword(encodePassword(changePasswordUserDTO.getNewPassword()));
            } else {
                throw new IllegalStateException("Wrong current password");
            }
        }
        catch (Exception e) {
            e.printStackTrace();
            throw new IllegalStateException("Failed to change password, check if old password match");
        }
    }

    @Override
    @Transactional
    public void disableUser(String username) {
        if(getCurrentRoles().contains("ROLE_ADMIN")) {
            try {
                User user = userDAO.findOne(username);
                if (user.isEnabled() && user.getAuthorities().stream().map(Authority::getAuthorityName).noneMatch(authorityName -> authorityName.equals("ROLE_ADMIN"))) {
                    user.setEnabled(false);
                }
            }
            catch (Exception e) {
                throw new IllegalStateException("Failed to find user");
            }
        }
        else {
            throw new IllegalStateException("You are not admin");
        }
    }

    @Override
    public UserDTO login(LoginUserDTO loginUserDTO) {
        User user;
        try {
            user = userDAO.findOne(loginUserDTO.getUsername());
        }
        catch (Exception e) {
            e.printStackTrace();
            throw new IllegalStateException("Failed to find user");
        }
        if(passwordMatches(loginUserDTO.getPassword(), user.getPassword())) {
            return userDTOMapper.userToUserDTO(user);
        }
        else {
            throw new IllegalStateException("Password is not correct");
        }
    }

    @Override
    public UserDTO getCurrentUser() {
        try {
            return userDTOMapper.userToUserDTO(userDAO.findOne(getCurrentUsername()));
        }
        catch (Exception e) {
            e.printStackTrace();
            throw new IllegalStateException("Failed to find current user information");
        }
    }

    @Override
    public String getCurrentUsername() {
        return securityContext.getCallerPrincipal().getName();
    }

    @Override
    public List<String> getCurrentRoles() {
        return Arrays.stream(new String[]{"ROLE_ADMIN", "ROLE_USER"}).filter(role->securityContext.isCallerInRole(role)).toList();
    }

    private boolean passwordMatches(String rawPassword, String encodedPassword) {
        return bCryptPasswordHash.verify(rawPassword.toCharArray(), encodedPassword);
    }

    private String encodePassword(String rawPassword) {
        return bCryptPasswordHash.generate(rawPassword.toCharArray());
    }

}