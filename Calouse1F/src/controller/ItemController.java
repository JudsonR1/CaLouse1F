package controller;

import java.util.ArrayList;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Label;
import model.Item;

public class ItemController {
	


	public static boolean UploadItem(
			int userId,
			String name,
			String category,
			String size,
			String price,
			Label nameErrorMessage,
			Label categoryErrorMessage,
			Label sizeErrorMessage,
			Label priceErrorMessage
	) {
		if(CheckItemValidation(
				name,
				category,
				size,
				price,
				nameErrorMessage,
				categoryErrorMessage,
				sizeErrorMessage,
				priceErrorMessage)
		) {
			int priceInInt = Integer.parseInt(price);
			return Item.UploadItem(userId, name, category, size, priceInInt);
		}
		
		return false;
	}

	public static boolean EditItem(
			int id,
			String name,
			String category,
			String size,
			String price,
			Label nameErrorMessage,
			Label categoryErrorMessage,
			Label sizeErrorMessage,
			Label priceErrorMessage
	) {
		if(CheckItemValidation(
				name,
				category,
				size,
				price,
				nameErrorMessage,
				categoryErrorMessage,
				sizeErrorMessage,
				priceErrorMessage)
		) {
			int priceInInt = Integer.parseInt(price);
			return Item.EditItem(id, name, category, size, priceInInt);
		}
		
		return false;
	}

	public static boolean DeleteItem(int id) {
		return Item.DeleteItem(id);
	}

	private static boolean CheckItemValidation(
			String name,
			String category,
			String size,
			String price,
			Label nameErrorMessage,
			Label categoryErrorMessage,
			Label sizeErrorMessage,
			Label priceErrorMessage
	) {
		
		boolean result = true;
		
		if(name == null ||name.isEmpty()) {
			nameErrorMessage.setManaged(true);
			nameErrorMessage.setText("Cannot be empty!");
			result = false;
		} else {
			nameErrorMessage.setManaged(false);
			nameErrorMessage.setText("");
		}
		
		if(category.isEmpty()) {
			categoryErrorMessage.setManaged(true);
			categoryErrorMessage.setText("Cannot be empty!");
			result = false;
		} else {
			categoryErrorMessage.setManaged(false);
			categoryErrorMessage.setText("");
		}
		
		if(size.isEmpty()) {
			sizeErrorMessage.setManaged(true);
			sizeErrorMessage.setText("Cannot be empty!");
			result = false;
		} else {
			sizeErrorMessage.setManaged(false);
			sizeErrorMessage.setText("");
		}
		
		if(price.isEmpty()) {
			priceErrorMessage.setManaged(true);
			priceErrorMessage.setText("Cannot be empty!");
			result = false;
		} else {
			priceErrorMessage.setManaged(false);
			priceErrorMessage.setText("");
		}
		
		// tidak boleh ada yang kosong
		if(result == false) return false;
		
		if(name.length() < 3) {
			nameErrorMessage.setManaged(true);
			nameErrorMessage.setText("Minimum length 3");
			result = false;
		}
		
		if(category.length() < 3) {
			categoryErrorMessage.setManaged(true);
			categoryErrorMessage.setText("Minimum length 3");
			result = false;
		}
		
		try {
			Integer priceInInt = Integer.parseInt(price);
			if(priceInInt == 0) {
				priceErrorMessage.setManaged(true);
				priceErrorMessage.setText("Price cant be zero");
				result = false;
			}
		} catch (Exception e) {
			priceErrorMessage.setManaged(true);
			priceErrorMessage.setText("Price must be a number");
			result = false;
		}
		
		return result;
		
	}

	public static ObservableList<Item> ViewRequestedItem() {
		ArrayList<Item> itemList = Item.ViewRequestedItem();
		if (itemList == null) {
	        itemList = new ArrayList<>(); 
	    }
		return FXCollections.observableArrayList(itemList);
	}

	public static boolean OfferPrice(int userId, int id, int price) {
		return Item.OfferPrice(userId, id, price);
	}

	public static boolean AcceptOffer(int itemId) {
		return Item.AcceptOffer(itemId);
	}

	public static boolean DeclineOffer(int itemId) {
		return Item.DeclineOffer(itemId);
	}

	public static boolean ApproveItem(int id) {
		return Item.ApproveItem(id);
	}

	public static boolean DeclineItem(int id) {
		return Item.DeclineItem(id);
	}

	public static ObservableList<Item> ViewAcceptedItem(int userId) {
		ArrayList<Item> itemList = Item.ViewAcceptedItem(userId);
		return FXCollections.observableArrayList(itemList);
	}

	public static ObservableList<Item> ViewOfferItem(int userId) {
		ArrayList<Item> itemList = Item.ViewOfferItem(userId);
		return FXCollections.observableArrayList(itemList);
	}
	
	public static ObservableList<Item> ViewSoldItem(int userId) {
		ArrayList<Item> itemList = Item.ViewSoldItem(userId);
		return FXCollections.observableArrayList(itemList);
	}

	public static ObservableList<Item> ViewPendingItem(int userId) {
		ArrayList<Item> itemList = Item.ViewPendingItem(userId);
		return FXCollections.observableArrayList(itemList);
	}
	
	public static ObservableList<Item> ViewSellerAcceptedItem(int userId) {
		ArrayList<Item> itemList = Item.ViewSellerAcceptedItem(userId);
		return FXCollections.observableArrayList(itemList);
	}
	
	public static boolean UpdateStatusToSold(int id) {
		return Item.UpdateStatusToSold(id);
	}
	

	
}
