package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import util.Connect;
import util.Connect;

public class Item{

	private int id;
	private int userId;
	private String name;
	private String category;
	private String size;
	private int price;
	private String status;
	private boolean isWishlistButtonDisabled;
	private int offer;
	private int updatedBy;

	public Item(int id, int userId, String name, String category, String size, int price, String status,
			int offer, int updatedBy) {
		this.id = id;
		this.userId = userId;
		this.name = name;
		this.category = category;
		this.size = size;
		this.price = price;
		this.status = status;
		this.offer = offer;
		this.updatedBy = updatedBy;
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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getSize() {
		return size;
	}

	public void setSize(String size) {
		this.size = size;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public boolean getIsWishlistButtonDisabled() {
		return isWishlistButtonDisabled;
	}

	public void setIsWishlistButtonDisabled(boolean isWishlistButtonDisabled) {
		this.isWishlistButtonDisabled = isWishlistButtonDisabled;
	}

	public int getOffer() {
		return offer;
	}

	public void setOffer(int offer) {
		this.offer = offer;
	}

	public int getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(int updatedBy) {
		this.updatedBy = updatedBy;
	}

	public static boolean UploadItem(int userId, String name, String category, String size, int price) {

		Connection dbConnection = Connect.getInstance().getCon();

		try {
			String query = "INSERT INTO item (userId, name, category, size, price, status, offer, updatedBy) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
			PreparedStatement st = dbConnection.prepareStatement(query);
			st.setInt(1, userId);
			st.setString(2, name);
			st.setString(3, category);
			st.setString(4, size);
			st.setInt(5, price);
			st.setString(6, "PENDING");
			st.setInt(7, 0);
			st.setInt(8, 0);
			st.executeUpdate();
			System.out.println(1);
			return true;
		} catch (Exception e) {
			System.out.println(2);
			return false;
		}

	}

	public static boolean EditItem(int id, String name, String category, String size, int price) {

		Connection dbConnection = Connect.getInstance().getCon();

		try {
			String query = "UPDATE item SET name = ?, category = ?, size = ?, price = ? WHERE id = ?";
			PreparedStatement st = dbConnection.prepareStatement(query);
			st.setString(1, name);
			st.setString(2, category);
			st.setString(3, size);
			st.setInt(4, price);
			st.setInt(5, id);
			st.executeUpdate();
			return true;
		} catch (Exception e) {
			return false;
		}

	}

	public static boolean DeleteItem(int id) {

		Connection dbConnection = Connect.getInstance().getCon();

		try {
			String query = "DELETE FROM item WHERE id = ?";
			PreparedStatement st = dbConnection.prepareStatement(query);
			st.setInt(1, id);
			st.executeUpdate();
			return true;
		} catch (Exception e) {
			return false;
		}

	}

	public static ArrayList<Item> ViewRequestedItem() {

		Connection dbConnection = Connect.getInstance().getCon();

		try {
			String query = "SELECT id, userId, name, category, size, price, status, offer, updatedBy FROM item WHERE status = ?";
			PreparedStatement st = dbConnection.prepareStatement(query);
			st.setString(1, "PENDING");
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

	public static boolean OfferPrice(int userId, int id, int price) {

		Connection dbConnection = Connect.getInstance().getCon();
		
		try {
			String query = "UPDATE item SET offer = ?, updatedBy = ? WHERE id = ?";
			PreparedStatement st = dbConnection.prepareStatement(query);
			st.setInt(1, price);
			st.setInt(2, userId);
			st.setInt(3, id);
			st.executeUpdate();
			return true;
		} catch (Exception e) {
			return false;
		}
		
	}

	public static boolean AcceptOffer(int id) {

		Connection dbConnection = Connect.getInstance().getCon();

		try {
			String query = "UPDATE item SET status = ? WHERE id = ?";
			PreparedStatement st = dbConnection.prepareStatement(query);
			st.setString(1, "SOLD");
			st.setInt(2, id);
			st.executeUpdate();
			return true;
		} catch (Exception e) {
			return false;
		}
		
	}

	public static boolean DeclineOffer(int id) {

		Connection dbConnection = Connect.getInstance().getCon();

		try {
			String query = "UPDATE item SET offer = ?, updatedBy = ? WHERE id = ?";
			PreparedStatement st = dbConnection.prepareStatement(query);
			st.setInt(1, 0);
			st.setInt(2, 0);
			st.setInt(3, id);
			st.executeUpdate();
			return true;
		} catch (Exception e) {
			return false;
		}
		
	}

	public static boolean ApproveItem(int id) {

		Connection dbConnection = Connect.getInstance().getCon();

		try {
			String query = "UPDATE item SET status = ? WHERE id = ?";
			PreparedStatement st = dbConnection.prepareStatement(query);
			st.setString(1, "APPROVED");
			st.setInt(2, id);
			st.executeUpdate();
			return true;
		} catch (Exception e) {
			return false;
		}

	}

	public static boolean DeclineItem(int id) {

		Connection dbConnection = Connect.getInstance().getCon();

		try {
			String query = "DELETE FROM item WHERE id = ?";
			PreparedStatement st = dbConnection.prepareStatement(query);
			st.setInt(1, id);
			st.executeUpdate();
			return true;
		} catch (Exception e) {
			return false;
		}

	}

	public static ArrayList<Item> ViewAcceptedItem(int userId) {

		Connection dbConnection = Connect.getInstance().getCon();

		try {
			String query = "SELECT i.id, i.userId, i.name, i.category, i.size, i.price, i.status, i.offer, CASE WHEN w.id IS NULL THEN 0 ELSE 1 END AS isWishlistButtonDisabled, i.updatedBy FROM item i LEFT JOIN (SELECT * FROM wishlist WHERE userId = ?) w ON i.id = w.itemId WHERE i.status = ?";
			
			// Query:
			//	SELECT
			// 		i.id,
			// 		i.userId,
			// 		i.name,
			// 		i.category,
			// 		i.size,
			// 		i.price,
			// 		i.status,
			// 		i.offer,
			// 		CASE
			// 			WHEN w.id IS NULL THEN 1
			// 			ELSE 0
			// 		END AS isWishlistButtonEnabled,
			// 		i.updatedBy
			// 	FROM
			// 		item i
			// 		LEFT JOIN (
			// 			SELECT * 
			// 			FROM wishlist
			// 			WHERE userId = ?
			// 		) w
			// 		ON i.id = w.itemId
			// 	WHERE
			// 		i.status = ?
			
			PreparedStatement st = dbConnection.prepareStatement(query);
			st.setInt(1, userId);
			st.setString(2, "APPROVED");
			ResultSet result = st.executeQuery();

			ArrayList<Item> itemList = new ArrayList<>();
			while (result.next()) {
				Item item = new Item(result.getInt("id"), result.getInt("userId"), result.getString("name"),
						result.getString("category"), result.getString("size"), result.getInt("price"),
						result.getString("status"), result.getInt("offer"), result.getInt("updatedBy"));
				item.setIsWishlistButtonDisabled(result.getBoolean("isWishlistButtonDisabled"));
				itemList.add(item);
			}

			return itemList;

		} catch (Exception e) {
			return null;
		}

	}

	public static ArrayList<Item> ViewOfferItem(int userId) {

		Connection dbConnection = Connect.getInstance().getCon();

		try {
			String query = "SELECT id, userId, name, category, size, price, status, offer, updatedBy FROM item WHERE status = ? AND offer != ? AND userId = ?";
			PreparedStatement st = dbConnection.prepareStatement(query);
			st.setString(1, "APPROVED");
			st.setInt(2, 0);
			st.setInt(3, userId);
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
	
	public static ArrayList<Item> ViewSoldItem(int userId) {
		
		Connection dbConnection = Connect.getInstance().getCon();

		try {
			String query = "SELECT id, userId, name, category, size, price, status, offer, updatedBy FROM item WHERE status = ? AND userId = ?";
			PreparedStatement st = dbConnection.prepareStatement(query);
			st.setString(1, "SOLD");
			st.setInt(2, userId);
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
	
	public static ArrayList<Item> ViewPendingItem(int userId) {
		
		Connection dbConnection = Connect.getInstance().getCon();
		
		try {
			String query = "SELECT id, userId, name, category, size, price, status, offer, updatedBy FROM item WHERE status = ? AND userId = ?";
			PreparedStatement st = dbConnection.prepareStatement(query);
			st.setString(1, "PENDING");
			st.setInt(2, userId);
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
	
	public static ArrayList<Item> ViewSellerAcceptedItem(int userId) {
		
		Connection dbConnection = Connect.getInstance().getCon();
		
		try {
			String query = "SELECT id, userId, name, category, size, price, status, offer, updatedBy FROM item WHERE status = ? AND userId = ? AND offer = ?";
			PreparedStatement st = dbConnection.prepareStatement(query);
			st.setString(1, "APPROVED");
			st.setInt(2, userId);
			st.setInt(3, 0);
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
	
	public static boolean UpdateStatusToSold(int id) {
		
		Connection dbConnection = Connect.getInstance().getCon();

		try {
			String query = "UPDATE item SET status = ? WHERE id = ?";
			PreparedStatement st = dbConnection.prepareStatement(query);
			st.setString(1, "SOLD");
			st.setInt(2, id);
			st.executeUpdate();
			return true;
		} catch (Exception e) {
			return false;
		}
		
	}

}