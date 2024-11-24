package lk.ijse.bistroculinaryacademyorm.controller;

import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import lk.ijse.bistroculinaryacademyorm.util.NavigateTo;

import java.io.IOException;

public class SignInFormController {

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
    private AnchorPane rootNodeLog;

    @FXML
    private JFXPasswordField txtPassword;

    @FXML
    private JFXTextField txtUsername;

    @FXML
    void btnSignInOnAction(ActionEvent event) {
        try {
            NavigateTo.parent("/lk/ijse/bistroculinaryacademyorm/view/dashboard.fxml",rootNodeLog);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void btnSignInOnMouseEntered(MouseEvent event) {

    }

    @FXML
    void btnSignInOnMouseExited(MouseEvent event) {

    }

    @FXML
    void btnSignUpOnAction(ActionEvent event) {
        try {
            NavigateTo.parent("/lk/ijse/bistroculinaryacademyorm/view/signUp_form.fxml",rootNodeLog);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void btnSignUpOnMouseEntered(MouseEvent event) {

    }

    @FXML
    void btnSignUpOnMouseExited(MouseEvent event) {

    }

    @FXML
    void hyperForgotPasswordOnAction(ActionEvent event) {

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
