module com.example.oop32022682062jelinekfrontend {
    requires javafx.controls;
    requires javafx.fxml;
    requires domain;


    opens com.example.oop32022682062jelinekfrontend to javafx.fxml;
    exports com.example.oop32022682062jelinekfrontend;
}