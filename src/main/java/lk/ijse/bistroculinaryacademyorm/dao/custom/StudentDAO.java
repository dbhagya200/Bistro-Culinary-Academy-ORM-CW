package lk.ijse.bistroculinaryacademyorm.dao.custom;

import lk.ijse.bistroculinaryacademyorm.dao.CrudDAO;
import lk.ijse.bistroculinaryacademyorm.entity.Student;

import java.io.IOException;
import java.sql.SQLException;

public interface StudentDAO extends CrudDAO<Student> {
    public  Student searchStudent(String contact) throws SQLException, IOException;

    String generateNewID();

    boolean IdExists(String studentId);

    Student getStudentById(String studentId) throws IOException;

    Student findStudentById(String studentId) throws IOException;

    int getStudentCount() throws SQLException;

}
