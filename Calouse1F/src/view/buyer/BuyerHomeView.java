package view.buyer;

import controller.ItemController;
import controller.TransactionController;
import controller.WishlistController;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import model.Item;
import util.Router;
import util.Session;
import view.factory.BuyerViewFactory;
import view.factory.GuestViewFactory;


	
	public class BuyerHomeView {

		// 16:9
		int SCENE_WIDTH = 640;
		int SCENE_HEIGHT = 360;

		private Scene scene;

		private BorderPane layout;

		private Button viewPurchaseHistoryButton;
		private Button viewWishlistButton;

		private HBox navigationBox;
		private Label label;
		private Region spacer;
		private Button logoutButton;

		private TableView<Item> table;

		public BuyerHomeView() {
			initializeNavigationBox();
			initializeTable();
			initializeLayout();
			initializeAction();
		}

		public Scene getScene() {
			return scene;
		}
		
		private void initializeNavigationBox() {

			viewPurchaseHistoryButton = new Button("View Purchase History");
			viewWishlistButton = new Button("View Wishlist");

			navigationBox = new HBox();
			label = new Label("Logo");
			spacer = new Region();
			logoutButton = new Button("Logout");

			HBox.setHgrow(spacer, Priority.ALWAYS);

			navigationBox.getChildren().addAll(label, spacer, viewPurchaseHistoryButton, viewWishlistButton, logoutButton);
			navigationBox.setPadding(new Insets(8));
			navigationBox.setAlignment(Pos.CENTER);
			navigationBox.setSpacing(8);

		}

		private void initializeTable() {

			table = new TableView<>();

			TableColumn<Item, String> nameColumn = new TableColumn<Item, String>("Name");
			TableColumn<Item, String> categoryColumn = new TableColumn<Item, String>("Category");
			TableColumn<Item, String> sizeColumn = new TableColumn<Item, String>("Size");
			TableColumn<Item, String> priceColumn = new TableColumn<Item, String>("Price");
			TableColumn<Item, String> offerColumn = new TableColumn<Item, String>("Offer");
			TableColumn<Item, Void> actionColumn = new TableColumn<Item, Void>("Action");

			nameColumn.setCellValueFactory(new PropertyValueFactory<Item, String>("name"));
			categoryColumn.setCellValueFactory(new PropertyValueFactory<Item, String>("category"));
			sizeColumn.setCellValueFactory(new PropertyValueFactory<Item, String>("size"));
			priceColumn.setCellValueFactory(new PropertyValueFactory<Item, String>("price"));
			offerColumn.setCellValueFactory(new PropertyValueFactory<Item, String>("offer"));
			actionColumn.setCellFactory(param -> new TableCell<Item, Void>() {

				private final Button addToWishlistButton = new Button("Add To Wishlist");
				private final Button purchaseButton = new Button("Purchase");
				private final Button makeOfferButton = new Button("Make Offer");

				{
					addToWishlistButton.setOnAction(e -> {
						int itemId = getTableView().getItems().get(getIndex()).getId();
						if(WishlistController.AddWishlist(itemId)) {
							// button perlu di disable agar tidak terjadi duplicate di table wishlist
							addToWishlistButton.setDisable(true);
						}
					});
					purchaseButton.setOnAction(e -> {
						// PurchaseConfirmationPopUp.display() untuk meminta konfirmasi dari buyer
						if(PurchaseConfirmationPopUp.display()) {						
							int userId = Session.getUserId();
							int itemId = getTableView().getItems().get(getIndex()).getId();
							
							// create new transaction
							TransactionController.PurchaseItems(userId, itemId);
							
							// update item
							ItemController.UpdateStatusToSold(itemId);
							
							// remove item from wishlist
							WishlistController.RemoveWishlistWithItemId(itemId);
							
							table.setItems(ItemController.ViewAcceptedItem(userId));
						}
					});
					makeOfferButton.setOnAction(e -> {
						int oldOffer = getTableView().getItems().get(getIndex()).getOffer();
						
						// OfferPricePrompt.display() untuk meminta offer buyer
						int newOffer = OfferPricePrompt.display(oldOffer);
						if(newOffer > 0) {
							int userId = Session.getUserId();
							int itemId = getTableView().getItems().get(getIndex()).getId();
							ItemController.OfferPrice(userId, itemId, newOffer);
							
							table.setItems(ItemController.ViewAcceptedItem(userId));
						}
					});
				}

				@Override
				protected void updateItem(Void item, boolean empty) {
					super.updateItem(item, empty);
					if (empty) {
						setGraphic(null);
					} else {
						HBox hBox = new HBox();
						addToWishlistButton.setDisable(getTableView().getItems().get(getIndex()).getIsWishlistButtonDisabled());
						hBox.getChildren().add(addToWishlistButton);
						hBox.getChildren().add(purchaseButton);
						hBox.getChildren().add(makeOfferButton);
						hBox.setSpacing(10);
						hBox.setAlignment(Pos.CENTER);
						setGraphic(hBox);
					}
				}
			});

			table.getColumns().add(nameColumn);
			table.getColumns().add(categoryColumn);
			table.getColumns().add(sizeColumn);
			table.getColumns().add(priceColumn);
			table.getColumns().add(offerColumn);
			table.getColumns().add(actionColumn);

			int userId = Session.getUserId();
			table.setItems(ItemController.ViewAcceptedItem(userId));

		}
		
		private void initializeLayout() {

			layout = new BorderPane();

			layout.setTop(navigationBox);
			layout.setCenter(table);

			scene = new Scene(layout, SCENE_WIDTH, SCENE_HEIGHT);

		}

		private void initializeAction() {

			viewPurchaseHistoryButton.setOnAction(e -> navigateToPurchaseHistoryPage());

			viewWishlistButton.setOnAction(e -> navigateToWishlistPage());
			
			logoutButton.setOnAction(e -> logoutButtonClicked());

		}
		
		private void navigateToPurchaseHistoryPage() {
			Router.getRouter().navigateTo("Purchase History", BuyerViewFactory.BuyerPurchaseHistoryPage());
		}
		
		private void navigateToWishlistPage() {
			Router.getRouter().navigateTo("Wishlist", BuyerViewFactory.BuyerWishlistPage());
		}
		
		private void logoutButtonClicked() {
			Session.setUserId(0);
			Router.getRouter().navigateTo("Login", GuestViewFactory.LoginPage());
		}

	}

	

