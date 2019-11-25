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

public class StudentSupportController {

    public TextField newPasswordMyAccountTextField;
    public TextField currentPasswordMyAccountTextField;
    public Text firstNameMyAccountLabel;
    public Text lastNameMyAccountLabel;
    public Text usernameMyAccountLabel;
    public Text emailMyAccountLabel;
    public Text roleMyAccountLabel;
    public Button saveMyAccountButton;
    private TicketManagerSystem ticketManagerSystem = TicketManagerSystem.getInstance();

    private Ticket ticketViewTicket;

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
    private Label assignedToTicketViewLabel;

    @FXML
    private Label clientTicketViewLabel;

    @FXML
    private TextField timeWorkedTicketViewTextField;

    @FXML
    private Label titleTicketViewLabel;

    @FXML
    private TextArea descriptionTicketViewTextField;

    @FXML
    private TextArea resolutionTicketViewTextField;

    @FXML
    private Label severityTicketViewLabel;

    @FXML
    private ComboBox<Status> statusTicketViewComboBox;

    @FXML
    private Label priorityTicketViewLabel;

    @FXML
    private Label closedTicketViewLabel;

    @FXML
    private Label openDateTicketViewLabel;

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
    private Text userText;

    @FXML
    private Button logoutButton;

    @FXML
    private Label supportCategoryTicketViewLabel;

    @FXML
    void initialize() {
        loadMyAccount();
        userText.setText(ticketManagerSystem.getUser().getName() + " " + ticketManagerSystem.getUser().getLastName());
        //priorityTicketViewLabel.setId(FXCollections.observableArrayList(0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10));
        priorityTicketSearchComboBox.setItems(FXCollections.observableArrayList(0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10));
        ticketListView.setItems(FXCollections.observableArrayList(ticketManagerSystem.getTicketManager().getAll()));
        statusTicketSearchComboBox.setItems(FXCollections.observableArrayList(ticketManagerSystem.getStatusManager().getAll()));
        severityTicketSearchComboBox.setItems(FXCollections.observableArrayList(ticketManagerSystem.getSeverityManager().getAll()));
//        priorityTicketSearchComboBox.setItems(FXCollections.observableArrayList(ticketManagerSystem.getP().getAll()));
        assignedToTicketSearchComboBox.setItems(FXCollections.observableArrayList(ticketManagerSystem.getUserManager().getAll()));
        clientTicketSearchComboBox.setItems(FXCollections.observableArrayList(ticketManagerSystem.getClientManager().getAll()));
        supportCategoryTicketSearchComboBox.setItems(FXCollections.observableArrayList(ticketManagerSystem.getSupportCategoryManager().getAll()));
        statusTicketViewComboBox.setItems(FXCollections.observableArrayList(ticketManagerSystem.getStatusManager().getAll()));

        //severityTicketViewComboBox.setItems(FXCollections.observableArrayList(ticketManagerSystem.getSeverityManager().getAll()));
//        priorityTicketViewComboBox.setItems(FXCollections.observableArrayList(ticketManagerSystem.g().getAll()));
        //assignedToTicketViewComboBox.setItems(FXCollections.observableArrayList(ticketManagerSystem.getUserManager().getAll()));
        //clientTicketViewComboBox.setItems(FXCollections.observableArrayList(ticketManagerSystem.getClientManager().getAll()));
    }

    @FXML
    private void onListViewClicked(MouseEvent event) {
        ticketViewTicket = ticketListView.getSelectionModel().getSelectedItem();
        loadTicketViewTicketInfo();
        // reload ticketViewInfo
    }

    private void loadTicketViewTicketInfo() {
        titleTicketViewLabel.setText(ticketViewTicket.getTitle());
        descriptionTicketViewTextField.setText(ticketViewTicket.getDescription());
        resolutionTicketViewTextField.setText(ticketViewTicket.getResolution());
        statusTicketViewComboBox.getSelectionModel().select(ticketViewTicket.getStatus());
        severityTicketViewLabel.setText(ticketViewTicket.getSeverity().getName());
        priorityTicketViewLabel.setText(ticketViewTicket.getPriority() + "");
        supportCategoryTicketViewLabel.setText(ticketViewTicket.getSupportCategory().toString());
        clientTicketViewLabel.setText(ticketViewTicket.getClient().getFirstName() + " " + ticketViewTicket.getClient().getFirstName());
        assignedToTicketViewLabel.setText(ticketViewTicket.getAssigned_to().getName() + " " +ticketViewTicket.getAssigned_to().getLastName());
//        Instant instant = Instant.ofEpochMilli(ticketViewTicket.getOpenDate().getTime());
//        LocalDateTime localDateTime = LocalDateTime.ofInstant(instant, ZoneId.systemDefault());
//        LocalDate localDate = localDateTime.toLocalDate();
        // TODO check if null
        openDateTicketViewLabel.setText(ticketViewTicket.getOpenDate().toString());
        closedTicketViewLabel.setText(ticketViewTicket.getCloseDate().toString());

    }

    @FXML
    private void loadMyAccount() {
        User user = ticketManagerSystem.getUser();
        emailMyAccountLabel.setText(user.getEmail());
        firstNameMyAccountLabel.setText(user.getName());
        lastNameMyAccountLabel.setText(user.getLastName());
        roleMyAccountLabel.setText(user.getRoles().iterator().next().getRole());
        usernameMyAccountLabel.setText(user.getName());
    }

    private void oncloseTicketViewButtonPressed(){

    }

    @FXML
    private void onsaveMyAccountButtonPressed() throws JsonProcessingException {
        // TODO handle exception
        User user = ticketManagerSystem.getUser();
        user.setPassword(newPasswordMyAccountTextField.getText());
        ticketManagerSystem.getUserManager().update(user);
    }

    private void onclearTicketViewButtonPressed(){
        System.out.println("Clear Ticket View Button Pressed");
        timeWorkedTicketViewTextField.clear();
        descriptionTicketSearchTextField.clear();
        resolutionTicketViewTextField.clear();
//        statusTicketViewComboBox.;

    }

    private void onresolvedTicketViewButtonPressed(){

    }

    private void onrejectTicketViewButtonPressed(){

    }

    @FXML
    private void onsaveTicketViewButtonPressed() throws JsonProcessingException {
        System.out.println("in saveticket pressed");
        ticketViewTicket.setDescription(descriptionTicketViewTextField.getText());
        ticketViewTicket.setResolution(resolutionTicketViewTextField.getText());
        ticketViewTicket.setStatus(statusTicketViewComboBox.getValue());

//        SupportCategory sc = new SupportCategory();
//        sc.setId(1);
 //       sc.setIn_use(true);
  //      sc.setName("Instructional Lab");
//         ticketViewTicket.setId(1);
 //      ticketViewTicket.setSupportCategory(sc);
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

    private void onsearchTicketSearchButtonPressed(){

    }

    private void onaddNewTicketButtonPressed(){

    }

    @FXML
    private void onlogoutButtonPressed() throws IOException {
        // TODO handle exception
        CSWT.changeScene("login");
        ticketManagerSystem.logout();

    }


}
