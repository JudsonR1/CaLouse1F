
package view.seller;

import controller.ItemController;
import javafx.geometry.Insets;
import javafx.geometry.VPos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import util.Router;
import util.Session;
import view.factory.GuestViewFactory;
import view.factory.SellerViewFactory;

public class SellerUploadItemView {

	
	
	int SCENE_WIDTH = 800;
	int SCENE_HEIGHT = 450;
	
	private Scene scene;

	private BorderPane layout;
	
	private HBox navigationBox;
	private Button backButton;
	private Region spacer;
	private Button logoutButton;
	
	private GridPane formLayout;
	
	private Label nameLabel;
	private Label categoryLabel;
	private Label sizeLabel;
	private Label priceLabel;
	
	private TextField nameTextField;
	private TextField categoryTextField;
	private TextField sizeTextField;
	private TextField priceTextField;
	
	private Label nameErrorMessage;
	private Label categoryErrorMessage;
	private Label sizeErrorMessage;
	private Label priceErrorMessage;
	
	private VBox nameBox;
	private VBox categoryBox;
	private VBox sizeBox;
	private VBox priceBox;
	
	private Button uploadButton;
	
	public SellerUploadItemView() {
		initializeNavigationBox();
		initializeForm();
		initializeLayout();
		initializeAction();
	}
	
	public Scene getScene() {
		return scene;
	}
	
	private void initializeNavigationBox() {
		
		navigationBox = new HBox();
		backButton = new Button("Home");
		spacer = new Region();
		logoutButton = new Button("Logout");
		
		HBox.setHgrow(spacer, Priority.ALWAYS);
		
		navigationBox.getChildren().addAll(backButton, spacer, logoutButton);
		navigationBox.setPadding(new Insets(8));
		
	}
	
	private void initializeForm() {
		
		formLayout = new GridPane();
		
		formLayout.setPadding(new Insets(16, 16, 16, 16));
		formLayout.setVgap(8);
		formLayout.setHgap(8);
		
		nameLabel = new Label("Name: ");
		categoryLabel = new Label("Category: ");
		sizeLabel = new Label("Size: ");
		priceLabel = new Label("Price: ");
		
		nameTextField = new TextField();
		categoryTextField = new TextField();
		sizeTextField = new TextField();
		priceTextField = new TextField();
		
		nameErrorMessage = new Label();
		categoryErrorMessage = new Label();
		sizeErrorMessage = new Label();
		priceErrorMessage = new Label();
		
		nameErrorMessage.setTextFill(javafx.scene.paint.Color.RED);
		categoryErrorMessage.setTextFill(javafx.scene.paint.Color.RED);
		sizeErrorMessage.setTextFill(javafx.scene.paint.Color.RED);
		priceErrorMessage.setTextFill(javafx.scene.paint.Color.RED);
		
		// style="display: none"
		nameErrorMessage.setManaged(false);
		categoryErrorMessage.setManaged(false);
		sizeErrorMessage.setManaged(false);
		priceErrorMessage.setManaged(false);
		
		nameBox = new VBox();
		categoryBox = new VBox();
		sizeBox = new VBox();
		priceBox = new VBox();
		
		nameBox.getChildren().addAll(nameTextField, nameErrorMessage);
		categoryBox.getChildren().addAll(categoryTextField, categoryErrorMessage);
		sizeBox.getChildren().addAll(sizeTextField, sizeErrorMessage);
		priceBox.getChildren().addAll(priceTextField, priceErrorMessage);
		
		uploadButton = new Button("Upload");
		
		formLayout.add(nameLabel, 0, 0);
		formLayout.add(categoryLabel, 0, 1);
		formLayout.add(sizeLabel, 0, 2);
		formLayout.add(priceLabel, 0, 3);
		
		GridPane.setValignment(nameLabel, VPos.TOP);
		GridPane.setValignment(categoryLabel, VPos.TOP);
		GridPane.setValignment(sizeLabel, VPos.TOP);
		GridPane.setValignment(priceLabel, VPos.TOP);
		
		formLayout.add(nameBox, 1, 0);
		formLayout.add(categoryBox, 1, 1);
		formLayout.add(sizeBox, 1, 2);
		formLayout.add(priceBox, 1, 3);
		
		formLayout.add(uploadButton, 1, 4);
		
	}
	
	private void initializeLayout() {
		
		layout = new BorderPane();
		
		layout.setTop(navigationBox);
		layout.setCenter(formLayout);
		layout.setStyle("-fx-background-color:  #D4EDDA;");

		scene = new Scene(layout, SCENE_WIDTH, SCENE_HEIGHT);
		
	}
	
	private void initializeAction() {
		
		backButton.setOnAction(e -> navigateToSellerHomePage());

		logoutButton.setOnAction(e -> logoutButtonClicked());
		
		uploadButton.setOnAction(e -> {
			
			int userId = Session.getUserId();
			
			if(ItemController.UploadItem(
					userId,
					nameTextField.getText(),
					categoryTextField.getText(),
					sizeTextField.getText(),
					priceTextField.getText(),
					nameErrorMessage,
					categoryErrorMessage,
					sizeErrorMessage,
					priceErrorMessage)
			) {
				navigateToSellerHomePage();
			}
		});
		
	}
	
	private void navigateToSellerHomePage() {
		Router.getRouter().navigateTo("Home", SellerViewFactory.SellerHomePage());
	}
	
	private void logoutButtonClicked() {
		Session.setUserId(0);
		Router.getRouter().navigateTo("Login", GuestViewFactory.LoginPage());
	}
	

}