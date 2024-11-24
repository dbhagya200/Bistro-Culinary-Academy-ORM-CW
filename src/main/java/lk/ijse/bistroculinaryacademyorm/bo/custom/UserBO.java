package lk.ijse.bistroculinaryacademyorm.bo.custom;

import lk.ijse.bistroculinaryacademyorm.bo.SuperBO;
import lk.ijse.bistroculinaryacademyorm.dto.UserDTO;

public interface UserBO extends SuperBO {
    boolean saveUser(UserDTO userDTO);
}
