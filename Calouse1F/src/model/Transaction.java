package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import util.Connect;

public class Transaction {


	private int id;
	private int userId;
	private int itemId;
	
	public Transaction(int id, int userId, int itemId) {
		super();
		this.id = id;
		this.userId = userId;
		this.itemId = itemId;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public int getItemId() {
		return itemId;
	}

	public void setItemId(int itemId) {
		this.itemId = itemId;
	}
	
	public static boolean PurchaseItems(int userId, int itemId) {
		
		Connection dbConnection = Connect.getInstance().getCon();
		
		try {
			String query = "INSERT INTO transaction (userId, itemId) VALUES (?, ?)";
			PreparedStatement st = dbConnection.prepareStatement(query);
			st.setInt(1, userId);
			st.setInt(2, itemId);
			st.executeUpdate();
			return true;
		} catch (Exception e) {
			return false;
		}
		
	}
	
	public static ArrayList<Item> ViewHistory(int userId) {
		
		Connection dbConnection = Connect.getInstance().getCon();

		try {
			String query = "SELECT item.id, item.userId, item.name, item.category, item.size, item.price, item.status, item.offer, item.updatedBy FROM transaction JOIN item ON transaction.itemId = item.id WHERE transaction.userId = ?";
			PreparedStatement st = dbConnection.prepareStatement(query);
			st.setInt(1, userId);
			ResultSet result = st.executeQuery();

			ArrayList<Item> itemList = new ArrayList<>();
			while (result.next()) {
				Item item = new Item(result.getInt("id"), result.getInt("userId"), result.getString("name"),
						result.getString("category"), result.getString("size"), result.getInt("price"),
						result.getString("status"), result.getInt("offer"), result.getInt("updatedBy"));
				itemList.add(item);
			}

			return itemList;

		} catch (Exception e) {
			return null;
		}
		
	}
	

}
