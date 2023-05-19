package view.mailOverview;

import app_front_end.Main;
import domain.Message;
import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

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

    @FXML
    private Label subjectLabel;

    @FXML
    private Label senderLabel;

    @FXML
    private Label timeLabel;

    @FXML
    private Label crackStatus;



    public void setMain(Main main) {
        this.main = main;

        messageTableView.setItems(main.getMessageObservableList());
    }

    @FXML
    public void initialize() throws Exception {
        senderColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getFrom().getEmailAddress()));
        subjectColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getSubject()));

        showMessageDetails(null);

        messageTableView.getSelectionModel().selectedItemProperty().addListener((observableValue, oldValue, newValue) -> {
            try {
                showMessageDetails(newValue);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
    }

    private void showMessageDetails(Message message) throws Exception {
        if (message != null) {
            subjectLabel.setText(message.getSubject());
            senderLabel.setText(message.getFrom().getEmailAddress());
            timeLabel.setText(message.getDateTime().format(DateTimeFormatter.ofLocalizedDateTime(FormatStyle.SHORT)));
            crackStatus.setText(message.getCracked());
            bodyTextArea.setText(message.getContent());
        }
        else {
            subjectLabel.setText("");
            senderLabel.setText("");
            timeLabel.setText("");
            crackStatus.setText("");
            bodyTextArea.setText("");
        }
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
