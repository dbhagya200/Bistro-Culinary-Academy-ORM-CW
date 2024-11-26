package lk.ijse.bistroculinaryacademyorm.dto;

import lk.ijse.bistroculinaryacademyorm.entity.Course;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CourseDTO {
    private String courseId;
    private String courseName;
    private String courseDuration;
    private double courseFee;

    public Course toEntity() {
        Course course = new Course();
        course.setCourse_id(this.courseId);
        course.setCourse_name(this.courseName);
        course.setDuration(this.courseDuration);
        course.setCourse_fee(this.courseFee);
        return course;
    }
}
