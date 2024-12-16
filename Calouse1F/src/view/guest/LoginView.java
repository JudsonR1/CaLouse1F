package view.guest;



import controller.UserController;
import javafx.geometry.Insets;
import javafx.geometry.VPos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import util.Router;
import util.Session;
import view.factory.AdminViewFactory;
import view.factory.BuyerViewFactory;
import view.factory.GuestViewFactory;
import view.factory.SellerViewFactory;

public class LoginView {

	
	int SCENE_WIDTH = 450;
	int SCENE_HEIGHT = 225;
	
	private Scene scene;
	
	private GridPane layout = new GridPane();
	
	private Label usernameLabel;
	private Label passwordLabel;
	
	private TextField usernameTextField;
	private PasswordField passField;
	
	private Label usernameErrorMessage;
	private Label passwordErrorMessage;
	
	private VBox usernameBox;
	private VBox passwordBox;
	
	private Button loginButton;
	
	private HBox hyperlinkBox;
	private Label hyperlinkLabel;
	private Hyperlink hyperlink;
	
	public LoginView() {
		initializeForm();
		initializeHyperlink();
		initializeLayout();
		initializeAction();
	}
	
	public Scene getScene() {
		return scene;
	}
	
	private void initializeForm() {
		
		usernameLabel = new Label("Username: ");
		passwordLabel = new Label("Password: ");
		
		usernameTextField = new TextField();
		passField = new PasswordField();
		
		usernameErrorMessage = new Label();
		usernameErrorMessage.setTextFill(javafx.scene.paint.Color.RED);
		passwordErrorMessage = new Label();
		passwordErrorMessage.setTextFill(javafx.scene.paint.Color.RED);
		
		
		usernameErrorMessage.setManaged(false);
		passwordErrorMessage.setManaged(false);
		
		usernameBox = new VBox();
		passwordBox = new VBox();
		usernameBox.getChildren().addAll(usernameTextField, usernameErrorMessage);
		passwordBox.getChildren().addAll(passField, passwordErrorMessage);
		
		
		GridPane.setValignment(usernameLabel, VPos.CENTER);
		GridPane.setValignment(passwordLabel, VPos.CENTER);
		
		loginButton = new Button("Login");
		
	}
	
	private void initializeHyperlink() {
		
		hyperlinkBox = new HBox();
		hyperlinkLabel = new Label("Dont have an account? ");
		hyperlink = new Hyperlink("Register");
		hyperlink.setStyle("-fx-padding: 0;");
		hyperlinkBox.getChildren().addAll(hyperlinkLabel, hyperlink);
		
	}
	
	private void initializeLayout() {

	    layout = new GridPane();
	    layout.setPadding(new Insets(16, 16, 16, 16));
	    layout.setVgap(8);
	    layout.setHgap(8);

	    layout.add(usernameLabel, 0, 0);
	    layout.add(passwordLabel, 0, 1);
	    layout.add(usernameBox, 1, 0);
	    layout.add(passwordBox, 1, 1);
	    layout.add(loginButton, 1, 2);
	    layout.add(hyperlinkBox, 1, 3);

	   
	    layout.setAlignment(javafx.geometry.Pos.CENTER);

	    
	    VBox root = new VBox();
	    root.setPrefSize(SCENE_WIDTH, SCENE_HEIGHT);
	    root.setAlignment(javafx.geometry.Pos.CENTER); 
	    root.getChildren().add(layout);

	    scene = new Scene(root, SCENE_WIDTH, SCENE_HEIGHT);
	}



	
	private void initializeAction() {
		
		loginButton.setOnAction(e -> {
			
			String username = usernameTextField.getText();
			String password = passField.getText();
			
			if(UserController.Login(
					username,
					password,
					usernameErrorMessage,
					passwordErrorMessage)
			) {
				if(Session.getUserRole().equals("admin")) {
					Router.getRouter().navigateTo("Home", AdminViewFactory.AdminHomePage());
				} else if(Session.getUserRole().equals("Buyer")) {
					Router.getRouter().navigateTo("Home", BuyerViewFactory.BuyerHomePage());
				} else if(Session.getUserRole().equals("Seller")) {
					Router.getRouter().navigateTo("Home", SellerViewFactory.SellerHomePage());
				}
			}
			
		});
		
		hyperlink.setOnAction(e -> navigateToRegisterPage());
		
	}
	
	private void navigateToRegisterPage() {
		
		Router.getRouter().navigateTo("Register", GuestViewFactory.RegisterPage());
		
	}
	
}

