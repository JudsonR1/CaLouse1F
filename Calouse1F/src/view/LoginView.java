package view;



import controller.UserController;
import javafx.geometry.Insets;
import javafx.geometry.VPos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import util.GuestViewData;
import util.Router;
import util.Session;

public class LoginView {

	// 16:9
	int SCENE_WIDTH = 640;
	int SCENE_HEIGHT = 360;
	
	private Scene scene;
	
	private GridPane layout = new GridPane();
	
	private Label usernameLabel;
	private Label passwordLabel;
	
	private TextField usernameTextField;
	private TextField passwordTextField;
	
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
		
		usernameLabel = new Label("Username");
		passwordLabel = new Label("Password");
		
		usernameTextField = new TextField();
		passwordTextField = new TextField();
		
		usernameErrorMessage = new Label("Must be filled");
		passwordErrorMessage = new Label("Must be filled");
		
		// style="display: none"
		usernameErrorMessage.setManaged(false);
		passwordErrorMessage.setManaged(false);
		
		usernameBox = new VBox();
		passwordBox = new VBox();
		usernameBox.getChildren().addAll(usernameTextField, usernameErrorMessage);
		passwordBox.getChildren().addAll(passwordTextField, passwordErrorMessage);
		
		// align vertically at top
		GridPane.setValignment(usernameLabel, VPos.TOP);
		GridPane.setValignment(passwordLabel, VPos.TOP);
		
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
		
		scene = new Scene(layout, SCENE_WIDTH, SCENE_HEIGHT);
		
	}
	
	private void initializeAction() {
		
		loginButton.setOnAction(e -> {
			
			String username = usernameTextField.getText();
			String password = passwordTextField.getText();
			
			if(UserController.Login(
					username,
					password,
					usernameErrorMessage,
					passwordErrorMessage)
			) {
				if(Session.getUserRole().equals("admin")) {
//					Router.getRouter().navigateTo("Home", AdminPageData.AdminHomePage());
				} else if(Session.getUserRole().equals("Buyer")) {
//					Router.getRouter().navigateTo("Home", BuyerPageData.BuyerHomePage());
				} else if(Session.getUserRole().equals("Seller")) {
//					Router.getRouter().navigateTo("Home", SellerPageData.SellerHomePage());
				}
			}
			
		});
		
		hyperlink.setOnAction(e -> navigateToRegisterPage());
		
	}
	
	private void navigateToRegisterPage() {
		
		Router.getRouter().navigateTo("Register", GuestViewData.RegisterPage());
		
	}
	
}

