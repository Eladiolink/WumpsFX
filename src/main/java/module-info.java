module com.example.wumpsfx {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.wumpsfx to javafx.fxml;
    exports com.example.wumpsfx;
}