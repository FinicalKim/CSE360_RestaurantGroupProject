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
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Main_test extends Application implements EventHandler<ActionEvent> {
	Stage window;
	Scene scene1, scene2;
	User newUser;

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

		window = primaryStage; //main display window
		window.setMaximized(true); //set the application to open in full-screen mode

		// Title the window "OrderUp"
		window.setTitle("OrderUp");

		/*
		Label label1 = new Label("Welcome to OrderUp, please sign in to get started!");

		// Button 1
		Button signInButton = new Button("Go to sign in menu");
		signInButton.setText("Sign in");
		signInButton.setOnAction(e -> window.setScene(scene2)); // button 1 sends you to the Sign in page

		// Layout 1
		BorderPane mainBorderPane = new BorderPane(); //the main Pane on which other panes can be drawn
		mainBorderPane.setCenter(signInButton);
		BorderPane.setAlignment(signInButton, Pos.CENTER);
		mainBorderPane.setTop(label1);
		BorderPane.setAlignment(label1, Pos.CENTER);
		mainBorderPane.setPadding(new Insets(15,12,15,12));

		scene1 = new Scene(mainBorderPane, 300, 300);
		*/
//-------------------------------------------------------------------

		// Layout 2 - Scene 2
		BorderPane signInPane = new BorderPane(); //the sign-in page pane
		HBox greetingPane = new HBox();
		greetingPane.setAlignment(Pos.CENTER);
		HBox bottomButtons = new HBox();
		VBox centerVBox = new VBox();

		FlowPane centerFlowPane = new FlowPane();
		centerFlowPane.setPadding(new Insets(5,5,5,5));
		centerFlowPane.setVgap(4);

		Label greeting = new Label("Welcome to OrderUp, please sign in to get started!");
		greeting.setFont(Font.font("Calibri", FontWeight.NORMAL, 20));

		Label userName = new Label("User Name:");
		Label pw = new Label("Password:");

		TextField userTextField = new TextField();
		userTextField.setPrefWidth(150);
		userTextField.setMaxWidth(150);

		PasswordField pwBox = new PasswordField();
		pwBox.setPrefWidth(150);
		pwBox.setMaxWidth(150);

		//Sign-In Button 2
		Button signInButton2 = new Button("Sign in");

		//Main-Menu Button
		Button mainMenuButton = new Button("Go to Home page");
		mainMenuButton.setText("Go back to main menu");
		mainMenuButton.setOnAction(e -> window.setScene(scene1)); // When button 2 is clicked it send you back to the home page
		signInPane.getChildren().addAll(mainMenuButton, signInButton2);

		//Node alignment and display settings for 'signInPane'
		greetingPane.getChildren().addAll(greeting);
		signInPane.setTop(greetingPane);
		signInPane.setCenter(centerVBox);
		signInPane.setBottom(bottomButtons);

		//Node alignment and display settings for 'centerVBox'
		centerVBox.getChildren().addAll(userName, userTextField, pw, pwBox);
		centerVBox.setAlignment(Pos.CENTER);

		scene2 = new Scene(signInPane, 300, 300);

		// Information to sign in on layout 2
		bottomButtons.setAlignment(Pos.BOTTOM_CENTER);
		bottomButtons.getChildren().add(signInButton2);

		final Text actiontarget = new Text();

		signInButton2.setOnAction(e -> 
		{

			actiontarget.setFill(Color.FIREBRICK);
			actiontarget.setText("Sign in button pressed");

			if (newUser.verifyCredentials(newUser.getUserID(), newUser.getPassword()))
			{
				newUser.setLoginStatus(true);
			}
		});

		// Display the first scene at program launch
		window.setScene(scene2);
		window.show();

	}

	@Override
	public void handle(ActionEvent arg0) {
		// TODO Auto-generated method stub

	}
}
