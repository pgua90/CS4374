package CSWT.controllers;

import CSWT.CSWT;
import TicketManagerSystem.*;
import com.fasterxml.jackson.core.JsonProcessingException;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.Tab;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;

import java.io.IOException;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;


public class ManagerController {

    private TicketManagerSystem ticketManagerSystem = TicketManagerSystem.getInstance();

    private Ticket ticketViewTicket;

    private User user;

    private SupportCategory supportCategory;

    @FXML
    private Tab ticketTab;

    @FXML
    private Pane ticketDescriptionPane;

    @FXML
    private Button closeTicketViewButton;

    @FXML
    private Button clearTicketViewButton;

    @FXML
    private Button resolvedTicketViewButton;

    @FXML
    private Button rejectTicketViewButton;

    @FXML
    private Button saveTicketViewButton;

    @FXML
    private ComboBox<User> assignedToTicketViewComboBox;

    @FXML
    private TextField clientTicketViewTextField;

    @FXML
    private TextField timeWorkedTicketViewTextField;

    @FXML
    private TextField titleTicketViewTextField;

    @FXML
    private TextArea descriptionTicketViewTextArea;

    @FXML
    private TextArea resolutionTicketViewTextArea;

    @FXML
    private Label severityTicketViewLabel;

    @FXML
    private Label supportCategoryTicketViewLabel;

    @FXML
    private ComboBox<Status> statusTicketViewComboBox;

    @FXML
    private ComboBox<Integer> priorityTicketViewComboBox;

    @FXML
    private DatePicker closedTicketViewLabel;

    @FXML
    private DatePicker openDateTicketViewLabel;

    @FXML
    private TableView<ResolutionUpdate> ticketResolutionUpdateTicketViewTableView;

    @FXML
    private Text messageTicketViewLabel;

    @FXML
    private ListView<Ticket> ticketListView;

    @FXML
    private TextField titleTicketSearchTextField;

    @FXML
    private TextField descriptionTicketSearchTextField;

    @FXML
    private TextField resolutionTicketSearchTextField;

    @FXML
    private ComboBox<Integer> priorityTicketSearchComboBox;

    @FXML
    private ComboBox<Severity> severityTicketSearchComboBox;

    @FXML
    private ComboBox<Status> statusTicketSearchComboBox;

    @FXML
    private Button searchTicketSearchButton;

    @FXML
    private Button addNewTicketButton;

    @FXML
    private DatePicker fromCloseDateTicketSearchDatePicker;

    @FXML
    private DatePicker toCloseDateTicketSearchDatePicker;

    @FXML
    private DatePicker fromOpenDateTicketSearchDatePicker;

    @FXML
    private DatePicker toOpenDateTicketSearchDatePicker;

    @FXML
    private ComboBox<User> assignedToTicketSearchComboBox;

    @FXML
    private ComboBox<Client> clientTicketSearchComboBox;

    @FXML
    private ComboBox<SupportCategory> supportCategoryTicketSearchComboBox;

    @FXML
    private Pane ticketDescriptionPane2111;

    @FXML
    private Button clearButton1111;

    @FXML
    private Button saveButton1111;

    @FXML
    private ListView<?> ticketListView2111;

    @FXML
    private TextField srchBar2111;

    @FXML
    private Label firstNameMyAccountLabel;

    @FXML
    private Label lastNameMyAccountLabel;

    @FXML
    private Label usernameMyAccountLabel;

    @FXML
    private Label emailMyAccountLabel;

    @FXML
    private Label roleMyAccountLabel;

    @FXML
    private TextField newPasswordMyAccountTextField;

    @FXML
    private TextField currentPasswordMyAccountTextField;

    @FXML
    private Text userText;

    @FXML
    private Button logoutButton;

    @FXML
    void initialize(){
        userText.setText(ticketManagerSystem.getUser().getName() + " " + ticketManagerSystem.getUser().getLastName());
        priorityTicketViewComboBox.setItems(FXCollections.observableArrayList(0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10));
        priorityTicketSearchComboBox.setItems(FXCollections.observableArrayList(0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10));
        ticketListView.setItems(FXCollections.observableArrayList(ticketManagerSystem.getTicketManager().getAll()));
        statusTicketSearchComboBox.setItems((FXCollections.observableArrayList(ticketManagerSystem.getStatusManager().getAll())));
        assignedToTicketSearchComboBox.setItems(FXCollections.observableArrayList(ticketManagerSystem.getUserManager().getAll()));
        supportCategoryTicketSearchComboBox.setItems(FXCollections.observableArrayList(ticketManagerSystem.getSupportCategoryManager().getAll()));
        statusTicketViewComboBox.setItems(FXCollections.observableArrayList(ticketManagerSystem.getStatusManager().getAll()));
        assignedToTicketViewComboBox.setItems(FXCollections.observableArrayList(ticketManagerSystem.getUserManager().getAll()));
        firstNameMyAccountLabel.setText(ticketManagerSystem.getUser().getName());
        lastNameMyAccountLabel.setText(ticketManagerSystem.getUser().getLastName());
        usernameMyAccountLabel.setText(ticketManagerSystem.getUser().getName());
        emailMyAccountLabel.setText(ticketManagerSystem.getUser().getEmail());
        roleMyAccountLabel.setText(ticketManagerSystem.getUser().getRoles().toString());
    }
    @FXML
    private void onListViewClicked(MouseEvent event){
        ticketViewTicket = ticketListView.getSelectionModel().getSelectedItem();
        loadTicketViewTicketInfo();
    }
    @FXML
    private void loadTicketViewTicketInfo(){
        titleTicketViewTextField.setText(ticketViewTicket.getTitle());
        descriptionTicketViewTextArea.setText(ticketViewTicket.getDescription());
        resolutionTicketSearchTextField.setText(ticketViewTicket.getResolution());
        statusTicketViewComboBox.getSelectionModel().select(ticketViewTicket.getStatus());
        severityTicketViewLabel.setText(ticketViewTicket.getSeverity().toString());
        priorityTicketViewComboBox.getSelectionModel().select(ticketViewTicket.getPriority());
        assignedToTicketViewComboBox.getSelectionModel().select(ticketViewTicket.getAssigned_to());
        clientTicketViewTextField.setText(ticketViewTicket.getClient().toString());
        supportCategoryTicketViewLabel.setText(ticketViewTicket.getSupportCategory().getName());
        Instant instant = Instant.ofEpochMilli(ticketViewTicket.getOpenDate().getTime());
        LocalDateTime localDateTime = LocalDateTime.ofInstant(instant, ZoneId.systemDefault());
        LocalDate localDate = localDateTime.toLocalDate();
        openDateTicketViewLabel.setValue(localDate);
    }
    @FXML
    private void oncloseTicketViewButtonPressed(){
        int id = 3;
        Status closedStatus = ticketManagerSystem.getStatusManager().get(id);
        //statusTicketViewComboBox.getSelectionModel().select(ticketViewTicket.setStatus(closedStatus));
        statusTicketViewComboBox.setValue(closedStatus);
    }
    @FXML
    private void onclearTicketViewButtonPressed(){
        System.out.println("Clear Ticket View Button Pressed");
        timeWorkedTicketViewTextField.clear();
        titleTicketViewTextField.clear();
        descriptionTicketViewTextArea.clear();
        resolutionTicketViewTextArea.clear();
        //assignedToTicketViewComboBox.getSelectionModel().se
        //assignedToTicketViewComboBox.getSelectionModel().selectFirst();
        //statusTicketViewComboBox.getSelectionModel().selectFirst();
        //priorityTicketViewComboBox.getSelectionModel().selectFirst();
    }
    @FXML
    private void onresolvedTicketViewButtonPressed(){
        int id = 4;
        Status resolvedStatus = ticketManagerSystem.getStatusManager().get(id);
        statusTicketViewComboBox.setValue(resolvedStatus);
    }
    @FXML
    private void onrejectTicketViewButtonPressed(){

    }
    @FXML
    private void onsaveTicketViewButtonPressed() throws JsonProcessingException {
        ticketViewTicket.setTitle(titleTicketViewTextField.getText());
        ticketViewTicket.setDescription(descriptionTicketViewTextArea.getText());
        ticketViewTicket.setResolution(resolutionTicketViewTextArea.getText());
        ticketViewTicket.setStatus(statusTicketViewComboBox.getValue());
        ticketViewTicket.setPriority(priorityTicketViewComboBox.getValue());

        Ticket updatedTicket = ticketManagerSystem.getTicketManager().add(ticketViewTicket);
        ResolutionUpdate resolutionUpdate = new ResolutionUpdate();
        resolutionUpdate.setId(0);
        resolutionUpdate.setTimeSpent(timeWorkedTicketViewTextField.getText());
        resolutionUpdate.setUser(ticketManagerSystem.getUser());
        resolutionUpdate.setTicket(updatedTicket);
        ticketManagerSystem.getResolutionUpdateManager().add(resolutionUpdate);
        System.out.println("TICKET____");
        System.out.println("GOT HERE");
    }
    @FXML
    private void onsearchTicketSearchButtonPressed(){

    }
    @FXML
    private void onlogoutButtonPressed()throws IOException{
        System.out.println("Logout Button Pressed");
        ticketManagerSystem.logout();
        CSWT.changeScene("login");
    }
    @FXML
    private void onsaveMyAccountButtonPressed(){
        user.setPassword(newPasswordMyAccountTextField.getText());

    }

}
