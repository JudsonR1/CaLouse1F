package Model;

public class Item {
	private String ID;
	private String name;
	private String price;
	private String category;
	private String status;
	private String wishlist;
	private String offer_status;

	public Item(String id, String name, String price, String category, String status, String wishlist,
			String offer_status) {
		this.ID = id;
		this.name = name;
		this.price = price;
		this.category = category;
		this.status = status;
		this.wishlist = wishlist;
		this.offer_status = offer_status;
	}

	public String getID() {
		return ID;
	}

	public void setID(String iD) {
		ID = iD;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getWishlist() {
		return wishlist;
	}

	public void setWishlist(String wishlist) {
		this.wishlist = wishlist;
	}

	public String getOffer_status() {
		return offer_status;
	}

	public void setOffer_status(String offer_status) {
		this.offer_status = offer_status;
	}

}
