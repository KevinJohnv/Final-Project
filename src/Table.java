import java.util.ArrayList;

/**
 * Created by iSeeU on 4/8/17.
 *
 * Check if played card is a win or next players turn
 */
public class Table {
    private ArrayList<Integer> tablePile;
    public Table(ArrayList<Integer> givenCards){
        tablePile = givenCards;
    }

    public void setTable(){
        tablePile.removeAll(tablePile);
    }

    public Integer getTablePoints(){
        int table = 0;
        if (size()<=2){
            if(tablePile.get(tablePile.size()-1)%13 == 11 && tablePile.get(tablePile.size()-2)%13 == 11){
                System.out.println("JACK PISHTI!");
                table = table+20;
            } else if (tablePile.get(tablePile.size()-1)%13  == tablePile.get(tablePile.size()-2)%13 ){
                System.out.println("PISHTI");
                table = table+10;
            }
        }


        int cardCount = 0;
        while (cardCount > size()){
                if(tablePile.get(cardCount) == 11||tablePile.get(cardCount) == 12||tablePile.get(cardCount) ==13){
                    table++;
                    cardCount++;
                } else if(tablePile.get(cardCount) == 36|| tablePile.get(cardCount) == 41){
                    table++;
                    cardCount++;
                }

        }

        return table;
    }

    public boolean win(){
        if (tablePile.get(tablePile.size()-1)%13 == tablePile.get(tablePile.size()-2)%13 || tablePile.get(tablePile.size()-1)%13 == 11) {
            return true;
        } else {return false;}
    }



    public int size(){
        return tablePile.size();
    }

    public void addCardToTable(Integer newCard){
        tablePile.add(newCard);
    }



}
