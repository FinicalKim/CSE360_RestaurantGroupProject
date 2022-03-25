import java.io.*;
import java.util.*;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class Main_test extends Application
{
	public static void main(String[] args) throws IOException
	{
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
}

