module GUIDependencies {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;
    requires java.naming;
    requires java.persistence;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires eu.hansolo.tilesfx;
    requires javafx.graphics;
    requires java.sql;
    requires org.hibernate.orm.core;
    requires org.hibernate.commons.annotations;

    opens GUIcode to javafx.fxml;
    exports GUIcode;
    opens BusinessLogic to javafx.base, org.hibernate.orm.core;
    exports GUIcode.AdminGUI;
    opens GUIcode.AdminGUI to javafx.fxml;
    exports GUIcode.CustomerGUI;
    opens GUIcode.CustomerGUI to javafx.fxml;
    exports BusinessLogic;
}