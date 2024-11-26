package lk.ijse.bistroculinaryacademyorm.dto;

import lk.ijse.bistroculinaryacademyorm.entity.Student;
import lk.ijse.bistroculinaryacademyorm.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class StudentDTO {
    private String StudentId;
    private String StudentName;
    private String StudentNIC;
    private String StudentAddress;
    private String StudentContact;
    private String StudentEmail;

    public Student toEntity() {
        Student student = new Student();
        student.setStudentID(this.getStudentId());
        student.setStudentName(this.getStudentName());
        student.setStudentNIC(this.getStudentNIC());
        student.setStudentAddress(this.getStudentAddress());
        student.setStudentContact(this.getStudentContact());
        student.setStudentEmail(this.getStudentEmail());
        return student;
    }


//    private User user;
}
