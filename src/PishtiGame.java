import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Created by HpUser on 4/18/2017.
 */
public class PishtiGame extends Application {

    @Override
    public void start(Stage primaryStage) {
        PishtiPane pane = new PishtiPane();

        Scene scene = new Scene(pane, 500, 500);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Pishti Game");
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
