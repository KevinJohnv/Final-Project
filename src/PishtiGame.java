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

        // ReDeals the cards if the cards have run out and there are still cards on the deck it will pass them out to player and Comp
        /*(Main.player.getSize() == 0){
            Main.giveCards(Main.player,Main.deck,player);
            player.setPlayerDeck(playerDeck);
        }

        if(compDeck.size() == 0 && deck.numberOfCards()>0){
            giveCards(compDeck,deck,computer);
            player.setPlayerDeck(compDeck);
        }else if(compDeck.size() == 0 && deck.numberOfCards()==0){

        }
        printGameInfo(deck,player,playerDeck,computer,compDeck,table,tableDeck);*/


        Scene scene = new Scene(pane, 500, 500);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Pishti Game");
        primaryStage.show();

    }

    public static void main(String[] args) {
        launch(args);
    }
}
