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
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Main_test extends Application implements EventHandler<ActionEvent> {
	Stage window;
	Scene scene1, scene2, scene3, scene4;

	public static void main(String[] args) throws IOException {

		launch(args);

	}

	public void start(Stage primaryStage) throws Exception, InterruptedException {

		//*TEST VARIABLE FOR USER OBJECT*
		User currentUser = new User("DEFAULT", "DEFAULT");

		// Display Window Set-Up
		// ----------------------
		window = primaryStage; // main display window

		// Title the window "OrderUp"
		window.setTitle("OrderUp");

		int defaultWindowHeight = 750;
		int defaultWindowWidth = 750;

		// ---------------------------------------------------

		// Scene 1 - Sign In Page
		// ---------------

		// Panes
		BorderPane signInPane = new BorderPane(); // the main pane
		HBox greetingPane = new HBox(); // the greeting at the top of the main 'signInPane' pane
		VBox centerVBox = new VBox(); // pane to hold the username and password labels and text fields
		HBox underButtons = new HBox(); // pane to hold buttons displayed underneath text fields

		signInPane.setTop(greetingPane);
		signInPane.setCenter(centerVBox);

		// Sign-In Button
		Button signInButton = new Button("Sign in");
		final Text signInButtonActionText = new Text();

		// Create Account Button
		Button createAccountButton = new Button("Create Account");
		createAccountButton.setOnAction(e -> window.setScene(scene2));
		signInPane.getChildren().addAll(signInButton, createAccountButton);

		// Header/Greeting Area
		greetingPane.setAlignment(Pos.CENTER);
		greetingPane.setPadding(new Insets(200, 0, -150, 0));

		Label greeting = new Label("Welcome to OrderUp, please sign in to get started!");
		greeting.setTextFill(Color.CRIMSON);
		greeting.setFont(Font.font("Calibri", FontWeight.BOLD, 20));
		greetingPane.getChildren().addAll(greeting);

		// Central Login Area
		underButtons.setAlignment(Pos.BOTTOM_CENTER);
		underButtons.setSpacing(10);
		underButtons.setPadding(new Insets(20, 0, 30, 0));
		underButtons.getChildren().addAll(signInButton, createAccountButton);

		Label userName = new Label("User Name:");
		TextField userTextField = new TextField();
		userTextField.setPrefWidth(150);
		userTextField.setMaxWidth(150);

		Label pw = new Label("Password:");
		PasswordField pwBox = new PasswordField();
		pwBox.setPrefWidth(150);
		pwBox.setMaxWidth(150);

		centerVBox.getChildren().addAll(userName, userTextField, pw, pwBox, underButtons, signInButtonActionText);
		centerVBox.setAlignment(Pos.CENTER);
		
		signInButton.setOnAction(e ->
		{
			signInButtonActionText.setFill(Color.FIREBRICK);
			
		if (currentUser.verifyCredentials(userTextField.getText(), pwBox.getText()))
		{
			signInButtonActionText.setFill(Color.FORESTGREEN);	
			signInButtonActionText.setText("Successfully verified credentials... logging in!");
			currentUser.setLoginStatus(true);
			window.setScene(scene3);
		}

		else
		{
			signInButtonActionText.setText("Username and/or password incorrect.");
		}
		});

		// Display 'scene1' with the default window size
		scene1 = new Scene(signInPane, defaultWindowWidth, defaultWindowHeight);

		// End Scene 1
		// ----------------------------

		// Scene 2 - Create Account Page
		// -----------------

		// Panes
		BorderPane createAccountPane = new BorderPane(); //Main pane for 'scene2'
		VBox createAccountCentralVBox = new VBox();
		HBox createAccountGreeting = new HBox();
		HBox underButtons2 = new HBox();
		HBox underButtons2Text = new HBox();

		createAccountPane.setTop(createAccountGreeting);
		createAccountPane.setCenter(createAccountCentralVBox);

		// Submit Button
		Button submitButton = new Button("Submit");

		// Main-Menu Button
		Button mainMenuButton = new Button("Main Menu");
		mainMenuButton.setOnAction(e -> window.setScene(scene1));

		underButtons2.getChildren().addAll(submitButton, mainMenuButton);
		underButtons2.setSpacing(10);
		underButtons2.setAlignment(Pos.CENTER);
		underButtons2.setPadding(new Insets(20,0,0,0));

		// Header/Greeting Area
		createAccountGreeting.setAlignment(Pos.CENTER);
		createAccountGreeting.setPadding(new Insets(200, 0, -150, 0));
		Label accountGreeting = new Label("Create a New Account");
		accountGreeting.setFont(Font.font("Calibri", FontWeight.BOLD, 20));
		createAccountGreeting.getChildren().add(accountGreeting);

		// Central Account Creation Area
		Label usernameInstructLabel = new Label("Please choose a username");
		TextField userTextField2 = new TextField();
		userTextField2.setMaxWidth(150);

		Label passwordInstructLabel = new Label("Please choose a password for your account");
		PasswordField pwField2 = new PasswordField();
		pwField2.setMaxWidth(150);

		Label contactNameLabel = new Label("Please enter your first and last name");
		TextField contactNameField = new TextField();
		contactNameField.setMaxWidth(150);

		Label emailAddressLabel = new Label("Please enter a contact email");
		TextField emailAddressField = new TextField();
		emailAddressField.setMaxWidth(150);

		Label phoneNumberLabel = new Label("Please enter your phone-number");
		TextField phoneNumberField = new TextField();
		phoneNumberField.setMaxWidth(150);

		final Text actiontarget = new Text();
		actiontarget.setFill(Color.FIREBRICK);
		underButtons2Text.getChildren().add(actiontarget);
		underButtons2Text.setAlignment(Pos.CENTER);

		createAccountCentralVBox.getChildren().addAll(usernameInstructLabel, userTextField2, passwordInstructLabel, pwField2,
				contactNameLabel, contactNameField,
				emailAddressLabel, emailAddressField, phoneNumberLabel, phoneNumberField,underButtons2, actiontarget);

		createAccountCentralVBox.setAlignment(Pos.CENTER);
		
		// Sets the action of Submit button to create a new User object with the information input into the text fields on the
		// 'Account Creation' page
		submitButton.setOnAction(e -> 
		{	


			// Check for empty fields
			if (userTextField2.getText().isEmpty() || pwField2.getText().isEmpty() || contactNameField.getText().isEmpty() ||
			emailAddressField.getText().isEmpty() || phoneNumberField.getText().isEmpty())
			{
				actiontarget.setFill(Color.FIREBRICK);
				actiontarget.setText("You left a field empty.");
			}

			// Set 'currentUser' attributes to String value inputs
			else if (currentUser.getUserID() == "DEFAULT" && currentUser.getPassword() == "DEFAULT")
			{	
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

			else
			{
				System.out.println("User already created.");
				System.out.println("currentUser has userID: " + currentUser.getUserID());
			}
		
		});

		scene2 = new Scene(createAccountPane, defaultWindowWidth, defaultWindowHeight);

		// End Scene 2
		// -----------------------

		// Scene 3 - Menu Page
		// -------------------

		BorderPane menuPane = new BorderPane();
		VBox menuVBoxPane = new VBox();

		//Label biscuitLabel = new Label("Biscuit");
		// Label cheeseLabel = new Label("Cheese");
		// Label eggLabel = new Label("Egg");
		// Label sausageLabel = new Label("Sausage");
		// Label butterLabel = new Label("Butter");
		//menuVBoxPane.getChildren().addAll(biscuitLabel, cheeseLabel, eggLabel, sausageLabel, butterLabel);
		//menuVBoxPane.setAlignment(Pos.CENTER);
		menuPane.setCenter(menuVBoxPane);

		// Load images
		Image biscuitImg = new Image(new FileInputStream("C:\\Users\\tyler\\OneDrive\\Desktop\\School Stuff\\CSE360_RestaurantGroupProject\\application\\src\\main\\resources\\Images\\4-2-biscuit-transparent.png"));
		ImageView biscuitImgV = new ImageView();
		biscuitImgV.setImage(biscuitImg);
		biscuitImgV.setFitWidth(75);
		biscuitImgV.setPreserveRatio(true);
		biscuitImgV.setSmooth(true);
		biscuitImgV.setCache(true);
		
		Image cheeseImg = new Image(new FileInputStream("C:\\Users\\tyler\\OneDrive\\Desktop\\School Stuff\\CSE360_RestaurantGroupProject\\application\\src\\main\\resources\\Images\\11-cheese-sliced-png-image.png"));
		ImageView cheeseImgV = new ImageView();
		cheeseImgV.setImage(cheeseImg);
		cheeseImgV.setFitWidth(75);
		cheeseImgV.setPreserveRatio(true);
		cheeseImgV.setSmooth(true);
		cheeseImgV.setCache(true);

		Image eggImg = new Image(new FileInputStream("C:\\Users\\tyler\\OneDrive\\Desktop\\School Stuff\\CSE360_RestaurantGroupProject\\application\\src\\main\\resources\\Images\\purepng.com-fried-eggfood-egg-cooking-eating-breakfast-fried-white-yolk-protein-941524632335wlndl.png"));
		ImageView eggImgV = new ImageView();
		eggImgV.setImage(eggImg);
		eggImgV.setFitWidth(75);
		eggImgV.setPreserveRatio(true);
		eggImgV.setSmooth(true);
		eggImgV.setCache(true);

		Image sausageImg = new Image(new FileInputStream("C:\\Users\\tyler\\OneDrive\\Desktop\\School Stuff\\CSE360_RestaurantGroupProject\\application\\src\\main\\resources\\Images\\71-714077_cooked-sausage-png-transparent-png.png"));
		ImageView sausageImgV = new ImageView();
		sausageImgV.setImage(sausageImg);
		sausageImgV.setFitWidth(75);
		sausageImgV.setPreserveRatio(true);
		sausageImgV.setSmooth(true);
		sausageImgV.setCache(true);

		menuVBoxPane.getChildren().addAll(biscuitImgV, cheeseImgV, eggImgV, sausageImgV);
		

		scene3 = new Scene(menuPane, defaultWindowWidth, defaultWindowHeight);

		// Scene 4
		// ----------

		// Panes

		GridPane layout3 = new GridPane();
		layout3.setAlignment(Pos.CENTER);
		layout3.setHgap(10);
		layout3.setVgap(10);
		layout3.setPadding(new Insets(25, 25, 25, 25));
		//layout3.getChildren().addAll(button3);
		//scene3 = new Scene(layout3, 300,300);
		
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
		//scene4 = new Scene(layout4, 300,300);
	
		//Label total = new
		//Label("Breakfast Sandwich: /nBiscuit /nCheese /nEgg /nSausage /nButter");
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
