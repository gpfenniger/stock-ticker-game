import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;


/** 	Game of Stock Ticker
	@author Griffin Pfenniger < griffin (dot) pfenniger (at) gmail >
	@version 0.0, 2018-01-13
*/
public class StockTicker extends Application {
	/** Launcher method for game
	 * 	Sets up the GUI and launches the main game loop
	 *  @param Stage : stage
	 */
	@Override public void start(Stage stage) {
		AnchorPane root = new AnchorPane();
		try {
			root = FXMLLoader.load(getClass().getResource("/game/index.fxml"));

			Scene scene = new Scene(root, 500, 500);

			scene.getStylesheets().add("/game/layout.css");

			stage.setScene(scene);
			stage.setTitle("Stock Ticker");
			stage.show();
		} catch(IOException e) {
			System.err.println("Could not find file");
		}
	}

	/** Launches app if run from command line
	 *  @param String[] : command line arguments
	 */
	public static void main(String[] args) {
		launch(args);
	}
}