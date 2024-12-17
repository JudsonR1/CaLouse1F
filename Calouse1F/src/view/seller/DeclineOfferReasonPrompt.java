package view.seller;

import javafx.geometry.Insets;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;

import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class DeclineOfferReasonPrompt {


	private static String reason;

	public static String display() {

		
		reason = "";
		
		Stage stage = new Stage();

		stage.initModality(Modality.APPLICATION_MODAL);

		GridPane layout = new GridPane();

		layout.setPadding(new Insets(16, 32, 16, 32));
		layout.setVgap(8);
		layout.setHgap(8);

		Label title = new Label("Please Provide a Reason: ");
		

		VBox box = new VBox();

		TextArea textArea = new TextArea();
		Label errorMessage = new Label("Field can't be empty!");
		errorMessage.setTextFill(javafx.scene.paint.Color.RED);
		Button button = new Button("Submit");

		button.setOnAction(e -> {
			reason = textArea.getText();

			if (reason.isEmpty()) {
				errorMessage.setManaged(true);
			} else {
				stage.close();
			}
		});

		title.setFont(Font.font("System", FontWeight.BOLD, 16));

		box.getChildren().addAll(textArea, errorMessage);

		errorMessage.setManaged(false);

		

		GridPane.setColumnSpan(title, 2);

		layout.add(title, 0, 0);
		
		layout.add(box, 1, 1);
		layout.add(button, 1, 2);

		Scene scene = new Scene(layout, 400, 225);

		stage.setTitle("Reason Prompt");
		stage.setScene(scene);
		stage.showAndWait();

		return reason;

	}


}
