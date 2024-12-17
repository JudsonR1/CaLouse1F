package view.admin;

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
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import model.Item;
import util.Router;
import util.Session;
import view.factory.GuestViewFactory;


public class AdminHomeView {


	
	int SCENE_WIDTH = 800;
	int SCENE_HEIGHT = 450;

	private Scene scene;

	private BorderPane layout;

	private HBox navigationBox;
	private Label label;
	private Region spacer;
	private Button logoutButton;

	private TableView<Item> table;

	public AdminHomeView() {
		initializeNavigationBox();
		initializeTable();
		initializeLayout();
		initializeAction();
	}

	public Scene getScene() {
		return scene;
	}
	
	private void initializeNavigationBox() {
	    
	    label = new Label("CaLouse1F");
	    label.setFont(Font.font("System", FontWeight.BOLD, 14));

	    Label welcomeLabel = new Label("Welcome, admin!" );
	    welcomeLabel.setFont(Font.font("System", FontWeight.BOLD, 20));

	    spacer = new Region();
	    logoutButton = new Button("Logout");

	    HBox.setHgrow(spacer, Priority.ALWAYS);

	    VBox labelBox = new VBox(5, label, welcomeLabel);

	    VBox buttonBox = new VBox(10, logoutButton);
	    buttonBox.setAlignment(Pos.TOP_RIGHT);

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
		TableColumn<Item, Void> actionColumn = new TableColumn<Item, Void>("Action");

		nameColumn.setCellValueFactory(new PropertyValueFactory<Item, String>("name"));
		categoryColumn.setCellValueFactory(new PropertyValueFactory<Item, String>("category"));
		sizeColumn.setCellValueFactory(new PropertyValueFactory<Item, String>("size"));
		priceColumn.setCellValueFactory(new PropertyValueFactory<Item, String>("price"));
		actionColumn.setCellFactory(param -> new TableCell<Item, Void>() {
			private final Button approveButton = new Button("Approve");
			private final Button declineButton = new Button("Decline");
			{
				approveButton.setOnAction(e -> {
					int id = getTableView().getItems().get(getIndex()).getId();
					if(ItemController.ApproveItem(id)) {
						
						table.setItems(ItemController.ViewRequestedItem());
					}
				});

				declineButton.setOnAction(e -> {
					
					String reason = DeclineItemReasonPrompt.display();
					if(reason != null && !reason.isEmpty()) {						
						int id = getTableView().getItems().get(getIndex()).getId();
						if(ItemController.DeclineItem(id)) {
						
							table.setItems(ItemController.ViewRequestedItem());
						}
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
					hBox.getChildren().add(approveButton);
					hBox.getChildren().add(declineButton);
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

		table.setItems(ItemController.ViewRequestedItem());
		
	}
	
	private void initializeLayout() {

		layout = new BorderPane();

		layout.setTop(navigationBox);
		layout.setCenter(table);
		layout.setStyle("-fx-background-color:  #D4EDDA;");
		scene = new Scene(layout, SCENE_WIDTH, SCENE_HEIGHT);

	}

	private void initializeAction() {

		logoutButton.setOnAction(e -> logoutButtonClicked());

	}
	
	private void logoutButtonClicked() {
		Session.setUserId(0);
		Router.getRouter().navigateTo("Login", GuestViewFactory.LoginPage());
	}


}
