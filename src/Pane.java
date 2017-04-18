import javafx.application.Application;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.util.Stack;

/**
 * Created by HpUser on 4/17/2017.
 */
public class Pane extends Application {

    @Override
    public void start(Stage primaryStage) {
        BorderPane bigPicture = new BorderPane();
        StackPane remainingDeck = new StackPane();
        StackPane table = new StackPane();
        HBox playerHand = new HBox(5);
        HBox compHand = new HBox(5);

        bigPicture.setLeft(remainingDeck);
        bigPicture.setCenter(table);
        bigPicture.setBottom(playerHand);
        bigPicture.setTop(compHand);

        // asdfad
    }

    public static void main(String[] args) {
        launch(args);
    }
}
