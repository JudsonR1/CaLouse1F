package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


import model.User;

public class Connect {
	private final String USERNAME = "root";
	private final String PASSWORD = "";
	private final String DATABASE = "kontolodon";
	private final String HOST = "Localhost:3306";
	private final String CONNECTION = 
			String.format("jdbc:mysql://%s/%s", HOST, DATABASE);
	
	private Connection con;
	
	public Connection getCon() {
		return con;
	}

	private static Connect database;
	
	public static Connect getInstance() {
		if(database == null) {
			database = new Connect();
		}
		return database;
	}
	
	private Connect() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection(CONNECTION, USERNAME, PASSWORD);
			System.out.println("db connected");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void inserUser(String name, String password, String phone, String address, String role) {
		String query = "INSERT INTO user(name, password, phone, address, role) VALUES (?,?,?,?,?)";
		try(PreparedStatement pst = con.prepareStatement(query)) {
			pst.setString(1, name);
			pst.setString(2, password);
			pst.setString(3, phone);
			pst.setString(4, address);
			pst.setString(5, role);

			pst.executeUpdate();
			

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public List<User> selectAllUser() {
		List<User> userList = new ArrayList<>();
		String query = "SELECT * FROM user";
		try (Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(query)){
			
			while(rs.next()) {
				userList.add(new User(rs.getInt("id"), rs.getString("name"), 
						rs.getString("password"), rs.getString("phone") , 
						rs.getString("address") , rs.getString("role")));
			}
			
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		return userList;
	}
	
}
