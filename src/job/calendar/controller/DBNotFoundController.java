package job.calendar.controller;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import java.io.IOException;

public class DBNotFoundController {


    public void DBNotFoundWindow() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/job/calendar/view/dbNotFound.fxml"));
        Parent root1 = fxmlLoader.load();
        DBNotFoundController dbNotFoundController = fxmlLoader.getController();
        Stage stage = new Stage();
        stage.setScene(new Scene(root1));
        stage.setTitle("Database file not found");
        stage.setAlwaysOnTop(true);
        stage.show();
        stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            public void handle(WindowEvent we) {
            System.exit(0);
            }
        });
    }

    @FXML
    public void createNewDB(){
    }

    @FXML
    public void openDB(){
    }

    @FXML
    public void closeApp(){
        System.exit(0);
    }

}
