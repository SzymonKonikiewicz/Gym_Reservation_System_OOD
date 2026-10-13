module com.example.mas {
    requires javafx.controls;
    requires javafx.fxml;
    requires jdk.jshell;
    requires jdk.jfr;
    requires java.desktop;
    requires java.sql;


    opens com.example.mas to javafx.fxml;
    exports com.example.mas;
}