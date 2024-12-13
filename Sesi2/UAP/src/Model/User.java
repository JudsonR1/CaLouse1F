package Model;

public class User {

	private String ID;
	private String Username;
	private String password;
	private String phone_number;
	private String address;
	private String role;

	public User(String id, String username, String password, String phone_number, String address, String role) {
		this.ID = id;
		this.Username = username;
		this.password = password;
		this.phone_number = phone_number;
		this.address = address;
		this.role = role;
	}

	public String getID() {
		return ID;
	}

	public void setID(String iD) {
		ID = iD;
	}

	public String getUsername() {
		return Username;
	}

	public void setUsername(String username) {
		Username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPhone_number() {
		return phone_number;
	}

	public void setPhone_number(String phone_number) {
		this.phone_number = phone_number;
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
