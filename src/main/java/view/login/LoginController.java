package view.login;

import app_front_end.Main;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class LoginController {

    @FXML
    private TextField usernameTextField;

    @FXML
    private TextField passwordTextField;

    @FXML
    private Button login;

    @FXML
    private Button register;

    Main main;
    public void setMain(Main main) {
        this.main = main;
    }
}
