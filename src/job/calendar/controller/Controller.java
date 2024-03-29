package job.calendar.controller;

import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import job.calendar.functions.DataManagement;
import job.calendar.functions.CalendarView;
import job.calendar.functions.Person;

import java.io.IOException;
import java.sql.SQLException;

public class Controller {

    @FXML
    private TextField nameTextField, amountTextField;
    @FXML
    private Button addButton, deleteButton, editButton, previousButton, nextButton, addNewEventButton;
    @FXML
    private Label monthNameLabel;
    @FXML
    private TableView<Person> tableView;
    @FXML
    private TableColumn<Person, String> nameColumn;
    @FXML
    private TableColumn<Person, Integer> amountColumn;
    @FXML
    private TableColumn<Person, Void> actionColumn;
    @FXML
    private VBox calendarVBox;
    @FXML
    private Label labelLeftStatus, labelRightStatus;
    @FXML
    private MenuItem newMenuItem, openMenuItem, quitMenuItem, aboutMenuItem;

    private CalendarView view;
    private ObservableList list;
    private EditMenuController editMenuController;
    private AddEventController addEventController;
    private Stage stage;

    @FXML
    void initialize() throws SQLException, IOException {
        view = new CalendarView();
        view.setCalendarBox(calendarVBox);
        view.setActualDate();
        view.setMonthName();
        monthNameLabel.setText(view.getMonthName());
        try {
            view.initializeCalendar();
            this.calendarVBox.getChildren().add(view.getCalendarBox());
            loadTableView();
        } catch (SQLException e){
            DBNotFoundController dbNotFoundController = new DBNotFoundController();
            dbNotFoundController.DBNotFoundWindow();
        }
    }

    @FXML
    public void nextMonth() throws SQLException {
        view.nextMonth();
        monthNameLabel.setText(view.getMonthName());
    }

    @FXML
    public void previousMonth() throws SQLException {
        view.previousMonth();
        monthNameLabel.setText(view.getMonthName());
    }

    @FXML
    public void addNewEvent() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/job/calendar/view/addEvent.fxml"));
        Parent root1 = fxmlLoader.load();
        addEventController = fxmlLoader.getController();
        stage = new Stage();
        stage.setScene(new Scene(root1));
        stage.setTitle("Add new event");
        stage.show();
        stage.setOnHidden(new EventHandler<WindowEvent>() {
            public void handle(WindowEvent we) {
                try {
                    view.populateCalendar();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        });
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
            DataManagement.deleteData(person.name.getValue(), person.amount.getValue());
            loadTableView();
        } else {
            selectAlert();
        }
    }

    @FXML
    public void editPerson() throws IOException {
        if (!tableView.getSelectionModel().isEmpty()) {
            Person person = tableView.getSelectionModel().getSelectedItem();
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/job/calendar/view/editMenu.fxml"));
            Parent root1 = fxmlLoader.load();
            editMenuController = fxmlLoader.getController();
            stage = new Stage();
            stage.setScene(new Scene(root1));
            stage.setTitle("Edit Person");
            stage.show();
            editMenuController.setPersonData(person.name.getValue(), person.amount.getValue().intValue());
            stage.setOnHidden(new EventHandler<WindowEvent>() {
                public void handle(WindowEvent we) {
                    try {
                        loadTableView();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
            });

        } else {
            selectAlert();
        }
    }

    public void loadTableView() throws SQLException {
        list = DataManagement.showData();
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        amountColumn.setCellValueFactory(new PropertyValueFactory<>("amount"));
        addActionButtons();
        tableView.setItems(list);
    }

    public void addActionButtons() {
        actionColumn.setCellFactory(param -> new TableCell<Person, Void>() {
            private final Button plusButton = new Button("+");
            private final Button minusButton = new Button("-");
            private final HBox pane = new HBox(plusButton, minusButton);

            {
                plusButton.setOnAction(event -> {
                    Person person = getTableView().getItems().get(getIndex());
                    try {
                        DataManagement.updateData(person.name.getValue(), person.amount.getValue(), person.name.getValue(), person.amount.get() + 1);
                        loadTableView();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                });

                minusButton.setOnAction(event -> {
                    Person person = getTableView().getItems().get(getIndex());
                    try {
                        DataManagement.updateData(person.name.getValue(), person.amount.getValue(), person.name.getValue(), person.amount.get() - 1);
                        loadTableView();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                });
            }

            @Override
            protected void updateItem(Void item, boolean empty) {
                super.updateItem(item, empty);

                setGraphic(empty ? null : pane);
            }
        });
    }

    public void selectAlert(){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information Dialog");
        alert.setHeaderText(null);
        alert.setContentText("You have to select person!");
        alert.showAndWait();
    }

    @FXML
    public void newDB(){

    }

    @FXML
    public void dbOpen(){
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open Resource File");
        fileChooser.showOpenDialog(stage);
    }

    @FXML
    public void quitApp(){
        System.exit(0);
    }
}
