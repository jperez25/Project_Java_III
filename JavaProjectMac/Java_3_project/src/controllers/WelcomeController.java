package controllers;

import javafx.animation.PauseTransition;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.stage.Stage;

import java.io.IOException;
import java.lang.Thread;
import java.util.Observable;

public class WelcomeController {
	private Task copyWorker;
	@FXML
	private ProgressBar progressBar;
	@FXML
	private Label welcomeLbl;
	
	
	public void initialize() throws Exception {
		copyWorker = createWorker();
		progressBar.progressProperty().unbind();
		progressBar.progressProperty().bind(copyWorker.progressProperty());
		new Thread(copyWorker).start();
		
		PauseTransition delay = new PauseTransition();
		delay.setOnFinished( e -> {
			try {
				
				FXMLLoader loader2 = new FXMLLoader(getClass().getResource("/screens/First_Login.fxml"));
		        Parent root2;
				root2 = loader2.load();
		        Scene scene = new Scene(root2);
				Stage stage = (Stage) welcomeLbl.getScene().getWindow(); 
				Thread.sleep(1500);
				stage.setScene(scene);
			} catch (IOException | InterruptedException e1) {
				e1.printStackTrace();
			}
		} );
		delay.play();
	}
	
	public Task createWorker() {
        return new Task() {
            @Override
            protected Object call() throws Exception {
                for (int i = 0; i < 10; i++) {
                    Thread.sleep(40);
                    //updateMessage("2000 milliseconds");
                    updateProgress(i + 1, 10);

                    System.out.println(progressBar.getProgress());
                }
                return true;
            }
        };
    }

}