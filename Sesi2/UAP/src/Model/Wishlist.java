package Model;

public class Wishlist {

	private String ID;
	private Item item;
	private User user;

	public Wishlist(String id, Item item, User user) {
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
