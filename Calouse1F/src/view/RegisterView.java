package view;

import controller.UserController;
import javafx.geometry.Insets;
import javafx.geometry.VPos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import util.GuestViewData;
import util.Router;

public class RegisterView {

	int SCENE_WIDTH = 700;
	int SCENE_HEIGHT = 450;
	
	private Scene scene;
	
	private GridPane layout;
	
	private Label nameLabel;
	private Label passwordLabel;
	private Label phoneLabel;
	private Label addressLabel;
	private Label roleLabel;
	
	private TextField nameTextField;
	private PasswordField passField;
	private TextField phoneTextField;
	private TextField addressTextField;
	
	private ToggleGroup roleToggleGroup;
	private RadioButton buyerOption;
	private RadioButton sellerOption;
	private HBox roleOptionBox;
	
	private Label nameErrorMessage;
	private Label passwordErrorMessage;
	private Label phoneErrorMessage;
	private Label addressErrorMessage;
	private Label roleErrorMessage;
	
	private VBox nameBox;
	private VBox passwordBox;
	private VBox phoneBox;
	private VBox addressBox;
	private VBox roleBox;
	
	private Button registerButton;
	
	private HBox hyperlinkBox;
	private Label hyperlinkLabel;
	private Hyperlink hyperlink;
	
	public RegisterView() {
		initializeForm();
		initializeHyperlink();
		initializeLayout();
		initializeAction();
	}
	
	public Scene getScene() {
		return scene;
	}
	
	private void initializeForm() {
		
		nameLabel = new Label("Username: ");
		passwordLabel = new Label("Password: ");
		phoneLabel = new Label("Phone Number: ");
		addressLabel = new Label("Address: ");
		roleLabel = new Label("Role: ");
		
		nameTextField = new TextField();
		passField = new PasswordField();
		phoneTextField = new TextField();
		addressTextField = new TextField();
		
		roleToggleGroup = new ToggleGroup();
		buyerOption = new RadioButton("Buyer");
		sellerOption = new RadioButton("Seller");
		buyerOption.setToggleGroup(roleToggleGroup);
		sellerOption.setToggleGroup(roleToggleGroup);
		roleOptionBox = new HBox();
		roleOptionBox.getChildren().addAll(buyerOption, sellerOption);
		roleOptionBox.setSpacing(8);
		
		nameErrorMessage = new Label();
		nameErrorMessage.setTextFill(javafx.scene.paint.Color.RED);
		passwordErrorMessage = new Label();
		passwordErrorMessage.setTextFill(javafx.scene.paint.Color.RED);
		phoneErrorMessage = new Label();
		phoneErrorMessage.setTextFill(javafx.scene.paint.Color.RED);
		addressErrorMessage = new Label();
		addressErrorMessage.setTextFill(javafx.scene.paint.Color.RED);
		roleErrorMessage = new Label();
		roleErrorMessage.setTextFill(javafx.scene.paint.Color.RED);
		// style="display: none"
		nameErrorMessage.setManaged(false);
		passwordErrorMessage.setManaged(false);
		phoneErrorMessage.setManaged(false);
		addressErrorMessage.setManaged(false);
		roleErrorMessage.setManaged(false);
		
		nameBox = new VBox();
		passwordBox = new VBox();
		phoneBox = new VBox();
		addressBox = new VBox();
		roleBox = new VBox();
		
		nameBox.getChildren().addAll(nameTextField, nameErrorMessage);
		passwordBox.getChildren().addAll(passField, passwordErrorMessage);
		phoneBox.getChildren().addAll(phoneTextField, phoneErrorMessage);
		addressBox.getChildren().addAll(addressTextField, addressErrorMessage);
		roleBox.getChildren().addAll(roleOptionBox, roleErrorMessage);
		
		
		GridPane.setValignment(nameLabel, VPos.CENTER);
		GridPane.setValignment(passwordLabel, VPos.CENTER);
		GridPane.setValignment(phoneLabel, VPos.CENTER);
		GridPane.setValignment(addressLabel, VPos.CENTER);
		GridPane.setValignment(roleLabel, VPos.CENTER);
		
		registerButton = new Button("Register");
		
	}
	
	private void initializeHyperlink() {
		
		hyperlinkBox = new HBox();
		hyperlinkLabel = new Label("Already have an account? ");
		hyperlink = new Hyperlink("Login");
		hyperlink.setStyle("-fx-padding: 0;");
		hyperlinkBox.getChildren().addAll(hyperlinkLabel, hyperlink);
		
	}
	
	private void initializeLayout() {
	    layout = new GridPane();
	    layout.setPadding(new Insets(20)); // Padding around the grid
	    layout.setVgap(15); // Vertical spacing
	    layout.setHgap(10); // Horizontal spacing
	    layout.setAlignment(javafx.geometry.Pos.CENTER); // Center-align the grid content

	    
	    layout.add(nameLabel, 0, 0);
	    layout.add(passwordLabel, 0, 1);
	    layout.add(phoneLabel, 0, 2);
	    layout.add(addressLabel, 0, 3);
	    layout.add(roleLabel, 0, 4);

	    layout.add(nameBox, 1, 0);
	    layout.add(passwordBox, 1, 1);
	    layout.add(phoneBox, 1, 2);
	    layout.add(addressBox, 1, 3);
	    layout.add(roleBox, 1, 4);

	    layout.add(registerButton, 1, 5);
	    layout.add(hyperlinkBox, 1, 6);

	    // Create a StackPane to center the GridPane
	    StackPane wrapper = new StackPane();
	    wrapper.getChildren().add(layout);
	    wrapper.setAlignment(layout, javafx.geometry.Pos.CENTER); 

	    scene = new Scene(wrapper, SCENE_WIDTH, SCENE_HEIGHT);
	}



	
	private void initializeAction() {
		
		registerButton.setOnAction(e -> registerButtonClicked());
		
		hyperlink.setOnAction(e -> navigateToLoginPage());
		
	}
	
	private void registerButtonClicked() {
		
		String name = nameTextField.getText();
		String password = passField.getText();
		String phone = phoneTextField.getText();
		String address = addressTextField.getText();
		String role = "";
		
		RadioButton roleRadioButton = (RadioButton) roleToggleGroup.getSelectedToggle();
		if(roleRadioButton != null) {			
			role = roleRadioButton.getText();
		}
		
		if(UserController.Register(
				name,
				password,
				phone,
				address,
				role,
				nameErrorMessage,
				passwordErrorMessage,
				phoneErrorMessage,
				addressErrorMessage,
				roleErrorMessage)
		) {
			navigateToLoginPage();
		}
		
	}
	
	private void navigateToLoginPage() {
		Router.getRouter().navigateTo("Login", GuestViewData.LoginPage());

	}
	
}
