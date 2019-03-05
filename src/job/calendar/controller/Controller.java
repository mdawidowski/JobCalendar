package job.calendar.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import job.calendar.functions.DataManagement;
import job.calendar.functions.CalendarView;
import job.calendar.functions.Person;

import javax.xml.crypto.Data;
import java.sql.SQLException;
import java.util.Arrays;

public class Controller {

    @FXML
    private TextField nameTextField;
    @FXML
    private TextField amountTextField;
    @FXML
    private Button addButton;
    @FXML
    private Button deleteButton;
    @FXML
    private Button editButton;
    @FXML
    private Button previousButton;
    @FXML
    private Button nextButton;
    @FXML
    private Label monthNameLabel;
    @FXML
    private TableView<Person> tableView;
    @FXML
    private TableColumn<Person, String> nameColumn;
    @FXML
    private TableColumn<Person, Integer> amountColumn;
    @FXML
    private VBox calendarVBox;
    @FXML
    private Label labelLeftStatus;
    @FXML
    private Label labelRightStatus;

    private CalendarView view;
    private ObservableList list;

    @FXML
    void initialize() throws SQLException {
        view = new CalendarView();
        view.setCalendarBox(calendarVBox);
        view.setActualDate();
        view.setMonthName();
        monthNameLabel.setText(view.getMonthName());
        view.initializeCalendar();
        this.calendarVBox.getChildren().add(view.getCalendarBox());
        loadTableView();

    }

    @FXML
    public void nextMonth(){
        view.nextMonth();
        monthNameLabel.setText(view.getMonthName());
    }

    @FXML
    public void previousMonth(){
        view.previousMonth();
        monthNameLabel.setText(view.getMonthName());
    }

    @FXML
    public void addNewItem() throws SQLException {
        if (!amountTextField.getText().isEmpty()) {
            DataManagement.insertData(nameTextField.getText(), Integer.parseInt(amountTextField.getText()));
            nameTextField.setText(null);
            amountTextField.setText(null);
        } else {
            System.out.println("Wrong value inserted!");
        }
        loadTableView();

    }

    @FXML
    public void deletePerson() throws SQLException {
        if (!tableView.getSelectionModel().isEmpty()) {
            Person person = tableView.getSelectionModel().getSelectedItem();
            DataManagement.deleteData(person.name.getValue(), person.amount.getValue().intValue());
            loadTableView();
        } else {
            selectAlert();
        }
    }

    @FXML
    public void editPerson(){
        if (!tableView.getSelectionModel().isEmpty()) {
            Person person = tableView.getSelectionModel().getSelectedItem();

        } else {
            selectAlert();
        }
    }

    public void loadTableView() throws SQLException {
        list = DataManagement.showData();
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        amountColumn.setCellValueFactory(new PropertyValueFactory<>("amount"));
        tableView.setItems(list);
    }

    public void selectAlert(){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information Dialog");
        alert.setHeaderText(null);
        alert.setContentText("You have to select one person!");
        alert.showAndWait();
    }
}
