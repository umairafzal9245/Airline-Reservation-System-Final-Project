module GUIDependencies {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires eu.hansolo.tilesfx;
    requires javafx.graphics;
    requires java.sql;

    opens GUIcode to javafx.fxml;
    exports GUIcode;
    opens BusinessLogic to javafx.base;
    exports GUIcode.AdminGUI;
    opens GUIcode.AdminGUI to javafx.fxml;
    exports GUIcode.CustomerGUI;
    opens GUIcode.CustomerGUI to javafx.fxml;
}