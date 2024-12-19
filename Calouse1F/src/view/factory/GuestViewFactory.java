package view.factory;

import javafx.scene.Scene;
import view.guest.LoginView;
import view.guest.RegisterView;


public class GuestViewFactory {




	public static Scene RegisterPage() {
		return new RegisterView().getScene();
	}
	
	public static Scene LoginPage() {
		return new LoginView().getScene();
		
	}
	

	

}
