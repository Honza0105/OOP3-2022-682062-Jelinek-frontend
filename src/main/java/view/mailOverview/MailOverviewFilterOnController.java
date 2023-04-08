package view.mailOverview;

import app.Main;
import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

public class MailOverviewFilterOnController {
    private Main main;

    @FXML
    private ImageView imageView;



    public void setMain(Main main) {
        this.main = main;

    }

    @FXML
    public void showEveryMessage(MouseEvent mouseEvent) {
        System.out.println("Switcher has been clicked");
        main.showMailOverviewFilterOff();
        //to be developed
        //changes the scene to the one with cracked messages only
    }

    @FXML
    public void showNewMessageWindow(MouseEvent mouseEvent) {
        System.out.println("New Message Window");
    }
}
