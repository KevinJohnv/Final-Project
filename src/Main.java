import java.util.ArrayList;
import java.util.Scanner;
/*Kevin John*/

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
        ArrayList<Integer> playerDeck = new ArrayList<>();
        deck.deal(playerDeck);
        player = new Player(playerDeck);

        //Deal the deck to the computer
        ArrayList<Integer> compDeck = new ArrayList<>();
        deck.deal(compDeck);
        computer = new Computer(compDeck);

        //Deal the deck to table
        ArrayList<Integer> tableDeck = new ArrayList<>();
        table =new Table( DeckDeal(tableDeck));

        PishtiGame.main(null);
    }

    public static void playCard(int i, Player player, ArrayList<Integer> playerDeck, Table table){
            Integer cardPlayed = player.playCard(i);


            table.addCardToTable(cardPlayed);

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
