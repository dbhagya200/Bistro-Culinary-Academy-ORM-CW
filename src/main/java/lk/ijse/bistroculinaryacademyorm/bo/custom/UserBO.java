package lk.ijse.bistroculinaryacademyorm.bo.custom;

import lk.ijse.bistroculinaryacademyorm.bo.SuperBO;
import lk.ijse.bistroculinaryacademyorm.dto.StudentDTO;
import lk.ijse.bistroculinaryacademyorm.dto.UserDTO;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public interface UserBO extends SuperBO {
    boolean saveUser(UserDTO dto) throws IOException;
    List<UserDTO> getAllUsers() throws Exception;

}
