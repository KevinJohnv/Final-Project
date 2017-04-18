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
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        // Initalize the deck and shuffle
        Deck deck = new Deck();
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
        deck.deal(tableDeck);
        table = new Table(tableDeck);


        boolean rerun = true;

        while (rerun == true) {

            PishtiGame.main(null);

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
            printGameInfo(deck,player,playerDeck,computer,compDeck,table,tableDeck);

            System.out.println("Enter a command\n1 - How many card are in the deck, Player's hand, table, and Computer's hand\n" +
                    "2 - What are the IDs of the Player's cards\n3 - What are the ID's of the Computer's hand\n4 - What are the IDS of the cards on the Table" +
                    "\n5 - exit the program\n6 - Play a card from player\n7 - See players points\n************************************************************************************\n");

            int commandInput = scan.nextInt();

            if (commandInput == 1) {
                System.out.println("\n************************************************************************************\n" + "Deck - " + deck.numberOfCards() + "\nPlayer - " + player.getSize() + "\nComputer - " + computer.size() +
                        "\nTable - " + tableDeck.size() + "\n************************************************************************************\n");
            } else if (commandInput == 2) {
                System.out.println("\n************************************************************************************\n" + "The player's hand consist of" + player.getPlayerDeck() + "\n************************************************************************************\n");
            } else if (commandInput == 3) {
                System.out.println("\n************************************************************************************\n" + "The computer's hand consits of " + compDeck + "\n************************************************************************************\n");
            } else if (commandInput == 4) {
                System.out.println("\n************************************************************************************\n" + "The table's hand consits of " + tableDeck + "\n************************************************************************************\n");
            } else if (commandInput == 5) {
                System.out.println("GoodBye :^)");
                rerun = false;
            } else if (commandInput == 6) {
                rerun = playerTurn(playerDeck, player, table);

            }else if (commandInput == 7){
                System.out.println(player.getScore());
            }
        }


    }

    public Player getPlayer(){
        return player;
    }

    public static boolean playerTurn(ArrayList<Integer> playerDeck, Player player, Table table) {
        System.out.println("Select a card  to play. Enter 1 - "+playerDeck.size()+" to play a card, or hit 5 to see all your cards");

        // This section allows the player to play a card depending one which one is selected
        int commandInput;
        Scanner scan = new Scanner(System.in);
        boolean myTurn = true;
        while (myTurn == true) {
            commandInput = scan.nextInt();

            if (commandInput == 5) {
                printDeck(playerDeck);
            } else if (commandInput < 5 && commandInput>0) {
                playCard(commandInput, player, playerDeck, table);
                myTurn = false;
            }
        }

        return true;
    }

    public static void playCard(int i, Player player, ArrayList<Integer> playerDeck, Table table){
        Integer cardPlayed = player.playCard(i);
        System.out.println("You are playing cardID number " + cardPlayed);
        playerDeck = player.getPlayerDeck();
        table.addCardToTable(cardPlayed);
        if (table.win()== true){
            player.setScore(table.calcTablePoints()) ;
            table.setTable();
            player.getScore();
        }
    }

    public static void giveCards (ArrayList playerDeck,Deck deck, Player player){
        player.setPlayerDeck(deck.deal(playerDeck));
    }

    public static void printDeck(ArrayList<Integer> deck){
        System.out.print("[");
        for(int i = 0; i< deck.size()-1;i++){
            if(deck.get(i) == 36){
                System.out.print("10\u2666,");
            }else if(deck.get(i) == 41){
                System.out.print("2\u2663,");
            }else {
                switch (deck.get(i) % 13) {
                    case 0:
                        System.out.print("K,");
                        break;
                    case 1:
                        System.out.print("A,");
                        break;

                    case 11:
                        System.out.print("J,");
                        break;

                    case 12:
                        System.out.print("Q,");
                        break;


                    default:
                        System.out.print(deck.get(i) % 13 + ",");
                        break;

                }
            }
        }

        if(deck.size() != 0) {
            if((deck.get(deck.size()-1)) == 36){
                System.out.print("10\u2666");
            }else if((deck.get(deck.size()-1)) == 41){
                System.out.print("2\u2663");
            }else{
                switch (deck.get(deck.size() - 1) % 13) {
                    case 0:
                        System.out.print("K");
                        break;
                    case 1:
                        System.out.print("A");
                        break;

                    case 11:
                        System.out.print("J");
                        break;

                    case 12:
                        System.out.print("Q");
                        break;


                    default:
                        System.out.print(deck.get(deck.size() - 1) % 13);
                        break;
                }
            }
        }
        System.out.print("]");
    }

    public static void printGameInfo(Deck deck, Player player, ArrayList playerDeck, Computer computer, ArrayList compDeck, Table table, ArrayList tableDeck){
        System.out.print(
                "\n************************************************************************************\n" +
                        "Deck - #Cards: " +  deck.numberOfCards() +" Cards in Deck:");
        printDeck(deck.returnDeck());
        System.out.print("\nPlayer - #Cards: " + player.getSize() +" Cards: ");
        printDeck(playerDeck);
        System.out.print(" Score: "+player.getScore());
        System.out.print("\nComputer - #Cards: " + computer.size() +" Cards: ");
        printDeck(compDeck);
        System.out.print(" Score: "+computer.getScore());
        System.out.print("\nTable - #Cards: " + tableDeck.size() +" Cards: ");
        printDeck(tableDeck);
        System.out.print(" Score: "+table.calcTablePoints());
        System.out.println(
                "\n************************************************************************************\n");

        }

}
