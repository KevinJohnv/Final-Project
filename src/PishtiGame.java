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


            pane.setOnMouseMoved(event -> {
                        for (int i = 0; i < Main.player.getPlayerDeck().size(); i++) {
                            Node n = pane.playerHand.getChildren().get(i);
                            n.setOnMouseClicked(e -> {

                                if (pane.otherSide.getChildren().size() > 2) {
                                    pane.otherSide.getChildren().remove(2,5);
                                }

                                if (pane.playerHand.getChildren().contains(n)) {
                                    Main.playCard(pane.playerHand.getChildren().indexOf(n), player, playerDeck, table);
                                    pane.pile.getChildren().add(pane.pile.getChildren().size(), n);
                                    System.out.println("Player: " + Integer.toString(player.getScore())+" "+player.getNumofCards() + " Computer: " + Integer.toString(computer.getScore()) +" "+ computer.getNumofCards());
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

                                        pane.playerCards.setText("Player: " + Integer.toString(player.getNumofCards()));
                                    }else if(!playerWon){
                                        computer.setScore(table.calcTablePoints());
                                        computer.setNumOfCardsInPile(table.getTablePile().size());
                                        table.getTablePile().clear();
                                        pane.updatePile();
                                        pane.compCards.setText("Computer: " + Integer.toString(Main.computer.getNumofCards()));
                                    }
                                    String playerScore =  Integer.toString(player.getScore());
                                    String compScore = Integer.toString(computer.getScore());

                                    if(player.getScore() > computer.getScore()){
                                        Text win = new Text("You Win! :D\nYour Score:"+playerScore+"\nComputer Score:"+compScore);
                                        win.setFont(new Font(47));
                                        pane.pile.getChildren().add(win);
                                    } else if(player.getScore() < computer.getScore()){
                                        Text win = new Text("You Lose! D;\nYour Score:"+playerScore+"\nComputer Score:"+compScore);
                                        win.setFont(new Font(47));
                                        pane.pile.getChildren().add(win);
                                    }else{
                                        Text win = new Text("Draw...\nYour Score:"+playerScore+"\nComputer Score:"+compScore);
                                        win.setFont(new Font(47));
                                        pane.pile.getChildren().add(win);
                                    }
                                }

                            });

                        }
                    });


        // ReDeals the cards if the cards have run out and there are still cards on the deck it will pass them out to player and Comp

        Scene scene = new Scene(pane, 700, 650);
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
                Main.playCard(pane.compHand.getChildren().indexOf(k), Main.computer, Main.computer.getCompDeck(), Main.table);
                pane.pile.getChildren().add(pane.pile.getChildren().size(), newCard);
                System.out.println("Player: " + Integer.toString(Main.player.getScore()) + " Computer: " + Integer.toString(Main.computer.getScore()));
                if (Main.table.win()){
                    firstPileWin = false;
                    Main.computer.setScore(Main.table.calcTablePoints()) ;
                    Main.computer.setNumOfCardsInPile(Main.table.getTablePile().size());
                    pane.compCards.setText("Computer: " + Integer.toString(Main.computer.getNumofCards()));
                    Main.table.getTablePile().clear();
                    Main.table.tableScore = 0;
                    pane.updatePile();
                    Main.computer.getScore();
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
                PathTransition pt = new PathTransition(Duration.millis(2000),
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
