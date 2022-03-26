import java.io.*;
import java.util.*;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class Main_test extends Application implements EventHandler<Action>
{
	Button button1;
	Stage window;
	Stage scene1, scene2;
	
	public static void main(String[] args) throws IOException
	{
		launch(args);
		
		String inputName = new String();
		String inputPassword = new String();
		
		InputStreamReader isr = new InputStreamReader(System.in);
		BufferedReader stdin = new BufferedReader(isr);
		
		System.out.println("Hello, let's create a User object.");
		System.out.print("First, type the username you want the user to have: ");
		inputName = stdin.readLine();
		System.out.println("Great, you chose '" + inputName + "' as a name.");
		System.out.print("Next, choose the password to associate with '" + inputName + "': ");
		inputPassword = stdin.readLine();
		User newUser = new User(inputName, inputPassword);
		System.out.println("'newUser' username is: " + newUser.getUserID() + " with password: " + newUser.getPassword());
		
	}
	
	public void start(Stage primaryStage) throws Exception {
		window = primaryStage;
		Label label1 = new Label("Welcome");
		primaryStage.setTitle("Restraurant");
		button1 = new Button();
		
		button.setText("Create new User");
		
		button.setOnAction(e -> window.setScene(scene2));
		
		StackPane layout = new StackPane();
		layout.getChildren().add(button);
		
		Scene scene = new Scene(layout, 300, 250);
		window.setScene(scene);
		window.show();
	}
}

