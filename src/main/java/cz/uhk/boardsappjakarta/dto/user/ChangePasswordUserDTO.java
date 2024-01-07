package cz.uhk.boardsappjakarta.dto.user;

import lombok.Data;

@Data
public class ChangePasswordUserDTO {
    private String oldPassword;
    private String newPassword;
}
