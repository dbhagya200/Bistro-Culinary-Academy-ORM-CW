module lk.ijse.bistroculinaryacademyorm {
    requires javafx.controls;
    requires javafx.fxml;
    requires mysql.connector.j;
    requires org.hibernate.orm.core;
    requires com.jfoenix;
    requires jakarta.persistence;
    requires java.naming;
    requires java.transaction.xa;
    requires static lombok;


    opens lk.ijse.bistroculinaryacademyorm.controller to javafx.fxml;
    opens lk.ijse.bistroculinaryacademyorm.view to javafx.fxml;
    opens lk.ijse.bistroculinaryacademyorm.entity to org.hibernate.orm.core;

    exports lk.ijse.bistroculinaryacademyorm;
}