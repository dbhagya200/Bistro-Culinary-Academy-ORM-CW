package lk.ijse.bistroculinaryacademyorm.bo.custom.impl;

import lk.ijse.bistroculinaryacademyorm.bo.custom.StudentBO;
import lk.ijse.bistroculinaryacademyorm.config.SessionFactoryConfig;
import lk.ijse.bistroculinaryacademyorm.dao.DAOFactory;
import lk.ijse.bistroculinaryacademyorm.dao.custom.StudentDAO;
import lk.ijse.bistroculinaryacademyorm.dao.custom.UserDAO;
import lk.ijse.bistroculinaryacademyorm.dto.StudentDTO;
import lk.ijse.bistroculinaryacademyorm.entity.Student;
import lk.ijse.bistroculinaryacademyorm.entity.User;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StudentBOImpl implements StudentBO {

//    StudentDAO studentDAO = (StudentDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.STUDENT);
    StudentDAO studentDAO = DAOFactory.getDaoFactory().getDao(DAOFactory.DAOTypes.STUDENT);

    UserDAO userDAO = DAOFactory.getDaoFactory().getDao(DAOFactory.DAOTypes.USER);

    @Override
    public boolean addStudent(StudentDTO dto) throws Exception {
//        Session session = SessionFactoryConfig.getInstance().getSession();
//        Transaction transaction = session.beginTransaction();
//        try {
//            studentDAO.setSession(session);
//            studentDAO.add(dto.toEntity());
//            System.out.println("student dto :"+dto);
//            transaction.commit();
//            return true;
//    }catch (Exception e) {
//        transaction.rollback();
//        session.close();
//        e.printStackTrace();
//        return false;
//    }
//        User user = userDAO.findUserById(dto.getUserid());
        return studentDAO.add(dto.toEntity());
    }

    @Override
    public List<StudentDTO> getAllStudents() throws Exception {
//        Session session = SessionFactoryConfig.getInstance().getSession();
//        List<Student> students = studentDAO.getAll();
//        studentDAO.setSession(session);
//
//
//
//        ArrayList<StudentDTO> studentDTOS = new ArrayList<>();
//        for (Student s : students) {
//            studentDTOS.add(new StudentDTO(
//                    s.getStudentID(),
//                    s.getStudentName(),
//                    s.getStudentNIC(),
//                    s.getStudentAddress(),
//                    s.getStudentContact(),
//                    s.getStudentEmail())
//            );
//        }
////        session.close();
//        return studentDTOS;

        List<Student> students = studentDAO.getAll();
        List<StudentDTO> dtos = new ArrayList<>();
        for (Student student : students) {
//            String userId = student.getUser() != null ? student.getUser().getUserid() : null;
            dtos.add(new StudentDTO(
                    student.getStudentID(),
                    student.getStudentName(),
                    student.getStudentNIC(),
                    student.getStudentAddress(),
                    student.getStudentContact(),
                    student.getStudentEmail()
            ));
        }
        return dtos;
    }

    @Override
    public Student searchStudent(String contact) throws SQLException, IOException {
        return studentDAO.searchStudent(contact);
    }

    @Override
    public boolean updateStudent(StudentDTO dto) throws Exception {
        return studentDAO.update(dto.toEntity());
    }

    @Override
    public String generateNewStudentID() {
        return studentDAO.generateNewID();
    }

    @Override
    public boolean deleteStudent(String id) throws Exception {
        return studentDAO.delete(id);
    }

    @Override
    public boolean StudentIdExists(String studentId) throws SQLException, ClassNotFoundException {
        return studentDAO.IdExists(studentId);
    }

    @Override
    public List<String> getAllStudentIds() throws Exception {
        List<String> studentIds = new ArrayList<>();
        List<Student> students = studentDAO.getAll();
        for (Student student : students) {
            studentIds.add(student.getStudentID());
        }
        return studentIds;
    }

    @Override
    public Student getStudentById(String studentId) throws Exception {
        return studentDAO.getStudentById(studentId);
    }

    @Override
    public Student findStudentById(String studentId) throws Exception {
        return studentDAO.findStudentById(studentId);
    }

    @Override
    public int getStudentCount() throws Exception {
        return studentDAO.getStudentCount();
    }
}
