import javafx.application.Application;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.util.ArrayList;

/**
 * Created by HpUser on 4/18/2017.
 */
public class PishtiGame extends Application {

    @Override
    public void start(Stage primaryStage) {
        // Local Datatypes
        PishtiPane pane = new PishtiPane();
        Player player = Main.player;
        Deck deck = Main.deck;
        Computer computer = Main.computer;
        Table table = Main.table;

        //Local ArrayLists
        ArrayList<Integer> playerDeck = player.getPlayerDeck();
        ArrayList<Integer> compDeck = computer.getPlayerDeck();
        ArrayList<Integer> tableDeck = table.getTablePile();

            for (int i = 0; i < Main.player.getPlayerDeck().size(); i++) {
                Node n = pane.playerHand.getChildren().get(i);
                n.setOnMouseClicked(e -> {
                    if (pane.playerHand.getChildren().contains(n)) {
                        Main.playCard(pane.playerHand.getChildren().indexOf(n), player, playerDeck, table);
                        pane.pile.getChildren().add(pane.pile.getChildren().size(), n);
                        n.requestFocus();
                        pane.playerHand.getChildren().remove(n);
                        pane.rotate(n);

                        compPlay(pane);
                    }

                });
            }


        // ReDeals the cards if the cards have run out and there are still cards on the deck it will pass them out to player and Comp
        if (Main.player.getSize() == 1){
            Main.giveCards(playerDeck,deck,player);
            pane.drawHand(player);
            player.setPlayerDeck(playerDeck);
        }

        if(computer.size() == 0 ){
            Main.giveCards(compDeck,deck,computer);
            player.setPlayerDeck(compDeck);
            pane.drawHand(computer);
        }

        Scene scene = new Scene(pane, 500, 500);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Pishti Game");
        primaryStage.show();

    }

    public void compPlay(PishtiPane pane) {
        for (int i = 0; i < Main.computer.size(); i++ ) {
            Node k = pane.compHand.getChildren().get(i);
            if (pane.compHand.getChildren().contains(k)) {
                ImageView newCard = new ImageView(new Image("card/" + Main.computer.getCompDeck().get(
                        pane.compHand.getChildren().indexOf(k)) + ".png"));
                pane.pile.getChildren().add(pane.pile.getChildren().size(), newCard);
                Main.computer.getCompDeck().remove(pane.compHand.getChildren().indexOf(k));
                pane.compHand.getChildren().remove(k);
                pane.rotate(newCard);
                //Main.playCard(pane.compHand.getChildren().indexOf(k), computer, compDeck, table);
                return;
            }
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
