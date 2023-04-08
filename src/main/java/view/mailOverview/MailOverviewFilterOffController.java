package view.mailOverview;

import app.Main;
import domain.Contact;
import domain.Message;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

public class MailOverviewFilterOffController {
    private Main main;

    @FXML
    private TableView<Message> messageTableView;

    @FXML
    private TableColumn<Message, String> senderColumn;

    @FXML
    private TableColumn<Message, String> subjectColumn;

    @FXML
    private TextArea bodyTextArea;

    @FXML
    private ImageView imageView;

    public void setMain(Main main) {
        this.main = main;

    }

    @FXML
    public void showCrackedMessage(MouseEvent mouseEvent) {
        System.out.println("Switcher has been clicked");
        main.showMailOverviewFilterOn();
        //to be developed
        //changes the scene to the one with cracked messages only
    }

    @FXML
    public void showNewMessageWindow(MouseEvent mouseEvent) {
        System.out.println("New Message Window");
    }
}
