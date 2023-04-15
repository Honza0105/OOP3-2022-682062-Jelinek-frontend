package app;

import cipher.CaesarCipher;
import custom.CustomRSA;
import domain.Message;
import domain.User;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import view.RootLayoutController;
import view.login.LoginController;
import view.mailOverview.MailOverviewFilterOffController;
import view.mailOverview.MailOverviewFilterOnController;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.time.LocalDateTime;

public class Main extends Application {

    private ObservableList<Message> messageObservableList = FXCollections.observableArrayList();

    private ObservableList<Message> crackedMessageObservableList = FXCollections.observableArrayList();

    private static Stage primaryStage;
    private BorderPane rootLayout;

    public void dummyAddition() throws Exception {
        User us = new User("Jack","Jay","Jackson","jack@jackson.com","Jack123","Gery");
        User them = new User("Penolope","Marie","Decker","pen@envelope.nl","Pene01","sdjoi23");

        String superSecretMessage = "It is ready";
        String caesarEncryptedSuperSecretMessage = new CaesarCipher(SecureRandom.getInstanceStrong().nextInt(30)).encrypt(superSecretMessage);
        CustomRSA rsaEncryptedMessage = new CustomRSA(superSecretMessage);

        //has to be encrypted
        //a PUBLIC key can be added to it
        //date will be diff as its receival whatever
        Message message = new Message(them,us, LocalDateTime.now(),"greetings", rsaEncryptedMessage.getEncryptedMessage());
        messageObservableList.add(message);


    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Main.primaryStage = primaryStage;
        primaryStage.setTitle("Outlook wannabe");
        dummyAddition();

        initRootLayout();


        primaryStage.show();

//        showLogIn();
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
            fxmlLoader.setLocation(Main.class.getResource("/view/mailOverview/MailOverviewFilterOff.fxml"));
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
            fxmlLoader.setLocation(Main.class.getResource("/view/mailOverview/MailOverviewFilterOn.fxml"));
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

    public void showLogIn(){
        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(Main.class.getResource("/view/login/Login.fxml"));
            System.out.println(fxmlLoader.getLocation());
            AnchorPane assetOverview = fxmlLoader.load();

            rootLayout.setCenter(assetOverview);
            LoginController controller = fxmlLoader.getController();
            controller.setMain(this);
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }

    public ObservableList<Message> getMessageObservableList() {
        return messageObservableList;
    }

    public static void main(String[] args) {
        launch();
    }
}