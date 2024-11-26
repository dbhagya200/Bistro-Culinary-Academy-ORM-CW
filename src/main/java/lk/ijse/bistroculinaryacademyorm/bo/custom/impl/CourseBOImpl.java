package lk.ijse.bistroculinaryacademyorm.bo.custom.impl;

import lk.ijse.bistroculinaryacademyorm.bo.custom.CourseBO;
import lk.ijse.bistroculinaryacademyorm.config.SessionFactoryConfig;
import lk.ijse.bistroculinaryacademyorm.dao.DAOFactory;
import lk.ijse.bistroculinaryacademyorm.dao.custom.CourseDAO;
import lk.ijse.bistroculinaryacademyorm.dto.CourseDTO;
import lk.ijse.bistroculinaryacademyorm.entity.Course;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.io.IOException;
import java.sql.SQLException;
import java.time.Year;
import java.util.ArrayList;
import java.util.List;

public class CourseBOImpl implements CourseBO {

//    CourseDAO courseDAO = (CourseDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.COURSE);

    CourseDAO courseDAO = DAOFactory.getDaoFactory().getDao(DAOFactory.DAOTypes.COURSE);
    @Override
    public boolean addCourse(CourseDTO dto) throws Exception {
        return courseDAO.add(dto.toEntity());
    }

    @Override
    public ArrayList<CourseDTO> getAllCourses() throws Exception {
        List<Course> courses = courseDAO.getAll();
        List<CourseDTO> dtos = new ArrayList<>();
        for (Course course : courses) {
            dtos.add(new CourseDTO(
                    course.getCourse_id(),
                    course.getCourse_name(),
                    course.getDuration(),
                    course.getCourse_fee()
            ));
        }
        return (ArrayList<CourseDTO>) dtos;
    }


    @Override
    public boolean updateCourse(CourseDTO dto) throws Exception {
       return courseDAO.update(dto.toEntity());
    }

    @Override
    public boolean deleteCourse(String Id) throws Exception {
        return courseDAO.delete(Id);
    }

    @Override
    public CourseDTO searchCourse(String Id) throws Exception {
        Course course = courseDAO.search(Id);
        return new CourseDTO(
                course.getCourse_id(),
                course.getCourse_name(),
                course.getDuration(),
                course.getCourse_fee()
        );
    }

    @Override
    public boolean loadIds(String id) throws Exception {
        return false;
    }

    @Override
    public String generateNextCourseId() throws Exception {
        return courseDAO.generateNewID();
    }

    private String IncrementCourseId(String lastId) {
        // Get the current year dynamically
        String currentYear = String.valueOf(Year.now().getValue());

        // If there's no last ID, return the first course ID
        if (lastId == null || lastId.isEmpty()) {
            return String.format("REG-COOK-%s-0001", currentYear);
        }

        // Split the last ID to extract the year and sequence number
        String[] parts = lastId.split("-");
        String lastYear = parts[2]; // The year part in the ID
        int lastSequence = Integer.parseInt(parts[3]); // The sequence number

        // Check if the year has changed
        if (!lastYear.equals(currentYear)) {
            // Reset the sequence if the year has changed
            return String.format("REG-COOK-%s-0001", currentYear);
        }

        // Increment the sequence number for the current year
        lastSequence++;
        return String.format("REG-COOK-%s-%04d", currentYear, lastSequence);
    }

    @Override
    public boolean isCourseExists(String Id) throws Exception {
        return courseDAO.isCourseExists(Id);
    }

    @Override
    public List<String> getAllCourseIds() throws Exception {
        List<String> courseIds = new ArrayList<>();
        List<Course> courses = courseDAO.getAll();
        for (Course course : courses) {
            courseIds.add(course.getCourse_id());
        }
        return courseIds;
    }

    @Override
    public Course getCourseById(String courseId) throws Exception {
        return courseDAO.getCourseById(courseId);
    }

    @Override
    public Course findCourseById(String courseId) throws Exception {
        return courseDAO.findCourseById(courseId);
    }

    @Override
    public int getCourseCount() throws Exception {
        return courseDAO.getCourseCount();
    }
}
