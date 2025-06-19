package com.abdatytch.user_service.dto.request;

import jakarta.validation.constraints.Size;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.NotBlank;
import com.abdatytch.user_service.constant.Message;
import io.swagger.v3.oas.annotations.media.Schema;

/**
 * DTO pour la mise à jour des identifiants (username et mot de passe) d'un utilisateur
 */
public class UserCredentialsUpdateDTO {

    @Schema(description = "Nouveau username", example = "msissoko")
    @Size(min = 3, max = 50, message = Message.USERNAME_TOO_SHORT + " et " + Message.USERNAME_TOO_LONG)
    private String newUsername;

    @Schema(description = "Nouveau mot de passe", example = "NewPassword123")
    @Size(min = 8, max = 50, message = Message.PASSWORD_TOO_SHORT + " et " + Message.PASSWORD_TOO_LONG)
    @Pattern(regexp = "^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d).+$", message = Message.PASSWORD_INVALID)
    private String newPassword;

    @Schema(description = "Ancien mot de passe pour la vérification", example = "OldPassword123")
    @NotBlank(message = Message.OLD_PASSWORD_REQUIRED)
    private String oldPassword;

    public String getNewUsername() {return newUsername;}

    public void setNewUsername(String newUsername) {this.newUsername = newUsername;}

    public String getNewPassword() {return newPassword;}

    public void setNewPassword(String newPassword) {this.newPassword = newPassword;}

    public String getOldPassword() {return oldPassword;}

    public void setOldPassword(String oldPassword) {this.oldPassword = oldPassword;}
}
