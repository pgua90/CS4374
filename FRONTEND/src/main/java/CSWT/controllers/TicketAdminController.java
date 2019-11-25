package CSWT.controllers;

import CSWT.AppUtils;
import CSWT.CSWT;
import TicketManagerSystem.*;
import com.fasterxml.jackson.core.JsonProcessingException;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;

import java.io.IOException;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

public class TicketAdminController {

    private TicketManagerSystem ticketManagerSystem = TicketManagerSystem.getInstance();
    Alert alert = new Alert(Alert.AlertType.NONE);



    //////////////////////////////////////////////////////////////////////////////////////
    // LISTENERS
    //////////////////////////////////////////////////////////////////////////////////////

    private Ticket ticketViewTicket;
    private User userViewUser;
    private Client clientViewClient;
    private SupportCategory supportCategoryViewSupportCategory;
    private Status statusViewStatus;
    private Severity severityViewSeverity;

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
    private ComboBox<Client> clientTicketViewComboBox;

    @FXML
    private TextField timeWorkedTicketViewTextField;

    @FXML
    private TextField titleTicketViewTextField;

    @FXML
    private TextArea descriptionTicketViewTextField;

    @FXML
    private TextArea resolutionTicketViewTextField;

    @FXML
    private ComboBox<Severity> severityTicketViewComboBox;

    @FXML
    private ComboBox<Status> statusTicketViewComboBox;

    @FXML
    private ComboBox<Integer> priorityTicketViewComboBox;

    @FXML
    private DatePicker closedTicketViewLabel;

    @FXML
    private DatePicker openDateTicketViewLabel;

    @FXML
    private TableView<Ticket> ticketResolutionUpdateTicketViewTableView;

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
    private Pane ticketDescriptionPane1;

    @FXML
    private Label AccountLabelName;

    @FXML
    private Label AccountLabelRole;

    @FXML
    private Label AccountLabelID;

    @FXML
    private Label AccountLabelEmail;

    @FXML
    private Label AccountLabelPhone;

    @FXML
    private Label AccountLabelUsername;

    @FXML
    private Label AccountLabelPassword;

    @FXML
    private TextField firstNameUserViewTextField;

    @FXML
    private TextField idUserViewTextField;

    @FXML
    private TextField emailUserViewTextField;

    @FXML
    private TextField numberUserViewTextField;

    @FXML
    private TextField usernameUserViewTextField;

    @FXML
    private Button saveUserViewButton;

    @FXML
    private Button removeUserViewButton;

    @FXML
    private PasswordField passwordUserViewTextField;

    @FXML
    private TextField lastNameUserViewTextField;

    @FXML
    private ComboBox<Role> roleUserViewComboBox;

    @FXML
    private Label AccountLabelPassword1;

    @FXML
    private PasswordField confirmPasswordUserViewTextField1;

    @FXML
    private ListView<User> userListView;

    @FXML
    private TextField firstNameUserSearchTextField;

    @FXML
    private TextField lastNameUserSearchTextField;

    @FXML
    private TextField idUserSearchTextField;

    @FXML
    private TextField emailUserSearchTextField;

    @FXML
    private TextField numberUserSearchTextField;

    @FXML
    private ComboBox<Role> roleUserSearchComboBox;

    @FXML
    private Button searchUserSearchButton;

    @FXML
    private Button addNewUserButton;

    @FXML
    private ListView<Client> clientListView;

    @FXML
    private Label AccountLabelName1;

    @FXML
    private Label AccountLabelEmail1;

    @FXML
    private Label AccountLabelPhone1;

    @FXML
    private TextField lastNameClientViewTextField;

    @FXML
    private TextField emailClientViewTextField;

    @FXML
    private TextField numberClientViewTextField;

    @FXML
    private Button saveClientViewButton;

    @FXML
    private Button removeClientViewButton;

    @FXML
    private TextField firstNameClientViewTextField;

    @FXML
    private TextField firstNameClientSearchTextField;

    @FXML
    private TextField lastNameClientSearchTextField;

    @FXML
    private TextField idClientSearchTextField;

    @FXML
    private TextField emailClientSearchTextField;

    @FXML
    private TextField numberClientSearchTextField;

    @FXML
    private Button searchClientSearchButton;

    @FXML
    private Button addNewClientButton;

    @FXML
    private Pane ticketDescriptionPane2111;

    @FXML
    private Button removeSupportCategoryViewButton;

    @FXML
    private Button saveSupportCateogryViewButton;

    @FXML
    private TextField titleSupportCategoryViewTextField;

    @FXML
    private ListView<SupportCategory> supportCategoryListView;

    @FXML
    private TextField titleSupportCategorySearchTextField;

    @FXML
    private Button searchSupportCategorySearchButton;

    @FXML
    private Button addNewSupportCategoryButton;

    @FXML
    private Button removeStatusViewButton;

    @FXML
    private TextField nameStatusViewTextField;

    @FXML
    private Button saveStatusViewButton;

    @FXML
    private ListView<Status> statusListView;

    @FXML
    private TextField nameStatusSearchTextField;

    @FXML
    private Button searchStatusSearchTextField;

    @FXML
    private Button addNewStatusButton;

    @FXML
    private Pane ticketDescriptionPane211;

    @FXML
    private Button removeSeverityViewButton;

    @FXML
    private TextField nameSeverityViewTextField;

    @FXML
    private Button saveSeverityViewButton;

    @FXML
    private ListView<Severity> severityListView;

    @FXML
    private TextField nameSeveritySaerchTextField;

    @FXML
    private Button searchSeveritySearchTextField;

    @FXML
    private Button addNewSeverityButton;

    @FXML
    private Text userText;

    @FXML
    private Button logoutButton;

    @FXML
    void initialize() {
        userText.setText(ticketManagerSystem.getUser().getName() + " " + ticketManagerSystem.getUser().getLastName());
        priorityTicketViewComboBox.setItems(FXCollections.observableArrayList(0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10));
        priorityTicketSearchComboBox.setItems(FXCollections.observableArrayList(0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10));
        ticketListView.setItems(FXCollections.observableArrayList(ticketManagerSystem.getTicketManager().getAll()));
        statusTicketSearchComboBox.setItems(FXCollections.observableArrayList(ticketManagerSystem.getStatusManager().getAll()));
        severityTicketSearchComboBox.setItems(FXCollections.observableArrayList(ticketManagerSystem.getSeverityManager().getAll()));
//        priorityTicketSearchComboBox.setItems(FXCollections.observableArrayList(ticketManagerSystem.getP().getAll()));
        assignedToTicketSearchComboBox.setItems(FXCollections.observableArrayList(ticketManagerSystem.getUserManager().getAll()));
        clientTicketSearchComboBox.setItems(FXCollections.observableArrayList(ticketManagerSystem.getClientManager().getAll()));
        supportCategoryTicketSearchComboBox.setItems(FXCollections.observableArrayList(ticketManagerSystem.getSupportCategoryManager().getAll()));
        statusTicketViewComboBox.setItems(FXCollections.observableArrayList(ticketManagerSystem.getStatusManager().getAll()));
        severityTicketViewComboBox.setItems(FXCollections.observableArrayList(ticketManagerSystem.getSeverityManager().getAll()));
//        priorityTicketViewComboBox.setItems(FXCollections.observableArrayList(ticketManagerSystem.g().getAll()));
        assignedToTicketViewComboBox.setItems(FXCollections.observableArrayList(ticketManagerSystem.getUserManager().getAll()));
        clientTicketViewComboBox.setItems(FXCollections.observableArrayList(ticketManagerSystem.getClientManager().getAll()));
        //////////////////////////////////////////////////////////////////////////////////////
        // Status Tab
        //////////////////////////////////////////////////////////////////////////////////////
        statusListView.setItems(FXCollections.observableArrayList(ticketManagerSystem.getStatusManager().getAll()));
        //severityListView.setItems(FXCollections.observableArrayList(ticketManagerSystem.get))

    }

    @FXML
    private void onListViewClicked(MouseEvent event) {
        ticketViewTicket = ticketListView.getSelectionModel().getSelectedItem();
        loadTicketViewTicketInfo();
        // reload ticketViewInfo
    }

    private void loadTicketViewTicketInfo() {
        titleTicketViewTextField.setText(ticketViewTicket.getTitle());
        descriptionTicketViewTextField.setText(ticketViewTicket.getDescription());
        resolutionTicketViewTextField.setText(ticketViewTicket.getResolution());
        statusTicketViewComboBox.getSelectionModel().select(ticketViewTicket.getStatus());
        severityTicketViewComboBox.getSelectionModel().select(ticketViewTicket.getSeverity());
        priorityTicketViewComboBox.getSelectionModel().select(ticketViewTicket.getPriority());
        assignedToTicketViewComboBox.getSelectionModel().select(ticketViewTicket.getAssigned_to());
        clientTicketViewComboBox.getSelectionModel().select(ticketViewTicket.getClient());
        Instant instant = Instant.ofEpochMilli(ticketViewTicket.getOpenDate().getTime());
        LocalDateTime localDateTime = LocalDateTime.ofInstant(instant, ZoneId.systemDefault());
        LocalDate localDate = localDateTime.toLocalDate();
        openDateTicketViewLabel.setValue(localDate);

        LocalDateTime localDateTimeClose = LocalDateTime.ofInstant(Instant.ofEpochMilli(ticketViewTicket.getCloseDate().getTime()), ZoneId.systemDefault());
        LocalDate localDateClose = localDateTimeClose.toLocalDate();
        closedTicketViewLabel.setValue(localDateClose);



    }

    //////////////////////////////////////////////////////////////////////////////////////
    // FUNCTIONS START
    //////////////////////////////////////////////////////////////////////////////////////

    //////////////////////////////////////////////////////////////////////////////////////
    //TICKET FUNCTIONALITY
    //////////////////////////////////////////////////////////////////////////////////////
    @FXML
    private void oncloseTicketViewButtonPressed(){
        System.out.println("Close Ticket Button Pressed");
    }
    @FXML
    private void onclearTicketViewButtonPressed(){
        System.out.println("Clear Ticket View Button Pressed");
        timeWorkedTicketViewTextField.clear();
        titleTicketViewTextField.clear();
        descriptionTicketSearchTextField.clear();
        resolutionTicketViewTextField.clear();
    }
    @FXML
    private void onresolvedTicketViewButtonPressed(){
        System.out.println("Resolved Ticket View Button Pressed");

        if(AppUtils.validifyComboBox(statusTicketViewComboBox)){

        }
    }
    @FXML
    private void onrejectTicketViewButtonPressed(){
        System.out.println("Reject Ticket View Button Pressed");
        //Need to add reject status
        if(AppUtils.validifyComboBox(statusTicketViewComboBox)){
            String currentTicketName = titleTicketViewTextField.getText();
            //Was currently trying to retrieve ticket information to update the ticket's status to Reject.
            //I know the ticket ID goes here, and was trying to get ticket info through this to update...
           // TicketManagerSystem.getInstance().getTicketManager().update();

        }

    }
    /*The Add functionality will need to rely on another method in the TicketManager class to be able to add a new ticket
    of which I did add one, but its a works in progress, I named it "addTicket".
    * */
    //Was currently printing out the stuff I was trying to get from the fields, to make sure I was getting them.
    //I know we need to set a condition for Closed Date where its okay if its not required...
    //Prints are sort of like placeholders as well, meanwhile I was working on the add function to send the information over to be addeded to the DB.
    @FXML
    private void onsaveTicketViewButtonPressed() throws JsonProcessingException {
//        System.out.println("Save Ticket View Button Pressed");
//        Ticket ticket = new Ticket();
//
//        if(AppUtils.validifyTextField(clientTicketViewTextField, timeWorkedTicketViewTextField, titleTicketViewTextField)){
//            System.out.println("What's the ticket name?" + titleTicketViewTextField.getText());
//            System.out.println("What's the time worked?" + timeWorkedTicketViewTextField.getText());
//            System.out.println("What's the client name?" + clientTicketViewTextField.getText());
//            //ticket.setTitle(titleTicketViewTextField.getText());
//            //Need to figure out how to add Client
//            //Need to figure out how to add timeWorked (create function where OpenDate - Current Date, stop counter when ticket is closed status)
//        }
//        if(AppUtils.validifyTextArea(descriptionTicketViewTextField,resolutionTicketViewTextField)){
//            System.out.println("What is description" + descriptionTicketSearchTextField.getText());
//            System.out.println("What is resoultion" + resolutionTicketViewTextField.getText());
//        }
//        if(AppUtils.validifyComboBox(severityTicketViewComboBox,statusTicketViewComboBox,priorityTicketViewComboBox)){
//            System.out.println("What is severity" + severityTicketViewComboBox.getValue());
//            System.out.println("What is status" + statusTicketViewComboBox.getValue());
//            System.out.println("What is priority" + priorityTicketViewComboBox.getValue());
//        }
//        if(AppUtils.validifyDatePicker(openDateTicketViewLabel)){
//            System.out.println("What is open date" + openDateTicketViewLabel.getAccessibleText());
//        }
//        if(AppUtils.validifyDatePicker(closedTicketViewLabel)){
//            System.out.println("What is closed date" + closedTicketViewLabel.getAccessibleText());
//        }
//        System.out.println("END SAVE NEW TICKET BUTTON PRESSED");
        ticketViewTicket.setTitle(titleTicketViewTextField.getText());
        ticketViewTicket.setDescription(descriptionTicketViewTextField.getText());
        ticketViewTicket.setResolution(resolutionTicketViewTextField.getText());
        ticketViewTicket.setStatus(statusTicketViewComboBox.getValue());
        ticketViewTicket.setSeverity(severityTicketViewComboBox.getValue());
        ticketViewTicket.setPriority(priorityTicketViewComboBox.getValue());
        ticketViewTicket.setClient(clientTicketViewComboBox.getValue());
        ticketViewTicket.setAssigned_to(assignedToTicketViewComboBox.getValue());
        ticketViewTicket.setOpenDate(Date.from(openDateTicketViewLabel.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant()));
        ticketViewTicket.setCloseDate(Date.from(closedTicketViewLabel.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant()));
        SupportCategory sc = new SupportCategory();
        sc.setId(1);
        sc.setIn_use(true);
        sc.setName("Instructional Lab");
//        ticketViewTicket.setId(0);
        ticketViewTicket.setSupportCategory(sc);
        ticketManagerSystem.getTicketManager().add(ticketViewTicket);
        System.out.println("GOT HERE");
    }
    @FXML
    private void onsearchTicketSearchButtonPressed(){
        System.out.println("Search Ticket Search Button Pressed");
    }


    @FXML
    private void onaddNewTicketButtonPressed(){
        System.out.println("Add New Ticket Button Pressed");
        Ticket ticket = new Ticket();
        //Coming to the thought that if the user was saved, then it will be added if the user is new.
        //For add a new user, the thought came that it will simply clear the fields for a new user to be added.
    }
    //////////////////////////////////////////////////////////////////////////////////////
    //USER FUNCTIONALITY
    //////////////////////////////////////////////////////////////////////////////////////
    @FXML
    private void onsaveUserViewButtonPressed(){
        System.out.println("Save User View Button Pressed");
        if(AppUtils.validifyTextField(firstNameUserViewTextField,idUserViewTextField,emailUserViewTextField,numberUserViewTextField,usernameUserViewTextField)){

        }
        //Need to add validfy for a PasswordField... unless it works the same as a textfield
        if(AppUtils.validifyComboBox(roleUserViewComboBox)){

        }
    }
    @FXML
    private void onremoveUserViewButton(){
        System.out.println("Remove user View Button Pressed");
        //Could we use an alert to confirm the deletion of a user
        alert.setAlertType(Alert.AlertType.WARNING);
    }
    @FXML
    private void onsearchUserSearchButtonPressed(){
        System.out.println("Search User Search Button Pressed");
    }
    @FXML
    private void onaddNewUserButtonPressed(){
        System.out.println("Add New User Button Pressed");
        AppUtils.clearTextFields(firstNameUserViewTextField,lastNameUserViewTextField,idUserViewTextField,emailUserViewTextField,numberUserViewTextField,usernameUserViewTextField,passwordUserViewTextField);
    }

    //////////////////////////////////////////////////////////////////////////////////////
    //CLIENT FUNCTIONALITY
    //////////////////////////////////////////////////////////////////////////////////////
    @FXML
    private void onsaveClientViewButtonPressed(){
        System.out.println("Save Client View Button Pressed");
    }
    @FXML
    private void onremoveClientViewButtonPressed(){
        System.out.println("Remove Client View Button Pressed");
        alert.setAlertType(Alert.AlertType.WARNING);
    }
    @FXML
    private void onsearchClientSearchButtonPressed(){
        System.out.println("Search Client Search Button Pressed");
    }
    @FXML
    private void onaddNewClientButtonPressed(){
        System.out.println("Add New Client Button Pressed");
        AppUtils.clearTextFields(firstNameClientViewTextField,lastNameClientViewTextField,emailClientViewTextField,numberClientViewTextField);
    }
    //////////////////////////////////////////////////////////////////////////////////////
    //SUPPORT CATEGORY FUNCTIONALITY
    //////////////////////////////////////////////////////////////////////////////////////
    @FXML
    private void onremoveSupportCategoryViewButtonPressed(){
        System.out.println("Remove Support Category View Button Pressed");
        alert.setAlertType(Alert.AlertType.WARNING);
    }
    @FXML
    private void onsaveSupportCateogryViewButtonPressed(){
        System.out.println("Save Support Category View Button Pressed");
    }
    @FXML
    private void onsearchSupportCategorySearchButtonPressed(){
        System.out.println("Search Support Category Search Button Pressed");
    }
    @FXML
    private void onaddNewSupportCategoryButtonPressed(){
        System.out.println("Add New Support Category Button Pressed");
        AppUtils.clearTextFields(titleSupportCategoryViewTextField);
    }
    //////////////////////////////////////////////////////////////////////////////////////
    //STATUS FUNCTIONALITY
    //////////////////////////////////////////////////////////////////////////////////////
    @FXML
    private void onremoveStatusViewButtonPressed(){
        System.out.println("Remove Status View Button Pressed");
        alert.setAlertType(Alert.AlertType.WARNING);
    }
    @FXML
    private void onsaveStatusViewButtonPressed(){
        System.out.println("Save Status View Button Pressed");
    }
    @FXML
    private void onsearchStatusSearchTextFieldPressed(){
        System.out.println("Search Status Search TextField Pressed");
        nameStatusSearchTextField.getText();

    }
    @FXML
    private void onaddNewStatusButtonPressed(){
        System.out.println("Add New Status Button Pressed");
        AppUtils.clearTextFields(nameStatusViewTextField);
    }

    //////////////////////////////////////////////////////////////////////////////////////
    //SEVERITY FUNCTIONALITY
    //////////////////////////////////////////////////////////////////////////////////////
    @FXML
    private void onremoveSeverityViewButtonPressed(){
        System.out.println("Remove Severity Button Pressed");
        alert.setAlertType(Alert.AlertType.WARNING);
    }
    @FXML
    private void onsaveSeverityViewButtonPressed(){
        System.out.println("Save Severity View Button Pressed");
    }
    @FXML
    private void onsearchSeveritySearchTextFieldPressed(){
        System.out.println("Search Severity Search TextField Pressed");
    }
    @FXML
    private void onaddNewSeverityButtonPressed(){
        System.out.println("Add New Severity Button Pressed");
        AppUtils.clearTextFields(nameSeverityViewTextField);

    }

    //////////////////////////////////////////////////////////////////////////////////////
    //SAVE ACCOUNT AND LOGOUT FUNCTIONALITY
    //////////////////////////////////////////////////////////////////////////////////////
    @FXML
    private void onlogoutButtonPressed() throws IOException {
        System.out.println("Logout Button Pressed");
        ticketManagerSystem.logout();
        CSWT.changeScene("login");
    }

    @FXML
    private void onsaveMyAccountButtonPressed(){
        System.out.println("Save My Account Button Pressed");
    }


}
