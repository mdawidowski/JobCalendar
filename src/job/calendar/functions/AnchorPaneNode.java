package job.calendar.functions;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import job.calendar.controller.CalendarDayDetailsController;

import java.io.IOException;
import java.time.LocalDate;

public class AnchorPaneNode extends AnchorPane {

    // Date associated with this pane
    private LocalDate date;
    private CalendarDayDetailsController calendarDayDetailsController = new CalendarDayDetailsController();
    private Stage stage;
    /**
     * Create a anchor pane node. Date is not assigned in the constructor.
     * @param children children of the anchor pane
     */
    public AnchorPaneNode(Node... children) {

        super(children);
        // Add action handler for mouse clicked
        this.setOnMouseClicked(e -> {
            try {
                openDayDetails();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        });
    }

    public void openDayDetails() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/job/calendar/view/calendarDayDetails.fxml"));
        Parent root1 = fxmlLoader.load();
        calendarDayDetailsController = fxmlLoader.getController();
        stage = new Stage();
        stage.setScene(new Scene(root1));
        stage.setTitle(date + " details");
        stage.show();
        calendarDayDetailsController.setTextDate(date);
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }
}
