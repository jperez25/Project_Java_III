package login.Controllers;

import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class JavaLessons {
	static private long begin_time;
	static private long stop_time;
	
	//Screen Elements
	@FXML
	private Button backBtn;
	@FXML
	private Button search;
	@FXML
	private Button sort;
	@FXML
	private Button hashing;
	
	//Sorting Elements <--------------------------------------Karissa put Your Elements here
	@FXML
	private AnchorPane sortingPane;
	
	//Hash Elements
	@FXML
	private AnchorPane hashingPane;
	@FXML
	private Button hashStringBtn;
	@FXML
	private TextField HashFld;
	@FXML
	private TextArea showHashedStringTxtArea;
	
	//Search Elements
	@FXML
	private AnchorPane searchPane;
	@FXML
	private TextField input;
	@FXML
	private Button run;
	@FXML
	private Button reset;
	@FXML
	private TextArea textArea;

	public void initialize() {
		
		//Set panes to not visible
		searchPane.setVisible(false);
		hashingPane.setVisible(false);
		
		
		//This is the Binary Search Code. 
/*------------------------------------------------------------------------------------------------------------------------------*/
		//An array of numbers to search
		int numbers[] = { 536, 289, 296, 429, 319, 142, 349,
				101, 388, 147, 417, 199, 207, 222,
				189, 30, 447, 521, 234, 600};

		//Once search button is pressed then do the following...
		search.setOnAction( e -> {
			
			//Set the searchPane true and textArea false
			//Set other panes to false
			searchPane.setVisible(true);
			hashingPane.setVisible(false);
			textArea.setEditable(false);
			//
			textArea.appendText(Arrays.toString(numbers));

			//First we must sort the array in ascending order
			//numbers.sort();
			Arrays.sort(numbers);
			textArea.appendText("\n" + Arrays.toString(numbers));
		});
		
		// listener
				input.setOnKeyPressed(e -> {
					if (e.getCode() == KeyCode.ENTER) {
						run.fire();
					}
				});

		//When the run button is pushed
		run.setOnAction( run->{
			int result;

			//Gather text from the textField
			String in = input.getText();
			int searchValue = Integer.parseInt(in);
			textArea.appendText("\n\nYou entered the number: " + in);

			//Display the current time in milliseconds when sorting started
			begin_time = System.currentTimeMillis();
			textArea.appendText("\nTime sorting started: " + begin_time);

			//Search for the value
			result = BinarySearch.search(numbers, searchValue);

			//Display the results
			if (result == -1)
			{
				//If the result is = -1 then the value was not found
				textArea.appendText("\n" + searchValue + " value cannot be found");
				textArea.appendText("\nTime sorting ended: " + stop_time);
				textArea.appendText("\nSelection Sort Took " + (stop_time - begin_time));
			} else {
				//If the result is found then display what element it was found at
				textArea.appendText("\n" + searchValue + " was found at element " + result);
				stop_time = System.currentTimeMillis();
				textArea.appendText("\nTime sorting ended: " + stop_time);
				textArea.appendText("\nSelection Sort Took " + (stop_time - begin_time));
			}
		});

		//If reset button is pushed...
		reset.setOnAction(reset-> {
			//Text area and input is cleared. Then the array is displayed again
			textArea.clear();
			input.clear();
			textArea.appendText(Arrays.toString(numbers));
		});
/*------------------------------------------------------------------------------------------------------------------------------*/
		
		
		
		//This is the Hash Code. 
/*------------------------------------------------------------------------------------------------------------------------------*/
		hashing.setOnAction(e->{
			hashingPane.setVisible(true);
			searchPane.setVisible(false);
		});
		//Listener
		HashFld.setOnKeyPressed(e -> {
			if (e.getCode() == KeyCode.ENTER) {
				hashStringBtn.fire();
			}
		});
		hashStringBtn.setOnAction(e->{
			//get text from field
			String toHashString = HashFld.getText();
			try {
				//Create instance of the hashing class using sha-256 algorithm
				MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
				//hash string
				messageDigest.update(toHashString.getBytes());
				//Convert to hashed string bytes to String
				String encryptedString = new String(messageDigest.digest());
				//Display hashed String
				showHashedStringTxtArea.setText(encryptedString);
			} catch (NoSuchAlgorithmException e1) {
				e1.printStackTrace();
				Alert alert = new Alert(Alert.AlertType.ERROR);
				alert.setTitle("Hashing Error");
				alert.setHeaderText("Oops We couldn't hash your string");
				alert.showAndWait();
				
			}
		});
		
/*------------------------------------------------------------------------------------------------------------------------------*/
		
		//This is the Sorting Code. 
/*------------------------------------------------------------------------------------------------------------------------------*/
		//Karissa put your code here
/*------------------------------------------------------------------------------------------------------------------------------*/
		
		
		//Go back to the home page screen
		backBtn.setOnAction(e->{
			FXMLLoader loader = new FXMLLoader(getClass().getResource("../Screens/Home.fxml"));
			Parent root;
			try {
				root = loader.load();
				Scene scene = new Scene(root);
				Stage stage = (Stage) backBtn.getScene().getWindow(); 
				stage.setScene(scene);
			} catch (IOException e1) {
				//Let user know screen was not found
				Alert alert = new Alert(Alert.AlertType.ERROR);
				alert.setTitle("Screen Error");
				alert.setHeaderText("Screen Not found");
				alert.setContentText("The screen was not found");
				alert.showAndWait();
			}
		});
	}
}
