package view.buyer;

import controller.TransactionController;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
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

public class BuyerPurchaseHistoryView {

	
	int SCENE_WIDTH = 800;
	int SCENE_HEIGHT = 450;

	private Scene scene;

	private BorderPane layout;

	private HBox navigationBox;
	private Button backButton;
	private Region spacer;
	private Button logoutButton;

	private TableView<Item> table;

	public BuyerPurchaseHistoryView() {
		initializeNavigationBox();
		initializeTable();
		initializeLayout();
		initializeAction();
	}

	public Scene getScene() {
		return scene;
	}
	
	private void initializeNavigationBox() {
		
		navigationBox = new HBox();
		backButton = new Button("Home");
		spacer = new Region();
		logoutButton = new Button("Logout");

		HBox.setHgrow(spacer, Priority.ALWAYS);

		navigationBox.getChildren().addAll(backButton, spacer, logoutButton);
		navigationBox.setPadding(new Insets(8));
		
	}
	
	private void initializeTable() {
		
		table = new TableView<Item>();

		TableColumn<Item, String> nameColumn = new TableColumn<Item, String>("Name");
		TableColumn<Item, String> categoryColumn = new TableColumn<Item, String>("Category");
		TableColumn<Item, String> sizeColumn = new TableColumn<Item, String>("Size");
		TableColumn<Item, String> priceColumn = new TableColumn<Item, String>("Price");
		TableColumn<Item, String> offerColumn = new TableColumn<Item, String>("Offer");

		nameColumn.setCellValueFactory(new PropertyValueFactory<Item, String>("name"));
		categoryColumn.setCellValueFactory(new PropertyValueFactory<Item, String>("category"));
		sizeColumn.setCellValueFactory(new PropertyValueFactory<Item, String>("size"));
		priceColumn.setCellValueFactory(new PropertyValueFactory<Item, String>("price"));
		offerColumn.setCellValueFactory(new PropertyValueFactory<Item, String>("offer"));

		table.getColumns().add(nameColumn);
		table.getColumns().add(categoryColumn);
		table.getColumns().add(sizeColumn);
		table.getColumns().add(priceColumn);
		table.getColumns().add(offerColumn);

		table.setItems(TransactionController.ViewHistory());
		
	}
	
	private void initializeLayout() {

		layout = new BorderPane();

		layout.setTop(navigationBox);
		layout.setCenter(table);
		layout.setStyle("-fx-background-color:  #D4EDDA;");

		scene = new Scene(layout, SCENE_WIDTH, SCENE_HEIGHT);

	}

	private void initializeAction() {

		backButton.setOnAction(e -> navigateToBuyerHomePage());

		logoutButton.setOnAction(e -> logoutButtonClicked());

	}
	
	private void navigateToBuyerHomePage() {
		Router.getRouter().navigateTo("", BuyerViewFactory.BuyerHomePage());
	}
	
	private void logoutButtonClicked() {
		Session.setUserId(0);
		Router.getRouter().navigateTo("Login", GuestViewFactory.LoginPage());
	}

}
