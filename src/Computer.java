import java.util.ArrayList;

public class Computer extends Player {

    public Computer (ArrayList<Integer> givenDeck) {
        this.setPlayerDeck(givenDeck);
    }

    public Integer size() {
        return this.getPlayerDeck().size();
    }

    public ArrayList<Integer> getCompDeck() {
        return super.getPlayerDeck();
    }

    @Override
    public ArrayList<Integer> getPlayerDeck() {
        return super.getPlayerDeck();
    }
}