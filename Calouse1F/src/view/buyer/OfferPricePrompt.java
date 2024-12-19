package view.buyer;

import javafx.geometry.Insets;
import javafx.geometry.VPos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class OfferPricePrompt {


	private static int offer;

	public static int display(int oldOffer) {

		// refresh value
		offer = 0;
		
		Stage stage = new Stage();

		stage.initModality(Modality.APPLICATION_MODAL);

		GridPane layout = new GridPane();

		layout.setPadding(new Insets(16, 32, 16, 32));
		layout.setVgap(8);
		layout.setHgap(8);

		Label title = new Label("Enter Your Offer");
		Label label = new Label("Price: ");

		VBox box = new VBox();

		TextField textField = new TextField();
		Label errorMessage = new Label();

		Button button = new Button("Submit");

		button.setOnAction(e -> {
			
			String newOfferInStr = textField.getText();

			if (newOfferInStr.isEmpty()) {
				errorMessage.setManaged(true);
				errorMessage.setText("Field can't be empty!");
				errorMessage.setTextFill(javafx.scene.paint.Color.RED);
				return;
			}
			
			int newOffer;
			
			try {
				newOffer = Integer.parseInt(newOfferInStr);
			} catch (Exception error) {
				errorMessage.setManaged(true);
				errorMessage.setText("Must be a number");
				return;
			}
			
			if(newOffer == 0) {
				errorMessage.setManaged(true);
				errorMessage.setText("Must be larger than 0");
				return;
			}
			
			if(oldOffer >= newOffer) {
				errorMessage.setManaged(true);
				errorMessage.setText("Must be larger than current offer");
				return;
			}
			
			offer = newOffer;
			stage.close();
		});

		title.setFont(Font.font("System", FontWeight.BOLD, 16));

		box.getChildren().addAll(textField, errorMessage);

		errorMessage.setManaged(false);

		GridPane.setValignment(label, VPos.TOP);

		GridPane.setColumnSpan(title, 2);

		layout.add(title, 0, 0);
		layout.add(label, 0, 1);
		layout.add(box, 1, 1);
		layout.add(button, 1, 2);

		Scene scene = new Scene(layout, 400, 225);

		stage.setTitle("Offer Prompt");
		stage.setScene(scene);
		stage.showAndWait();

		return offer;

	}


}
