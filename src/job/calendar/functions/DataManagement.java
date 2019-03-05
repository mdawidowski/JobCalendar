package job.calendar.functions;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import job.calendar.model.Database;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class DataManagement {

    private static ArrayList dataBaseArrayList(ResultSet resultSet) throws SQLException {
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
            System.out.println(name + " + " + amount);
        } else {
            System.out.println("Błędnie podana wartość");
        }

        PreparedStatement statement = db.conn.prepareStatement("insert into staff values (?, ?);");
        statement.setString(1,  name);
        statement.setInt(2, amount);
        statement.execute();
    }

    public static ObservableList showData() throws SQLException {
        ArrayList<Person> list = new ArrayList();
        Person person;
        ResultSet resultSet = null;
        resultSet = db.stat.executeQuery("SELECT * FROM staff;");
        ObservableList dbData = FXCollections.observableArrayList(dataBaseArrayList(resultSet));
        return dbData;
    }

    public static void deleteData() throws SQLException {
    }

    public static void editData() throws SQLException {
    }


}
