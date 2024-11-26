package lk.ijse.bistroculinaryacademyorm.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserDTO {
    private String username;
    private String email;
    private String password;
    private String confirmPassword;
    private String jobrole;
}
