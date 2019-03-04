package job.calendar.functions;

import javafx.scene.Node;
import javafx.scene.layout.AnchorPane;

import java.time.LocalDate;

public class AnchorPaneNode extends AnchorPane {

    // Date associated with this pane
    private LocalDate date;
    private DetailsPane detailsPane = new DetailsPane();
    /**
     * Create a anchor pane node. Date is not assigned in the constructor.
     * @param children children of the anchor pane
     */
    public AnchorPaneNode(Node... children) {

        super(children);
        // Add action handler for mouse clicked
        this.setOnMouseClicked(e -> detailsPane.setDetails(date));
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }
}
