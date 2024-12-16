package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import util.Connect;

public class Wishlist {


	private int id;
	private int itemId;
	private int userId;
	
	public Wishlist(int id, int itemId, int userId) {
		this.id = id;
		this.itemId = itemId;
		this.userId = userId;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getItemId() {
		return itemId;
	}

	public void setItemId(int itemId) {
		this.itemId = itemId;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}
	
	public static ArrayList<Item> ViewWishlist(int userId) {
		
		Connection dbConnection = Connect.getInstance().getCon();

		try {
			String query = "SELECT item.id, item.userId, item.name, item.category, item.size, item.price, item.status, item.offer, item.updatedBy FROM wishlist JOIN item ON wishlist.itemId = item.id WHERE wishlist.userId = ?";
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
	
	public static boolean AddWishlist(int itemId, int userId) {
		
		Connection dbConnection = Connect.getInstance().getCon();
		
		try {
			String query = "INSERT INTO wishlist (itemId, userId) VALUES (?, ?)";
			PreparedStatement st = dbConnection.prepareStatement(query);
			st.setInt(1, itemId);
			st.setInt(2, userId);
			st.executeUpdate();
			return true;
		} catch (Exception e) {
			return false;
		}
		
	}
	
	public static boolean RemoveWishlist(int itemId, int userId) {
		
		Connection dbConnection = Connect.getInstance().getCon();
		
		try {
			String query = "DELETE FROM wishlist WHERE itemId = ? AND userId = ?";
			PreparedStatement st = dbConnection.prepareStatement(query);
			st.setInt(1, itemId);
			st.setInt(2, userId);
			st.executeUpdate();
			return true;
		} catch (Exception e) {
			return false;
		}
		
	}
	
	public static boolean RemoveWishlist(int itemId) {
		
		Connection dbConnection = Connect.getInstance().getCon();
		
		try {
			String query = "DELETE FROM wishlist WHERE itemId = ?";
			PreparedStatement st = dbConnection.prepareStatement(query);
			st.setInt(1, itemId);
			st.executeUpdate();
			return true;
		} catch (Exception e) {
			return false;
		}
		
	}
	

}
