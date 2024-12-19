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
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import model.Item;
import util.Router;
import util.Session;
import view.factory.BuyerViewFactory;
import view.factory.GuestViewFactory;


	
	public class BuyerHomeView {
		
		private Button clearSearchButton;
		private TextField searchField;
		private Button searchButton;
		
		private void initializeSearchBar() {
		    
		    searchField = new TextField();
		    searchField.setPromptText("Search items...");
		    searchButton = new Button("Search");
		    clearSearchButton = new Button("Clear");

		    
		    searchButton.setOnAction(e -> filterTableData());

		    
		    clearSearchButton.setOnAction(e -> clearSearch());

		   
		    HBox searchBox = new HBox(10, searchField, searchButton, clearSearchButton);
		    searchBox.setAlignment(Pos.CENTER_LEFT);
		    searchBox.setPadding(new Insets(10));
		    layout.setTop(new VBox(navigationBox, searchBox));
		}
		
		private void filterTableData() {
		    String searchQuery = searchField.getText().toLowerCase();

		    int userId = Session.getUserId();
		    var allItems = ItemController.ViewAcceptedItem(userId);

		    var filteredItems = allItems.filtered(item ->
		        item.getName().toLowerCase().contains(searchQuery) ||
		        item.getCategory().toLowerCase().contains(searchQuery)
		    );

		    table.setItems(filteredItems);
		}

		private void clearSearch() {
		    
		    searchField.clear();

		   
		    int userId = Session.getUserId();
		    table.setItems(ItemController.ViewAcceptedItem(userId));
		}
		
		
		
		int SCENE_WIDTH = 800;
		int SCENE_HEIGHT = 450;

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
			initializeSearchBar();
		}

		public Scene getScene() {
			return scene;
		}
		
		private void initializeNavigationBox() {
		    viewPurchaseHistoryButton = new Button("View Purchase History");
		    viewWishlistButton = new Button("View Wishlist");
		    logoutButton = new Button("Logout");

		    label = new Label("CaLouse1F");
		    label.setFont(Font.font("System", FontWeight.BOLD, 14));

		    Label welcomeLabel = new Label("Welcome, " + Session.getUsername() + "!");
		    welcomeLabel.setFont(Font.font("System", FontWeight.BOLD, 20));

		    spacer = new Region();
		    HBox.setHgrow(spacer, Priority.ALWAYS);

		    HBox group1 = new HBox(10, viewPurchaseHistoryButton, viewWishlistButton);
		    HBox group2 = new HBox(10, logoutButton);

		    VBox buttonBox = new VBox(10, group1, group2);
		    buttonBox.setAlignment(Pos.TOP_RIGHT);

		    VBox labelBox = new VBox(5, label, welcomeLabel);

		    navigationBox = new HBox(labelBox, spacer, buttonBox);
		    navigationBox.setPadding(new Insets(8));
		    navigationBox.setAlignment(Pos.CENTER_LEFT);
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
							
							addToWishlistButton.setDisable(true);
						}
					});
					purchaseButton.setOnAction(e -> {
						
						if(PurchaseConfirmationPopUp.display()) {						
							int userId = Session.getUserId();
							int itemId = getTableView().getItems().get(getIndex()).getId();
							
							
							TransactionController.PurchaseItems(userId, itemId);
							
							
							ItemController.UpdateStatusToSold(itemId);
							
							
							WishlistController.RemoveWishlistWithItemId(itemId);
							
							table.setItems(ItemController.ViewAcceptedItem(userId));
						}
					});
					makeOfferButton.setOnAction(e -> {
						int oldOffer = getTableView().getItems().get(getIndex()).getOffer();
						
						
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
			layout.setStyle("-fx-background-color:  #D4EDDA;");
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
