package lk.ijse.bistroculinaryacademyorm.controller;

import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import lk.ijse.bistroculinaryacademyorm.bo.BOFactory;
import lk.ijse.bistroculinaryacademyorm.bo.custom.CourseBO;
import lk.ijse.bistroculinaryacademyorm.dto.CourseDTO;
import lk.ijse.bistroculinaryacademyorm.entity.Course;
import lk.ijse.bistroculinaryacademyorm.entity.Student;
import lk.ijse.bistroculinaryacademyorm.tm.CourseTM;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

import static java.lang.Double.parseDouble;

public class CourseFormController implements Initializable {

    @FXML
    private TableColumn<?, ?> colAction;

    @FXML
    private TableColumn<?, ?> colDuration;

    @FXML
    private TableColumn<?, ?> colFee;

    @FXML
    private TableColumn<?, ?> colName;

    @FXML
    private TableColumn<?, ?> colid;

    @FXML
    private TableView<CourseTM> tblCourses;

    @FXML
    private JFXTextField txtCourseFee;

    @FXML
    private JFXTextField txtDuration;

    @FXML
    private TextField txtID;

    @FXML
    private JFXTextField txtName;
    @FXML
    private TextField txtSearchId;
    ObservableList<CourseTM> observableList;
    String ID;

//    CourseBO courseBO = (CourseBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.COURSE);
    CourseBO courseBO = BOFactory.getBoFactory().getBO(BOFactory.BOTypes.COURSE);

    @FXML
    void btnAddOnAction(ActionEvent event) throws Exception {
    if(txtName.getText().trim().isEmpty()||txtDuration.getText().trim().isEmpty()||txtCourseFee.getText().trim().isEmpty()){
        new Alert(Alert.AlertType.WARNING, "Please fill all fields").show();
    }
        try {

            if (courseBO.addCourse(
                    new CourseDTO(
                            txtID.getText(),
                            txtName.getText(),
                            txtDuration.getText(),
                            Double.parseDouble(txtCourseFee.getText())
                    )
            ))
            {
                System.out.println("save id: "+txtID.getText());
                clearFields();
                generateNextCourserId();
                loadAllCourses();

            }
            new Alert(Alert.AlertType.INFORMATION, "Course saved successfully").show();
            clearFields();
            generateNextCourserId();
            loadAllCourses();

        }catch (Exception e){
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }

//        String id = txtID.getText();
//        String name = txtName.getText();
//        String duration = txtDuration.getText();
//        Double fees = Double.valueOf(txtCourseFee.getText());
//
//        if (courseBO.isCourseExists(id)){
//            new Alert(Alert.AlertType.ERROR, "Course ID " + id + " already exists!").show();
//            return;
//        }
//
//        if (courseBO.addCourse(new CourseDTO(id, name, duration, fees))) {
//            clearFields();
//            generateNextCourserId();
//            loadAllCourses();
//            new Alert(Alert.AlertType.CONFIRMATION, "Saved!!").show();
//        } else {
//            new Alert(Alert.AlertType.ERROR, "Error!!").show();
//        }


    }
    private void clearFields() {
        txtName.clear();
        txtDuration.clear();
        txtID.clear();
        txtCourseFee.clear();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        loadAllCourses();
        setCellValueFactory();
        generateNextCourserId();
    }

    private void validationIntentFields() {
    }

    private void setCellValueFactory() {
        colid.setCellValueFactory(new PropertyValueFactory<>("courseId"));
        colName.setCellValueFactory(new PropertyValueFactory<>("courseName"));
        colDuration.setCellValueFactory(new PropertyValueFactory<>("courseDuration"));
        colFee.setCellValueFactory(new PropertyValueFactory<>("courseFee"));
    }
    private void generateNextCourserId() {
        String nextId = null;
        try {
            nextId = courseBO.generateNextCourseId();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        txtID.setText(nextId);
    }
    private void loadAllCourses() {
        observableList = FXCollections.observableArrayList();
        tblCourses.getItems().clear();
        try {
            List<CourseDTO> allCourses = courseBO.getAllCourses();
            for (CourseDTO dto : allCourses) {
                tblCourses.getItems().add(new CourseTM(
                        dto.getCourseId(),
                        dto.getCourseName(),
                        dto.getCourseDuration(),
                        dto.getCourseFee()
                ));

            }
            tblCourses.setItems(observableList);
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void btnAddOnMouseEntered(MouseEvent event) {

    }

    @FXML
    void btnAddOnMouseExited(MouseEvent event) {

    }

    @FXML
    void btnClearOnAction(ActionEvent event) {
        clearFields();
    }

    @FXML
    void btnClearOnMouseEntered(MouseEvent event) {

    }

    @FXML
    void btnClearOnMouseExited(MouseEvent event) {

    }

    @FXML
    void btnDeleteOnAction(ActionEvent event) throws Exception {
        ButtonType yes = new ButtonType("Yes", ButtonBar.ButtonData.OK_DONE);
        ButtonType no = new ButtonType("No", ButtonBar.ButtonData.CANCEL_CLOSE);
        Optional<ButtonType> result = new Alert(Alert.AlertType.INFORMATION, "Are you sure to remove?", yes, no).showAndWait();

        if (result.orElse(no) == yes) {
            if (!courseBO.deleteCourse(ID)) {
                new Alert(Alert.AlertType.ERROR, "Error!!").show();
            }
        }
        generateNextCourserId();
        clearFields();
        loadAllCourses();
    }

    @FXML
    void btnDeleteOnMouseEntered(MouseEvent event) {

    }

    @FXML
    void btnDeleteOnMouseExited(MouseEvent event) {

    }

    @FXML
    void btnSearchOnAction(ActionEvent event) {
        String courseId = txtSearchId.getText();

        try {
            Course course= courseBO.searchCourse(courseId).toEntity();
            if (course != null) {
               txtID.setText(course.getCourse_id());
               txtName.setText(course.getCourse_name());
               txtDuration.setText(course.getDuration());
               txtCourseFee.setText(String.valueOf(course.getCourse_fee()));
                txtID.clear();

            } else {
                new Alert(Alert.AlertType.WARNING, "No student found").show();
            }
        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) throws Exception {
        String name = txtName.getText();
        String duration = txtDuration.getText();
        Double fees = Double.valueOf(txtCourseFee.getText());

        if (courseBO.updateCourse(new CourseDTO(ID, name, duration, fees))) {

            new Alert(Alert.AlertType.CONFIRMATION, "Updated!!").show();
        } else {
            new Alert(Alert.AlertType.ERROR, "Error!!").show();
        }
        clearFields();
        loadAllCourses();
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
