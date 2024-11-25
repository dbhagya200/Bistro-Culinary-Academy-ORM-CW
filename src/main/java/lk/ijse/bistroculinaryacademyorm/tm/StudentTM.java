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
    private String studentAddress;
    private String studentEmail;
    private String studentContact;

}
