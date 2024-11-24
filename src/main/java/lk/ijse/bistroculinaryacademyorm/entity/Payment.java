package lk.ijse.bistroculinaryacademyorm.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "payment")
public class Payment {
    @Id
    @Column(name = "pay_id")
    private String pay_id;

    @Column(name = "pay_date")
    private String pay_date;

    @Column(name = "pay_amount")
    private double pay_amount;

    @Column(name = "status")
    private String status;

    @Column(name = "upfront_amount")
    private double upfront_amount;

    @Column(name = "balance_amount")
    private double balance_amount;

    @ManyToOne
    @JoinColumn(name = "student_course_id")
    private Student_Course_Details studentCourse;
}
