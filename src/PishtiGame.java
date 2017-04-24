import javafx.animation.PathTransition;
import javafx.application.Application;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Line;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.util.ArrayList;

/**
 * Created by HpUser on 4/18/2017.
 */
public class PishtiGame extends Application {

    private boolean playerWon;
    private boolean firstPileWin = true;

    @Override
    public void start(Stage primaryStage) {
        // Local Variables
        PishtiPane pane = new PishtiPane();
        Player player = Main.player;
        Deck deck = Main.deck;
        Computer computer = Main.computer;
        Table table = Main.table;

        //Localized ArrayLists
        ArrayList<Integer> playerDeck = player.getPlayerDeck();
        ArrayList<Integer> compDeck = computer.getPlayerDeck();
        ArrayList<Integer> tableDeck = table.getTablePile();


            pane.setOnMouseMoved(event -> {
                        for (int i = 0; i < Main.player.getPlayerDeck().size(); i++) {
                            Node n = pane.playerHand.getChildren().get(i);
                            n.setOnMouseClicked(e -> {
                                if (pane.otherSide.getChildren().size() > 3) {
                                    pane.otherSide.getChildren().remove(3,6);
                                }
                                if (pane.playerHand.getChildren().contains(n)) {
                                    Main.playCard(pane.playerHand.getChildren().indexOf(n), player, playerDeck, table);
                                    pane.pile.getChildren().add(pane.pile.getChildren().size(), n);
                                    if (table.win()) {
                                        if (firstPileWin) {
                                            for (int j = 0; j < 3; j++) {
                                                ImageView flip = new ImageView(new Image("card/" + tableDeck.get(j) + ".png"));
                                                pane.otherSide.getChildren().add(flip);
                                            }
                                            firstPileWin = false;
                                        }
                                        player.setScore(table.calcTablePoints());
                                        player.setNumOfCardsInPile(table.getTablePile().size());
                                        pane.playerCards.setText("Player: " + Integer.toString(player.getNumofCards()));
                                        table.getTablePile().clear();
                                        table.tableScore = 0;
                                        pane.updatePile();
                                        playerWon = true;
                                        Node x = new ImageView(new Image("card/b2fv.png"));
                                        pane.pile.getChildren().add(x);
                                        PathTransition pt = new PathTransition(Duration.millis(1000),
                                                new Line(40, 40, 1000, 600),x);
                                        pt.setCycleCount(1);
                                        pt.play();
                                    }
                                    n.requestFocus();
                                    pane.playerHand.getChildren().remove(n);
                                    pane.rotate(n);
                                    PathTransition pt = new PathTransition(Duration.millis(1000),
                                            new Line(50, 200, 40, 40), n);
                                    pt.setCycleCount(1);
                                    pt.play(); // Start animation
                                    compPlay(pane);
                                }
                                if (Main.player.getPlayerDeck().isEmpty() && deck.returnDeck().size() != 0 ) {
                                    Main.giveCards(playerDeck, deck, player);
                                    pane.drawHand(player);
                                    //player.setPlayerDeck(playerDeck);
                                }
                                if (computer.getCompDeck().isEmpty() && deck.returnDeck().size() != 0) {
                                    Main.giveCards(compDeck, deck, computer);
                                    //player.setPlayerDeck(compDeck);
                                    pane.drawHand(computer);
                                }
                                if(deck.returnDeck().isEmpty() && compDeck.isEmpty()){
                                    if(player.getNumofCards() > computer.getNumofCards()){
                                        player.setScore(3);
                                    }else if(player.getNumofCards() < computer.getNumofCards()){
                                        computer.setScore(3);
                                    }
                                    if(playerWon){
                                        player.setScore(table.calcTablePoints());
                                        player.setNumOfCardsInPile(table.getTablePile().size());
                                        table.getTablePile().clear();
                                        pane.updatePile();
                                        Node x = new ImageView(new Image("card/b2fv.png"));
                                        pane.pile.getChildren().add(x);
                                        PathTransition pt = new PathTransition(Duration.millis(1000),
                                                new Line(40, 40, 1000, 600),x);
                                        pt.setCycleCount(1);
                                        pt.play();
                                        pane.playerCards.setText("Player: " + Integer.toString(player.getNumofCards()));
                                    }else if(!playerWon){
                                        computer.setScore(table.calcTablePoints());
                                        computer.setNumOfCardsInPile(table.getTablePile().size());
                                        table.getTablePile().clear();
                                        pane.updatePile();
                                        Node x = new ImageView(new Image("card/b2fv.png"));
                                        pane.pile.getChildren().add(x);
                                        PathTransition pt = new PathTransition(Duration.millis(1000),
                                                new Line(40, 40, 1000, -520),x);
                                        pt.setCycleCount(1);
                                        pt.play();
                                        pane.compCards.setText("Computer: " + Integer.toString(Main.computer.getNumofCards()));
                                    }
                                    String playerScore =  Integer.toString(player.getScore());
                                    String compScore = Integer.toString(computer.getScore());
                                    if(player.getScore() > computer.getScore()){
                                        Text win = new Text("You Win!\nYour Score:"+playerScore+"\nComputer Score:"+compScore);
                                        win.setFont(new Font(47));
                                        pane.pile.getChildren().add(win);
                                    } else if(player.getScore() < computer.getScore()){
                                        Text win = new Text("You Lose!\nYour Score:"+playerScore+"\nComputer Score:"+compScore);
                                        win.setFont(new Font(47));
                                        pane.pile.getChildren().add(win);
                                    }else{
                                        Text win = new Text("Draw!\nYour Score:"+playerScore+"\nComputer Score:"+compScore);
                                        win.setFont(new Font(47));
                                        pane.pile.getChildren().add(win);
                                    }
                                }
                            });
                        }
                    });
        Scene scene = new Scene(pane, 700, 650);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Pishti Game");
        primaryStage.show();
    }

    public void compPlay(PishtiPane pane) {
        for (int i = 0; i < Main.computer.size(); i++ ) {
            Node k = pane.compHand.getChildren().get(i);
            if (!Main.table.getTablePile().isEmpty()) {
                for (int j = 0; j < Main.computer.size(); j++) {
                    if (Main.computer.getCompDeck().get(j) % 13 == 11 ||
                            Main.computer.getCompDeck().get(j) % 13 == Main.table.getTablePile().get(Main.table.getTablePile().size() - 1) % 13) {
                        k = pane.compHand.getChildren().get(j);
                        break;
                    }
                }
            }
            if (pane.compHand.getChildren().contains(k)) {
                ImageView newCard = new ImageView(new Image("card/" + Main.computer.getCompDeck().get(
                        pane.compHand.getChildren().indexOf(k)) + ".png"));
                Main.playCard(pane.compHand.getChildren().indexOf(k), Main.computer, Main.computer.getCompDeck(), Main.table);
                pane.pile.getChildren().add(pane.pile.getChildren().size(), newCard);
                if (Main.table.win()){
                    firstPileWin = false;
                    Main.computer.setScore(Main.table.calcTablePoints()) ;
                    Main.computer.setNumOfCardsInPile(Main.table.getTablePile().size());
                    pane.compCards.setText("Computer: " + Integer.toString(Main.computer.getNumofCards()));
                    Main.table.getTablePile().clear();
                    Main.table.tableScore = 0;
                    pane.updatePile();
                    playerWon = false;
                    Node x = new ImageView(new Image("card/b2fv.png"));
                    pane.pile.getChildren().add(x);
                    PathTransition pt = new PathTransition(Duration.millis(1000),
                            new Line(40, 40, 1000, -520),x);
                    pt.setCycleCount(1);
                    pt.play();
                }
                pane.compHand.getChildren().remove(k);
                pane.rotate(newCard);
                PathTransition pt = new PathTransition(Duration.millis(1500),
                        new Line(50, -120, 40, 40), newCard);
                pt.setCycleCount(1);
                pt.play();
                return;
            }
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
