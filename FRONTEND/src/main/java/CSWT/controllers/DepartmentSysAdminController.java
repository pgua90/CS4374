package CSWT.controllers;

import TicketManagerSystem.*;
import javafx.collections.ObservableArray;
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
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;

public class DepartmentSysAdminController {

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
    private ComboBox<User> assignedToTicketViewComboBox;

    @FXML
    private TextField clientTicketViewTextField;

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

    private void oncloseTicketViewButtonPressed(){

    }

    private void onclearTicketViewButtonPressed(){

    }

    private void onresolvedTicketViewButtonPressed(){

    }

    private void onrejectTicketViewButtonPressed(){

    }

    private void onsaveTicketViewButtonPressed(){

    }

    private void onsearchTicketSearchButtonPressed(){

    }

    private void onaddNewTicketButtonPressed(){

    }

    private void onlogoutButtonPressed(){

    }


}
