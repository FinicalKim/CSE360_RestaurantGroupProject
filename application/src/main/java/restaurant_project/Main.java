package restaurant_project;



import java.io.*;
import java.io.FileInputStream;
import java.text.DecimalFormat;
import java.util.stream.Stream;
import javafx.animation.Interpolator;
import javafx.animation.Timeline;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.TextFlow;
import javafx.stage.Screen;

public class Main extends Application implements EventHandler<ActionEvent> {

	Stage window;
	Scene scene1, scene2, scene3, scene4, scene5, scene6;

	public static void main(String[] args) throws IOException {

		launch(args);

	}

	public void start(Stage primaryStage) throws Exception, InterruptedException {

		// *DEBUG AND TESTING USER VARIABLE FOR NOW*
		Customer currentUser = new Customer("d", "d");

		//For formatting money
		DecimalFormat df = new DecimalFormat("0.00");

		// Display/stage set-up
		window = primaryStage; // main display window

		// Title the window "OrderUp"
		window.setTitle("OrderUp");
		window.setMaximized(true);
		window.setResizable(true);

		// Get visible bounds of the screen for application resolution
		Rectangle2D screenbounds = Screen.getPrimary().getVisualBounds();

		// ---------------------------------------------------

		// Scene 1 - Sign In Page
		// ---------------

		// Page pane layout
		BorderPane signInMainBorderPane = new BorderPane(); // the main pane for the sign-in page
		signInMainBorderPane.setStyle(
				"-fx-background-image: url('https://thumbs.dreamstime.com/z/food-lunch-boxes-delivery-food-ukrainian-cuisine-wooden-background-top-view-copy-space-food-lunch-boxes-delivery-151206649.jpg');");
		
		HBox signInPageGreetingPane = new HBox(); // the greeting at the top of the main 'signInMainBorderPane' pane
		signInPageGreetingPane.setStyle("-fx-background-color: rgba(0, 0, 0, 0.7); -fx-background-radius: 10;"); // set glassy background for banner																					
		signInPageGreetingPane.setMaxWidth(700);
		signInMainBorderPane.setTop(signInPageGreetingPane);
		BorderPane.setAlignment(signInPageGreetingPane, Pos.TOP_CENTER);
		BorderPane.setMargin(signInPageGreetingPane, new Insets(100, 0, 0, 0));

		VBox signInPageCentralVBox = new VBox(); // pane to hold the signInPageUsername and password labels and text fields
		signInPageCentralVBox.setMaxSize(350, 250);
		setAnimatedBorder(signInPageCentralVBox);
		signInPageCentralVBox.setStyle("-fx-background-color: rgba(0, 100, 100, 0.5); -fx-background-radius: 10;"); // 'rgba' value with 0.5 set for alpha value for transparency
		signInMainBorderPane.setCenter(signInPageCentralVBox);

		HBox signInPageUnderButtons = new HBox(); // pane to hold buttons displayed underneath text fields in the central VBox

		// Header/Greeting Area
		TextFlow orderUpFlow = new TextFlow();

		Text orderUpGreen = new Text("OrderUp");
		orderUpGreen.setFill(Color.LIMEGREEN);
		orderUpGreen.setFont(Font.font("Impact", FontWeight.BOLD, 35));

		Text greetingStart = new Text("Welcome to ");
		greetingStart.setFont(Font.font("Impact", FontWeight.BOLD, 30));
		greetingStart.setFill(Color.WHITESMOKE);

		Text greetingEnd = new Text(", delicious food is just a click away!");
		greetingEnd.setFont(Font.font("Impact", FontWeight.BOLD, 30));
		greetingEnd.setFill(Color.WHITESMOKE);

		orderUpFlow.getChildren().addAll(greetingStart, orderUpGreen, greetingEnd);

		signInPageGreetingPane.getChildren().add(orderUpFlow);

		// Central Login Area

		// Sign-In Button
		Button signInButton = new Button("Sign in");
		Text signInButtonActionText = new Text();

		// Create Account Button
		Button signInPageCreateAccountButton = new Button("Create Account");
		signInPageCreateAccountButton.setOnAction(e -> {
			window.setScene(scene2);
		});

		signInPageUnderButtons.setAlignment(Pos.BOTTOM_CENTER);
		signInPageUnderButtons.setSpacing(10);
		signInPageUnderButtons.setPadding(new Insets(20, 0, 30, 0));
		signInPageUnderButtons.getChildren().addAll(signInButton, signInPageCreateAccountButton);

		Label signInPageLoginInstruct = new Label("Please log-in to get started");
		signInPageLoginInstruct.setTextFill(Color.LIGHTCYAN);
		signInPageLoginInstruct.setPadding(new Insets(0, 0, 25, 0));

		Label signInPageUserName = new Label("Username:");
		signInPageUserName.setTextFill(Color.LIGHTCYAN);
		TextField signInPageUserTextField = new TextField();
		signInPageUserTextField.setPrefWidth(150);
		signInPageUserTextField.setMaxWidth(150);

		Label signInPagePasswordLabel = new Label("Password:");
	 	signInPagePasswordLabel.setTextFill(Color.LIGHTCYAN);
		PasswordField signInPagePasswordBox = new PasswordField();
	 	signInPagePasswordBox.setPrefWidth(150);
	 	signInPagePasswordBox.setMaxWidth(150);

		signInPageCentralVBox.getChildren().addAll(signInPageLoginInstruct, signInPageUserName, signInPageUserTextField, signInPagePasswordLabel, signInPagePasswordBox, signInPageUnderButtons,
				signInButtonActionText);
		signInPageCentralVBox.setAlignment(Pos.CENTER);

		// When the user cliCks on the 'Sign-In' button on the sign-in page
		signInButton.setOnAction(e -> {
			signInButtonActionText.setFill(Color.TOMATO);
			signInButtonActionText.setFont(Font.font("Calibri", FontWeight.BOLD, 15));

			if (currentUser.verifyCredentials(signInPageUserTextField.getText(), signInPagePasswordBox.getText())) {
				signInButtonActionText.setFill(Color.FORESTGREEN);
				signInButtonActionText.setText("Successfully verified credentials... logging in!");
				signInPageUserTextField.clear();
			 signInPagePasswordBox.clear();
				currentUser.login();
				window.setScene(scene3);
			}

			else {
				signInButtonActionText.setText("Username and/or password incorrect.");
			}
		});

		// Display 'scene1' with the default window size
		scene1 = new Scene(signInMainBorderPane, screenbounds.getWidth(), screenbounds.getHeight());



		// Scene 2 - Create Account Page
		// -----------------------------

		// Panes
		BorderPane createAccountPagePane = new BorderPane(); // Main pane for 'scene2'
		createAccountPagePane.setStyle(
				"-fx-background-image: url('https://thumbs.dreamstime.com/z/food-delivery-workdesk-paper-bags-flatware-table-background-top-view-mock-up-restourant-gray-91618763.jpg');"
						+ "-fx-background-size: cover;");

		VBox createAccountPageCentralVBox = new VBox();
		createAccountPageCentralVBox.setStyle("-fx-background-color: rgba(0,0,0, 0.7);");
		createAccountPageCentralVBox.setMaxSize(350, 350);

		HBox createAccountPageHeaderBox = new HBox();
		createAccountPageHeaderBox.setStyle("-fx-background-color: rgba(0,0,0, 0.7); -fx-background-radius: 10;");
		createAccountPageHeaderBox.setMaxSize(500, 500);
		BorderPane.setAlignment(createAccountPageHeaderBox, Pos.TOP_CENTER);
		createAccountPagePane.setTop(createAccountPageHeaderBox);
		BorderPane.setMargin(createAccountPageHeaderBox, new Insets(100, 0, 0, 0));
		createAccountPagePane.setCenter(createAccountPageCentralVBox);

		HBox createAccountPageUnderButtons2 = new HBox();

		HBox createAccountPageUnderButtons2Text = new HBox();

		// Create Account Page buttons

		// Submit Button
		Button submitButton = new Button("Submit");

		// Back Button
		Button backButton = new Button("Back");

		createAccountPageUnderButtons2.getChildren().addAll(submitButton, backButton);
		createAccountPageUnderButtons2.setSpacing(10);
		createAccountPageUnderButtons2.setAlignment(Pos.CENTER);
		createAccountPageUnderButtons2.setPadding(new Insets(20, 0, 0, 0));

		// Header/Greeting Area
		createAccountPageHeaderBox.setAlignment(Pos.CENTER);
		Label accountGreeting = new Label("Create a New Account");
		accountGreeting.setFont(Font.font("Impact", FontWeight.BOLD, 40));
		accountGreeting.setTextFill(Color.WHITESMOKE);
		createAccountPageHeaderBox.getChildren().add(accountGreeting);

		// Central Account Creation Area
		Label createAccountPageUsernameInstructLabel = new Label("Please choose a username");
		createAccountPageUsernameInstructLabel.setTextFill(Color.WHITESMOKE);
		TextField createAccountPageUserTextField = new TextField();
		createAccountPageUserTextField.setMaxWidth(150);

		Label createAccountPagePasswordInstructLabel = new Label("Please choose a password for your account");
		createAccountPagePasswordInstructLabel.setTextFill(Color.WHITESMOKE);
		PasswordField createAccountPagePasswordField = new PasswordField();
		createAccountPagePasswordField.setMaxWidth(150);

		Label createAccountPageContactNameLabel = new Label("Please enter your first and last name");
		createAccountPageContactNameLabel.setTextFill(Color.WHITESMOKE);
		TextField createAccountPageContactNameField = new TextField();
		createAccountPageContactNameField.setMaxWidth(150);

		Label createAccountPageEmailAddressLabel = new Label("Please enter a contact email");
		createAccountPageEmailAddressLabel.setTextFill(Color.WHITESMOKE);
		TextField createAccountPageEmailAddressField = new TextField();
		createAccountPageEmailAddressField.setMaxWidth(150);

		Label createAccountPagePhoneNumberLabel = new Label("Please enter your phone-number");
		createAccountPagePhoneNumberLabel.setTextFill(Color.WHITESMOKE);
		TextField createAccountPagePhoneNumberField = new TextField();
		createAccountPagePhoneNumberField.setMaxWidth(150);

		final Text actiontarget = new Text();
		actiontarget.setFill(Color.FIREBRICK);
		createAccountPageUnderButtons2Text.getChildren().add(actiontarget);
		createAccountPageUnderButtons2Text.setAlignment(Pos.BOTTOM_CENTER);
		createAccountPageUnderButtons2Text.setPadding(new Insets(25, 0, 0, 0));

		createAccountPageCentralVBox.getChildren().addAll(createAccountPageUsernameInstructLabel, createAccountPageUserTextField, createAccountPagePasswordInstructLabel,
				createAccountPagePasswordField,
				createAccountPageContactNameLabel, createAccountPageContactNameField,
				createAccountPageEmailAddressLabel, createAccountPageEmailAddressField, createAccountPagePhoneNumberLabel, createAccountPagePhoneNumberField, createAccountPageUnderButtons2, actiontarget);

		createAccountPageCentralVBox.setSpacing(3);
		createAccountPageCentralVBox.setAlignment(Pos.CENTER);

		backButton.setOnAction(e -> {
			actiontarget.setText("");
			window.setScene(scene1);

		});

		// Sets the action of Submit button to create a new User object with the
		// information input into the text fields on the
		// 'Account Creation' page
		submitButton.setOnAction(e -> {

			// Check for empty fields
			if (createAccountPageUserTextField.getText().isEmpty() || createAccountPagePasswordField.getText().isEmpty()
					|| createAccountPageContactNameField.getText().isEmpty() ||
					createAccountPageEmailAddressField.getText().isEmpty() || createAccountPagePhoneNumberField.getText().isEmpty()) {
				actiontarget.setFill(Color.TOMATO);
				actiontarget.setText("You left a field empty.");

			}

			// Set 'currentUser' attributes to String value inputs
			else if (currentUser.getUserID() == "d" && currentUser.getPassword() == "d") {
				// Set attributes
				currentUser.setUserID(createAccountPageUserTextField.getText());
				currentUser.setPassword(createAccountPagePasswordField.getText());
				currentUser.setContactName(createAccountPageContactNameField.getText());
				currentUser.setEmail(createAccountPageEmailAddressField.getText());
				currentUser.setPhoneNumber(createAccountPagePhoneNumberField.getText());

				// Clear out the text fields
				createAccountPageUserTextField.clear();
			 	createAccountPagePasswordField.clear();
				createAccountPageContactNameField.clear();
				createAccountPageEmailAddressField.clear();
				createAccountPagePhoneNumberField.clear();

				// Display success message
				actiontarget.setFill(Color.FORESTGREEN);
				actiontarget.setText("Account Successfully created!");
			}

			else {
				System.out.println("User already created.");
				System.out.println("currentUser has userID: " + currentUser.getUserID());
			}

		});

		scene2 = new Scene(createAccountPagePane, screenbounds.getWidth(), screenbounds.getHeight());



		// Scene 3 - Menu Page
		// -------------------

		// Panes
		GridPane menuPageGridPane_Left = new GridPane();
		menuPageGridPane_Left.setAlignment(Pos.CENTER);
		menuPageGridPane_Left.setGridLinesVisible(false); // set 'true' to see grid-lines
		menuPageGridPane_Left.setHgap(10);
		menuPageGridPane_Left.setVgap(10);
		menuPageGridPane_Left.setPadding(new Insets(0, 10, 0, 10));
		menuPageGridPane_Left.setMaxHeight(600);
		menuPageGridPane_Left.setStyle("-fx-background-color: #FFE4C4; -fx-background-radius: 10");

		// Header/Greeting
		GridPane menuPageBannerBox = new GridPane();
		menuPageBannerBox.setAlignment(Pos.TOP_RIGHT);
		menuPageBannerBox.setHgap(1);
		menuPageBannerBox.setVgap(1);
		menuPageBannerBox.setGridLinesVisible(false);

		// Navigation Buttons
		HBox menuPageHeaderButtons = new HBox();
		menuPageHeaderButtons.setAlignment(Pos.CENTER);

		Button menuPageSignOutButton = new Button("Sign-Out");
		menuPageSignOutButton.setOnAction(e -> {
			window.setScene(scene1);
			currentUser.resetUser();
			signInButtonActionText.setFill(Color.TOMATO);
			signInButtonActionText.setText("Signed out successfully");

		});

		Button menuPageAccountButton = new Button("Account");
		menuPageAccountButton.setOnAction(e -> {
			currentUser.updateProfile();
			window.setScene(scene4);
		});

		Button menuPageCartButton = new Button("Cart");
		menuPageCartButton.setOnAction(e -> {
			window.setScene(scene5);
		});

		menuPageHeaderButtons.getChildren().addAll(menuPageSignOutButton, menuPageAccountButton, menuPageCartButton);
		menuPageHeaderButtons.setSpacing(5);

		Label menuHeader = new Label("Food Menu");
		menuHeader.setTextFill(Color.DARKOLIVEGREEN);
		menuHeader.setFont(Font.font("Calibri", FontWeight.EXTRA_BOLD, 50));
		menuHeader.setUnderline(true);
		menuPageBannerBox.add(menuHeader, 0, 1);
		menuPageBannerBox.add(menuPageHeaderButtons, 1, 0);
		ColumnConstraints column1 = new ColumnConstraints(300);
		ColumnConstraints column2 = new ColumnConstraints(600);
		menuPageBannerBox.getColumnConstraints().addAll(column1, column2);

		// Main Pane
		BorderPane menuPane = new BorderPane();
		menuPane.setLeft(menuPageGridPane_Left);
		menuPane.setTop(menuPageBannerBox);
		menuPane.setStyle("-fx-background-image: url('https://www.teahub.io/photos/full/41-417767_restaurant-menu-theme-backgrounds-menu-restaurant.jpg');" + "-fx-background-size: cover;");

		Label biscuitLabel = new Label("Biscuit\n$1.99");
		Label cheeseLabel = new Label("Cheese\n$0.99");
		Label eggLabel = new Label("Egg\n$1.39");
		Label sausageLabel = new Label("Sausage\n$2.25");
		Label butterLabel = new Label("Butter\n$0.15");

		// Load images
		Image biscuitImg = new Image(
				new FileInputStream("application\\src\\main\\resources\\Images\\4-2-biscuit-transparent.png"));
		ImageView biscuitImgV = new ImageView();
		biscuitImgV.setImage(biscuitImg);
		biscuitImgV.setFitWidth(75);
		biscuitImgV.setPreserveRatio(true);
		biscuitImgV.setSmooth(true);
		biscuitImgV.setCache(true);

		Image cheeseImg = new Image(
				new FileInputStream("application\\src\\main\\resources\\Images\\11-cheese-sliced-png-image.png"));
		ImageView cheeseImgV = new ImageView();
		cheeseImgV.setImage(cheeseImg);
		cheeseImgV.setFitWidth(75);
		cheeseImgV.setPreserveRatio(true);
		cheeseImgV.setSmooth(true);
		cheeseImgV.setCache(true);

		Image eggImg = new Image(new FileInputStream(
				"application\\src\\main\\resources\\Images\\purepng.com-fried-eggfood-egg-cooking-eating-breakfast-fried-white-yolk-protein-941524632335wlndl.png"));
		ImageView eggImgV = new ImageView();
		eggImgV.setImage(eggImg);
		eggImgV.setFitWidth(75);
		eggImgV.setPreserveRatio(true);
		eggImgV.setSmooth(true);
		eggImgV.setCache(true);

		Image sausageImg = new Image(new FileInputStream(
				"application\\src\\main\\resources\\Images\\71-714077_cooked-sausage-png-transparent-png.png"));
		ImageView sausageImgV = new ImageView();
		sausageImgV.setImage(sausageImg);
		sausageImgV.setFitWidth(75);
		sausageImgV.setPreserveRatio(true);
		sausageImgV.setSmooth(true);
		sausageImgV.setCache(true);

		Image butterImg = new Image(new FileInputStream(
				"application\\src\\main\\resources\\Images\\purepng.com-butterfood-dairy-milk-butter-buttermilk-cream-butterfat-941524621398zsmge.png"));
		ImageView butterImgV = new ImageView();
		butterImgV.setImage(butterImg);
		butterImgV.setFitWidth(75);
		butterImgV.setPreserveRatio(true);
		butterImgV.setSmooth(true);
		butterImgV.setCache(true);
		
		Button addBiscuit = new Button("Add Biscuit");
		addBiscuit.setOnAction(e -> {

			currentUser.getCart().addItem(new Food("Biscuit", "food_biscuit", 1.00));
			
		});

		Button addCheese = new Button("Add Cheese");
		addCheese.setOnAction(e -> {

			currentUser.getCart().addItem(new Food("Cheese", "food_cheese", 0.99));
		});

		Button addEgg = new Button("Add Egg");
		addEgg.setOnAction(e -> {

			currentUser.getCart().addItem(new Food("Egg", "food_egg", 1.39));
		});

		Button addSausage = new Button("Add Sausage");
		addSausage.setOnAction(e -> {

			currentUser.getCart().addItem(new Food("Sausage", "food_sausage", 2.25));
		});

		Button addButter = new Button ("Add Butter");
		addButter.setOnAction(e -> {

			currentUser.getCart().addItem(new Food("Butter", "food_butter", 0.15));
		});


		// Add all of the images/labels to the grid-pane for display
		menuPageGridPane_Left.add(biscuitImgV, 0, 0);
		menuPageGridPane_Left.add(biscuitLabel, 1, 0);
		menuPageGridPane_Left.add(addBiscuit, 2, 0);
		menuPageGridPane_Left.add(cheeseImgV, 0, 1);
		menuPageGridPane_Left.add(cheeseLabel, 1, 1);
		menuPageGridPane_Left.add(addCheese, 2, 1);
		menuPageGridPane_Left.add(eggImgV, 0, 2);
		menuPageGridPane_Left.add(eggLabel, 1, 2);
		menuPageGridPane_Left.add(addEgg, 2, 2);
		menuPageGridPane_Left.add(sausageImgV, 0, 3);
		menuPageGridPane_Left.add(sausageLabel, 1, 3);
		menuPageGridPane_Left.add(addSausage, 2, 3);
		menuPageGridPane_Left.add(butterImgV, 0, 4);
		menuPageGridPane_Left.add(butterLabel, 1, 4);
		menuPageGridPane_Left.add(addButter, 2, 4);

		scene3 = new Scene(menuPane, screenbounds.getWidth(), screenbounds.getHeight());

		// Scene 4 - Account Information Page
		// ----------

		// Panes
		BorderPane accountInfoPage = new BorderPane(); // Main pane for 'scene2'
		accountInfoPage.setStyle(
				"-fx-background-image: url('https://thumbs.dreamstime.com/z/food-delivery-workdesk-paper-bags-flatware-table-background-top-view-mock-up-restourant-gray-91618763.jpg');"
						+ "-fx-background-size: cover;");
		
		VBox accountInfoPageCentralVBox = new VBox();
		accountInfoPageCentralVBox.setStyle("-fx-background-color: rgba(0,0,0, 0.7);" + "-fx-background-radius: 10;");
		accountInfoPageCentralVBox.setMaxSize(350, 350);

		// Header/Greeting holder pane
		GridPane accountInfoPageBannerBox = new GridPane();
		accountInfoPageBannerBox.setAlignment(Pos.TOP_RIGHT);
		accountInfoPageBannerBox.setHgap(1);
		accountInfoPageBannerBox.setVgap(1);
		accountInfoPageBannerBox.setGridLinesVisible(false);
		ColumnConstraints column3 = new ColumnConstraints(600);
		ColumnConstraints column4 = new ColumnConstraints(350);
		accountInfoPageBannerBox.getColumnConstraints().addAll(column3, column4);

		accountInfoPage.setCenter(accountInfoPageCentralVBox);
		accountInfoPage.setTop(accountInfoPageBannerBox);

		HBox accountInfoPageHeaderButtons = new HBox();

		HBox accountInfoPageHeaderLabelBox = new HBox();
		accountInfoPageHeaderLabelBox.setMaxWidth(435);
		accountInfoPageHeaderLabelBox.setAlignment(Pos.TOP_LEFT);
		BorderPane.setMargin(accountInfoPageHeaderLabelBox, new Insets(0,0,0,200));

		Label accountInfoPageHeader = new Label("Account Information");
		accountInfoPageHeader.setTextFill(Color.WHITESMOKE);
		accountInfoPageHeader.setFont(Font.font("Calibri", FontWeight.EXTRA_BOLD, 50));
		accountInfoPageHeader.setUnderline(true);

		accountInfoPageHeaderLabelBox.getChildren().add(accountInfoPageHeader);
		accountInfoPageHeaderLabelBox.setStyle("-fx-background-color: rgba(0,0,0,0.7);" + "-fx-background-radius: 10;");
		Button accountInfoPageMenuButton = new Button("Menu");
		accountInfoPageMenuButton.setOnAction(e -> {
			window.setScene(scene3);
		});

		Button accountInfoPageCartButton = new Button("Cart");
		accountInfoPageCartButton.setOnAction(e -> {
			window.setScene(scene5);
		});

		Button accountInfoPageSignOutButton = new Button("Sign Out");
		accountInfoPageSignOutButton.setOnAction(e -> {
			currentUser.resetUser();
			window.setScene(scene1);
			signInButtonActionText.setFill(Color.TOMATO);
			signInButtonActionText.setText("Signed out successfully");
		});

		accountInfoPageHeaderButtons.setPadding(new Insets(0,50, 0, 0));
		accountInfoPageHeaderButtons.getChildren().addAll(accountInfoPageSignOutButton, accountInfoPageMenuButton, accountInfoPageCartButton);
		accountInfoPageHeaderButtons.setSpacing(5);
		accountInfoPageHeaderButtons.setAlignment(Pos.CENTER);
		accountInfoPageHeader.setAlignment(Pos.CENTER);
		accountInfoPageBannerBox.add(accountInfoPageHeaderButtons, 1, 0);
		accountInfoPageBannerBox.add(accountInfoPageHeaderLabelBox, 0, 1);

		// Labels to hold and display the user's account information
		Label accountInformationPageUsernameLabel = new Label();
		accountInformationPageUsernameLabel.setTextFill(Color.WHITESMOKE);
		Label createAccountPageContactNameLabel_AccInfo = new Label();
		createAccountPageContactNameLabel_AccInfo.setTextFill(Color.WHITESMOKE);
		Label emailLabel_AccInfo = new Label();
		emailLabel_AccInfo.setTextFill(Color.WHITESMOKE);
		Label createAccountPagePhoneNumberlabel_AccInfo = new Label();
		createAccountPagePhoneNumberlabel_AccInfo.setTextFill(Color.WHITESMOKE);
		accountInfoPageCentralVBox.getChildren().addAll(accountInformationPageUsernameLabel, createAccountPageContactNameLabel_AccInfo, emailLabel_AccInfo, createAccountPagePhoneNumberlabel_AccInfo);
		accountInfoPageCentralVBox.setAlignment(Pos.CENTER);

		scene4 = new Scene(accountInfoPage, screenbounds.getWidth(), screenbounds.getHeight());

		// Scene 5 - Cart Page
		// -----------

		BorderPane cartPageMainPane = new BorderPane();
		cartPageMainPane.setStyle(
				"-fx-background-image: url('https://img.freepik.com/free-psd/top-view-free-food-delivery-assortment-with-background-mock-up_23-2148421296.jpg?t=st=1649334513~exp=1649335113~hmac=b77793ec57018e5086c85a58d74ef43481b17cf0f8bf8284edef8efd9f6236c9&w=1060');"
						+ "-fx-background-size: cover;");

		// The pane that will display items in the user's cart
		VBox cartPageItemViewBox = new VBox();
		cartPageItemViewBox.setStyle("-fx-background-color: #d3d3d3; -fx-background-radius: 10;");
		cartPageItemViewBox.setPrefWidth(350);
		cartPageItemViewBox.setMaxHeight(450);
		
		Label yourCartLabel = new Label("Your Cart Items:");
		yourCartLabel.setTextFill(Color.CRIMSON);
		yourCartLabel.setFont(Font.font("Calibri", FontWeight.BOLD, 15));
		yourCartLabel.setUnderline(true);
		yourCartLabel.setPadding(new Insets(10,0,0,20));
		
		HBox cartLabelHBox = new HBox();
		cartLabelHBox.getChildren().add(yourCartLabel);
		cartPageItemViewBox.getChildren().add(cartLabelHBox);
		
		Label cartItemsText = new Label();
		cartItemsText.setPadding(new Insets(25,0,0,20));
		cartItemsText.setFont(Font.font("Calibri", FontWeight.MEDIUM, 15));
		
		HBox cartItemsHBox = new HBox();
		cartItemsHBox.getChildren().add(cartItemsText);
		//cartItemsHBox.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, new CornerRadii(5), new BorderWidths(2))));
		cartPageItemViewBox.getChildren().add(cartItemsHBox);
		
		HBox subtotalBox = new HBox();
		subtotalBox.setPadding(new Insets(20,0,0,20));
		//subtotalBox.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, new CornerRadii(5), new BorderWidths(2))));
		cartPageItemViewBox.getChildren().add(subtotalBox);
		
		Label cartPageSubtotalLabel = new Label();
		cartPageSubtotalLabel.setFont(Font.font("Calibri", FontWeight.BOLD, 15));
		subtotalBox.getChildren().add(cartPageSubtotalLabel);

		// The top of the cart page, which will hold the buttons
		GridPane cartPageBannerGridBox = new GridPane();
		cartPageBannerGridBox.setAlignment(Pos.TOP_RIGHT);
		cartPageBannerGridBox.setHgap(1);
		cartPageBannerGridBox.setVgap(1);
		cartPageBannerGridBox.setGridLinesVisible(false);

		Label cartPageGreeting = new Label("Your Cart");
		cartPageGreeting.setUnderline(true);
		cartPageGreeting.setFont(Font.font("Calibri", FontWeight.EXTRA_BOLD, 50));
		cartPageGreeting.setTextFill(Color.CRIMSON);

		Button cartPageSignOutButton = new Button("Sign Out");
		cartPageSignOutButton.setOnAction(e -> {
			currentUser.resetUser();
			window.setScene(scene1);
			signInButtonActionText.setFill(Color.TOMATO);
			signInButtonActionText.setText("Signed out successfully");
		});

		Button cartPageMenuButton = new Button("View Menu");
		cartPageMenuButton.setOnAction(e -> {
			window.setScene(scene3);
		});

		Button cartPageAccountButton = new Button("Account");
		cartPageAccountButton.setOnAction(e -> {
			window.setScene(scene4);
		});

		// The pane that will display the buttons
		HBox cartPageHeaderButtons = new HBox();
		cartPageHeaderButtons.getChildren().addAll(cartPageSignOutButton, cartPageAccountButton, cartPageMenuButton);
		cartPageHeaderButtons.setSpacing(5);
		cartPageHeaderButtons.setAlignment(Pos.CENTER);

		cartPageBannerGridBox.add(cartPageGreeting, 0, 1);
		cartPageBannerGridBox.add(cartPageHeaderButtons, 1, 0);
		cartPageBannerGridBox.getColumnConstraints().addAll(column1, column2);
		cartPageMainPane.setTop(cartPageBannerGridBox);
		cartPageMainPane.setLeft(cartPageItemViewBox);
		BorderPane.setMargin(cartPageItemViewBox, new Insets(150, 0, 0, 25));

		scene5 = new Scene(cartPageMainPane, screenbounds.getWidth(), screenbounds.getHeight());

		// Display the first scene at program launch
		window.setScene(scene1);
		window.show();

		// A listener event that will execute code when a scene changes from 'oldScene' to 'newScene'
		window.sceneProperty().addListener((Scene, oldScene, newScene) -> {

			if (newScene == scene4) { // Account information page transition
				accountInformationPageUsernameLabel.setText("Username: " + currentUser.getUserID());
				createAccountPageContactNameLabel_AccInfo.setText("Contact Name: " + currentUser.getContactName());
				emailLabel_AccInfo.setText("Email: " + currentUser.getEmail());
				createAccountPagePhoneNumberlabel_AccInfo.setText("Phone: " + currentUser.getPhoneNumber());
			}

			if (newScene == scene5) { // Cart page transition
				cartItemsText.setText(currentUser.getCart().printCartItems());
				String subtotalString = df.format(currentUser.getCart().getSubtotal());
				cartPageSubtotalLabel.setText("Subtotal: $" + subtotalString);
				
			}

		});

	}


	// END MAIN
	//--------------

	// Method 'setBorder()': This method will cycle through a series of colors and
	// uses javafx libraries to create an animated border
	// effect. Currently implemented only for 'VBox' panes, but can be changed if
	// needed.
	private void setAnimatedBorder(VBox signInPageCentralVBox) {
		Color[] colors = Stream
				.of("darkorange", "tomato", "deeppink", "blueviolet", "steelblue", "cornflowerblue", "lightseagreen",
						"#6fba82", "chartreuse", "crimson")
				.map(Color::web)
				.toArray(Color[]::new);

		int mills[] = { 0 };
		KeyFrame keyFrames[] = Stream.iterate(0, i -> i + 1)
				.limit(100)
				.map(i -> new LinearGradient(0, 0, 1, 1, true, CycleMethod.NO_CYCLE,
						new Stop[] { new Stop(0, colors[i % colors.length]),
								new Stop(1, colors[(i + 1) % colors.length]) }))
				.map(lg -> new Border(
						new BorderStroke(lg, BorderStrokeStyle.SOLID, new CornerRadii(5), new BorderWidths(2))))
				.map(b -> new KeyFrame(Duration.millis(mills[0] += 250),
						new KeyValue(signInPageCentralVBox.borderProperty(), b, Interpolator.EASE_IN)))
				.toArray(KeyFrame[]::new);

		Timeline timeline = new Timeline(keyFrames);
		timeline.setCycleCount(Timeline.INDEFINITE);
		timeline.play();
	}

	@Override
	public void handle(ActionEvent arg0) {
		// TODO Auto-generated method stub

	}
}
