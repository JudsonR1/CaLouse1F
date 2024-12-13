package Model;

public class Transaction {
	private String ID;
	private User user;
	private Item item;

	public Transaction(String id) {
		this.ID = id;
	}

	public String getID() {
		return ID;
	}

	public void setID(String iD) {
		ID = iD;
	}

	public String getUser_ID() {
		return user.getID();
	}

	public String getItem_ID() {
		return item.getID();
	}

}
