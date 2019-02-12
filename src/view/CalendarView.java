package view;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

import java.time.LocalDate;
import java.time.YearMonth;
import java.util.ArrayList;

public class CalendarView {
    private VBox view;
    private ArrayList<AnchorPaneNode> calendarDays = new ArrayList<>(35);
    private Text title;
    private YearMonth currentYearMonth;

    public CalendarView(YearMonth yearMonth){
        currentYearMonth = yearMonth;
        GridPane calendar = new GridPane();
        calendar.setPrefSize(600, 400);
        calendar.setGridLinesVisible(true);
        for (int i = 0; i < 5; i++){
            for (int j = 0; j < 7; j++){
                AnchorPaneNode anchorPaneNode = new AnchorPaneNode();
                anchorPaneNode.setPrefSize(200, 200);
                calendar.add(anchorPaneNode, j ,i);
                calendarDays.add(anchorPaneNode);
            }
        }

        Text[] nameOfDay = new Text[]{new Text("Sunday"), new Text("Monday"), new Text("Tuesday"),
                new Text("Wednesday"), new Text("Thursday"), new Text("Friday"),
                new Text("Saturday")};

        GridPane dayLabels = new GridPane();
        dayLabels.setPrefWidth(600);
        Integer col = 0;
        for (Text txt : nameOfDay) {
            AnchorPaneNode anchorPaneNode = new AnchorPaneNode();
            anchorPaneNode.setPrefSize(200, 10);
            anchorPaneNode.setBottomAnchor(txt, 5.0);
            anchorPaneNode.getChildren().add(txt);
            dayLabels.add(anchorPaneNode, col++, 0);
        }

        title = new Text();
        Button previous = new Button("<<");
        previous.setOnAction(e -> previousMonth());
        Button next = new Button(">>");
        next.setOnAction(e -> nextMonth());
        HBox titleBar = new HBox(previous, title, next);
        titleBar.setAlignment(Pos.BASELINE_CENTER);

        populateCalendar(yearMonth);
        view = new VBox(titleBar, dayLabels, calendar);
    }

    public void  populateCalendar(YearMonth yearMonth){
        LocalDate localDate = LocalDate.of(yearMonth.getYear(), yearMonth.getMonthValue(), 1);
        while (!localDate.getDayOfWeek().toString().equals("SUNDAY")){
            localDate = localDate.minusDays(1);
        }
        for (AnchorPaneNode anchorPaneNode : calendarDays){
            if (anchorPaneNode.getChildren().size() != 0){
                anchorPaneNode.getChildren().remove(0);
            }
        Text txt = new Text(String.valueOf(localDate.getDayOfMonth()));
            anchorPaneNode.setDate(localDate);
            anchorPaneNode.setTopAnchor(txt, 5.0);
            anchorPaneNode.setLeftAnchor(txt, 5.0);
            anchorPaneNode.getChildren().add(txt);
            localDate = localDate.plusDays(1);
        }

        title.setText(yearMonth.getMonth().toString() + " " + String.valueOf(yearMonth.getYear()));
    }

    private void previousMonth(){
        currentYearMonth = currentYearMonth.minusMonths(1);
        populateCalendar(currentYearMonth);
    }

    private void nextMonth(){
        currentYearMonth = currentYearMonth.plusMonths(1);
        populateCalendar(currentYearMonth);
    }

    public VBox getView(){
        return view;
    }

    public ArrayList<AnchorPaneNode> getAllCalendarDays(){
        return calendarDays;
    }

    public void  setAllCalendarDays(ArrayList<AnchorPaneNode> allCalendarDays){
        this.calendarDays = allCalendarDays;
    }
}
