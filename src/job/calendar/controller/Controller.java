package job.calendar.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import job.calendar.functions.AddData;

public class Controller {

    @FXML
    private TextField nameTextField;
    @FXML
    private TextField amountTextField;
    @FXML
    private Button addButton;
    @FXML
    private Button previousButton;
    @FXML
    private Button nextButton;
    @FXML
    private Label monthNameLabel;
    @FXML
    private TableView tableView;
    @FXML
    private VBox calendarVBox;
    @FXML
    private Label labelLeftStatus;
    @FXML
    private Label labelRightStatus;


    @FXML
    void initialize() {
    }

    @FXML
    public void displayCalendar(){

    }

    @FXML
    public void nextMonth(){
    }

    @FXML
    public void previousMonth(){
    }

    @FXML
    public void addNewItem(){
        if (amountTextField.getText() == null) {
            AddData.insertData(nameTextField.getText(), Integer.parseInt(amountTextField.getText()));
        } else {
            System.out.println("Wrong value inserted!");
        }
    }
}
