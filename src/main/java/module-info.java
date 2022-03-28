module com.example.bd {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires mysql.connector.java;


    opens com.example.bd to javafx.fxml;
    exports com.example.bd;
}