package lk.ijse.bistroculinaryacademyorm.tm;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CourseTM {
    private String courseId;
    private String courseName;
    private String duration;
    private double courseFee;
}
