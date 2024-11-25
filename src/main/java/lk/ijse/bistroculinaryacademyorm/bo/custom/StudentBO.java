package lk.ijse.bistroculinaryacademyorm.bo.custom;

import lk.ijse.bistroculinaryacademyorm.bo.SuperBO;
import lk.ijse.bistroculinaryacademyorm.dto.StudentDTO;
import lk.ijse.bistroculinaryacademyorm.entity.Student;

import java.io.IOException;
import java.util.ArrayList;

public interface StudentBO extends SuperBO {
    boolean addStudent(Student dto) throws IOException;

    ArrayList<StudentDTO> getAllStudents() throws Exception;
}
