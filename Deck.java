/* Katie Bernard
9/20/22
*/
import java.util.ArrayList;
import java.util.Random;

public class Deck {

    protected ArrayList<Card> deck;
    private ArrayList<Card> newdeck;
    /**
     * Creates the underlying deck as an ArrayList of Card objects. 
     * Calls build() as a subroutine to build the deck itself.
     */
    public Deck() {
        deck = new ArrayList<Card>();
        build();
    }

    /**
     * Builds the underlying deck as a standard 52 card deck. 
     * Replaces any current deck stored. 
     */
    public void build() {
        newdeck = new ArrayList<Card>(); //Creates a new deck to replace the regular deck with
        //ADDS 4 CARDS EACH OF VALUES 1 THROUGH 11
        for (int x=0; x<5; x++){
            for (int i=2; i<12; i++){
                Card newCard = new Card(i);
                newdeck.add(newCard);
            }
        //ADDS 12 CARDS OF VALUE 10
        for (int i = 0; i<13; i++){
            Card tenCard = new Card(10);
            newdeck.add(tenCard);
        }
        deck = newdeck; //replaces the deck with the newly built one
    }
    }

    /**
     * Returns the number of cards left in the deck. 
     * @return the number of cards left in the deck
     */
    public int size() {
        return deck.size();
    }

    /**
     * Returns and removes the first card of the deck.
     * @return the first card of the deck
     */
    public Card deal() {
        Card first = deck.get(0);
        deck.remove(0);
        return first;

    }

    
    /**
     * Shuffles the cards currently in the deck. 
     */
    public void shuffle() {
        Random rand = new Random();
        int size = deck.size();
        for (int i=0; i<size; i++){
            int num = rand.nextInt(size - 1); //Index of the randomly selected card

            Card selected = deck.get(num); //Value of the randomly selected card
            Card last = deck.get(size-1); //Value of the last card in the deck
            //Switching the places of the randomly selected card and the last card:
            deck.set(num, last);
            deck.set(size-1, selected);
        }
        
    }

    /**
     * Returns a string representation of the deck.
     * @return a string representation of the deck
     */
    public String toString() {
        return ("" + deck);
    }
}