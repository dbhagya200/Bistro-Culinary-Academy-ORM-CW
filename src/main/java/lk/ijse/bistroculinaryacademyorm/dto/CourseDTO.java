package lk.ijse.bistroculinaryacademyorm.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CourseDTO {
    private String courseId;
    private String courseName;
    private String duration;
    private double courseFee;
}
