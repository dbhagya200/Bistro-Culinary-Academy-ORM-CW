package lk.ijse.bistroculinaryacademyorm.tm;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class StudentTM {
    private String studentId;
    private String studentName;
    private String studentNIC;
    private String studentAddress;
    private String studentContact;
    private String studentEmail;


}
