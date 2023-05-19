package app_front_end;

import cipher.CaesarCipher;
import conf.Settings;
import custom.CustomRSA;
import domain.Message;
import domain.User;
import javafx.application.Application;
import javafx.application.Platform;
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
import java.security.SecureRandom;
import java.time.LocalDateTime;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main extends Application {

    private ObservableList<Message> messageObservableList = FXCollections.observableArrayList();

    private ObservableList<Message> crackedMessageObservableList = FXCollections.observableArrayList();

    private Main main;

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

        String notEncryptedString = "Hi,\nHow are you doing? I am doing great\nI would like to ask you something...\nPlease do not hesitate to contact me soon!";
        Message message1 = new Message(them,us, LocalDateTime.now(),"not encrypted accidently", notEncryptedString);
        messageObservableList.add(message1);


    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Main.primaryStage = primaryStage;
        primaryStage.setTitle("Outlook wannabe");
        dummyAddition();

        // Create a thread pool with two threads
        ExecutorService executor = Executors.newFixedThreadPool(3);

        // Thread 1: Handle the GUI and frontend requests
        executor.execute(() -> {
            try {
                FrontEnd frontEnd = new FrontEnd(main, Settings.FRONT_LISTENER_PORT, Settings.TCP_SERVER_NAME, Settings.BACK_LISTENER_PORT);
                frontEnd.startListening();
//                System.out.println("I do request");
//                frontEnd.requestPull();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });


        // Thread 2: Handle the GUI
        executor.execute(() -> {
            try {
                initRootLayout();
                Platform.runLater(() -> primaryStage.show());
                Platform.runLater(() -> showMailOverviewFilterOff());
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        // Thread 3: Handle the GUI and frontend requests
        executor.execute(() -> {
            try {
                FrontEnd request = new FrontEnd(main, Settings.FRONT_LISTENER_PORT, Settings.TCP_SERVER_NAME, Settings.BACK_LISTENER_PORT);
                System.out.println("I do request");
                request.requestPull();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });


        executor.shutdown();
    }
    //        showLogIn();
// to be added
//        showMainWindow();
    private void initRootLayout() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(Main.class.getResource("/view/RootLayout.fxml"));
            rootLayout = fxmlLoader.load();

            Platform.runLater(() -> {
                Scene scene = new Scene(rootLayout, 1250, 600);
                primaryStage.setScene(scene);

                RootLayoutController controller = fxmlLoader.getController();
                // controller.setMain(this);
            });
        } catch (Exception e) {
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

    public boolean addToMessageObservableList(Message message){
        return messageObservableList.add(message);
    }

    public static void main(String[] args) {
        launch();
    }
}