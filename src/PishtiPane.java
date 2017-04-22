import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

import java.util.Random;

/**
 * Created by HpUser on 4/18/2017.
 */
public class PishtiPane extends BorderPane {
    StackPane pile = new StackPane();
    StackPane remainingDeck = new StackPane();
    HBox playerHand = new HBox(5);
    HBox compHand = new HBox(5);

    private Text deckSize = new Text(Integer.toString(Main.deck.numberOfCards()));

    public PishtiPane() {

        drawHand(Main.player);
        drawHand(Main.computer);

        deckSize.setFont(new Font(45));

        remainingDeck.getChildren().add(new ImageView(new Image("card/b2fv.png")));
        remainingDeck.getChildren().add(deckSize);

        for (int i = 0; i < 3; i++) {
            Node n = new ImageView(new Image("card/b2fv.png"));
            rotate(n);
            pile.getChildren().add(n);
        }
        Node n = new ImageView(new Image("card/" + Main.table.getTablePile().get(3) + ".png"));
        rotate(n);
        pile.getChildren().add(n);

        setCenter(pile);
        pile.setAlignment(Pos.CENTER);
        pile.setPadding(new Insets(10,10,10,10));
        pile.setStyle("-fx-border-color: black; -fx-background-color: forestgreen");

        setBottom(playerHand);
        playerHand.setAlignment(Pos.CENTER);
        playerHand.setPadding(new Insets(10,10,10,10));
        playerHand.setPrefHeight(120);
        //playerHand.setStyle("-fx-border-color: black");

        setLeft(remainingDeck);
        remainingDeck.setAlignment(Pos.CENTER);
        remainingDeck.setPadding(new Insets(10,10,10,10));
        remainingDeck.setPrefWidth(100);
        //remainingDeck.setStyle("-fx-border-color: black");


        setTop(compHand);
        compHand.setAlignment(Pos.CENTER);
        compHand.setPadding(new Insets(10,10,10,10));
        //compHand.setStyle("-fx-border-color: black");
        compHand.setPrefHeight(120);

        setStyle("-fx-border-color: black; -fx-background-color: saddlebrown");
    }

    public void rotate(Node n) {
        Random rdm = new Random();
        n.setRotate(rdm.nextInt(90) - rdm.nextInt(90));
    }

    public void drawHand(Player p) {
        if (p instanceof Computer) {
            compHand.getChildren().clear();
            for (int i = 0; i < Main.computer.size(); i++) {
                compHand.getChildren().add(new ImageView(new Image("card/b2fv.png")));
            }
        }
        else {
            playerHand.getChildren().clear();
            for (Integer n : Main.player.getPlayerDeck()) {
                playerHand.getChildren().add(new ImageView(new Image("card/" + n + ".png")));
            }
        }

        deckSize.setText(Integer.toString(Main.deck.numberOfCards()));
    }

    public void updatePile() {
        pile.getChildren().clear();
        for (int n = 0; n < Main.table.getTablePile().size(); n++) {
            ImageView card = new ImageView(new Image("card/" + n + ".png"));
            pile.getChildren().add(card);
            rotate(card);
        }
    }
}