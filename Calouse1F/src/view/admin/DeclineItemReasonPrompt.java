package view.admin;

import javafx.geometry.Insets;
import javafx.geometry.Pos;

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

public class DeclineItemReasonPrompt {

    private static String reason;

    public static String display() {

        reason = "";

        Stage stage = new Stage();

        stage.initModality(Modality.APPLICATION_MODAL);

        GridPane layout = new GridPane();

        layout.setPadding(new Insets(16, 32, 16, 32));
        layout.setVgap(16);
        layout.setHgap(16);
        layout.setAlignment(Pos.CENTER);

        Label title = new Label("Please provide a reason: ");
        

        VBox box = new VBox(8);
        box.setAlignment(Pos.CENTER_LEFT);

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

        layout.add(title, 0, 0, 2, 1);
      
        layout.add(box, 1, 1);
        layout.add(button, 1, 2);

        Scene scene = new Scene(layout, 400, 225);

        stage.setTitle("Reason Prompt");
        stage.setScene(scene);
        stage.showAndWait();

        return reason;
    }
}
