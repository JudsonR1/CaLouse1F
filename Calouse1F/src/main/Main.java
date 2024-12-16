package main;



import javafx.application.Application;
import javafx.stage.Stage;
import util.GuestViewData;
import util.Router;
import view.RegisterView;

public class Main extends Application {
	
public void start(Stage primaryStage) throws Exception {
		
		Router.initializeRouter(primaryStage);
		Router.getRouter().navigateTo("Register", GuestViewData.RegisterPage());
		
	}
}
