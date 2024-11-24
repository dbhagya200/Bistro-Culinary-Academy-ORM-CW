package lk.ijse.bistroculinaryacademyorm;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import lk.ijse.bistroculinaryacademyorm.config.SessionFactoryConfig;
import lk.ijse.bistroculinaryacademyorm.entity.User;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.io.IOException;
import java.util.List;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("view/signIn_form.fxml"));
//        Scene scene = new Scene(fxmlLoader.load(), 320, 240);
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        Session session = null;
        try {
            session = SessionFactoryConfig.getInstance().getSession();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        String sql = "SELECT U FROM User AS U";
        Query query = session.createQuery(sql);
        List<User> list = query.list();
        for (User customer : list) {
            System.out.println(customer);
        }
        session.close();
        launch();
    }
}