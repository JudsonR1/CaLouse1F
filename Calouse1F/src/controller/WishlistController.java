package controller;

import java.util.ArrayList;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Item;
import model.Wishlist;
import util.Session;

public class WishlistController {


	public static ObservableList<Item> ViewWishlist() {
		int userId = Session.getUserId();
		ArrayList<Item> itemList = Wishlist.ViewWishlist(userId);
		return FXCollections.observableArrayList(itemList);
	}
	
	public static boolean AddWishlist(int itemId) {
		int userId = Session.getUserId();
		return Wishlist.AddWishlist(itemId, userId);
	}
	
	public static boolean RemoveWishlistWithItemIdUserId(int itemId) {
		int userId = Session.getUserId();
		return Wishlist.RemoveWishlist(itemId, userId);
	}
	
	public static boolean RemoveWishlistWithItemId(int itemId) {
		return Wishlist.RemoveWishlist(itemId);
	}
	

}
