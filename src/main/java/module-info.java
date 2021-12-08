module GUIDependencies {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.persistence;

    requires javafx.base;
    requires javafx.graphics;
    requires java.sql;
    requires org.hibernate.orm.core;
    requires java.naming;

    opens GUIcode to javafx.fxml;
    exports GUIcode;
    opens BusinessLogic to javafx.base, org.hibernate.orm.core;
    exports GUIcode.AdminGUI;
    opens GUIcode.AdminGUI to javafx.fxml;
    exports GUIcode.CustomerGUI;
    opens GUIcode.CustomerGUI to javafx.fxml;
    exports BusinessLogic;
}