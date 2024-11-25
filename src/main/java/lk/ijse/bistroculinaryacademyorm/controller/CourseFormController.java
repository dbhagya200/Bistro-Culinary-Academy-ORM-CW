package lk.ijse.bistroculinaryacademyorm.controller;

import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import lk.ijse.bistroculinaryacademyorm.bo.BOFactory;
import lk.ijse.bistroculinaryacademyorm.bo.custom.CourseBO;
import lk.ijse.bistroculinaryacademyorm.dto.CourseDTO;

public class CourseFormController {

    @FXML
    private TableView<?> tblCourses;

    @FXML
    private JFXTextField txtCourseFee;

    @FXML
    private JFXTextField txtDuration;

    @FXML
    private TextField txtID;

    @FXML
    private JFXTextField txtName;

    CourseBO courseBO = (CourseBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.COURSE);

    @FXML
    void btnAddOnAction(ActionEvent event) {
        String id = txtID.getText();
        String name = txtName.getText();
        String duration = txtDuration.getText();
        double fee = Double.parseDouble(txtCourseFee.getText());

try {
//    if(id.trim().isEmpty()||name.trim().isEmpty()||fee==0||duration.trim().isEmpty()){
//        new Alert(Alert.AlertType.WARNING, "Please fill all fields").show();
//    }
    CourseDTO courseDTO = new CourseDTO(id,name,duration,fee);
    boolean isAdded = courseBO.addCourse(courseDTO);

    if(isAdded){
        new Alert(Alert.AlertType.CONFIRMATION, "Course Added").show();
        clearFields();
    }else {
        new Alert(Alert.AlertType.ERROR, "Course Not Added").show();
    }
}catch (Exception e){
    new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
}


    }
    private void clearFields() {
        txtName.clear();
        txtDuration.clear();
        txtID.clear();
        txtCourseFee.clear();
    }

    @FXML
    void btnAddOnMouseEntered(MouseEvent event) {

    }

    @FXML
    void btnAddOnMouseExited(MouseEvent event) {

    }

    @FXML
    void btnClearOnAction(ActionEvent event) {

    }

    @FXML
    void btnSearchOnAction(ActionEvent event) {

    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) {

    }

    @FXML
    void btnUpdateOnMouseEntered(MouseEvent event) {

    }

    @FXML
    void btnUpdateOnMouseExited(MouseEvent event) {

    }

    @FXML
    void searchOnKeyPressed(KeyEvent event) {

    }

    @FXML
    void txtCourseFeeOnAction(ActionEvent event) {

    }

    @FXML
    void txtCourseFeeOnKeyPressed(KeyEvent event) {

    }

    @FXML
    void txtDurationOnAction(ActionEvent event) {

    }

    @FXML
    void txtDurationOnKeyPressed(KeyEvent event) {

    }

    @FXML
    void txtIDOnAction(ActionEvent event) {

    }

    @FXML
    void txtIDOnKeyPressed(KeyEvent event) {

    }

    @FXML
    void txtNameOnAction(ActionEvent event) {

    }

    @FXML
    void txtNameOnKeyPressed(KeyEvent event) {

    }

}
