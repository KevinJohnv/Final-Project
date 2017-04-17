import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by iSeeU on 4/6/17.
 */
public class Deck {
    private ArrayList<Integer> myDeck = new ArrayList<Integer>();

    public Deck(){
        for(int i=0; i<52; i++) myDeck.add(1+i);
        System.out.println(myDeck.size());
    }

    public void shuffle(){
        Collections.shuffle(this.myDeck);
    }

    public ArrayList<Integer> deal(ArrayList<Integer> deckName){
        for(int i=0;i<4;i++){
            deckName.add( i,myDeck.get(0));
            myDeck.remove(0);
            myDeck.trimToSize();
        }

        return deckName;
    }

    public ArrayList<Integer> returnDeck(){
        return myDeck;
    }

    public Integer numberOfCards(){
        return myDeck.size();
    }

}
