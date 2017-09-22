package login.Controllers;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class RestorationController {
	@FXML
	private Button backBtn;
	
	
	
	public void initialize() throws Exception {
			
		/*backBtn.setOnAction(e->{
	        FXMLLoader loader2 = new FXMLLoader(getClass().getResource("..\\Screens\\First_Login.fxml"));
	        Parent root2;
			try {
				root2 = loader2.load();
		        Scene scene = new Scene(root2);
				Stage stage = (Stage) backBtn.getScene().getWindow(); 
				stage.setScene(scene);
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		});*/
	}
}