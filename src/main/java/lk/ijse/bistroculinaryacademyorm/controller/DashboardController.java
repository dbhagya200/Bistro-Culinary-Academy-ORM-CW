package lk.ijse.bistroculinaryacademyorm.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import lk.ijse.bistroculinaryacademyorm.util.NavigateTo;

import java.io.IOException;

public class DashboardController {

    @FXML
    private Pane booksPane;

    @FXML
    private Pane branchesPane;

    @FXML
    private Pane catalogPane;

    @FXML
    private AnchorPane customroot;

    @FXML
    private Pane dashboardPane;

    @FXML
    private AnchorPane dashbord;

    @FXML
    private ImageView imgBranches;

    @FXML
    private ImageView imgDashboard;

    @FXML
    private ImageView imgLogOut;

    @FXML
    private ImageView imgPrograms;

    @FXML
    private ImageView imgStudent;

    @FXML
    private ImageView imgUsers;

    @FXML
    private Label lblAdmin;

    @FXML
    private Label lblBooks;

    @FXML
    private Label lblBranches;

    @FXML
    private Label lblCatalog;

    @FXML
    private Label lblDashboard;

    @FXML
    private Label lblDate;

    @FXML
    private Label lblLogOut;

    @FXML
    private Label lblTime;

    @FXML
    private Label lblUsers;

    @FXML
    private Pane logOutPane;

    @FXML
    private Pane popUpLargePane;

    @FXML
    private Pane popUpPane;

    @FXML
    private Pane settingsPane;

    @FXML
    private Pane usersPane;


    @FXML
    void btnDashboardOnAction(ActionEvent event) {

    }

    @FXML
    void btnLogOutOnAction(ActionEvent event) {
        try {
            NavigateTo.parent("/lk/ijse/bistroculinaryacademyorm/view/signIn_form.fxml",dashbord);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void btnProgramsOnAction(ActionEvent event) {
        try {
            NavigateTo.children("/lk/ijse/bistroculinaryacademyorm/view/course_form.fxml",customroot);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void btnSettingsOnAction(ActionEvent event) {

    }

    @FXML
    void btnStudentOnAction(ActionEvent event) {
        try {
            NavigateTo.children("/lk/ijse/bistroculinaryacademyorm/view/student.fxml",customroot);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void btnUsersOnAction(ActionEvent event) {

    }

}
