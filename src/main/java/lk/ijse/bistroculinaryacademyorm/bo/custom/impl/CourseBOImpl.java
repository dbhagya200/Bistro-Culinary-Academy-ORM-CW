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

public class CourseBOImpl implements CourseBO {

    CourseDAO courseDAO = (CourseDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.COURSE);
    @Override
    public boolean addCourse(CourseDTO dto) throws IOException {
        Session session = SessionFactoryConfig.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        try {
            courseDAO.setSession(session);
            courseDAO.add(new Course(dto.getCourseId(), dto.getCourseName(),
                    dto.getDuration(), dto.getCourseFee()));
            transaction.commit();
            return true;
        }catch (Exception e) {
            transaction.rollback();
            return false;
        }finally {
            session.close();
        }
    }
}
