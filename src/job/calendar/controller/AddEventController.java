package job.calendar.controller;


import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import job.calendar.functions.DataManagement;
import job.calendar.functions.Person;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;


public class AddEventController {

    @FXML
    private Button cancelButton, newEventButton;
    @FXML
    private ComboBox<Person> personComboBox;
    @FXML
    private TextField descriptionTextField;
    @FXML
    private DatePicker startDatePicker, endDatePicker;

    ObservableList<Person> comboBoxOptions = DataManagement.showData();
    private String name;
    private String description;
    private String start_date;
    private String end_date;

    public AddEventController() throws SQLException {
    }

    public void initialize(){
        personComboBox.setItems(comboBoxOptions);
    }

    @FXML
    public void addEvent() throws SQLException, ParseException {
        setName();
        setDescription();
        setStart_date();
        setEnd_date();
        DataManagement.insertNewEvent(name, description, start_date, end_date);
        closeWindow();
    }

    @FXML
    public void closeWindow(){
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();
    }

    public void setName(){
        if (!personComboBox.getSelectionModel().isEmpty()){
            this.name = String.valueOf(personComboBox.getSelectionModel().getSelectedItem().getName());
        }
    }

    public void setDescription(){
        if (!descriptionTextField.getText().isEmpty()){
            this.description = descriptionTextField.getText();
        }
    }

    public void setStart_date() throws ParseException {
        SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd");
        if (startDatePicker.getValue() != null){
            this.start_date = ft.format(ft.parse(startDatePicker.getValue().toString()));
        }
    }

    public void setEnd_date() throws ParseException {
        SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd");
        if (endDatePicker.getValue() != null){
            this.end_date = ft.format(ft.parse(endDatePicker.getValue().toString()));
        }
    }
}
