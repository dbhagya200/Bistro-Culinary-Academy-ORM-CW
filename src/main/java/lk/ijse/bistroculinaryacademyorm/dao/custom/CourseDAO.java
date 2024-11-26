package lk.ijse.bistroculinaryacademyorm.dao.custom;

import lk.ijse.bistroculinaryacademyorm.dao.CrudDAO;
import lk.ijse.bistroculinaryacademyorm.entity.Course;

import java.io.IOException;
import java.sql.SQLException;

public interface CourseDAO extends CrudDAO<Course> {
    Course search(String id);
    boolean isCourseExists(String id) throws IOException;
    public int getCourseCount() throws SQLException, ClassNotFoundException;
    public Course getCourseById(String courseId) throws Exception;
    public Course findCourseById(String courseId) throws Exception;

    String generateNewID();

}
