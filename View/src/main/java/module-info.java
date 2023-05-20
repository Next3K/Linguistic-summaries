module project {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;
    requires model;
    requires javafx.swing;
    //requires json.simple;
    //requires com.fasterxml.jackson.core;
    //requires com.fasterxml.jackson.databind;
    //requires org.apache.commons.lang3;

    requires org.controlsfx.controls;

    opens project to javafx.fxml;
    exports project;
}