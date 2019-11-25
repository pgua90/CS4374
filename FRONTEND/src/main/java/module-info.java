module CSWT {
    requires javafx.controls;
    requires javafx.fxml;
    requires spring.web;
    requires com.fasterxml.jackson.core;
    requires com.fasterxml.jackson.databind;
    requires spring.core;
    requires spring.security.crypto;
    requires java.sql;

    opens CSWT to javafx.fxml;
    opens CSWT.controllers;
    opens TicketManagerSystem to CSWT;
    exports CSWT;
    exports TicketManagerSystem;
    exports CSWT.controllers;
}