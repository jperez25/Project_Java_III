package login.Controllers;

import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

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
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class JavaLessons {
	static private long begin_time;
	static private long stop_time;
	int searchSize = 10;
	int passNumber = -1;
	ArrayList<Integer> random = new ArrayList<>(searchSize);
	
	//Screen Elements
	@FXML
	private Button backBtn;
	@FXML
	private Button search;
	@FXML
	private Button sort;
	@FXML
	private Button hashing;
	@FXML
	private Button bblSort;
	
	//Selection sorting Elements 
	@FXML
	private AnchorPane sortingPane;
	@FXML
	private BorderPane sortingBorder;
	@FXML
	private HBox sortingBottom;
	@FXML
	private Button selectStart;
	@FXML
	private Button sortResetBtn;
	@FXML
	private Button sortStepBtn;
	@FXML
	private Pane pane;
	
	
	
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
	
	// Bubble sort Elements
	@FXML
	private AnchorPane bblSortPane;
	@FXML
	private BorderPane bblSortBorder;
	@FXML
	private HBox bblSortBottom;
	@FXML
	private Button bblStart;
	@FXML
	private Button bblResetBtn;
	@FXML
	private Button bblStepBtn;
	@FXML
	private Pane bblPane;
	
	private boolean needNextPass = true;
	private boolean endOfPass = true;
	private int index = -1; 
	
	public void initialize() {
		
		//Set panes to not visible
		searchPane.setVisible(false);
		hashingPane.setVisible(false);
		sortingPane.setVisible(false);
		
		
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
			sortingPane.setVisible(false);
			bblSortPane.setVisible(false);
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
			sortingPane.setVisible(false);
			bblSortPane.setVisible(false);
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
		
		//This is the Selection sorting Code. 
/*------------------------------------------------------------------------------------------------------------------------------*/

		for (int i = 1; i <= searchSize; i++) {
			 random.add(i);
			}
			Collections.shuffle(random);
			
			
		sort.setOnAction(e->{
			hashingPane.setVisible(false);
			searchPane.setVisible(false);
			sortingPane.setVisible(true);
			bblSortPane.setVisible(false);
		});
		
		selectStart.setOnAction(e->{
			reset();
			selectStart.setDisable(true);
		});
		
		// when the next step button is pressed, this clears the visual, figures out the next swap being made,
		// and repaints the scene
	 sortStepBtn.setOnAction(e->{
		   pane.getChildren().clear();
		   nextStep();
		   repaint();  
	 
	});
	 	// resets the animation
	 sortResetBtn.setOnAction(e->{
		 	pane.getChildren().clear();
		 	reset();
	 });
		
		  
		  
	
/*------------------------------------------------------------------------------------------------------------------------------*/
	 
	 //This is the Bubble sorting Code. 
/*------------------------------------------------------------------------------------------------------------------------------*/

	 		bblSort.setOnAction(e->{
	 			hashingPane.setVisible(false);
	 			searchPane.setVisible(false);
	 			sortingPane.setVisible(false);
	 			bblSortPane.setVisible(true);
	 		});
	 			
	 		bblStart.setOnAction(e->{
				bblReset();
				bblStart.setDisable(true);
			});
	 		// when the next step button is pressed, this clears the visual, figures out the next step being made,
	 		// and repaints the scene
	 	 bblStepBtn.setOnAction(e->{
	 		   bblPane.getChildren().clear();
	 		   bblNextStep();
	 		   bblRepaint();  
	 	 
	 	});
	 	 	// resets the animation
	 	 bblResetBtn.setOnAction(e->{
	 		 	bblPane.getChildren().clear();
	 		 	bblReset();
	 	 });
	 		
	 		  
	 		  
	 	
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
	
/*------------------------------------------------------------------------------------------------------------------------------------------------*/
	
	// Selection sort methods
/*-----------------------------------------------------------------------------------------------------------------------------------------------*/
	// figuring out the next numbers being swapped in selection sort
	public void nextStep() {
	passNumber++;
	   if (passNumber >= searchSize) { // end of sorting
		  sortStepBtn.setDisable(true);
	    passNumber = -1;
	   } else {
	    int currentMin = random.get(passNumber); // current number and its index are minimum
	    int currentMinIndex = passNumber;

	    for (int j = passNumber + 1; j < random.size(); j++) { 
	     if (currentMin > random.get(j)) { // looking for a smaller number to be the new minimum
	      currentMin = random.get(j);
	      currentMinIndex = j;
	     }
	    }
	    if (currentMinIndex != passNumber) { // if the index of the smallest number is not the one originally set as the minimum
	     random.set(currentMinIndex, random.get(passNumber)); // swap the numbers 
	     random.set(passNumber, currentMin);
	    }    
	   }
	}
	public void repaint() { // painting the rectangles and numbers 
		double numberWidth = pane.getWidth() / (searchSize + 2);
		   double numberHeight = pane.getHeight() / (searchSize + 2);
		   for (int i = 0; i < searchSize; i++) {
		    Rectangle r1 = new Rectangle(numberWidth * (i + 1), pane.getHeight() - numberHeight * random.get(i), numberWidth, numberHeight * random.get(i));
		    r1.setFill(Color.WHITE);
		    r1.setStroke(Color.BLACK);
		    Text num = new Text(numberWidth * (i + 1), pane.getHeight() - numberHeight * random.get(i) - (int)(numberHeight * 0.5), random.get(i) + "");
		    pane.getChildren().addAll(r1, num);
		   }
		   if ((passNumber != -1)&&(passNumber < searchSize)) {
		    Rectangle r2 = new Rectangle(numberWidth * (passNumber + 1), pane.getHeight() - numberHeight * random.get(passNumber), numberWidth, numberHeight * random.get(passNumber));
		    r2.setFill(Color.BLACK);
		    pane.getChildren().add(r2);
		   }
	}
	public void reset() { // resetting the selection sort animation
		 Collections.shuffle(random);
		 sortStepBtn.setDisable(false);
		  passNumber = -1;
		  repaint();
		}
	
/*-------------------------------------------------------------------------------------------------------------------------------------*/
	
	// Bubble sort methods
/*---------------------------------------------------------------------------------------------------------------------------------------*/
	
	//figuring out the next step to take
	public void bblNextStep() {
		   if (passNumber == -1) {
		    passNumber = 1;
		   } 
		   else {
		    if (endOfPass) {
		     passNumber++;
		    }
		   }
		   if (passNumber >= searchSize) {
		    bblStepBtn.setDisable(true);
		    passNumber = -1;
		   } 
		   else {    
		    if (endOfPass) {
		     needNextPass = false;
		     endOfPass = false;
		     index = 0;
		    } 
		    if (!endOfPass) {
		     if (random.get(index) > random.get(index + 1)) {
		      // Swap list[i] with list[i + 1]
		      int temp = random.get(index);
		      random.set(index, random.get(index + 1));
		      random.set(index + 1, temp);
		      needNextPass = true; // Next pass still needed      
		     }
		     index++;
		     if (!(index < random.size() - passNumber)) {
		      endOfPass = true;
		     }
		    }
		    if (endOfPass) {
		     if (!needNextPass) {
		      bblStepBtn.setDisable(true);
		      passNumber = -1;
		      index = -1; 
		     }
		    }    		      
		   }		   
	}
	
	
	public void bblRepaint() { // painting the rectangles and numbers 
		double numberWidth = bblPane.getWidth() / (searchSize + 2);
		   double numberHeight = bblPane.getHeight() / (searchSize + 2);
		   for (int i = 0; i < searchSize; i++) {
		    Rectangle r1 = new Rectangle(numberWidth * (i + 1), bblPane.getHeight() - numberHeight * random.get(i), numberWidth, numberHeight * random.get(i));
		    r1.setFill(Color.WHITE);
		    r1.setStroke(Color.BLACK);
		    Text num = new Text(numberWidth * (i + 1), bblPane.getHeight() - numberHeight * random.get(i) - (int)(numberHeight * 0.5), random.get(i) + "");
		    bblPane.getChildren().addAll(r1, num);
		   }
		   if ((index != -1)&&(index < searchSize)) {
		    Rectangle r2 = new Rectangle(numberWidth * (index + 1), bblPane.getHeight() - numberHeight * random.get(index), numberWidth, numberHeight * random.get(index));
		    r2.setFill(Color.BLACK);
		    bblPane.getChildren().add(r2);
		   }
	}
	
	
	public void bblReset() { // resetting the bubble sort animation
		 Collections.shuffle(random);
		 bblStepBtn.setDisable(false);
		  passNumber = -1;
		  needNextPass = true;
		  endOfPass = true;
		  index = -1;
		  bblRepaint();
	}
}