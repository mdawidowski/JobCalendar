package job.calendar.controller;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import job.calendar.functions.DataManagement;
import job.calendar.functions.CalendarView;
import job.calendar.functions.Person;

import java.sql.SQLException;
import java.util.ArrayList;

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
    private ObservableList<Person> list;
    @FXML
    void initialize() {
        view = new CalendarView();
        view.setCalendarBox(calendarVBox);
        view.setActualDate();
        view.setMonthName();
        monthNameLabel.setText(view.getMonthName());
        view.initializeCalendar();
        this.calendarVBox.getChildren().add(view.getCalendarBox());
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("Name"));
        amountColumn.setCellValueFactory(new PropertyValueFactory<>("Amounts"));
        list = DataManagement.showData();
        tableView.setItems(list);
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
        } else {
            System.out.println("Wrong value inserted!");
        }
    }

}
