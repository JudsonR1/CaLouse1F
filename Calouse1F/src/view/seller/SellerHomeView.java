package view.seller;

import controller.ItemController;
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
import view.factory.GuestViewFactory;
import view.factory.SellerViewFactory;

public class SellerHomeView {


	// 16:9
	int SCENE_WIDTH = 640;
	int SCENE_HEIGHT = 360;

	private Scene scene;

	private BorderPane layout;

	private Button uploadItemButton;
	private Button offeredItemButton;
	private Button soldItemButton;
	private Button pendingItemButton;

	private HBox navigationBox;
	private Label label;
	private Region spacer;
	private Button logoutButton;

	private TableView<Item> table;

	public SellerHomeView() {
		initializeNavigationBox();
		initializeTable();
		initializeLayout();
		initializeAction();
	}

	public Scene getScene() {
		return scene;
	}
	
	private void initializeNavigationBox() {
		
		uploadItemButton = new Button("Upload Item");
		offeredItemButton = new Button("Offered Item");
		soldItemButton = new Button("Sold Item");
		pendingItemButton = new Button("Pending Item");

		navigationBox = new HBox();
		label = new Label("Logo");
		spacer = new Region();
		logoutButton = new Button("Logout");

		HBox.setHgrow(spacer, Priority.ALWAYS);

		navigationBox.getChildren().addAll(label, spacer, uploadItemButton, offeredItemButton, soldItemButton,
				pendingItemButton, logoutButton);
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
		TableColumn<Item, Void> actionColumn = new TableColumn<Item, Void>("Action");

		nameColumn.setCellValueFactory(new PropertyValueFactory<Item, String>("name"));
		categoryColumn.setCellValueFactory(new PropertyValueFactory<Item, String>("category"));
		sizeColumn.setCellValueFactory(new PropertyValueFactory<Item, String>("size"));
		priceColumn.setCellValueFactory(new PropertyValueFactory<Item, String>("price"));
		actionColumn.setCellFactory(param -> new TableCell<Item, Void>() {
			private final Button editButton = new Button("Edit");
			private final Button deleteButton = new Button("Delete");

			{
				editButton.setOnAction(e -> {
					Item item = getTableView().getItems().get(getIndex());
					Router.getRouter().navigateTo("Edit Item", SellerViewFactory.SellerEditItemPage(item));
				});

				deleteButton.setOnAction(e -> {
					int id = getTableView().getItems().get(getIndex()).getId();
					if(ItemController.DeleteItem(id)) {
						// refresh table
						int userId = Session.getUserId();
						table.setItems(ItemController.ViewSellerAcceptedItem(userId));
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
					hBox.getChildren().add(editButton);
					hBox.getChildren().add(deleteButton);
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
		table.getColumns().add(actionColumn);

		int userId = Session.getUserId();
		table.setItems(ItemController.ViewSellerAcceptedItem(userId));
		
	}
	
	private void initializeLayout() {

		layout = new BorderPane();

		layout.setTop(navigationBox);
		layout.setCenter(table);

		scene = new Scene(layout, SCENE_WIDTH, SCENE_HEIGHT);

	}

	private void initializeAction() {

		logoutButton.setOnAction(e -> logoutButtonClicked());

		uploadItemButton.setOnAction(e -> navigateToSellerUploadItemPage());

		offeredItemButton.setOnAction(e -> navigateToSellerOfferedItemPage());

		soldItemButton.setOnAction(e -> navigateToSellerSoldItemPage());

		pendingItemButton.setOnAction(e -> navigateToSellerPendingItemPage());

	}
	
	private void logoutButtonClicked() {
		Session.setUserId(0);
		Router.getRouter().navigateTo("Login", GuestViewFactory.LoginPage());
	}
	private void navigateToSellerUploadItemPage() {
		Router.getRouter().navigateTo("Upload Item", SellerViewFactory.SellerUploadItemPage());
	}
	private void navigateToSellerOfferedItemPage() {
		Router.getRouter().navigateTo("Offered Item", SellerViewFactory.SellerOfferedItemPage());
	}
	private void navigateToSellerSoldItemPage() {
		Router.getRouter().navigateTo("Sold Item", SellerViewFactory.SellerSoldItemPage());
	}
	private void navigateToSellerPendingItemPage() {
		Router.getRouter().navigateTo("Pending Item", SellerViewFactory.SellerPendingItemPage());
	}


}
