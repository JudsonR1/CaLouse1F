package controller;

import java.util.ArrayList;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Item;
import model.Transaction;
import util.Session;

public class TransactionController {


	public static boolean PurchaseItems(int userId, int itemId) {
		return Transaction.PurchaseItems(userId, itemId);
	}
	
	public static ObservableList<Item> ViewHistory() {
		int userId = Session.getUserId();
		ArrayList<Item> itemList = Transaction.ViewHistory(userId);
		return FXCollections.observableArrayList(itemList);
	}
	

}
