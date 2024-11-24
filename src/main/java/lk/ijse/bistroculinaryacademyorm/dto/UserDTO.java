package lk.ijse.bistroculinaryacademyorm.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserDTO {
    private String userid;
    private String username;
    private String password;
    private String jobrole;
}
