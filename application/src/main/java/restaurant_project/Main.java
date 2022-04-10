package restaurant_project;

import java.io.*;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
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
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.stage.Screen;
import javafx.stage.Stage;

public class Main extends Application implements EventHandler<ActionEvent> {
	Stage window;
	Scene scene1, scene2, scene3, scene4, scene5, scene6;

	public static void main(String[] args) throws IOException {

		launch(args);

	}

	public void start(Stage primaryStage) throws Exception, InterruptedException {

		// *DEBUG AND TESTING VARIABLE FOR NOW*
		Customer currentUser = new Customer("DEFAULT", "DEFAULT");

		// Display Window Set-Up
		// ----------------------
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

		// Panes
		BorderPane signInPane = new BorderPane(); // the main pane
		signInPane.setStyle(
				"-fx-background-image: url('https://thumbs.dreamstime.com/z/food-lunch-boxes-delivery-food-ukrainian-cuisine-wooden-background-top-view-copy-space-food-lunch-boxes-delivery-151206649.jpg');");
		HBox greetingPane = new HBox(); // the greeting at the top of the main 'signInPane' pane
		greetingPane.setStyle("-fx-background-color: rgba(0, 0, 0, 0.7); -fx-background-radius: 10;"); // set glassy
																										// background
																										// for banner
		greetingPane.setMaxWidth(700);
		;
		VBox centerVBox = new VBox(); // pane to hold the username and password labels and text fields
		centerVBox.setMaxSize(350, 250);
		setAnimatedBorder(centerVBox);
		centerVBox.setStyle("-fx-background-color: rgba(0, 100, 100, 0.5); -fx-background-radius: 10;"); // 'rgba' value
																											// with
																											// 'alpha'
																											// set to
																											// 0.5 for
																											// transparency
		HBox underButtons = new HBox(); // pane to hold buttons displayed underneath text fields

		signInPane.setTop(greetingPane);
		signInPane.setAlignment(greetingPane, Pos.TOP_CENTER);
		signInPane.setMargin(greetingPane, new Insets(100, 0, 0, 0));
		;
		signInPane.setCenter(centerVBox);

		// Sign-In Button
		Button signInButton = new Button("Sign in");
		final Text signInButtonActionText = new Text();

		// Create Account Button
		Button createmenuPageAccountButton = new Button("Create Account");
		createmenuPageAccountButton.setOnAction(e -> {
			window.setScene(scene2);
		});
		signInPane.getChildren().addAll(signInButton, createmenuPageAccountButton);

		// Header/Greeting Area
		TextFlow orderUpFlow = new TextFlow();

		Text orderUpRed = new Text("OrderUp");
		orderUpRed.setFill(Color.LIMEGREEN);
		orderUpRed.setFont(Font.font("Impact", FontWeight.BOLD, 35));

		Text greetingStart = new Text("Welcome to ");
		greetingStart.setFont(Font.font("Impact", FontWeight.BOLD, 30));
		greetingStart.setFill(Color.WHITESMOKE);

		Text greetingEnd = new Text(", delicious food is just a click away!");
		greetingEnd.setFont(Font.font("Impact", FontWeight.BOLD, 30));
		greetingEnd.setFill(Color.WHITESMOKE);

		orderUpFlow.getChildren().addAll(greetingStart, orderUpRed, greetingEnd);
		greetingPane.getChildren().add(orderUpFlow);

		// Central Login Area
		underButtons.setAlignment(Pos.BOTTOM_CENTER);
		underButtons.setSpacing(10);
		underButtons.setPadding(new Insets(20, 0, 30, 0));
		underButtons.getChildren().addAll(signInButton, createmenuPageAccountButton);

		Label loginInstruct = new Label("Please log-in to get started");
		loginInstruct.setTextFill(Color.LIGHTCYAN);
		loginInstruct.setPadding(new Insets(0, 0, 25, 0));

		Label userName = new Label("Username:");
		userName.setTextFill(Color.LIGHTCYAN);
		TextField userTextField = new TextField();
		userTextField.setPrefWidth(150);
		userTextField.setMaxWidth(150);

		Label pw = new Label("Password:");
		pw.setTextFill(Color.LIGHTCYAN);
		PasswordField pwBox = new PasswordField();
		pwBox.setPrefWidth(150);
		pwBox.setMaxWidth(150);

		centerVBox.getChildren().addAll(loginInstruct, userName, userTextField, pw, pwBox, underButtons,
				signInButtonActionText);
		centerVBox.setAlignment(Pos.CENTER);

		// When the user cliks on the 'Sign-In' button on the sign-in page
		signInButton.setOnAction(e -> {
			signInButtonActionText.setFill(Color.TOMATO);
			signInButtonActionText.setFont(Font.font("Calibri", FontWeight.BOLD, 15));

			if (currentUser.verifyCredentials(userTextField.getText(), pwBox.getText())) {
				signInButtonActionText.setFill(Color.FORESTGREEN);
				signInButtonActionText.setText("Successfully verified credentials... logging in!");
				userTextField.clear();
				pwBox.clear();
				currentUser.login();
				window.setScene(scene3);
			}

			else {
				signInButtonActionText.setText("Username and/or password incorrect.");
			}
		});

		// Display 'scene1' with the default window size
		scene1 = new Scene(signInPane, screenbounds.getWidth(), screenbounds.getHeight());

		// End Scene 1
		// ----------------------------

		// Scene 2 - Create Account Page
		// -----------------

		// Panes
		BorderPane createAccountPane = new BorderPane(); // Main pane for 'scene2'
		createAccountPane.setStyle(
				"-fx-background-image: url('https://thumbs.dreamstime.com/z/food-delivery-workdesk-paper-bags-flatware-table-background-top-view-mock-up-restourant-gray-91618763.jpg');"
						+ "-fx-background-size: cover;");
		VBox createAccountCentralVBox = new VBox();
		createAccountCentralVBox.setStyle("-fx-background-color: rgba(0,0,0, 0.7);");
		createAccountCentralVBox.setMaxSize(350, 350);
		HBox createAccountGreeting = new HBox();
		createAccountGreeting.setStyle("-fx-background-color: rgba(0,0,0, 0.7); -fx-background-radius: 10;");
		createAccountGreeting.setMaxSize(500, 500);
		HBox underButtons2 = new HBox();
		HBox underButtons2Text = new HBox();

		createAccountPane.setTop(createAccountGreeting);
		createAccountPane.setAlignment(createAccountGreeting, Pos.TOP_CENTER);
		createAccountPane.setMargin(createAccountGreeting, new Insets(100, 0, 0, 0));
		createAccountPane.setCenter(createAccountCentralVBox);

		// Submit Button
		Button submitButton = new Button("Submit");

		// Back Button
		Button backButton = new Button("Back");

		underButtons2.getChildren().addAll(submitButton, backButton);
		underButtons2.setSpacing(10);
		underButtons2.setAlignment(Pos.CENTER);
		underButtons2.setPadding(new Insets(20, 0, 0, 0));

		// Header/Greeting Area
		createAccountGreeting.setAlignment(Pos.CENTER);
		Label accountGreeting = new Label("Create a New Account");
		accountGreeting.setFont(Font.font("Impact", FontWeight.BOLD, 40));
		accountGreeting.setTextFill(Color.WHITESMOKE);
		createAccountGreeting.getChildren().add(accountGreeting);

		// Central Account Creation Area
		Label usernameInstructLabel = new Label("Please choose a username");
		usernameInstructLabel.setTextFill(Color.WHITESMOKE);
		TextField userTextField2 = new TextField();
		userTextField2.setMaxWidth(150);

		Label passwordInstructLabel = new Label("Please choose a password for your account");
		passwordInstructLabel.setTextFill(Color.WHITESMOKE);
		PasswordField pwField2 = new PasswordField();
		pwField2.setMaxWidth(150);

		Label contactNameLabel = new Label("Please enter your first and last name");
		contactNameLabel.setTextFill(Color.WHITESMOKE);
		TextField contactNameField = new TextField();
		contactNameField.setMaxWidth(150);

		Label emailAddressLabel = new Label("Please enter a contact email");
		emailAddressLabel.setTextFill(Color.WHITESMOKE);
		TextField emailAddressField = new TextField();
		emailAddressField.setMaxWidth(150);

		Label phoneNumberLabel = new Label("Please enter your phone-number");
		phoneNumberLabel.setTextFill(Color.WHITESMOKE);
		TextField phoneNumberField = new TextField();
		phoneNumberField.setMaxWidth(150);

		final Text actiontarget = new Text();
		actiontarget.setFill(Color.FIREBRICK);
		underButtons2Text.getChildren().add(actiontarget);
		underButtons2Text.setAlignment(Pos.BOTTOM_CENTER);
		underButtons2Text.setPadding(new Insets(25, 0, 0, 0));

		createAccountCentralVBox.getChildren().addAll(usernameInstructLabel, userTextField2, passwordInstructLabel,
				pwField2,
				contactNameLabel, contactNameField,
				emailAddressLabel, emailAddressField, phoneNumberLabel, phoneNumberField, underButtons2, actiontarget);

		createAccountCentralVBox.setSpacing(3);
		createAccountCentralVBox.setAlignment(Pos.CENTER);

		backButton.setOnAction(e -> {
			actiontarget.setText("");
			window.setScene(scene1);

		});

		// Sets the action of Submit button to create a new User object with the
		// information input into the text fields on the
		// 'Account Creation' page
		submitButton.setOnAction(e -> {

			// Check for empty fields
			if (userTextField2.getText().isEmpty() || pwField2.getText().isEmpty()
					|| contactNameField.getText().isEmpty() ||
					emailAddressField.getText().isEmpty() || phoneNumberField.getText().isEmpty()) {
				actiontarget.setFill(Color.TOMATO);
				actiontarget.setText("You left a field empty.");

			}

			// Set 'currentUser' attributes to String value inputs
			else if (currentUser.getUserID() == "DEFAULT" && currentUser.getPassword() == "DEFAULT") {
				// Set attributes
				currentUser.setUserID(userTextField2.getText());
				currentUser.setPassword(pwField2.getText());
				currentUser.setContactName(contactNameField.getText());
				currentUser.setEmail(emailAddressField.getText());
				currentUser.setPhoneNumber(phoneNumberField.getText());

				// Clear out the text fields
				userTextField2.clear();
				pwField2.clear();
				contactNameField.clear();
				emailAddressField.clear();
				phoneNumberField.clear();

				// Display success message
				actiontarget.setFill(Color.FORESTGREEN);
				actiontarget.setText("Account Successfully created!");
			}

			else {
				System.out.println("User already created.");
				System.out.println("currentUser has userID: " + currentUser.getUserID());
			}

		});

		scene2 = new Scene(createAccountPane, screenbounds.getWidth(), screenbounds.getHeight());

		// End Scene 2
		// -----------------------

		// Scene 3 - Menu Page
		// -------------------

		// Panes
		GridPane menuPageGridPane_Left = new GridPane();
		menuPageGridPane_Left.setAlignment(Pos.CENTER);
		menuPageGridPane_Left.setGridLinesVisible(true); // set 'true' to see grid-lines
		menuPageGridPane_Left.setHgap(10);
		menuPageGridPane_Left.setVgap(10);
		menuPageGridPane_Left.setPadding(new Insets(0, 10, 0, 10));

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

		// Add all of the images/labels to the grid-pane for display
		menuPageGridPane_Left.add(biscuitImgV, 0, 0);
		menuPageGridPane_Left.add(biscuitLabel, 1, 0);
		menuPageGridPane_Left.add(cheeseImgV, 0, 1);
		menuPageGridPane_Left.add(cheeseLabel, 1, 1);
		menuPageGridPane_Left.add(eggImgV, 0, 2);
		menuPageGridPane_Left.add(eggLabel, 1, 2);
		menuPageGridPane_Left.add(sausageImgV, 0, 3);
		menuPageGridPane_Left.add(sausageLabel, 1, 3);
		menuPageGridPane_Left.add(butterImgV, 0, 4);
		menuPageGridPane_Left.add(butterLabel, 1, 4);

		scene3 = new Scene(menuPane, screenbounds.getWidth(), screenbounds.getHeight());

		// End Scene 3

		// Scene 4 - Account Information Page
		// ----------

		// Panes
		BorderPane accountInfoPage = new BorderPane(); // Main pane for 'scene2'
		accountInfoPage.setStyle(
				"-fx-background-image: url('https://thumbs.dreamstime.com/z/food-delivery-workdesk-paper-bags-flatware-table-background-top-view-mock-up-restourant-gray-91618763.jpg');"
						+ "-fx-background-size: cover;");
		VBox accountInfoPageCentralVBox = new VBox();
		accountInfoPageCentralVBox.setStyle("-fx-background-color: rgba(0,0,0, 0.7);");
		accountInfoPageCentralVBox.setMaxSize(350, 350);

		HBox accountInfoPageGreeting = new HBox();
		accountInfoPageGreeting.setStyle("-fx-background-color: rgba(0,0,0, 0.7); -fx-background-radius: 10;");
		accountInfoPageGreeting.setMaxSize(500, 500);

		accountInfoPage.setCenter(accountInfoPageCentralVBox);
		scene4 = new Scene(accountInfoPage, screenbounds.getWidth(), screenbounds.getHeight());

		// End Scene 4

		// Scene 5 - Cart Page
		// -----------
		BorderPane cartPageMainPane = new BorderPane();
		cartPageMainPane.setStyle(
				"-fx-background-image: url('https://img.freepik.com/free-psd/top-view-free-food-delivery-assortment-with-background-mock-up_23-2148421296.jpg?t=st=1649334513~exp=1649335113~hmac=b77793ec57018e5086c85a58d74ef43481b17cf0f8bf8284edef8efd9f6236c9&w=1060');"
						+ "-fx-background-size: cover;");

		GridPane cartPageBannerGridBox = new GridPane();
		cartPageBannerGridBox.setAlignment(Pos.TOP_RIGHT);
		cartPageBannerGridBox.setHgap(1);
		cartPageBannerGridBox.setVgap(1);
		cartPageBannerGridBox.setGridLinesVisible(false);

		VBox cartItemsVBox = new VBox();
		

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

		HBox cartPageHeaderButtons = new HBox();
		cartPageHeaderButtons.getChildren().addAll(cartPageSignOutButton, cartPageMenuButton);
		cartPageHeaderButtons.setSpacing(5);

		cartPageBannerGridBox.add(cartPageHeaderButtons, 0, 1);
		cartPageMainPane.setTop(cartPageBannerGridBox);
		cartPageMainPane.setLeft(cartItemsVBox);

		scene5 = new Scene(cartPageMainPane, screenbounds.getWidth(), screenbounds.getHeight());

		// Scene 6
		// ----------

		// Panes

		GridPane layout3 = new GridPane();
		layout3.setAlignment(Pos.CENTER);
		layout3.setHgap(10);
		layout3.setVgap(10);
		layout3.setPadding(new Insets(25, 25, 25, 25));
		// layout3.getChildren().addAll(button3);
		// scene3 = new Scene(layout3, 300,300);

		Label total = new Label("Total: $");
		layout3.add(total, 0, 1);

		// Button 4
		Button button4 = new Button("Go to Ingredients page");
		button4.setText("Ingredients");
		button4.setOnAction(e -> window.setScene(scene4));

		// Layout 5
		GridPane layout4 = new GridPane();
		layout4.setAlignment(Pos.CENTER);
		layout4.setHgap(10);
		layout4.setVgap(10);
		layout4.setPadding(new Insets(25, 25, 25, 25));
		layout4.getChildren().addAll(button4);
		// scene4 = new Scene(layout4, 300,300);

		// Label total = new
		// Label("Breakfast Sandwich: /nBiscuit /nCheese /nEgg /nSausage /nButter");
		layout4.add(total, 0, 1);

		// Display the first scene at program launch
		window.setScene(scene1);
		window.show();

	}

	// Method 'setBorder()': This method will cycle through a series of colors and
	// uses javafx libraries to create an animated border
	// effect. Currently implemented only for 'VBox' panes, but can be changed if
	// needed.
	private void setAnimatedBorder(VBox centerVBox) {
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
						new KeyValue(centerVBox.borderProperty(), b, Interpolator.EASE_IN)))
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
