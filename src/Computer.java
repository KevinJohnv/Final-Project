import java.lang.reflect.Array;
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

    public ArrayList<Integer> getCompDeck() {
        return compDeck;
    }

    @Override
    public ArrayList<Integer> getPlayerDeck() {
        return getCompDeck();
    }



    public void computerplay(ArrayList<Integer> table)
    {
        boolean hasmatch=false;
        //for(int x = 0;x<compDeck.size();x++) {
           // if (compDeck.get(x)%13 == table.get(table.size())%13) {
             //   Main.playCard(x,Main.computer, Main.computer.compDeck, Main.table);
               // hasmatch = true;
                //break;
          //}

        //}
        //if(!hasmatch)
        {
            Main.playCard(0, Main.computer, Main.computer.compDeck, Main.table);
        }
    }
}
// yes this is a test 