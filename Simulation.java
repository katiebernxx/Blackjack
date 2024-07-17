import java.util.ArrayList;

public class Simulation{
    ArrayList<Integer> record;

    public Simulation() {
        record = new ArrayList<Integer>();
    }
    public void compile(){
        Blackjack b = new Blackjack();
        for (int i = 0; i<1001; i++){
            record.add(b.game(false));
        }
        int playerWins =0;
        int dealerWins =0;
        int pushes = 0;
        for(int i = 0; i<1000; i++){
            if(record.get(i)==-1){
                dealerWins = dealerWins +1;
            }
            else if(record.get(i)==1){
                playerWins = playerWins +1;
            }
            else if(record.get(i)==0){
                pushes = pushes +1;
            }
        }
  
        System.out.println("Player Wins: " + playerWins + ", " + playerWins/10 + "%");
        System.out.println("Dealer Wins: " + dealerWins + ", " + dealerWins/10 + "%");
        System.out.println("Pushes: " + pushes + ", " + pushes/10 + "%");

    }
    public static void main (String[] args){
        Simulation s = new Simulation();
        s.compile();
    }
}