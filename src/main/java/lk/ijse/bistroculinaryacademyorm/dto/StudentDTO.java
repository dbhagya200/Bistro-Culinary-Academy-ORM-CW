package lk.ijse.bistroculinaryacademyorm.dto;

import lk.ijse.bistroculinaryacademyorm.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class StudentDTO {
    private String StudentId;
    private String StudentName;
    private String StudentNIC;
    private String StudentAddress;
    private String StudentContact;
    private String StudentEmail;


//    private User user;
}
