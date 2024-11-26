package lk.ijse.bistroculinaryacademyorm.controller;

import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import lk.ijse.bistroculinaryacademyorm.bo.BOFactory;
import lk.ijse.bistroculinaryacademyorm.bo.custom.UserBO;
import lk.ijse.bistroculinaryacademyorm.dto.UserDTO;
import lk.ijse.bistroculinaryacademyorm.util.NavigateTo;

import java.io.IOException;

public class SignUpFormController {

    @FXML
    private JFXRadioButton admin;

    @FXML
    private ToggleGroup admintype;

    @FXML
    private JFXRadioButton coordinator;

    @FXML
    private Label lblContactNoAlert;

    @FXML
    private Label lblEmailAlert;

    @FXML
    private Label lblFirstNameAlert;

    @FXML
    private Label lblLastNameAlert;

    @FXML
    private Label lblPasswordAlert;

    @FXML
    private Label lblUsernameAlert;


    @FXML
    private AnchorPane rootNodeUp;

    @FXML
    private JFXTextField txtEmail;

    @FXML
    private JFXPasswordField txtConfirm;

    @FXML
    private JFXPasswordField txtPassword;

    @FXML
    private JFXTextField txtUsername;

//    UserBO userBO = (UserBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.USER);
    UserBO userBO = BOFactory.getBoFactory().getBO(BOFactory.BOTypes.USER);

    @FXML
    void btnSignInOnAction(ActionEvent event) {
        try {
            NavigateTo.parent("/lk/ijse/bistroculinaryacademyorm/view/signIn_form.fxml",rootNodeUp);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void btnSignUpOnAction(ActionEvent event) throws IOException {
        String username = txtUsername.getText();
        String email = txtEmail.getText();
        String pass = txtPassword.getText();
        String confirm = txtConfirm.getText();


        if ((admin.isSelected()||coordinator.isSelected())||pass.equals(confirm)) {

                boolean isSaved=userBO.saveUser(new UserDTO(
                        username,
                        email,
                        pass,
                        confirm,
                        gettype()
                ));

            NavigateTo.parent("/lk/ijse/bistroculinaryacademyorm/view/signIn_form.fxml",rootNodeUp);

            new Alert(Alert.AlertType.ERROR, "please sign in").show();

        }else {
            new Alert(Alert.AlertType.WARNING, "select one admin type").show();
        }

    }
    private String gettype() {
        if (admin.isSelected()){
            return "admin";
        } else if (coordinator.isSelected()) {
            return "coordinator";
        }
        return null;
    }

    @FXML
    void btnSignUpOnMouseEntered(MouseEvent event) {

    }

    @FXML
    void btnSignUpOnMouseExited(MouseEvent event) {

    }



    @FXML
    void txtConfirmOnAction(ActionEvent event) {

    }

    @FXML
    void txtConfirmOnKeyPressed(KeyEvent event) {

    }

    @FXML
    void txtEmailOnAction(ActionEvent event) {

    }

    @FXML
    void txtEmailOnKeyPressed(KeyEvent event) {

    }



    @FXML
    void txtPasswordOnAction(ActionEvent event) {

    }

    @FXML
    void txtPasswordOnKeyPressed(KeyEvent event) {

    }

    @FXML
    void txtUsernameOnAction(ActionEvent event) {

    }

    @FXML
    void txtUsernameOnKeyPressed(KeyEvent event) {

    }

}
