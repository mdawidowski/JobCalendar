package job.calendar.functions;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import job.calendar.model.Database;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Objects;
import java.util.stream.Collectors;

public class DataManagement {

    private static ArrayList getDataBaseArrayList(ResultSet resultSet) throws SQLException {
        ArrayList<Person> data =  new ArrayList<>();
        while (resultSet.next()) {
            Person person = new Person((resultSet.getString("name")), (resultSet.getInt("amount")));
            data.add(person);
        }
        return data;
    }

    public static Database db = new Database();
    public static void insertData(String name, int amount) throws SQLException {
        if (!name.isEmpty()){
            PreparedStatement statement = db.conn.prepareStatement("insert into staff values (?, ?);");
            statement.setString(1,  name);
            statement.setInt(2, amount);
            statement.execute();
        } else {
            System.out.println("Błędnie podana wartość");
        }

    }

    public static ObservableList showData() throws SQLException {
        ResultSet resultSet;
        resultSet = db.stat.executeQuery("SELECT * FROM staff;");
        ObservableList dbData = FXCollections.observableArrayList(getDataBaseArrayList(resultSet));
        return dbData;
    }

    public static void deleteData(String name, int amount) throws SQLException {
            PreparedStatement statement = db.conn.prepareStatement("DELETE FROM staff where name=(?) and amount=(?);");
            statement.setString(1, name);
            statement.setInt(2,amount);
            statement.execute();
    }

    public static void updateData(String name, int amount, String newName, int newAmount) throws SQLException {
        PreparedStatement statement = db.conn.prepareStatement("UPDATE staff SET name=(?), amount=(?) WHERE name=(?) and amount=(?);");
        statement.setString(1, newName);
        statement.setInt(2, newAmount);
        statement.setString(3, name);
        statement.setInt(4, amount);
        statement.execute();
    }

    public static void insertNewEvent(String name, String description, String start_date, String end_date) throws SQLException {
            PreparedStatement statement = db.conn.prepareStatement("insert into events values (?, ?, ?, ?);");
            statement.setString(1,  name);
            statement.setString(2, description);
            statement.setString(3, start_date);
            statement.setString(4, end_date);
            statement.execute();
    }

    public static ObservableList getEventFromDatabase() throws SQLException {
        ResultSet resultSet;
        resultSet = db.stat.executeQuery("SELECT * FROM events;");
        ObservableList dbData = FXCollections.observableArrayList(getEventsDataBaseArrayList(resultSet));
        return dbData;
    }

    private static ArrayList getEventsDataBaseArrayList(ResultSet resultSet) throws SQLException {
        ArrayList<Events> data =  new ArrayList<>();
        while (resultSet.next()) {
            Events events = new Events((resultSet.getString("person")), (resultSet.getString("description")), (resultSet.getString("date_start")), (resultSet.getString("date_end")));
            data.add(events);
        }
        return data;
    }

    public static ObservableList getEventsByDate(String start_date, String end_date) throws SQLException {
        ObservableList<Events> eventsList;
        eventsList = getEventFromDatabase();
        eventsList.stream()
                .filter(a -> Objects.equals(a.getStartDate(), start_date))
                .collect(Collectors.toList());
        return eventsList;
    }
}
