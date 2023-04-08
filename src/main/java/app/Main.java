package app;

import domain.Message;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import view.RootLayoutController;
import view.mailOverview.MailOverviewFilterOffController;
import view.mailOverview.MailOverviewFilterOnController;

import java.io.IOException;

public class Main extends Application {

    private ObservableList<Message> messageObservableList = FXCollections.observableArrayList();

    private ObservableList<Message> crackedMessageObservableList = FXCollections.observableArrayList();

    private static Stage primaryStage;
    private BorderPane rootLayout;

    @Override
    public void start(Stage primaryStage) throws IOException {
        Main.primaryStage = primaryStage;
        primaryStage.setTitle("Outlook wannabe");

        initRootLayout();


        primaryStage.show();

        showMailOverviewFilterOff();
// to be added
//        showMainWindow();

    }

    private void initRootLayout() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(Main.class.getResource("/view/RootLayout.fxml"));
            rootLayout = fxmlLoader.load();
            Scene scene = new Scene(rootLayout, 1250, 600);
            primaryStage.setScene(scene);

            RootLayoutController controller = fxmlLoader.getController();
//            controller.setMain(this);
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    public void showMailOverviewFilterOff() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(Main.class.getResource("/view/mailOverview/mailOverviewFilterOff.fxml"));
            System.out.println(fxmlLoader.getLocation());
            AnchorPane assetOverview = fxmlLoader.load();

            rootLayout.setCenter(assetOverview);
            MailOverviewFilterOffController controller = fxmlLoader.getController();
            controller.setMain(this);
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }

    public void showMailOverviewFilterOn() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(Main.class.getResource("/view/mailOverview/mailOverviewFilterOn.fxml"));
            System.out.println(fxmlLoader.getLocation());
            AnchorPane assetOverview = fxmlLoader.load();

            rootLayout.setCenter(assetOverview);
            MailOverviewFilterOnController controller = fxmlLoader.getController();
            controller.setMain(this);
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }


    public static void main(String[] args) {
        launch();
    }
}