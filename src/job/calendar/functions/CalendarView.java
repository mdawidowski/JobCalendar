package job.calendar.functions;

import javafx.collections.ObservableList;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.YearMonth;
import java.util.ArrayList;

public class CalendarView {
    private VBox calendarBox;
    private String monthName;
    private YearMonth yearMonth;
    private ArrayList<AnchorPaneNode> allCalendarDays = new ArrayList<>(35);
    private ObservableList<Events> allEvents;

    public void initializeCalendar() throws SQLException {
        GridPane calendar = new GridPane();
        calendar.setPrefSize(800, 600);
        calendar.setGridLinesVisible(true);
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 7; j++) {
                AnchorPaneNode anchorPaneNode = new AnchorPaneNode();
                anchorPaneNode.setPrefSize(400,300);
                calendar.add(anchorPaneNode,j,i);
                allCalendarDays.add(anchorPaneNode);
            }
        }

        Text[] dayLabels = new Text[]{ new Text("Monday"), new Text("Tuesday"),
                new Text("Wednesday"), new Text("Thursday"), new Text("Friday"),
                new Text("Saturday"), new Text("Sunday") };
        GridPane days = new GridPane();
        days.setPrefWidth(600);
        Integer col = 0;
        for (Text txt : dayLabels) {
            AnchorPane anchorPane = new AnchorPane();
            anchorPane.setPrefSize(200, 10);
            anchorPane.setBottomAnchor(txt, 5.0);
            anchorPane.getChildren().add(txt);
            days.add(anchorPane, col++, 0);
        }
        calendarBox = new VBox(days,calendar);
        populateCalendar();
    }

    public void populateCalendar() throws SQLException {
        LocalDate calendarDate = LocalDate.of(yearMonth.getYear(), yearMonth.getMonthValue(), 1);
        allEvents = DataManagement.getEventFromDatabase();
        while (!calendarDate.getDayOfWeek().toString().equals("MONDAY") ) {
            calendarDate = calendarDate.minusDays(1);
        }
        for (AnchorPaneNode anchorPaneNode : allCalendarDays) {
            if (anchorPaneNode.getChildren().size() != 0) {
                anchorPaneNode.getChildren().remove(0);
            }
            Text eventLabel = new Text("");
            for (int i = 0; i < allEvents.size(); i++) {
                if (calendarDate.isAfter(LocalDate.parse(String.valueOf(allEvents.get(i).getStartDate())).minusDays(1)) && calendarDate.isBefore(LocalDate.parse(String.valueOf(allEvents.get(i).getEndDate())).plusDays(1))) {
                    eventLabel = new Text(eventLabel.getText() + " " + allEvents.get(i).getInitials());
                }
            }
            Text dayNumber = new Text(String.valueOf(calendarDate.getDayOfMonth()));
            anchorPaneNode.setBottomAnchor(eventLabel, 5.0);
            anchorPaneNode.setRightAnchor(eventLabel, 5.0);
            anchorPaneNode.setDate(calendarDate);
            anchorPaneNode.setTopAnchor(dayNumber, 5.0);
            anchorPaneNode.setLeftAnchor(dayNumber, 5.0);
            anchorPaneNode.getChildren().setAll(dayNumber, eventLabel);
            calendarDate = calendarDate.plusDays(1);
        }
    }

    public void nextMonth() throws SQLException {
        yearMonth = yearMonth.plusMonths(1);
        populateCalendar();
        setMonthName();
    }

    public void previousMonth() throws SQLException {
        yearMonth = yearMonth.minusMonths(1);
        populateCalendar();
        setMonthName();
    }

    public void setCalendarBox(VBox calendarBox){
        this.calendarBox = calendarBox;
    }

    public void setActualDate(){
        this.yearMonth = YearMonth.now();
    }
    public void setMonthName(){
        this.monthName = yearMonth.getMonth().toString() + " " + yearMonth.getYear();
    }

    public String getMonthName(){
        return monthName;
    }

    public VBox getCalendarBox(){
        return this.calendarBox;
    }

}
