package view;

import app_front_end.Main;
import javafx.fxml.FXML;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;

import java.util.Optional;

public class RootLayoutController {

	public void setMain(Main main) {

	}

	@FXML
	public void initialize() {

	}
	
	@FXML
	public void onClose() {
		Dialog<ButtonType> dialog = new Dialog<>();
		dialog.setTitle("Are you sure?");
		dialog.setContentText("Be aware that all unsaved activity will be lost!");
		ButtonType yesButton = new ButtonType("Yes I am sure", ButtonBar.ButtonData.YES);
		ButtonType noButton = new ButtonType("No! I have changed my mind", ButtonBar.ButtonData.NO);

		dialog.getDialogPane().getButtonTypes().setAll(yesButton, noButton);

		Optional<ButtonType> result = dialog.showAndWait();

		if (result.isPresent() && result.get() == yesButton) {
			System.exit(0);
		}
	}
	
	@FXML
	public void onAbout() {
		Dialog<String> dialog = new Dialog<>();
		dialog.setTitle("About");
		ButtonType type = new ButtonType("Ok", ButtonBar.ButtonData.OK_DONE);
		dialog.setContentText("""
								  Welcome to HeirBnB™ !

				We provide the most luxurious service to the richest among us.
				Add your valuable assets and rent them through our service.
				
				In case of any technical difficulties contact us 24/7 on:
				Tel: +31 06 29 29 29 29
				Email: heirbnbsupport@heir.com""");
		dialog.getDialogPane().getButtonTypes().add(type);
		dialog.showAndWait();

	}

	@FXML
	public void onHelp(){
		//To be implemented
	}
}
