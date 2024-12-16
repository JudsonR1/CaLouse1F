package view.factory;



import javafx.scene.Scene;
import view.buyer.BuyerHomeView;
import view.buyer.BuyerPurchaseHistoryView;
import view.buyer.BuyerWishlistView;



public class BuyerViewFactory {

	public static Scene BuyerHomePage() {
		return new BuyerHomeView().getScene();
	}
	
	public static Scene BuyerPurchaseHistoryPage() {
		return new BuyerPurchaseHistoryView().getScene();
	}
	
	public static Scene BuyerWishlistPage() {
		return new BuyerWishlistView().getScene();
	}
	
}

