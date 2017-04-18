import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;

import java.util.Random;

/**
 * Created by HpUser on 4/18/2017.
 */
public class PishtiPane extends BorderPane {
    StackPane pile = new StackPane();
    StackPane remainingDeck = new StackPane();
    HBox playerHand = new HBox(5);
    HBox compHand = new HBox(5);


    public PishtiPane() {

        for (Integer n: Main.player.getPlayerDeck()) {
            playerHand.getChildren().add(new ImageView(new Image("card/" + n + ".png")));
        }

        setCenter(pile);
        pile.setAlignment(Pos.CENTER);
        pile.setPadding(new Insets(10,10,10,10));
        pile.setStyle("-fx-border-color: black; -fx-background-color: green");

        setBottom(playerHand);
        playerHand.setAlignment(Pos.CENTER);
        playerHand.setPadding(new Insets(10,10,10,10));
        playerHand.setPrefHeight(120);
        playerHand.setStyle("-fx-border-color: black");

        setLeft(remainingDeck);
        remainingDeck.setAlignment(Pos.CENTER);
        remainingDeck.setPadding(new Insets(10,10,10,10));
        remainingDeck.setPrefWidth(100);
        remainingDeck.setStyle("-fx-border-color: black");


        setTop(compHand);
        compHand.setAlignment(Pos.CENTER);
        compHand.setPadding(new Insets(10,10,10,10));
        compHand.setStyle("-fx-border-color: black");
        compHand.setPrefHeight(120);

        setStyle("-fx-border-color: black; -fx-background-color: lime");
    }

    public void rotate(Node n) {
        Random rdm = new Random();
        n.setRotate(rdm.nextInt(90) - rdm.nextInt(90));
    }
}
