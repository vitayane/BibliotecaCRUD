module BibliotecaCRUD {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;

    opens controller to javafx.fxml;
    opens model to javafx.base;

    exports application;
}

