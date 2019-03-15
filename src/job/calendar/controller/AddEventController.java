package job.calendar.controller;


import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class AddEventController {

    @FXML
    private Button cancelButton;

    @FXML
    private Button newEventButton;

    @FXML
    public void closeWindow(){
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();
    }
}
