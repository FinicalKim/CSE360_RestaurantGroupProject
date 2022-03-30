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
		Button button1 = new Button("Go to sign in menu");
		button1.setText("Sign in");
		button1.setOnAction(e -> window.setScene(scene2));	//button 1 sends you to the Sign in page
		
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
		GridPane layout2 = new GridPane();
		layout2.setAlignment(Pos.CENTER);
		layout2.setHgap(10);
		layout2.setVgap(10);
		layout2.setPadding(new Insets(25, 25, 25, 25));
		layout2.getChildren().addAll(button2);
		scene2 = new Scene(layout2, 300,300);
		// Information to sign in on layout 2
		Text scenetitle = new Text("Welcome");
		scenetitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
		layout2.add(scenetitle, 0, 0, 2, 1);

		Label userName = new Label("User Name:");
		layout2.add(userName, 0, 1);

		TextField userTextField = new TextField();
		layout2.add(userTextField, 1, 1);

		Label pw = new Label("Password:");
		layout2.add(pw, 0, 2);

		PasswordField pwBox = new PasswordField();
		layout2.add(pwBox, 1, 2);
		
		Button btn = new Button("Sign in");
		HBox hbBtn = new HBox(10);
		hbBtn.setAlignment(Pos.BOTTOM_RIGHT);
		hbBtn.getChildren().add(btn);
		grid.add(hbBtn, 1, 4);
		
		final Text actiontarget = new Text();
        	grid.add(actiontarget, 1, 6);
		
		btn.setOnAction(e -> {
		actiontarget.setFill(Color.FIREBRICK);
		actiontarget.setText("Sign in button pressed");
		});
		
			// Display the first scene
		window.setScene(scene1);
		window.show();

	}

	@Override
	public void handle(ActionEvent arg0) {
		// TODO Auto-generated method stub
		
	}
}

