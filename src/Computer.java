import java.util.ArrayList;

/**
 * Created by iSeeU on 4/10/17.
 */
public class Computer extends Player {
    private ArrayList<Integer> compDeck;

    public Computer(ArrayList<Integer> givenDeck) {
        compDeck = givenDeck;
    }

    public Integer size(){
        return compDeck.size();
    }
}