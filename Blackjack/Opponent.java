/*The opponent will already have "played" by the time the player inputs
 * fold or goes past 21. 
 * */

import java.util.*;

public class Opponent extends Player{
	
	public Opponent(){
		super(1000, 0);
	}
	
	
	public int game(){
		Random rand = new Random();
		
		do{
			inc = rand.nextInt(10) + 1;
			hand += inc;
			nums[turnNum] = hand; //same as the system in player class
			turnNum++;
			
			//this makes it so the opponent can take a chance when it
			//already has a high hand
			if(hand >= 18 & inc <= 2){
				continue;
			}else if(hand  >= 18){
				break;
			}
		}while(hand != 21 & hand < 21); //leaves loop when hand >=21
		
		return hand;
	}
	
	public int bet(){
		if(balance >= 500){
			this.betAmnt = 50; //bets 50 if bal is greater than 500
			return 50;
		}else{
			this.betAmnt = 25; //bets 25 is bal is lower than 500
			return 25;
		}
		
	}
	
	public void giveWinnings(int profit){ //adds the winnings to opp's balance
		this.balance += profit;
	}
	
}


/*problems:
 * 
 * */
