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
    private String Name;
    private String address;
    private String contact;

    private User user;
}
