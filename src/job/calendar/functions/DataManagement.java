package job.calendar.functions;

import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import job.calendar.model.Database;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class DataManagement {
    public static Database db = new Database();
    public static void insertData(String name, int amount) throws SQLException {
        if (!name.isEmpty()){
            System.out.println(name + " + " + amount);
        } else {
            System.out.println("Błędnie podana wartość");
        }

        PreparedStatement statement = db.conn.prepareStatement("insert into staff values (?, ?);");
        statement.setString(1,  name);
        statement.setInt(2, amount);
        statement.execute();
    }

    public static ObservableList<Person> showData() {
        ArrayList<Person> list = new ArrayList();
        Person person;
        try {
            ResultSet resultSet = db.stat.executeQuery("SELECT * FROM staff;");
            while(resultSet.next()) {
                String name = resultSet.getString("name");
                int amount = resultSet.getInt("amount");
                person = new Person();
                person.name = name;
                person.amount = amount;
                list.add(person);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        ObservableList<Person> observableList = FXCollections.observableList(list);
        return observableList;


    }

    public static void deleteData() throws SQLException {
    }

    public static void editData() throws SQLException {
    }


}
