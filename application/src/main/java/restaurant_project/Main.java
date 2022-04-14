package runfx;

import java.io.*;
import java.io.FileInputStream;
import java.text.DecimalFormat;
import java.util.stream.Stream;
import javafx.animation.Interpolator;
import javafx.animation.Timeline;
import javafx.scene.layout.AnchorPane;
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
import javafx.collections.ListChangeListener;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.PasswordField;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Separator;
import javafx.scene.control.TextField;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.TextFlow;
import javafx.stage.Screen;

public class Main extends Application implements EventHandler<ActionEvent> {

	Stage window;

	public static void main(String[] args) throws IOException {

		launch(args);

	}

	public void start(Stage primaryStage) throws Exception, InterruptedException {

		// *DEBUG AND TESTING USER VARIABLE FOR NOW*
		Customer currentUser = new Customer("DEFAULT", "DEFAULT");

		// For formatting money
		DecimalFormat df = new DecimalFormat("0.00");

		// Display/stage set-up
		window = primaryStage; // main display window

		// Title the window "OrderUp"
		window.setTitle("OrderUp");
		window.setMaximized(true);
		window.setResizable(true);

		// Get visible bounds of the screen for application resolution
		Rectangle2D screenbounds = Screen.getPrimary().getVisualBounds();
		final double MAX_WIDTH = screenbounds.getWidth();
		final double MAX_HEIGHT = screenbounds.getHeight();

		final double MARGIN = (MAX_WIDTH / 7) / 3;
		// ---------------------------------------------------
		// URL's
		final String SIGNIN_BG = "https://thumbs.dreamstime.com/z/food-lunch-boxes-delivery-food-ukrainian-cuisine-wooden-background-top-view-copy-space-food-lunch-boxes-delivery-151206649.jpg";
		final String CREATE_ACCOUNT_BG = "https://thumbs.dreamstime.com/z/food-delivery-workdesk-paper-bags-flatware-table-background-top-view-mock-up-restourant-gray-91618763.jpg";
		final String ACCOUNT_PAGE_BG = "https://thumbs.dreamstime.com/z/food-delivery-workdesk-paper-bags-flatware-table-background-top-view-mock-up-restourant-gray-91618763.jpg";
		final String CART_PAGE_BG = "https://img.freepik.com/free-psd/top-view-free-food-delivery-assortment-with-background-mock-up_23-2148421296.jpg?t=st=1649334513~exp=1649335113~hmac=b77793ec57018e5086c85a58d74ef43481b17cf0f8bf8284edef8efd9f6236c9&w=1060";
		final String MENU_PAGE_BG = "https://www.teahub.io/photos/full/41-417767_restaurant-menu-theme-backgrounds-menu-restaurant.jpg";
		//
		// The Separate Panes for each page
		// ---------------------------------
		BorderPane menuPane = new BorderPane();
		menuPane.setPrefSize(MAX_WIDTH, MAX_HEIGHT);

		BorderPane createAccountPagePane = new BorderPane();
		createAccountPagePane.setPrefSize(MAX_WIDTH, MAX_HEIGHT);

		BorderPane signInMainBorderPane = new BorderPane();
		signInMainBorderPane.setPrefSize(MAX_WIDTH, MAX_HEIGHT);

		BorderPane cartPageMainPane = new BorderPane();
		cartPageMainPane.setPrefSize(MAX_WIDTH, MAX_HEIGHT);

		BorderPane accountInfoPage = new BorderPane();
		accountInfoPage.setPrefSize(MAX_WIDTH, MAX_HEIGHT);

		// Setting up the main pane
		// ------------------------
		AnchorPane index = new AnchorPane();

		VBox mainVbox = new VBox();
		mainVbox.setPrefWidth(MAX_WIDTH);

		HBox navbar = new HBox();
		navbar.setStyle("-fx-background-color: forestgreen");
		navbar.setAlignment(Pos.TOP_CENTER);
		navbar.setPrefHeight(50);
		navbar.setPadding(new Insets(17, 0, 17, 0));

		AnchorPane currentPage = new AnchorPane();
		currentPage.setPrefWidth(MAX_WIDTH);

		// Navbar Elements
		// ----------------
		Text homeButton = new Text("Home");
		Text aboutButton = new Text("About");
		Text viewMenuButton = new Text("View Menu");
		Text logInButton = new Text("Log-in");
		Text helpButton = new Text("Help");
		Text contactButton = new Text("Contact");
		Text cartButton = new Text("View Cart 🛒");
		homeButton.setFill(Color.WHITE);
		aboutButton.setFill(Color.WHITE);
		viewMenuButton.setFill(Color.WHITE);
		logInButton.setFill(Color.WHITE);
		helpButton.setFill(Color.WHITE);
		contactButton.setFill(Color.WHITE);
		cartButton.setFill(Color.WHITE);
		final Insets NAVBAR_MARGIN = new Insets(0, MARGIN, 0, MARGIN);
		HBox.setMargin(homeButton, NAVBAR_MARGIN);
		HBox.setMargin(aboutButton, NAVBAR_MARGIN);
		HBox.setMargin(viewMenuButton, NAVBAR_MARGIN);
		HBox.setMargin(logInButton, NAVBAR_MARGIN);
		HBox.setMargin(helpButton, NAVBAR_MARGIN);
		HBox.setMargin(contactButton, NAVBAR_MARGIN);
		HBox.setMargin(cartButton, NAVBAR_MARGIN);

		// Vertical seperators
		// -------------------
		Separator sep1 = new Separator(Orientation.VERTICAL);
		Separator sep2 = new Separator(Orientation.VERTICAL);
		Separator sep3 = new Separator(Orientation.VERTICAL);
		Separator sep4 = new Separator(Orientation.VERTICAL);
		Separator sep5 = new Separator(Orientation.VERTICAL);
		Separator sep6 = new Separator(Orientation.VERTICAL);

		// Hover effects navbar
		// --------------------
		homeButton.setOnMouseEntered(e -> {
			index.setCursor(Cursor.HAND);
			homeButton.setOpacity(0.6);
		});
		homeButton.setOnMouseExited(e -> {
			index.setCursor(Cursor.DEFAULT);
			homeButton.setOpacity(1);
		});

		aboutButton.setOnMouseEntered(e -> {
			index.setCursor(Cursor.HAND);
			aboutButton.setOpacity(0.6);
		});
		aboutButton.setOnMouseExited(e -> {
			index.setCursor(Cursor.DEFAULT);
			aboutButton.setOpacity(1);
		});

		viewMenuButton.setOnMouseEntered(e -> {
			if (!currentPage.getChildren().contains(menuPane))
				index.setCursor(Cursor.HAND);
			viewMenuButton.setOpacity(0.6);
		});
		viewMenuButton.setOnMouseExited(e -> {
			index.setCursor(Cursor.DEFAULT);
			if (!currentPage.getChildren().contains(menuPane))
				viewMenuButton.setOpacity(1);
		});

		logInButton.setOnMouseEntered(e -> {
			if (!currentPage.getChildren().contains(signInMainBorderPane) &&
					!currentPage.getChildren().contains(accountInfoPage) &&
					!currentPage.getChildren().contains(createAccountPagePane))
				index.setCursor(Cursor.HAND);
			logInButton.setOpacity(0.6);
		});
		logInButton.setOnMouseExited(e -> {
			index.setCursor(Cursor.DEFAULT);
			if (!currentPage.getChildren().contains(signInMainBorderPane) &&
					!currentPage.getChildren().contains(accountInfoPage) &&
					!currentPage.getChildren().contains(createAccountPagePane))
				logInButton.setOpacity(1);
		});

		helpButton.setOnMouseEntered(e -> {
			index.setCursor(Cursor.HAND);
			helpButton.setOpacity(0.6);
		});
		helpButton.setOnMouseExited(e -> {
			index.setCursor(Cursor.DEFAULT);
			helpButton.setOpacity(1);
		});

		contactButton.setOnMouseEntered(e -> {
			index.setCursor(Cursor.HAND);
			contactButton.setOpacity(0.6);
		});
		contactButton.setOnMouseExited(e -> {
			index.setCursor(Cursor.DEFAULT);
			contactButton.setOpacity(1);
		});

		cartButton.setOnMouseEntered(e -> {
			if (!currentPage.getChildren().contains(cartPageMainPane))
				index.setCursor(Cursor.HAND);
			cartButton.setOpacity(0.6);
		});
		cartButton.setOnMouseExited(e -> {
			index.setCursor(Cursor.DEFAULT);
			if (!currentPage.getChildren().contains(cartPageMainPane))
				cartButton.setOpacity(1);
		});

		// Adding elements together
		// ------------------------
		navbar.getChildren().addAll(homeButton, sep1, aboutButton, sep2,
				viewMenuButton, sep3, logInButton, sep4, helpButton, sep5, contactButton, sep6, cartButton);
		mainVbox.getChildren().addAll(navbar, currentPage);
		index.getChildren().addAll(mainVbox);

		// Start on log in page
		currentPage.getChildren().setAll(signInMainBorderPane);
		Scene mainScene = new Scene(index, MAX_WIDTH, MAX_HEIGHT);

		// Sign In Page
		// ---------------

		// Page pane layout
		signInMainBorderPane.setStyle(
				"-fx-background-image: url('" + SIGNIN_BG + "');");

		// the greeting at the top of the main 'signInMainBorderPane' pane
		HBox signInPageGreetingPane = new HBox();

		// set glassy background for banner
		signInPageGreetingPane.setStyle("-fx-background-color: rgba(0, 0, 0, 0.7); -fx-background-radius: 10;");
		signInPageGreetingPane.setMaxWidth(700);
		signInMainBorderPane.setTop(signInPageGreetingPane);
		BorderPane.setAlignment(signInPageGreetingPane, Pos.TOP_CENTER);
		BorderPane.setMargin(signInPageGreetingPane, new Insets(100, 0, 0, 0));

		// pane to hold the signInPageUsername and password labels and text fields
		VBox signInPageCentralVBox = new VBox();
		signInPageCentralVBox.setMaxSize(350, 250);
		setAnimatedBorder(signInPageCentralVBox);
		// 'rgba' value with 0.5 set for alpha value for transparency
		signInPageCentralVBox.setStyle("-fx-background-color: rgba(0, 100, 100, 0.5); -fx-background-radius: 10;");
		signInMainBorderPane.setCenter(signInPageCentralVBox);

		// pane to hold buttons displayed underneath text fields in the central VBox
		HBox signInPageUnderButtons = new HBox();

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
			currentPage.getChildren().setAll(createAccountPagePane);
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

		signInPageCentralVBox.getChildren().addAll(signInPageLoginInstruct, signInPageUserName, signInPageUserTextField,
				signInPagePasswordLabel, signInPagePasswordBox, signInPageUnderButtons,
				signInButtonActionText);
		signInPageCentralVBox.setAlignment(Pos.CENTER);

		// Display 'scene1' with the default window size

		// Scene 2 - Create Account Page
		// -----------------------------

		// Panes
		// createAccountPagePane = new BorderPane(); // Main pane for 'scene2'
		createAccountPagePane.setStyle(
				"-fx-background-image: url('" + CREATE_ACCOUNT_BG + "');"
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

		createAccountPageCentralVBox.getChildren().addAll(createAccountPageUsernameInstructLabel,
				createAccountPageUserTextField, createAccountPagePasswordInstructLabel,
				createAccountPagePasswordField,
				createAccountPageContactNameLabel, createAccountPageContactNameField,
				createAccountPageEmailAddressLabel, createAccountPageEmailAddressField,
				createAccountPagePhoneNumberLabel, createAccountPagePhoneNumberField, createAccountPageUnderButtons2,
				actiontarget);

		createAccountPageCentralVBox.setSpacing(3);
		createAccountPageCentralVBox.setAlignment(Pos.CENTER);

		backButton.setOnAction(e -> {
			actiontarget.setText("");
			currentPage.getChildren().setAll(signInMainBorderPane);
		});

		// Sets the action of Submit button to create a new User object with the
		// information input into the text fields on the
		// 'Account Creation' page
		submitButton.setOnAction(e -> {

			// Check for empty fields
			if (createAccountPageUserTextField.getText().isEmpty() || createAccountPagePasswordField.getText().isEmpty()
					|| createAccountPageContactNameField.getText().isEmpty() ||
					createAccountPageEmailAddressField.getText().isEmpty()
					|| createAccountPagePhoneNumberField.getText().isEmpty()) {
				actiontarget.setFill(Color.TOMATO);
				actiontarget.setText("You left a field empty.");

			}

			// Set 'currentUser' attributes to String value inputs
			else if (currentUser.getUserID() == "DEFAULT" && currentUser.getPassword() == "DEFAULT") {
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
			currentPage.getChildren().setAll(signInMainBorderPane);
			currentUser.resetUser();
			signInButtonActionText.setFill(Color.TOMATO);
			signInButtonActionText.setText("Signed out successfully");
			logInButton.setText("Log-in");
		});

		Button menuPageAccountButton = new Button("Account");
		menuPageAccountButton.setOnAction(e -> {
			currentUser.updateProfile();
			currentPage.getChildren().setAll(accountInfoPage);
		});

		Button menuPageCartButton = new Button("Cart");
		menuPageCartButton.setOnAction(e -> {
			currentPage.getChildren().setAll(cartPageMainPane);
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
		// menuPane = new BorderPane();
		menuPane.setLeft(menuPageGridPane_Left);
		menuPane.setTop(menuPageBannerBox);
		menuPane.setStyle(
				"-fx-background-image: url('" + MENU_PAGE_BG + "');"
						+ "-fx-background-size: cover;");

		Label biscuitLabel = new Label("Biscuit\n$1.99");
		Label cheeseLabel = new Label("Cheese\n$0.99");
		Label eggLabel = new Label("Egg\n$1.39");
		Label sausageLabel = new Label("Sausage\n$2.25");
		Label butterLabel = new Label("Butter\n$0.15");

		// Load images
		Image biscuitImg = new Image(new FileInputStream(
				"application\\src\\main\\resources\\Images\\biscuit.png"));
		ImageView biscuitImgV = new ImageView();
		biscuitImgV.setImage(biscuitImg);
		biscuitImgV.setFitWidth(75);
		biscuitImgV.setPreserveRatio(true);
		biscuitImgV.setSmooth(true);
		biscuitImgV.setCache(true);

		Image cheeseImg = new Image(new FileInputStream(
				"application\\src\\main\\resources\\Images\\cheese.png"));
		ImageView cheeseImgV = new ImageView();
		cheeseImgV.setImage(cheeseImg);
		cheeseImgV.setFitWidth(75);
		cheeseImgV.setPreserveRatio(true);
		cheeseImgV.setSmooth(true);
		cheeseImgV.setCache(true);

		Image eggImg = new Image(new FileInputStream(
				"application\\src\\main\\resources\\Images\\egg.png"));
		ImageView eggImgV = new ImageView();
		eggImgV.setImage(eggImg);
		eggImgV.setFitWidth(75);
		eggImgV.setPreserveRatio(true);
		eggImgV.setSmooth(true);
		eggImgV.setCache(true);

		Image sausageImg = new Image(new FileInputStream(
				"application\\src\\main\\resources\\Images\\sausage.png"));
		ImageView sausageImgV = new ImageView();
		sausageImgV.setImage(sausageImg);
		sausageImgV.setFitWidth(75);
		sausageImgV.setPreserveRatio(true);
		sausageImgV.setSmooth(true);
		sausageImgV.setCache(true);

		Image butterImg = new Image(new FileInputStream(
				"application\\src\\main\\resources\\Images\\butter.png"));
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

		Button addButter = new Button("Add Butter");
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

		// Scene 4 - Account Information Page
		// ----------

		// Panes
		// accountInfoPage = new BorderPane(); // Main pane for 'scene2'
		accountInfoPage.setStyle(
				"-fx-background-image: url('" + ACCOUNT_PAGE_BG + "');"
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
		BorderPane.setMargin(accountInfoPageHeaderLabelBox, new Insets(0, 0, 0, 200));

		Label accountInfoPageHeader = new Label("Account Information");
		accountInfoPageHeader.setTextFill(Color.WHITESMOKE);
		accountInfoPageHeader.setFont(Font.font("Calibri", FontWeight.EXTRA_BOLD, 50));
		accountInfoPageHeader.setUnderline(true);

		accountInfoPageHeaderLabelBox.getChildren().add(accountInfoPageHeader);
		accountInfoPageHeaderLabelBox.setStyle("-fx-background-color: rgba(0,0,0,0.7);" + "-fx-background-radius: 10;");
		Button accountInfoPageMenuButton = new Button("Menu");
		accountInfoPageMenuButton.setOnAction(e -> {
			currentPage.getChildren().setAll(menuPane);
		});

		Button accountInfoPageCartButton = new Button("Cart");
		accountInfoPageCartButton.setOnAction(e -> {
			currentPage.getChildren().setAll(cartPageMainPane);
		});

		Button accountInfoPageSignOutButton = new Button("Sign Out");
		accountInfoPageSignOutButton.setOnAction(e -> {
			currentUser.resetUser();
			currentPage.getChildren().setAll(signInMainBorderPane);
			signInButtonActionText.setFill(Color.TOMATO);
			signInButtonActionText.setText("Signed out successfully");
		});

		accountInfoPageHeaderButtons.setPadding(new Insets(0, 50, 0, 0));
		accountInfoPageHeaderButtons.getChildren().addAll(accountInfoPageSignOutButton, accountInfoPageMenuButton,
				accountInfoPageCartButton);
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
		accountInfoPageCentralVBox.getChildren().addAll(accountInformationPageUsernameLabel,
				createAccountPageContactNameLabel_AccInfo, emailLabel_AccInfo,
				createAccountPagePhoneNumberlabel_AccInfo);
		accountInfoPageCentralVBox.setAlignment(Pos.CENTER);

		// Scene 5 - Cart Page
		// -----------

		// cartPageMainPane = new BorderPane();
		cartPageMainPane.setStyle(
				"-fx-background-image: url('" + CART_PAGE_BG + "');"
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
		yourCartLabel.setPadding(new Insets(10, 0, 0, 20));

		HBox cartLabelHBox = new HBox();
		cartLabelHBox.getChildren().add(yourCartLabel);
		cartPageItemViewBox.getChildren().add(cartLabelHBox);

		Label cartItemsText = new Label();
		cartItemsText.setPadding(new Insets(25, 0, 0, 20));
		cartItemsText.setFont(Font.font("Calibri", FontWeight.MEDIUM, 15));

		HBox cartItemsHBox = new HBox();
		cartItemsHBox.getChildren().add(cartItemsText);
		// cartItemsHBox.setBorder(new Border(new BorderStroke(Color.BLACK,
		// BorderStrokeStyle.SOLID, new CornerRadii(5), new BorderWidths(2))));
		cartPageItemViewBox.getChildren().add(cartItemsHBox);

		HBox subtotalBox = new HBox();
		subtotalBox.setPadding(new Insets(20, 0, 0, 20));
		// subtotalBox.setBorder(new Border(new BorderStroke(Color.BLACK,
		// BorderStrokeStyle.SOLID, new CornerRadii(5), new BorderWidths(2))));
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
			logInButton.setText("Log-In");
			currentPage.getChildren().setAll(signInMainBorderPane);
			signInButtonActionText.setFill(Color.TOMATO);
			signInButtonActionText.setText("Signed out successfully");
		});

		Button cartPageMenuButton = new Button("View Menu");
		cartPageMenuButton.setOnAction(e -> {
			currentPage.getChildren().setAll(menuPane);
		});

		Button cartPageAccountButton = new Button("Account");
		cartPageAccountButton.setOnAction(e -> {
			currentPage.getChildren().setAll(accountInfoPage);
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

		// Display the scene at program launch

		/*
		 * Event Handlers
		 */

		// Onclick Actions in navbar
		// -------------------------
		viewMenuButton.setOnMouseClicked(e -> {
			currentPage.getChildren().setAll(menuPane);
			index.setCursor(Cursor.DEFAULT);
		});

		logInButton.setOnMouseClicked(e -> {
			if (index.getCursor() != Cursor.DEFAULT) {
				if (currentUser.getLoginStatus()) {
					currentPage.getChildren().setAll(accountInfoPage);
				} else
					currentPage.getChildren().setAll(signInMainBorderPane);
				index.setCursor(Cursor.DEFAULT);
			}
		});

		cartButton.setOnMouseClicked((e) -> {
			if (index.getCursor() != Cursor.DEFAULT) {
				currentPage.getChildren().setAll(cartPageMainPane);
				index.setCursor(Cursor.DEFAULT);

				cartItemsText.setText(currentUser.getCart().printCartItems());
				String subtotalString = df.format(currentUser.getCart().getSubtotal());
				cartPageSubtotalLabel.setText("Subtotal: $" + subtotalString);
			}
		});

		// When the user cliCks on the 'Sign-In' button on the sign-in page
		// ----------------------------------------------------------------
		signInButton.setOnAction(e -> {
			signInButtonActionText.setFill(Color.TOMATO);
			signInButtonActionText.setFont(Font.font("Calibri", FontWeight.BOLD, 15));

			if (currentUser.verifyCredentials(signInPageUserTextField.getText(), signInPagePasswordBox.getText())) {
				signInButtonActionText.setFill(Color.FORESTGREEN);
				signInButtonActionText.setText("Successfully verified credentials... logging in!");
				signInPageUserTextField.clear();
				signInPagePasswordBox.clear();
				currentUser.login();
				logInButton.setText("View Account");
				currentPage.getChildren().setAll(accountInfoPage);
			}

			else {
				signInButtonActionText.setText("Username and/or password incorrect.");
			}
		});

		// Listens for page changes
		// ------------------------
		currentPage.getChildren().addListener((ListChangeListener.Change<? extends Node> e) -> {
			homeButton.setOpacity(1);
			aboutButton.setOpacity(1);
			viewMenuButton.setOpacity(1);
			logInButton.setOpacity(1);
			helpButton.setOpacity(1);
			contactButton.setOpacity(1);
			cartButton.setOpacity(1);

			if (currentPage.getChildren().contains(menuPane)) {
				viewMenuButton.setOpacity(0.6);
			}
			if (currentPage.getChildren().contains(cartPageMainPane)) {
				cartButton.setOpacity(0.6);
			}
			if (currentPage.getChildren().contains(signInMainBorderPane) ||
					currentPage.getChildren().contains(accountInfoPage) ||
					currentPage.getChildren().contains(createAccountPagePane)) {
				logInButton.setOpacity(0.6);
			}
			if (currentPage.getChildren().contains(accountInfoPage)) {
				accountInformationPageUsernameLabel.setText("Username: " + currentUser.getUserID());
				createAccountPageContactNameLabel_AccInfo.setText("Contact Name: " +
						currentUser.getContactName());
				emailLabel_AccInfo.setText("Email: " + currentUser.getEmail());
				createAccountPagePhoneNumberlabel_AccInfo.setText("Phone: " +
						currentUser.getPhoneNumber());
				currentPage.getChildren().setAll(accountInfoPage);
			}
		});

		window.setScene(mainScene);
		window.show();

	}

	// END MAIN
	// --------------

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
