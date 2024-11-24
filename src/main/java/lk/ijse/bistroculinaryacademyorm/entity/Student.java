package lk.ijse.bistroculinaryacademyorm.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "student")
public class Student implements Serializable {

    @Id
    @Column(name = "studentID")
    private String studentID;

    @Column(name = "studentName")
    private String studentName;

    @Column(name = "studentNIC")
    private String studentNIC;

    @Column(name = "studentAddress")
    private String studentAddress;

    @Column(name = "studentContact")
    private String studentContact;

    @Column(name = "studentEmail")
    private String studentEmail;

}
