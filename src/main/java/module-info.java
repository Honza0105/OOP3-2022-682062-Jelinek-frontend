module com.example.oop32022682062jelinekfrontend {
    requires javafx.controls;
    requires javafx.fxml;
    requires domain;


    opens view to javafx.fxml;
    exports view;
    exports app;

    opens view.mailOverview to javafx.fxml;
    exports view.mailOverview to javafx.fxml;

    opens view.login to javafx.fxml;
    exports view.login to javafx.fxml;
}