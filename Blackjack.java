/* Katie Bernard
9/20/22
*/
import java.util.ArrayList;


public class Blackjack{
    private Deck deck =new Deck();
    private Hand dealerHand = new Hand();
    private Hand playerHand = new Hand();
    static int reshuffleCutOff;

    /* Constructor method that accepts and stores a reshuffle cutoff value
     * Calls reset to set up a game
     */
    public Blackjack(int cutoff){
        reshuffleCutOff = cutoff;
        deck.shuffle();
        reset();
    }

    /* Second constructor method that calls the first constructor method with a particular cutoff value */
    public Blackjack(){
        this(26);
    }

    /*Clears the dealer and player hands. If the deck has fewer cards than the cutoff, it creates a new shuffled deck. */
    public void reset(){
        dealerHand.reset();
        playerHand.reset();
        if (deck.size() < reshuffleCutOff){
            deck.build();
            deck.shuffle();
        }
    }
    /*deals two cards to the player and two cards to the dealer from the top of the deck */
    public void deal(){
        for (int i = 0; i<2; i++){
            playerHand.add(deck.deal());
            dealerHand.add(deck.deal());
            
        }
    }
    /* Player draws cards until reaches a total value of 16 or above. Returns false if player busts and true if not */
    public boolean playerTurn(){
        int total = playerHand.getTotalValue();
        Card highAce = new Card(11);
        Card lowAce = new Card(1);
        while (total<16){ //Player drawing cards until 16+
            playerHand.add(deck.deal());
            total = playerHand.getTotalValue();
        }
        if (total>21){ //Switch high ace for low ace
            if (playerHand.isIn(highAce)){
                playerHand.remove(highAce);
                playerHand.add(lowAce);
            }
            while (total<16){ //Turn again
                playerHand.add(deck.deal());
                total = playerHand.getTotalValue();
            }
        }
        if (total>21){ //If busts
            return false;
        }else{
            return true;
        }
    }
    /* Dealer draws cards until reaches a total value of 17 or above. Returns false if dealer busts and true otherwise. */
    public boolean dealerTurn(){
        int total = dealerHand.getTotalValue();
        while (total<17){ //Dealer drawing cards until 17+
            dealerHand.add(deck.deal());
            total = dealerHand.getTotalValue();
        }
        if (total>21){ //If busts
            return false;
        }else{
            return true;
        }
    }

    /*Sets a new reshuffle cutoff */
    public void setReshuffleCutoff(int cutoff){
        reshuffleCutOff = cutoff;
    }
    /*returns the current value of the reshuffle cutoff */
    public int getReshuffleCutoff(){
        return reshuffleCutOff;
    }

    /*returns a string representing the status of the game */
    public String toString(){
        String playerStatus = ( "Player Hand total value: " + playerHand.getTotalValue() + ", made up of cards: " + playerHand + ".");
        String dealerStatus = ( " Dealer Hand total value: " + dealerHand.getTotalValue() + ", made up of cards: " + dealerHand + ".");

        return (playerStatus + dealerStatus);
    }
 

    public static void main (String[] args){
        Blackjack b = new Blackjack();
        b.game(true);
    }

    public int game(boolean verbose){
        /* Runs a single game of blackjack. If verbose, it prints the details of the initial and final hands as well as who won. 
         * Returns an int representation of who won
         */
        reset();
        Blackjack b = new Blackjack();
        boolean playerbust;
        boolean dealerbust;
        int loss = -1;
        int win = 1;
        int tie = 0;
        int results;
        b.deal(); //deal two cards to player and dealer
        Hand initialPlayer = b.playerHand; //Their hands after deal
        Hand initialDealer = b.dealerHand;
        playerbust = b.playerTurn(); //Player and dealers' turns - expand their hands
        dealerbust = b.dealerTurn();

        //if, else determines who was the winner
        if (playerbust){
            results = loss;
        }
        else if (dealerbust){
            results = win;
        }
        else if (playerHand.getTotalValue() > dealerHand.getTotalValue()){
            results = win;
        }
        else if (playerHand.getTotalValue() == dealerHand.getTotalValue()){
            results = tie;
        }
        else{
            results = loss;
        }
    
        if (verbose){ //Prints the details
            
            System.out.println("Initial player hand:"+initialPlayer+ ". Final player hand:"+b.playerHand);
            System.out.println("Initial dealer hand:"+initialDealer+ ". Final dealer hand:"+b.dealerHand);
            if (results == -1){
                System.out.println("The player lost.");
            }
            else if (results == 0){
                System.out.println("The player and dealer tied");
            }
            else{ 
                System.out.println("The player won!");
            }
        }
        return results; //Returns -1, 0, or 1 based on who wins

    }
}