package CSWT.controllers;

import CSWT.CSWT;
import TicketManagerSystem.*;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.client.HttpClientErrorException;

import java.io.IOException;

public class LoginController {
    private TicketManagerSystem ticketManagerSystem = TicketManagerSystem.getInstance();

    @FXML
    TextField usernameTextField;

    @FXML
    TextField passwordTextField;

    @FXML
    Text statusText;

    @FXML
    Button loginButton;

    @FXML
    private void loginPressed() throws IOException {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

        loginButton.setDisable(true);
        try{
            String newPassword = passwordTextField.getText();
            System.out.println(ticketManagerSystem.login(usernameTextField.getText(), newPassword));
        }
        catch (HttpClientErrorException.Unauthorized e){
            statusText.setText("Authentication Failure");
            System.out.println(e);
        }
        loginButton.setDisable(false);
        if (ticketManagerSystem.getUser() != null) {
            User user = ticketManagerSystem.getUser();
            switch (user.getRoles().iterator().next().getRole()) {
                case "TICKET_ADMIN":
                    CSWT.changeScene("ticket_admin");
                    break;
                case "DEPARTMENT_SYSADMIN":
                    CSWT.changeScene("department_sys_admin");
                    break;
                case "MANAGER":
                    CSWT.changeScene("manager");
                    break;
                case "STUDENT_SUPPORT":
                    CSWT.changeScene("student_support");
                    break;
            }
        }
        System.out.println(usernameTextField.getText());
        System.out.println(passwordTextField.getText());
    }
}
