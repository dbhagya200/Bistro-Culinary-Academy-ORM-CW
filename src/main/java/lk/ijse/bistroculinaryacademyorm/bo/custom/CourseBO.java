package lk.ijse.bistroculinaryacademyorm.bo.custom;

import lk.ijse.bistroculinaryacademyorm.bo.SuperBO;
import lk.ijse.bistroculinaryacademyorm.dto.CourseDTO;
import lk.ijse.bistroculinaryacademyorm.entity.Course;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface CourseBO extends SuperBO {
    boolean addCourse(CourseDTO dto) throws Exception;

    ArrayList<CourseDTO> getAllCourses() throws Exception;

    boolean updateCourse(CourseDTO dto) throws Exception;

    boolean deleteCourse(String  Id) throws Exception;

    CourseDTO searchCourse(String Id) throws Exception;

    boolean loadIds(String id) throws Exception;

    String generateNextCourseId() throws Exception;

    boolean isCourseExists(String Id) throws Exception;
    public List<String> getAllCourseIds() throws Exception;
    public Course getCourseById(String courseId) throws Exception;
    public Course findCourseById(String courseId) throws Exception;
    public int getCourseCount() throws Exception;

}
