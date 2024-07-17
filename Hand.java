/* Katie Bernard
9/20/22
*/
import java.util.ArrayList;

public class Hand {

    private ArrayList<Card> hand; 
    /**
     * Creates an empty hand as an ArrayList of Cards.  
     */
    public Hand(){
        hand = new ArrayList<Card>();

    }

    /**
     * Removes any cards currently in the hand. 
     */
    
    public void reset(){
        int size = hand.size();
            for (int i=size-1; i>=0; i--){
                hand.remove(i);
            }
        
    }

    public boolean isIn(Card card){
        if (hand.contains(card)){
            return true;
        }
        else{
            return false;
        }
    }


    /**
     * Adds the specified card to the hand.
     * @param card the card to be added to the hand
     */
    public void add(Card card){
        hand.add(card);
    }

    public void remove(Card card){
        hand.remove(card);
    }

    /**
     * Returns the number of cards in the hand.
     * @return the number of cards in the hand
     */
    public int size(){
        int size = hand.size();
        return size;
    }

    /**
     * Returns the card in the hand specified by the given index. 
     * @param index the index of the card in the hand.
     * @return the card in the hand at the specified index.
     */
    public Card getCard(int index){
        Card card = hand.get(index);
        return card;
    }

    /**
     * Returns the summed value over all cards in the hand.
     * @return the summed value over all cards in the hand
     */
    public int getTotalValue(){
        int counter = 0;
        int size = hand.size();
        for (int i=size-1; i>=0; i--){
            Card card = hand.get(i);
            int val = card.getValue();
            counter = counter + val;
        }
        return counter;
    }
    
    public int getIndex(Card card){
        return hand.indexOf(card);
    }
    

    /**
     * Returns a string representation of the hand.
     * @return a string representation of the hand
     */
    public String toString(){
        return "" + hand;
        }

   
}