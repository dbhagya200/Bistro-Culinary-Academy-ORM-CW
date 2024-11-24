package lk.ijse.bistroculinaryacademyorm.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "course")
public class Course implements Serializable {

//    @GeneratedValue(generator = "ProgramsIDGenerator")
//    @GenericGenerator(
//            name = "ProgramsIDGenerator",
//            strategy = "lk.ijse.culinaryAcademy.util.ProgramsIDGenerator"
//    )
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "course_id")
    private String course_id;

    @Column(name = "course_name")
    private String course_name;

    @Column(name = "duration")
    private String duration;

    @Column(name = "course_fee")
    private double course_fee;




}
