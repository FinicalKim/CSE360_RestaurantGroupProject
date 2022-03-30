import java.io.*;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Main_test extends Application implements EventHandler<ActionEvent>
{
	Stage window;
	Scene scene1, scene2;
	
	public static void main(String[] args) throws IOException
	{
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
		System.out.println("'newUser' username is: " + newUser.getUserID() + " with password: " + newUser.getPassword());
		
	}
	
	public void start(Stage primaryStage) throws Exception {
		window = primaryStage;	// variable to reference the applications window
			//Title the window "Restaurant"
		window.setTitle("Restaurant");
		Label label1 = new Label("Welcome to the Restaurants application");
		
			// Button 1
		Button button1 = new Button("Go to Account information");
		button1.setText("Create new User");
		button1.setOnAction(e -> window.setScene(scene2));	//button 1 sends you to the account information page
		
			// Layout 1
		GridPane layout1 = new GridPane();
		layout1.setAlignment(Pos.CENTER);
		layout1.setHgap(10);
		layout1.setVgap(10);
		layout1.setPadding(new Insets(25, 25, 25, 25));
		layout1.getChildren().addAll(label1, button1);
		scene1 = new Scene(layout1, 300,300);
		
			// Button 2
		Button button2 = new Button("Go to Home page");
		button2.setText("Go back to main menu");
		button2.setOnAction(e -> window.setScene(scene1));	// When button 2 is clicked it send you back to the home page
		
			// Layout 2
		StackPane layout2 = new StackPane();
		layout2.getChildren().add(button2);
		scene2 = new Scene(layout2, 600,600);
		
			// Display the first scene
		window.setScene(scene1);
		window.show();

	}

	@Override
	public void handle(ActionEvent arg0) {
		// TODO Auto-generated method stub
		
	}
}

