package lk.ijse.bistroculinaryacademyorm.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import com.mysql.cj.Session;
import javafx.beans.Observable;
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
import lk.ijse.bistroculinaryacademyorm.bo.SuperBO;
import lk.ijse.bistroculinaryacademyorm.bo.custom.StudentBO;
import lk.ijse.bistroculinaryacademyorm.config.SessionFactoryConfig;
import lk.ijse.bistroculinaryacademyorm.dto.StudentDTO;
import lk.ijse.bistroculinaryacademyorm.entity.Student;
import lk.ijse.bistroculinaryacademyorm.tm.StudentTM;
import lombok.SneakyThrows;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

public class StudentController implements Initializable {


    @FXML
    private TableColumn<?, ?> colAction;

    @FXML
    private TableColumn<?, ?> colAddress;

    @FXML
    private TableColumn<?, ?> colContact;

    @FXML
    private TableColumn<?, ?> colEmail;

    @FXML
    private TableColumn<?, ?> colName;

    @FXML
    private TableColumn<?, ?> colid;

    @FXML
    private TableView<StudentTM> tblStudent;

    @FXML
    private JFXTextField txtAddress;

    @FXML
    private JFXTextField txtEmail;

    @FXML
    private TextField txtID;

    @FXML
    private JFXTextField txtNIC;

    @FXML
    private JFXTextField txtName;

    @FXML
    private JFXTextField txtphone;
    @FXML
    private TextField txtSearchContact;
    @FXML
    private JFXButton btnDelete;
    String ID;
    String userid = "U001";

    ObservableList<StudentTM> observableList;

//    StudentBO  studentBO = (StudentBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.STUDENT);
    StudentBO studentBO = BOFactory.getBoFactory().getBO(BOFactory.BOTypes.STUDENT);

    private List<StudentDTO> studentList = new ArrayList<>();

    @SneakyThrows
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        loadAllStudents();

        setCellValueFactory();
        generateNextId();

    }

    private void generateNextId() {
        String nextId = null;
        nextId = studentBO.generateNewStudentID();
        txtID.setText(nextId);
    }

    @FXML
    void btnAddOnAction(ActionEvent event) throws Exception {


        if (txtID.getText().trim().isEmpty() || txtName.getText().trim().isEmpty() ||
                txtNIC.getText().trim().isEmpty() || txtAddress.getText().trim().isEmpty() ||
                txtEmail.getText().trim().isEmpty() || txtphone.getText().trim().isEmpty()) {
            new Alert(Alert.AlertType.WARNING, "Please fill all fields").show();
        } else {
            studentBO.addStudent(new StudentDTO(
                    txtID.getText(),
                    txtName.getText(),
                    txtNIC.getText(),
                    txtAddress.getText(),
                    txtphone.getText(),
                    txtEmail.getText()

            ));

            clearFields();
            loadAllStudents();
            generateNextId();
            new Alert(Alert.AlertType.CONFIRMATION, "Student Added").show();

        }
//        tblStudent.getItems().add(
//                new StudentTM(
//                        txtID.getText(),
//                        txtName.getText(),
//                        txtNIC.getText(),
//                        txtAddress.getText(),
//                        txtphone.getText(),
//                        txtEmail.getText()
//                )
//        );
//        loadAllStudents();
//        clearFields();
    }

    private void clearFields() {
        txtID.clear();
        txtName.clear();
        txtNIC.clear();
        txtAddress.clear();
        txtEmail.clear();
        txtphone.clear();
        generateNextId();

    }

    private void setCellValueFactory() {
        colid.setCellValueFactory(new PropertyValueFactory<>("studentId"));
        colName.setCellValueFactory(new PropertyValueFactory<>("studentName"));
        colAddress.setCellValueFactory(new PropertyValueFactory<>("studentAddress"));
        colEmail.setCellValueFactory(new PropertyValueFactory<>("studentEmail"));
        colContact.setCellValueFactory(new PropertyValueFactory<>("studentContact"));
    }
    private void loadAllStudents() throws Exception {
//        ObservableList<StudentTM> obList = FXCollections.observableArrayList();
//        tblStudent.getItems().clear();
//
//        try {
//            ArrayList<StudentDTO> allStudents = (ArrayList<StudentDTO>) studentBO.getAllStudents();
//            for (StudentDTO dto : allStudents) {
//                tblStudent.getItems().add(new StudentTM(dto.getStudentId(),
//                        dto.getStudentName(), dto.getStudentNIC(), dto.getStudentAddress(),
//                        dto.getStudentContact(), dto.getStudentEmail()));
//
//
//            }
//
//        }catch (Exception e){
//            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
//            System.out.println("selectedItem = " + tblStudent.getSelectionModel().getSelectedItem());
//        }

        observableList = FXCollections.observableArrayList();
        List<StudentDTO> allStudent = studentBO.getAllStudents();

        for (StudentDTO studentDTO : allStudent){
            observableList.add(new StudentTM(
                    studentDTO.getStudentId(),
                    studentDTO.getStudentName(),
                    studentDTO.getStudentNIC(),
                    studentDTO.getStudentAddress(),
                    studentDTO.getStudentContact(),
                    studentDTO.getStudentEmail()
            ));
        }
        tblStudent.setItems(observableList);
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
    void btnSearchOnAction(ActionEvent event) {
        String contact = txtSearchContact.getText();


        try {
            Student student = studentBO.searchStudent(contact);
            if (student != null) {
                txtID.setText(student.getStudentID());
                txtName.setText(student.getStudentName());
                txtNIC.setText(student.getStudentNIC());
                txtAddress.setText(student.getStudentAddress());
                txtphone.setText(student.getStudentContact());
                txtEmail.setText(student.getStudentEmail());
                txtSearchContact.clear();

            } else {
                new Alert(Alert.AlertType.WARNING, "No student found").show();
            }
        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
//        String searchText = txtSearchContact.getText().toLowerCase();
//        ObservableList<StudentTM> filteredList = FXCollections.observableArrayList();
//
//        for (StudentTM studenttm : observableList) {
//            if (studenttm.getStudentContact().toLowerCase().contains(searchText)) {
//                filteredList.add(studenttm);
//            }
//        }
//        tblStudent.setItems(filteredList);
    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) throws Exception {
//        boolean isUpdated = studentBO.updateStudent(new StudentDTO(
//                txtID.getText(),
//                txtName.getText(),
//                txtNIC.getText(),
//                txtAddress.getText(),
//                txtphone.getText(),
//                txtEmail.getText()
//        ));
//        if (isUpdated) {
//            new Alert(Alert.AlertType.CONFIRMATION, "Student Updated").show();
//            clearFields();
//        } else {
//            new Alert(Alert.AlertType.ERROR, "Student Not Updated").show();
//        }
//        StudentTM selectedItem = new StudentTM(
//                txtID.getText(),
//                txtName.getText(),
//                txtNIC.getText(),
//                txtAddress.getText(),
//                txtphone.getText(),
//                txtEmail.getText()
//        );
//        System.out.println("selectesItem = " + selectedItem);
        String id = txtID.getText();
        String name = txtName.getText();
        String nic = txtNIC.getText();
        String address = txtAddress.getText();
        String tel = txtphone.getText();
        String email = txtEmail.getText();

        if(studentBO.updateStudent(new StudentDTO(id,name,nic,address,tel,email))){
            new Alert(Alert.AlertType.CONFIRMATION, "Update Successfully!!").show();
        }else {
            new Alert(Alert.AlertType.ERROR, "Error!!").show();
        }
        clearFields();
        loadAllStudents();
    }
    @FXML
    void btnDeleteOnAction(ActionEvent event) throws Exception {
        ButtonType yes = new ButtonType("Yes", ButtonBar.ButtonData.OK_DONE);
        ButtonType no = new ButtonType("No", ButtonBar.ButtonData.CANCEL_CLOSE);
        Optional<ButtonType> result = new Alert(Alert.AlertType.INFORMATION, "Are you sure to remove?", yes, no).showAndWait();

        if (result.orElse(no) == yes) {
            if (!studentBO.deleteStudent(txtID.getText())) {
                new Alert(Alert.AlertType.ERROR, "Error!!").show();
            }
        }
        generateNextId();
        clearFields();
        loadAllStudents();
    }

    @FXML
    void btnDeleteOnMouseEntered(MouseEvent event) {

    }

    @FXML
    void btnDeleteOnMouseExited(MouseEvent event) {

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
    void txtAddressOnAction(ActionEvent event) {

    }

    @FXML
    void txtAddressOnKeyPressed(KeyEvent event) {

    }

    @FXML
    void txtEmailOnAction(ActionEvent event) {

    }

    @FXML
    void txtEmailOnKeyPressed(KeyEvent event) {

    }

    @FXML
    void txtIDOnAction(ActionEvent event) {

    }

    @FXML
    void txtIDOnKeyPressed(KeyEvent event) {

    }

    @FXML
    void txtNICOnAction(ActionEvent event) {

    }

    @FXML
    void txtNICOnKeyPressed(KeyEvent event) {

    }

    @FXML
    void txtNameOnAction(ActionEvent event) {

    }

    @FXML
    void txtNameOnKeyPressed(KeyEvent event) {

    }

    @FXML
    void txtPhoneOnAction(ActionEvent event) {

    }

    @FXML
    void txtPhoneOnKeyPressed(KeyEvent event) {

    }
    private boolean isValid() {
        if (txtName.getText() == null || txtName.getText().trim().isEmpty()) {
            new Alert(Alert.AlertType.WARNING, " Name cannot be empty").show();
            return false;
        }if(txtNIC.getText() == null || txtNIC.getText().trim().isEmpty()){
            new Alert(Alert.AlertType.WARNING, "NIC cannot be empty").show();
            return false;
        }
        if (txtAddress.getText() == null || txtAddress.getText().trim().isEmpty()) {
            new Alert(Alert.AlertType.WARNING, "Address cannot be empty").show();
            return false;
        }
        if (txtEmail.getText() == null || txtEmail.getText().trim().isEmpty()) {
            new Alert(Alert.AlertType.WARNING, "Email cannot be empty").show();
            return false;
        }
        if (txtphone.getText() == null || txtphone.getText().trim().isEmpty()) {
            new Alert(Alert.AlertType.WARNING, "Enter a valid contact number").show();
            return false;
        }
        return true;
    }

}
