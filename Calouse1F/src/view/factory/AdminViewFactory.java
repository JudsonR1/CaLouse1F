package view.factory;

import javafx.scene.Scene;
import view.admin.AdminHomeView;




public class AdminViewFactory {

	public static Scene AdminHomePage() {
		return new AdminHomeView().getScene();
	}

}
