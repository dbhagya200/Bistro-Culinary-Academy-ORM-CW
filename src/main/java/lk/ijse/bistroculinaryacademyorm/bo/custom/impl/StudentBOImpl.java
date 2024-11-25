package lk.ijse.bistroculinaryacademyorm.bo.custom.impl;

import lk.ijse.bistroculinaryacademyorm.bo.custom.StudentBO;
import lk.ijse.bistroculinaryacademyorm.config.SessionFactoryConfig;
import lk.ijse.bistroculinaryacademyorm.dao.DAOFactory;
import lk.ijse.bistroculinaryacademyorm.dao.custom.StudentDAO;
import lk.ijse.bistroculinaryacademyorm.dto.StudentDTO;
import lk.ijse.bistroculinaryacademyorm.entity.Student;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.io.IOException;
import java.util.ArrayList;

public class StudentBOImpl implements StudentBO {

    StudentDAO studentDAO = (StudentDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.STUDENT);

    @Override
    public boolean addStudent(Student dto) throws IOException {
        Session session = SessionFactoryConfig.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        try {
            studentDAO.setSession(session);
            studentDAO.add(new Student(dto.getStudentID(), dto.getStudentName(),
                    dto.getStudentNIC(),dto.getStudentAddress(), dto.getStudentEmail(),
                    dto.getStudentContact()));
            transaction.commit();
            return true;
    }catch (Exception e) {
        transaction.rollback();
        return false;
    }finally {
            session.close();

        }
    }

    @Override
    public ArrayList<StudentDTO> getAllStudents() throws Exception {
        Session session = SessionFactoryConfig.getInstance().getSession();
        studentDAO.setSession(session);
        ArrayList<Student> students= (ArrayList<Student>) studentDAO.getAll();


        ArrayList<StudentDTO> studentDTOS = new ArrayList<>();
        for (Student s : students) {
            studentDTOS.add(new StudentDTO(
                    s.getStudentID(),
                    s.getStudentName(),
                    s.getStudentNIC(),
                    s.getStudentAddress(),
                    s.getStudentContact(),
                    s.getStudentEmail())
            );
        }
        session.close();
        return studentDTOS;
    }
}
