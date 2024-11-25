package lk.ijse.bistroculinaryacademyorm.bo.custom;

import lk.ijse.bistroculinaryacademyorm.bo.SuperBO;
import lk.ijse.bistroculinaryacademyorm.dto.CourseDTO;

import java.io.IOException;

public interface CourseBO extends SuperBO {
    boolean addCourse(CourseDTO dto) throws IOException;

}
