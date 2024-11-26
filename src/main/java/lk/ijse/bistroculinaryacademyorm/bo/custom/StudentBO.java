package lk.ijse.bistroculinaryacademyorm.bo.custom;

import lk.ijse.bistroculinaryacademyorm.bo.SuperBO;
import lk.ijse.bistroculinaryacademyorm.dto.StudentDTO;
import lk.ijse.bistroculinaryacademyorm.entity.Student;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface StudentBO extends SuperBO {
    boolean addStudent(StudentDTO dto) throws Exception;

    List<StudentDTO> getAllStudents() throws Exception;

    Student searchStudent(String contact) throws SQLException, IOException;

    boolean updateStudent(StudentDTO dto) throws Exception;

    String generateNewStudentID();

    boolean deleteStudent(String id) throws Exception;

    public boolean StudentIdExists(String studentId) throws SQLException, ClassNotFoundException;
    public List<String> getAllStudentIds() throws Exception;
    public Student getStudentById(String studentId) throws Exception;
    public Student findStudentById(String studentId) throws Exception;
    public int getStudentCount() throws Exception;
}
