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

    public void computerplay(ArrayList<Integer> table)
    {
        boolean hasmatch=false;
        for(int x = 0;x<compDeck.size();x++) {
            if (compDeck.get(x)%13 == table.get(table.size()-1)%13) {
                playCard(compDeck.get(x));
                hasmatch = true;
                break;
            }

        }
        if(hasmatch==false)
        {
            playCard(compDeck.get(compDeck.get(compDeck.size()-1)));
        }
    }
}
// yes this is a test 