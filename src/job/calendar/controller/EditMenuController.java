package job.calendar.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import job.calendar.functions.DataManagement;
import java.sql.SQLException;

public class EditMenuController {

    @FXML
    private TextField nameField;
    @FXML
    private TextField amountField;
    @FXML
    private Button menuEditButton;
    @FXML
    private Button cancelButton;

    private String oldName;
    private int oldAmount;
    private boolean dataChanged = false;

    @FXML
    public void editSelectedPerson() throws SQLException {
         if (!nameField.getText().isEmpty() && !amountField.getText().isEmpty()) {
             DataManagement.editData(oldName, oldAmount, nameField.getText(), Integer.valueOf(amountField.getText()));
             setDataChanged();
             closeEditWindow();
         }
    }

    @FXML
    private void closeEditWindow(){
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();
    }

    public void setPersonData(String name, int amount) {
        this.oldName = name;
        this.oldAmount = amount;
        nameField.setText(name);
        amountField.setText(String.valueOf(amount));
    }


    public void setDataChanged(){
        this.dataChanged = true;
    }

    public boolean getDataChanged(){
        return dataChanged;
    }
}