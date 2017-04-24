import java.util.ArrayList;

public class Computer extends Player {
    private ArrayList<Integer> compDeck;

    public Computer (ArrayList<Integer> givenDeck) {
        compDeck = givenDeck;
    }

    public Integer size() {
        return compDeck.size();
    }

    public ArrayList<Integer> getCompDeck() {
        return compDeck;
    }

    @Override
    public ArrayList<Integer> getPlayerDeck() {
        return getCompDeck();
    }
}