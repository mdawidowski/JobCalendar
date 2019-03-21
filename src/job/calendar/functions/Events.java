package job.calendar.functions;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Events {
    public StringProperty name;
    public StringProperty description;
    public StringProperty startDate;
    public StringProperty endDate;

    public Events(String name, String description, String startDate, String endDate) {
        this.name = new SimpleStringProperty(name);
        this.description = new SimpleStringProperty(description);
        this.startDate = new SimpleStringProperty(startDate);
        this.endDate = new SimpleStringProperty(endDate);
    }

    public String getName() {
        return name.get();
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public StringProperty nameProperty() {
        return name;
    }

    public String getInitials(){
        String initials = name.getValue().replaceAll("([^\\s])[^\\s]+", "$1").replaceAll("\\s", "").toUpperCase();
        return initials;
    }

    public String getDescription() {
        return description.get();
    }

    public void setDescription(String description) {
        this.description.set(description);
    }

    public StringProperty descriptionProperty() {
        return description;
    }

    public String getStartDate() {
        return startDate.get();
    }

    public void setStartDate(String startDate) {
        this.startDate.set(startDate);
    }

    public StringProperty startDateProperty() {
        return startDate;
    }

    public String getEndDate() {
        return endDate.get();
    }

    public void setEndDate(String endDate) {
        this.endDate.set(endDate);
    }

    public StringProperty endDateProperty() {
        return endDate;
    }

    @Override
    public String toString(){
        return name.get();
    }
}
