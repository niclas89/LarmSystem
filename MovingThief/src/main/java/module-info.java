module com.example.movingthief {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.media;
    requires java.desktop;


    opens com.example.movingthief to javafx.fxml;
    exports com.example.movingthief;
}