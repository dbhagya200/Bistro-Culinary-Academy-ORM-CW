package lk.ijse.bistroculinaryacademyorm.dao.custom;



import lk.ijse.bistroculinaryacademyorm.dao.CrudDAO;
import lk.ijse.bistroculinaryacademyorm.entity.Course;

import java.sql.SQLException;
import java.util.List;

public interface CourseDAO extends CrudDAO<Course> {
    public Course getCourseById(String courseId) throws Exception;
    public Course findCourseById(String courseId) throws Exception;
    public List<String> getAllCourseIds() throws SQLException, ClassNotFoundException;
    public int getCourseCount() throws SQLException, ClassNotFoundException;
}
