package job.calendar.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.time.LocalDate;

public class CalendarDayDetailsController {

    @FXML
    private Button buttonClose;
    @FXML
    private Text textDate;

    @FXML
    public void closeWindow(){
        Stage stage = (Stage) buttonClose.getScene().getWindow();
        stage.close();
    }

    public void setTextDate(LocalDate date){
        this.textDate.setText(String.valueOf(date));
    }
}
