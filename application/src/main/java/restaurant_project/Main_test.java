package restaurant_project;

import java.io.*;

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
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class Main_test extends Application implements EventHandler<ActionEvent> {
	Stage window;
	Scene scene1, scene2, scene3, scene4;

	public static void main(String[] args) throws IOException {

		launch(args);

		String inputName = new String();
		String inputPassword = new String();

		InputStreamReader isr = new InputStreamReader(System.in);
		BufferedReader stdin = new BufferedReader(isr);

		System.out.println("Hello, let's create a new User.");
		System.out.print("First, type the username you want the user to have: ");
		inputName = stdin.readLine();
		System.out.println("Great, you chose '" + inputName + "' as a name.");
		System.out.print("Next, choose the password to associate with '" + inputName + "': ");
		inputPassword = stdin.readLine();
		User newUser = new User(inputName, inputPassword);
		System.out
				.println("'newUser' username is: " + newUser.getUserID() + " with password: " + newUser.getPassword());

	}

	public void start(Stage primaryStage) throws Exception {

		// Display Window Set-Up
		// ----------------------
		window = primaryStage; // main display window

		// Title the window "OrderUp"
		window.setTitle("OrderUp");

		int defaultWindowHeight = 750;
		int defaultWindowWidth = 750;

		// ---------------------------------------------------

		// Scene 1
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

		// Create Account Button
		Button createAccountButton = new Button("Create Account");
		createAccountButton.setOnAction(e -> window.setScene(scene2));
		signInPane.getChildren().addAll(signInButton, createAccountButton);

		// Header/Greeting Area
		greetingPane.setAlignment(Pos.CENTER);
		greetingPane.setPadding(new Insets(200, 0, -150, 0));

		Label greeting = new Label("Welcome to OrderUp, please sign in to get started!");
		greeting.setFont(Font.font("Calibri", FontWeight.NORMAL, 20));
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

		centerVBox.getChildren().addAll(userName, userTextField, pw, pwBox, underButtons);
		centerVBox.setAlignment(Pos.CENTER);

		//Print text fields input to standard output when clicking on 'signInButton'
		signInButton.setOnAction(e -> 
		{
			System.out.println(userTextField.getText());
			System.out.println(pwBox.getText());
		});

		// Display 'scene1' with the default window size
		scene1 = new Scene(signInPane, defaultWindowWidth, defaultWindowHeight);

		// End Scene 1

		// Scene 2
		// -----------------

		// Panes
		BorderPane createAccountPane = new BorderPane(); //Main pane for 'scene2'
		VBox createAccountCentralVBox = new VBox();
		HBox createAccountGreeting = new HBox();
		HBox underButtons2 = new HBox();

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
		Label usernameInstruct = new Label("Please choose a username");
		TextField userTextField2 = new TextField();
		userTextField2.setMaxWidth(150);

		Label passwordInstruct = new Label("Please choose a password for your account");
		PasswordField pw2 = new PasswordField();
		pw2.setMaxWidth(150);

		Label contactName = new Label("Please enter your first and last name");
		TextField contactNameField = new TextField();
		contactNameField.setMaxWidth(150);

		Label emailAddress = new Label("Please enter a contact email");
		TextField emailAddressField = new TextField();
		emailAddressField.setMaxWidth(150);

		Label phoneNumber = new Label("Please enter your phone-number");
		TextField phoneNumberField = new TextField();
		phoneNumberField.setMaxWidth(150);

		createAccountCentralVBox.getChildren().addAll(usernameInstruct, userTextField2, passwordInstruct, pw2,
				contactName, contactNameField,
				emailAddress, emailAddressField, phoneNumber, phoneNumberField,underButtons2);

		createAccountCentralVBox.setAlignment(Pos.CENTER);

		scene2 = new Scene(createAccountPane, defaultWindowWidth, defaultWindowHeight);

		// End Scene 2

		/*
		// Scene 3
		// ----------

		// Panes

		GridPane layout3 = new GridPane();
		layout3.setAlignment(Pos.CENTER);
		layout3.setHgap(10);
		layout3.setVgap(10);
		layout3.setPadding(new Insets(25, 25, 25, 25));
		layout3.getChildren().addAll(button3);
		scene3 = new Scene(layout3, 300,300);
		
		Label total = new Label("Total: $");
		layout3.add(total, 0, 1);
		
		// Button 4
		Button button4 = new Button("Go to Ingredients page");
		button4.setText("Ingredients");
		button4.setOnAction(e -> window.setScene(scene4));
		
		// Layout 4
		GridPane layout4 = new GridPane();
		layout4.setAlignment(Pos.CENTER);
		layout4.setHgap(10);
		layout4.setVgap(10);
		layout4.setPadding(new Insets(25, 25, 25, 25));
		layout4.getChildren().addAll(button4);
		scene4 = new Scene(layout4, 300,300);
	
		Label total = new
		Label("Breakfast Sandwich: /nBiscuit /nCheese /nEgg /nSausage /nButter");
		layout4.add(total, 0, 1);
		*/

		// Display the first scene at program launch
		window.setScene(scene1);
		window.show();

	}

	@Override
	public void handle(ActionEvent arg0) {
		// TODO Auto-generated method stub

	}
}
