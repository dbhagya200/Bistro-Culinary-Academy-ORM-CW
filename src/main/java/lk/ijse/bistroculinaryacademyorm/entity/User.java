package lk.ijse.bistroculinaryacademyorm.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "user")
public class User {

    @Id
    @Column(name = "userName")
    private String userName;

    @Column(name = "email")
    private String email;

    @Column(name = "userPassword")
    private String userPassword;

    @Column(name = "confirmPassword")
    private String confirmPassword;

    @Column(name = "jobRole")
    private String jobRole;
}
