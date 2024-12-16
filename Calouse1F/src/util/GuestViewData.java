package util;

import javafx.scene.Scene;
import view.LoginView;
import view.RegisterView;


public class GuestViewData {



	public static Scene RegisterPage() {
		return new RegisterView().getScene();
	}
	
	public static Scene LoginPage() {
		return new LoginView().getScene();
		
	}
	

	
}
