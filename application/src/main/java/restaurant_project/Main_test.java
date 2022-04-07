package restaurant_project;

import java.io.*;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
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

public class Main_test extends Application implements EventHandler<ActionEvent> {
	Stage window;
	Scene scene1, scene2, scene3, scene4;

	public static void main(String[] args) throws IOException {

		launch(args);

	}

	public void start(Stage primaryStage) throws Exception, InterruptedException {

		// *DEBUG AND TESTING VARIABLE FOR NOW*
		User currentUser = new User("DEFAULT", "DEFAULT");

		// Display Window Set-Up
		// ----------------------
		window = primaryStage; // main display window

		// Title the window "OrderUp"
		window.setTitle("OrderUp");
		window.setMaximized(true);
		window.setResizable(true);
		
		//Get visible bounds of the screen for application resolution
		Rectangle2D screenbounds = Screen.getPrimary().getVisualBounds();

		// ---------------------------------------------------

		// Scene 1 - Sign In Page
		// ---------------

		// Panes
		BorderPane signInPane = new BorderPane(); // the main pane
		signInPane.setStyle("-fx-background-image: url('https://thumbs.dreamstime.com/z/food-lunch-boxes-delivery-food-ukrainian-cuisine-wooden-background-top-view-copy-space-food-lunch-boxes-delivery-151206649.jpg');");
		HBox greetingPane = new HBox(); // the greeting at the top of the main 'signInPane' pane
		greetingPane.setStyle("-fx-background-color: rgba(0, 0, 0, 0.7); -fx-background-radius: 10;"); //set glassy background for banner
		greetingPane.setMaxWidth(700);;
		VBox centerVBox = new VBox(); // pane to hold the username and password labels and text fields
		centerVBox.setMaxSize(350, 250);
		centerVBox.setStyle("-fx-background-color: rgba(0, 100, 100, 0.5); -fx-background-radius: 10;"); //'rgba' value with 'alpha' set to 0.5 for transparency
		HBox underButtons = new HBox(); // pane to hold buttons displayed underneath text fields
		
		signInPane.setTop(greetingPane);
		signInPane.setAlignment(greetingPane, Pos.TOP_CENTER);
		signInPane.setMargin(greetingPane, new Insets(100,0,0,0));;
		signInPane.setCenter(centerVBox);

		// Sign-In Button
		Button signInButton = new Button("Sign in");
		final Text signInButtonActionText = new Text();

		// Create Account Button
		Button createAccountButton = new Button("Create Account");
		createAccountButton.setOnAction(e -> {
			window.setScene(scene2);
		});
		signInPane.getChildren().addAll(signInButton, createAccountButton);

		// Header/Greeting Area
		TextFlow orderUpFlow = new TextFlow();

		Text orderUpRed = new Text("OrderUp");
		orderUpRed.setFill(Color.LIMEGREEN);
		orderUpRed.setFont(Font.font("Impact", FontWeight.BOLD, 35));

		Text greetingStart = new Text("Welcome to ");
		greetingStart.setFont(Font.font("Impact", FontWeight.BOLD, 30));
		greetingStart.setFill(Color.WHITESMOKE);

		Text greetingEnd = new Text(", please sign in to get started!");
		greetingEnd.setFont(Font.font("Impact", FontWeight.BOLD, 30));
		greetingEnd.setFill(Color.WHITESMOKE);

		orderUpFlow.getChildren().addAll(greetingStart, orderUpRed, greetingEnd);
		greetingPane.getChildren().add(orderUpFlow);

		// Central Login Area
		underButtons.setAlignment(Pos.BOTTOM_CENTER);
		underButtons.setSpacing(10);
		underButtons.setPadding(new Insets(20, 0, 30, 0));
		underButtons.getChildren().addAll(signInButton, createAccountButton);

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

		centerVBox.getChildren().addAll(userName, userTextField, pw, pwBox, underButtons, signInButtonActionText);
		centerVBox.setAlignment(Pos.CENTER);

		signInButton.setOnAction(e -> {
			signInButtonActionText.setFill(Color.TOMATO);
			signInButtonActionText.setFont(Font.font("Calibri", FontWeight.BOLD, 15));

			if (currentUser.verifyCredentials(userTextField.getText(), pwBox.getText())) {
				signInButtonActionText.setFill(Color.FORESTGREEN);
				signInButtonActionText.setText("Successfully verified credentials... logging in!");
				userTextField.clear();
				pwBox.clear();
				currentUser.setLoginStatus(true);
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
		createAccountPane.setStyle("-fx-background-image: url('https://thumbs.dreamstime.com/z/food-delivery-workdesk-paper-bags-flatware-table-background-top-view-mock-up-restourant-gray-91618763.jpg');" + "-fx-background-size: cover;");
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
		createAccountPane.setMargin(createAccountGreeting, new Insets(100,0,0,0));
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
		underButtons2Text.setPadding(new Insets(25,0,0,0));

		createAccountCentralVBox.getChildren().addAll(usernameInstructLabel, userTextField2, passwordInstructLabel,
				pwField2,
				contactNameLabel, contactNameField,
				emailAddressLabel, emailAddressField, phoneNumberLabel, phoneNumberField, underButtons2, actiontarget);

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
		GridPane menuGridPane_Left = new GridPane();
		menuGridPane_Left.setAlignment(Pos.CENTER);
		menuGridPane_Left.setGridLinesVisible(true); // set 'true' to see grid-lines
		menuGridPane_Left.setHgap(10);
		menuGridPane_Left.setVgap(10);
		menuGridPane_Left.setPadding(new Insets(0, 10, 0, 10));

		// Header/Greeting
		GridPane bannerBox = new GridPane();
		bannerBox.setAlignment(Pos.TOP_RIGHT);
		bannerBox.setHgap(1);
		bannerBox.setVgap(1);
		bannerBox.setGridLinesVisible(false);

		//Navigation Buttons
		HBox headerButtons = new HBox();
		headerButtons.setAlignment(Pos.CENTER);
		Button signOutButton = new Button("Sign-Out");
		signOutButton.setOnAction(e -> 
		{
			window.setScene(scene1);
			currentUser.resetUser();
			signInButtonActionText.setText("");

		});

		Button accountButton = new Button("Account");
		Button cartButton = new Button("Cart");
		headerButtons.getChildren().addAll(signOutButton, accountButton, cartButton);
		headerButtons.setSpacing(5);

		Label menuHeader = new Label("Food Menu");
		menuHeader.setTextFill(Color.DARKOLIVEGREEN);
		menuHeader.setFont(Font.font("Calibri", FontWeight.EXTRA_BOLD, 50));
		menuHeader.setUnderline(true);
		bannerBox.add(menuHeader, 0, 1);
		bannerBox.add(headerButtons, 1, 0);
		ColumnConstraints column1 = new ColumnConstraints(300);
		ColumnConstraints column2 = new ColumnConstraints(600);
		bannerBox.getColumnConstraints().addAll(column1, column2);


		// Main Pane
		BorderPane menuPane = new BorderPane();
		menuPane.setLeft(menuGridPane_Left);
		menuPane.setTop(bannerBox);

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

		// Add all of the images/labels to the grid-pane for display
		menuGridPane_Left.add(biscuitImgV, 0, 0);
		menuGridPane_Left.add(biscuitLabel, 1, 0);
		menuGridPane_Left.add(cheeseImgV, 0, 1);
		menuGridPane_Left.add(cheeseLabel, 1, 1);
		menuGridPane_Left.add(eggImgV, 0, 2);
		menuGridPane_Left.add(eggLabel, 1, 2);
		menuGridPane_Left.add(sausageImgV, 0, 3);
		menuGridPane_Left.add(sausageLabel, 1, 3);
		menuGridPane_Left.add(butterLabel, 1, 4);

		scene3 = new Scene(menuPane, screenbounds.getWidth(), screenbounds.getHeight());

		// End Scene 3

		// Scene 4
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

	@Override
	public void handle(ActionEvent arg0) {
		// TODO Auto-generated method stub

	}
}
