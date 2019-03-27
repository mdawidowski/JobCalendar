package job.calendar.controller;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import job.calendar.functions.DataManagement;
import job.calendar.functions.Events;

import java.sql.SQLException;
import java.time.LocalDate;

public class CalendarDayDetailsController {

    @FXML
    private Button buttonClose;
    @FXML
    private Text textDate;
    @FXML
    private TableView<Events> detailsTableView;
    @FXML
    private TableColumn<Events, String> nameColumn;
    @FXML
    private TableColumn<Events, String> descriptionColumn;

    private ObservableList<Events> allEvents;

    @FXML
    public void closeWindow(){
        Stage stage = (Stage) buttonClose.getScene().getWindow();
        stage.close();
    }

    public void setTextDate(LocalDate date) throws SQLException {
        this.textDate.setText(String.valueOf(date));
        getEvents();
    }


    public void getEvents() throws SQLException {
        allEvents = DataManagement.getEventFromDatabase();
        loadTableView();
        }


    public void loadTableView() {
        LocalDate current_date = LocalDate.parse(String.valueOf(textDate.getText()));
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        descriptionColumn.setCellValueFactory(new PropertyValueFactory<>("description"));
        detailsTableView.setItems(allEvents.filtered(a -> (LocalDate.parse(a.getStartDate()).minusDays(1).isBefore(current_date)) && LocalDate.parse(a.getEndDate()).plusDays(1).isAfter(current_date)));
    }

}