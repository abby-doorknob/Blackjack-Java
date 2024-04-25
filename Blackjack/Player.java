import java.util.*;

public class Player{
	

	private int total, tracker, totalProfit;
	private boolean outcome;
	
	protected int[] nums = new int[10];
	protected int turnNum, hand, inc, betAmnt, balance;
	private String hit = "hit", fold = "fold", readInput = "", bal = "balance", bet = "bet";
	
	Clear clear = new Clear();
	
	public Player(int deposit, int betAmnt){
		this.balance = deposit;
		this.betAmnt = betAmnt;
	}
	
	public int game(int balance){
		
		Random rand = new Random();
		Scanner console = new Scanner(System.in);
		
		do{
			System.out.println("Hit or fold? Or, you can check your balance or your bet");
			readInput = console.next();
			clear.clearScreen();
			
			if(!readInput.equalsIgnoreCase(hit) & !readInput.equalsIgnoreCase(fold) & 
								!readInput.equalsIgnoreCase(bet) & !readInput.equalsIgnoreCase(bal)){
				System.out.println("Please enter hit, fold, bet or balance\n");
				continue; //if user enters unwanted input
			}
			if(readInput.equalsIgnoreCase(bal)){ //if user asks for their balance
				System.out.println("Your balance is $" + balance + "");
				System.out.println("Your hand = " + hand + "\n");
				continue;
			}
			if(readInput.equalsIgnoreCase(bet)){ //if user asks for their bet
				System.out.println("Your bet is $" + betAmnt + "");
				System.out.println("Your hand = " + hand + "\n");
				continue;
			}
			
			if(readInput.equalsIgnoreCase(hit)){
				inc = rand.nextInt(10) + 1; //random num gen generates num from 1-11 
				hand += inc; //num is added to your hand
				
				System.out.println("Your hand = " + hand);
				System.out.println();
				nums[turnNum] = hand; //hand is stored in an array so
				turnNum++;				//number of hits can be kept track of
			}else{
				break;
			}
			
		}while(!readInput.equalsIgnoreCase(fold) & hand < 21);
		
		return hand;
	}
	
	
	
	public void betSystem(boolean outcome){
		if(outcome){
			System.out.println("\nCongrats, you won!");
			totalProfit += betAmnt;
		}else{
			System.out.println("\nOh no! You lose!");
			totalProfit -= betAmnt;
		}
	}
	
	public void giveBalance(int balance){
		this.balance = balance;
	}
	
	public int[] getNums(){
		return nums;
	}
	
	public int getBalance(){
		return balance;
	}
	
	public int getBet(){
		return betAmnt;
	}
	
	
}


/*ideas:
 * 
 * 
 * problems:
 * 
 * 
 * */
