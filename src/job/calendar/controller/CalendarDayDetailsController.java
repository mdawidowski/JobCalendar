package job.calendar.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class CalendarDayDetailsController {

    @FXML
    private Button buttonClose;

    @FXML
    public void closeWindow(){
        Stage stage = (Stage) buttonClose.getScene().getWindow();
        stage.close();
    }
}
