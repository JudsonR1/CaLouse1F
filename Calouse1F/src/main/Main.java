package main;



import javafx.application.Application;
import javafx.stage.Stage;
import util.Router;
import view.factory.GuestViewFactory;

public class Main extends Application {
	
public void start(Stage primaryStage) throws Exception {
		
		Router.initializeRouter(primaryStage);
		Router.getRouter().navigateTo("Login", GuestViewFactory.LoginPage());
		
	}
}
