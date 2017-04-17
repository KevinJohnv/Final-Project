import java.util.ArrayList;

/**
 * Created by iSeeU on 4/8/17.
 * Needed Methods
 *  - Setters
 *    # setNumOfCards
 *    # setScore
 *    # setPlayerDeck
 *
 *  - Getters
 *    # getSize
 *    # getPlayerDeck
 *    * getPoints
 *    * getNumOfCards
 *
 */
public class Player {
    private ArrayList<Integer> playerDeck;
    private int count = 0;
    private int score = 0;

    public Player(){

    }

    public Player(ArrayList<Integer> playerDeck){
        setPlayerDeck(playerDeck);
    }

    // Setters *****************************************************************************************************
    public void setPlayerDeck(ArrayList<Integer> givenDeck){
        playerDeck = givenDeck;
    }

    public void setNumOfCardsInPile(Integer numOfCards){
        // This counts the amount of cards the player has won
        count = count + numOfCards;
    }



    public void setScore(Integer score){
        this.score = this.score+score;
    }

   // Getters *****************************************************************************************************
    public int getScore(){
        return score;
    }

    public int getNumofCards(){
        return count;
    }

    public Integer getSize(){
        return playerDeck.size();
    }

    public ArrayList<Integer> getPlayerDeck(){
        return playerDeck;
    }

    // Special Methods *****************************************************************************************************
    public Integer playCard(int selectedCard){
            int cardNumber = playerDeck.get(selectedCard - 1);
            playerDeck.remove(selectedCard-1);
            return cardNumber;
    }


}
