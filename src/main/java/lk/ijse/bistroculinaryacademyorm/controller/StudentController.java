package lk.ijse.bistroculinaryacademyorm.controller;

import com.jfoenix.controls.JFXTextField;
import com.mysql.cj.Session;
import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
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
import java.util.ArrayList;
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

    StudentBO  studentBO = (StudentBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.STUDENT);

    @SneakyThrows
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setCellValueFactory();
        loadAllStudents();

    }

    @FXML
    void btnAddOnAction(ActionEvent event) throws IOException {
        String id = txtID.getText();
        String name = txtName.getText();
        String nic = txtNIC.getText();
        String address = txtAddress.getText();
        String email = txtEmail.getText();
        String contact = txtphone.getText();

        if (id.trim().isEmpty() || name.trim().isEmpty() || nic.trim().isEmpty() || address.trim().isEmpty() || email.trim().isEmpty() || contact.trim().isEmpty()) {
            new Alert(Alert.AlertType.WARNING, "Please fill all fields").show();
        } else {
            studentBO.addStudent(new Student(id, name, nic, address, email, contact));
            new Alert(Alert.AlertType.CONFIRMATION, "Student Added").show();
            clearFields();
        }
    }

    private void clearFields() {
        txtID.clear();
        txtName.clear();
        txtNIC.clear();
        txtAddress.clear();
        txtEmail.clear();
        txtphone.clear();

    }

    private void setCellValueFactory() {
        colid.setCellValueFactory(new PropertyValueFactory<>("id"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
        colEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        colContact.setCellValueFactory(new PropertyValueFactory<>("contact"));
    }
    private void loadAllStudents() {
        ObservableList<StudentTM> obList = FXCollections.observableArrayList();
        tblStudent.getItems().clear();

        try {
            ArrayList<StudentDTO> allStudents = studentBO.getAllStudents();
            for (StudentDTO dto : allStudents) {
                StudentTM studentTM = new StudentTM(
                        dto.getStudentId(),
                        dto.getStudentName(),
                        dto.getStudentAddress(),
                        dto.getStudentContact(),
                        dto.getStudentEmail()

                );
                obList.add(studentTM);

            }
            tblStudent.setItems(obList);
        }catch (Exception e){
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
