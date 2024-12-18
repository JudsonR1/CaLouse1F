package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import util.Connect;

public class User {

	
	
	private int id;
	private String username;
	private String password;
	private String phoneNumber;
	private String address;
	private String role;
	
	
	
	public User(int id, String username, String password, String phoneNumber, String address, String role) {
		
		this.id = id;
		this.username = username;
		this.password = password;
		this.phoneNumber = phoneNumber;
		this.address = address;
		this.role = role;
	}
	
	
	public static boolean Register(String username, String password, String phoneNumber, String address,
			String role) {
		
		Connection dbConnection = Connect.getInstance().getCon();
		try {
			String query = "INSERT INTO user (username, password, phoneNumber, address, role) VALUES (?, ?, ?, ?, ?)";
			PreparedStatement st = dbConnection.prepareStatement(query);
			st.setString(1, username);
			st.setString(2, password);
			st.setString(3, phoneNumber);
			st.setString(4, address);
			st.setString(5, role);
			st.executeUpdate();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return false;
	}
	
	public static User getUserByUsername(String username) {
		
	    Connection con = Connect.getInstance().getCon();
			
	    try {
	        String query = "SELECT id, username, password, role FROM user WHERE BINARY username = ?";
	        PreparedStatement st = con.prepareStatement(query);
	        st.setString(1, username);
	        ResultSet result = st.executeQuery();
	        
	        User userData = null;
	        while (result.next()) {
	            User user = new User(result.getInt("id"), 
	                                 result.getString("username"), 
	                                 result.getString("password"), 
	                                 "", "", 
	                                 result.getString("role"));
	            userData = user;
	            break;
	        }
	        
	        return userData;
	    } catch (Exception e) {
	        return new User(0, "", "", "", "", "");
	    }
	}


	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPhone() {
		return phoneNumber;
	}
	public void setPhone(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}



	
	
	
	
	
	

}
