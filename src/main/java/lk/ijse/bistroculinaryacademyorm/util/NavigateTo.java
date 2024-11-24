package lk.ijse.bistroculinaryacademyorm.util;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class NavigateTo {
    public static void children(String path, AnchorPane childNode) throws IOException {
        AnchorPane root = FXMLLoader.load(NavigateTo.class.getResource(path));

        childNode.getChildren().clear();
        childNode.getChildren().add(root);
    }
    public static void parent(String path, AnchorPane parentNode) throws IOException {
        AnchorPane rootNode = FXMLLoader.load(NavigateTo.class.getResource(path));
        Scene scene = new Scene(rootNode);

        Stage stage = (Stage) parentNode.getScene().getWindow();
        stage.setScene(scene);
        stage.centerOnScreen();
    }
}
