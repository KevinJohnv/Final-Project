import java.util.ArrayList;
import java.util.Scanner;
/*Kevin John*/
/**
 * Created by iSeeU on 4/6/17.
 */
/* Objects/Classes needed:
*  - Deck
*  - Card
*  - Pile
*  - Points
*  - Player
*
*
* Methods:
* Main
*   - Distribute 4 cards to Player, Comp, and Pile (pile has one facing up)
*   - De
* Deck - Methods Complete
*   - Shuffle
*   - Deal x2(one to deal the cards and one to take from the deck onw you have run out of cards)
* Table - Not started
*   - CurrentPile
*   - Give(when player has won the pile)
*   - Pistish(a form of give)
* Points - Not started
*   - Count(count up all the card)
*   - FacePoint(points for all face cards)
*   - Pistish
*   - Total(returns the total score)
*       *Check if cards are similar with x%13
*   - Compare(Finds out who won and displays message who won)
* Player
*   - NewSet(after the player is out of four cards)
*   - PlayCard
*   - PlayAgain
*   - Quit
* Computer - Subclass of player
*   - AI
*
*/
public class Main {
    public static Player player;
    public static Computer computer;
    public static Table table;
    public static Deck deck;
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        // Initalize the deck and shuffle
        deck = new Deck();
        deck.shuffle();

        //Deal the deck to the player
        ArrayList<Integer> playerDeck = new ArrayList<Integer>();
        deck.deal(playerDeck);
        player = new Player(playerDeck);

        //Deal the deck to the computer
        ArrayList<Integer> compDeck = new ArrayList<Integer>();
        deck.deal(compDeck);
        computer = new Computer(compDeck);

        //Deal the deck to table
        ArrayList<Integer> tableDeck = new ArrayList<Integer>();
        table =new Table( DeckDeal(tableDeck));

        PishtiGame.main(null);

        boolean rerun = false;

        while (rerun == true) {

            // ReDeals the cards if the cards have run out and there are still cards on the deck it will pass them out to player and Comp
            if(playerDeck.size() == 0){
                giveCards(playerDeck,deck,player);
                player.setPlayerDeck(playerDeck);
            }

            if(compDeck.size() == 0 && deck.numberOfCards()>0){
                giveCards(compDeck,deck,computer);
                player.setPlayerDeck(compDeck);
            }else if(compDeck.size() == 0 && deck.numberOfCards()==0){

            }

        }


    }

    public Player getPlayer(){
        return player;
    }

    public static boolean playerTurn(ArrayList<Integer> playerDeck, Player player, Table table) {


        // This section allows the player to play a card depending one which one is selected
        int commandInput;
        Scanner scan = new Scanner(System.in);
        boolean myTurn = true;
        while (myTurn == true) {
            commandInput = scan.nextInt();

            if (commandInput == 5) {

            } else if (commandInput < 5 && commandInput>0) {
                playCard(commandInput, player, playerDeck, table);
                myTurn = false;
            }
        }

        return true;
    }

    public static void playCard(int i, Player player, ArrayList<Integer> playerDeck, Table table){
        if (player instanceof Computer) {
            Integer cardPlayed = player.playCard(i);

            table.addCardToTable(cardPlayed);
        } else {
            Integer cardPlayed = player.playCard(i);


            table.addCardToTable(cardPlayed);
        }

    }

    public static void giveCards (ArrayList playerDeck,Deck deck, Player player){
        if (player instanceof Computer) {
            computer.setPlayerDeck(deck.deal(computer.getCompDeck()));
        } else {
            player.setPlayerDeck(deck.deal(playerDeck));
        }
    }






    public static ArrayList<Integer> DeckDeal(ArrayList<Integer> tableDeck){
        if((deck.returnDeck().get(3))%13  == 11){
            deck.shuffle();

            DeckDeal(tableDeck);
        }else{
            deck.deal(tableDeck);
        }
        return tableDeck;
    }

}
