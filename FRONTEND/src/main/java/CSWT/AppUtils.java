package CSWT;

import TicketManagerSystem.Ticket;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class AppUtils {

    public static void clearTextFields(TextField... textFields){
        for(TextField textField : textFields){
            textField.clear();
        }
    }

    public static boolean isEmptyTextField(TextField textField){
        return textField.getText().equals("");
    }

    public static boolean isEmptyTextArea(TextArea textArea){
        return textArea.getText().equals("");
    }

    public static boolean isEmptyComboBox(ComboBox comboBox){
        return (comboBox.getValue()== null);
    } //Not sure if correct; I need to verify.

    public static boolean isEmptyDatePicker(DatePicker datePicker){
        return (!datePicker.equals(null));
    }

    public static boolean validifyTextField(TextField... textFields){
        boolean valid = true;
        for(TextField textField : textFields){
            if(isEmptyTextField(textField)){
                textField.setStyle("-fx-border-color: red");
                valid = false;
            }
            else textField.setStyle("");
        }
        return valid;
    }

    public static boolean validifyTextArea(TextArea... textAreas){
        boolean valid = true;
        for(TextArea textArea : textAreas){
            if(isEmptyTextArea(textArea)){
                textArea.setStyle("-fx-border-color: red");
                valid = false;
            }
            else textArea.setStyle("");
        }
        return valid;
    }

    public static boolean validifyComboBox(ComboBox... comboBoxes){
        boolean valid = true;
        for(ComboBox comboBox : comboBoxes){
            if(isEmptyComboBox(comboBox)){
                comboBox.setStyle("-fx-border-color: red");
                valid = false;
            }
            else comboBox.setStyle("");
        }
        return valid;
    }

    public static boolean validifyDatePicker(DatePicker... datePickers){
        boolean valid = true;
        for(DatePicker datePicker : datePickers){
            if(isEmptyDatePicker(datePicker)){
                valid = false;
            }
        }
        return valid;
    }

    /*
    public static boolean isDisabledTextField(){

    }
    public static boolean isDisabledTextArea(){

    }
    public static boolean isDisabledComboBox(){

    }
    public static boolean isDisabledDatePicker(){

    }
    */
}
