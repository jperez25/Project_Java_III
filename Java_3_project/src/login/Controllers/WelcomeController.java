package login.Controllers;

import javafx.animation.PauseTransition;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.lang.Thread;

public class WelcomeController {
	@FXML
	private ProgressBar progressBar;
	@FXML
	private Label welcomeLbl;

	public void initialize() throws Exception {

		/*
		 * To make it work it essential to understand multithreading. The task below is
		 * executed in parallel to delay object. It increases the progress bar
		 * percentage over time
		 */
		Task<Void> task = new Task<Void>() {

			@Override
			public Void call() {

				for (int i = 0, j = 10; i <= 10; i++, j--) {
					try {
						Thread.sleep(j * 100);
					} catch (InterruptedException e) {
						Thread.interrupted();
						break;
					}
					System.out.println(progressBar.getProgress());
					updateProgress(i + 1, 10);

				}
				return null;
			}

		};

		/*
		 * Binding progress bar to task Create new thread a pass task as the task to be
		 * executed in parallel start the task
		 */
		progressBar.progressProperty().bind(task.progressProperty());
		Thread th = new Thread(task);
		th.setDaemon(true);
		th.start();

		/*
		 * To make the transition between screens smooth add a delay equal to the time
		 * the task takes to execute and then resume threading
		 */
		PauseTransition delay = new PauseTransition(Duration.millis(5550));
		delay.setOnFinished(e -> {
			try {

				FXMLLoader loader2 = new FXMLLoader(getClass().getResource("../Screens/Home.fxml"));
				Parent root2;
				root2 = loader2.load();
				Scene scene = new Scene(root2);
				Stage stage = (Stage) welcomeLbl.getScene().getWindow();
				Thread.sleep(1500);
				stage.setScene(scene);
			} catch (IOException | InterruptedException e1) {

				Alert alert = new Alert(Alert.AlertType.ERROR);
				alert.setTitle("Screen Error");
				alert.setHeaderText("Screen Not found");
				alert.setContentText("The screen was not found");
				alert.showAndWait();
			}
		});
		delay.play();

	}

}
