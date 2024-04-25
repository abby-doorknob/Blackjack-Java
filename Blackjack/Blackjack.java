/*@author Abby Doinog
 * 
 * This code creates a game of Blackjack
 * 
 * 12, 123 gives 21
 * */

import java.util.*;
import java.io.*;

public class Blackjack{
		
	public static void main(String[]args) throws FileNotFoundException{
		
		int result = 0, bet = 0, userNumCount = 0, oppNumCount = 0, balance = 0;
		int totalProfit = 0;
		
		boolean playAgain = true;
		int gameCount = 0;
		
		
		Clear clear = new Clear();
		Scanner console = new Scanner(System.in);
		File save = new File("savegame.txt");
		Scanner scan = new Scanner(save);
		
		//code for the game starts
		
		System.out.println("Welcome to Blackjack created by Abby Doinog!\n");
		
		tutorial(console, clear);
		
		if(askSave(console, clear)){//new save
			PrintStream out = new PrintStream("savegame.txt");
			out.println("0");//resets text to 0 and 0 means no save
			
		}else{//continue save
			int temp = Integer.parseInt(scan.next());//converts string within file to int
			if(temp != 0){
				gameCount++; //makes it so user isn't asked for a deposit
				balance = temp;
			}else{//for when the user inputs continue, but there isn't a save game to continue
				System.out.println("\nPrevious save file does not exist");
				System.out.println("You'll have to enter a new deposit");
				gameCount = 0;
				clear.sleep(3); 
			}
		}
		
		clear.clearScreen();
		
		while(playAgain){
			
			balance = deposit(gameCount, console, balance); //gets balance from user
			System.out.println();
			bet = setBet(gameCount, console, balance); //gets bet from user
			
			Player user = new Player(balance, bet);// creates player
			Opponent opp = new Opponent();// creates opponent
			
			clear.clearScreen();
			
			result = user.game(balance);// runs the game for the player
			int opResult = opp.game(); //runs the game for opponent
			
			clear.clearScreen();
			
			System.out.println("Your hand = " + result);
			System.out.println("Opponent hand =  " + opResult);
						
			//-------------------win conditions--------------------
			//can't just use num.length to get the length since i automatically
			//set the length of the array to 10. Counting the amount of
			//numbers within the array returns the acutal length (aka the amount of hits)
			
			for(int n : user.getNums()){ //counts length of user array, aka how many times hit was called
				if(n != 0){
					userNumCount++;
				}
			}
			
			for(int n : opp.getNums()){ //same as above but for opponent
				if(n != 0){
					oppNumCount++;
				}
			}
			//helps with determining wins when both opp and user hands are 21, etc
			//---------------------------------------------------
			
			//------------prints out winnings-----------------------
			int temp = winConditions(user, result, opResult, userNumCount, oppNumCount); //determines who wins
			System.out.println("\nYou bet $" + user.getBet());
			System.out.println("Your opponent bet $" + opp.bet());
			
			int bothBets = user.getBet() + opp.bet(); //total winnings for the round
			
			//deals with the profit/loss for the round
			if(temp == 1){ //win
				System.out.println("In total, you won $" + bothBets);
				balance += user.getBet() + opp.bet();
				totalProfit += bothBets;
			} else if(temp == -1){ //lose
				System.out.println("In total, you lost $" + user.getBet());
				balance -= user.getBet();
				totalProfit -= user.getBet();
				opp.giveWinnings(user.getBet() + opp.bet());
			}
			System.out.println("\nYou have $" + balance + " in total!");
			//-------------------------------------------------------
			
			System.out.println();
			
			playAgain = askPlayAgain(console); //asks if you want to play again
			
			if(playAgain){	//used this to keep track of whether it's the first round
				gameCount++; //so it isn't repeatingly asking you for a deposit
			}
			
			clear.clearScreen();
		}
		
		clear.clearScreen();
		
		makeSave(console, save, balance, totalProfit, clear); //saves the game
		
		clear.clearScreen();
		
		if(totalProfit < 0){ //prints out total profit/total loss
			System.out.println("Your balance is $" + balance);
			System.out.println("You have lost $"+ Math.abs(totalProfit) + "!");
		}else {
			System.out.println("Your balance is $" + balance);
			System.out.println("You made a total of $" + totalProfit + "!\n");
		}
		
		System.out.println("Hope to see you soon!");
	}
	
	public static int deposit(int gameCount, Scanner console, int balance){
		boolean depositTest = true;
		
		do{
			try{
				if(gameCount == 0) {
					System.out.print("What is your initial deposit? $");
					balance = console.nextInt();
					
					if(balance > 0){ //if balance checks out, leave loop
						depositTest = false;
						break;
					}
					
					System.out.println("Enter a positive value please\n"); 
					//loops if balance was negative
				}else{
					break;
				}
				
			}catch(InputMismatchException ime){ //prevents mismatch exception
				System.out.println("Please enter a positive integer value");
				console.next();
				System.out.println();
			}
		}while(depositTest); //leaves loop if deposit is a positive integer
		
		return balance;
	}
	
	public static int setBet(int gameCount, Scanner console, int balance){
		int bet = 0;
		boolean betTest = true;
		do{
			try{
				if(gameCount != 0){
					//prints out balance before asking for your bet after round 1
					//makes it look nicer
					System.out.println("Your balance is $" + balance);
				}
				System.out.print("How much do you want to bet? $");
				bet = console.nextInt();
				System.out.println();
				
				if(bet > 0 && bet <= balance){//leaves loop if bet checks out
					betTest = false;
					break;
				}else{
					System.out.println("Enter a positive value that is less than or equal to your deposit please\n");
				}
				
			}catch(InputMismatchException ime){ //if user enters a word
				System.out.println("Please enter a positive value that is less than your deposit");
				betTest = true;
				console.next();
				System.out.println();
			}
		}while(betTest);//leaves loop if bet is a positive integer <= the deposit
		
		return bet;
	}
	
	public static void makeSave(Scanner console, File save, int balance, int totalProfit, Clear clear){
		String yes = "yes";
		String no = "no";
		String readInput = "";
		
		System.out.println("Do you want to save your game?(enter yes or no)");
		readInput = console.next();

		while(!readInput.equalsIgnoreCase(yes) & !readInput.equalsIgnoreCase(no)){ 
			System.out.println("Please enter yes or no"); //if user doesn't enter the required input
			readInput = console.next();
		}
		
		if(readInput.equalsIgnoreCase(yes)){ //saves the game by printing out the balance to the file
			try{
				PrintStream out = new PrintStream("savegame.txt");
				out.println(balance);
			}catch(FileNotFoundException fnfe){
				System.out.println("Save file does not exist");
			}
		}else{ //doesn't save the game by printing out 0, 0 means new save(deposit can't be 0)
			try{
				PrintStream out = new PrintStream("savegame.txt");
				out.println("0");
			}catch(FileNotFoundException fnfe){
				System.out.println("Save file does not exist");
			}
		}
	}
	
	public static boolean askSave(Scanner console, Clear clear){
		String readInput = " ";
		String con = "con";
		String newSave = "new";
		int savedBal = 0;
		Scanner scan = new Scanner("savegame.txt");
		
		System.out.println("Do you want to continue or start a new save? (input con or new)");
		readInput = console.next();
		
		while(!readInput.equalsIgnoreCase(con) & !readInput.equalsIgnoreCase(newSave)){
			System.out.println("Please enter continue or new save"); 
			readInput = console.next();//prevents user from entering unwanted input
		}
		
		if(readInput.equalsIgnoreCase(newSave)){ //creates new save
			return true;
			
		}else{ //continues game
			return false;
		}
	}
	
	public static void tutorial(Scanner console, Clear clear){
		String yes = "yes";
		String no = "no";
		String readInput = " ";
		
		System.out.println("Would you like to read the tutorial?(yes or no)");
		readInput = console.next();
		
		while(!readInput.equalsIgnoreCase(yes) & !readInput.equalsIgnoreCase(no)){
			System.out.println("Please enter yes or no");
			readInput = console.next();//prevents user from entering unwanted input
		}
		
		if(readInput.equalsIgnoreCase(yes)){
			
			clear.clearScreen();
			
			System.out.println("In this game, you will be playing against an opponent.");
			System.out.println("To win, you want your hand to be closest to 21.");
			System.out.println();
			System.out.println("Before the game starts, you'll be asked whether you want to continue or start a new save.");
			System.out.println("If you choose to continue, you and your opponent will start with");
			System.out.println("the same balance as you last left off.");
			System.out.println("Otherwise, you will be asked to deposit however much you want.");
			System.out.println("Either way, you will still be asked to bet.");
			System.out.println();
			System.out.println("You will have the choice to input \"hit\", \"fold\", \"balance\", or \"bet\".");
			System.out.println("\"hit\" will cause the program to deal you another card.");
			System.out.println("\"fold\" will stop the program from dealing you cards,");
			System.out.println(" and your hand will be compared to your opponent's.");
			System.out.println("\"bet\" or \"balance\" will remind you of how much you've");
			System.out.println("betted and your balance.\n");
			System.out.println("Enter anything when you're ready to continue");
			
			readInput = console.next();
			}
		
		clear.clearScreen();
	}
	
	public static boolean askPlayAgain(Scanner console){
		String yesOrNo = " ";
		String yes = "yes";
		String no = "no";
		
		System.out.println("Play again? (Yes or no)");
			yesOrNo = console.next();
			
		while(!yesOrNo.equalsIgnoreCase(yes) & !yesOrNo.equalsIgnoreCase(no)){
			System.out.println("Please enter yes or no");
			yesOrNo = console.next(); //prevents user from entering unwanted input
		}
		if(yesOrNo.equalsIgnoreCase(yes)){ //if yes, play again
			return true;
		}else if(yesOrNo.equalsIgnoreCase(no)){
			return false;
		}
		return false;
	}
	
	public static int winConditions(Player user, int result, int opResult, int userNumCount, int oppNumCount){
		//returns int to tell the main code whether user won(1), lost(-1), or tied(0)
		
		if(result == 0){ //for when user instantly folds
			System.out.println("Instant fold!");
			user.betSystem(false);
			return -1;
		}
		
		//if user and opp both got 21
		if(result == 21 & opResult == 21){ 
			if(userNumCount > oppNumCount){ //if user gets it in less turns
				System.out.println("You and your opponent both got 21! However, you got it first!");
				user.betSystem(true);
				return 1;
				
			}else if (userNumCount < oppNumCount){ //if opp gets it in less turns

				System.out.println("You and your opponent both got 21! However, your opponent got it first!");
				user.betSystem(false);
				return -1;
				
			}else{ //if theres a tie

				System.out.println("Wow, it's a tie! You and your opponent both got 21 in the same amount of turns!");
				System.out.println("Since it's a tie, no one wins or loses money.");
				return 0;
			}
		}
		//deals with 21s
		if(result == 21){ //if user got 21, user wins

			System.out.println("Woah! 21 on the dot!");
			user.betSystem(true);
			return 1;
		}
		if(opResult == 21){ //if opp got 21, opp wins

			System.out.println("Opponent got 21!");
			user.betSystem(false);
			return -1;
		}
		
		//deals with busts
		if (opResult > 21 & result > 21){
			if(userNumCount == oppNumCount){
				System.out.println("Both players bust in the same amount of turns!");
				System.out.println("Therefore, it's a tie. No one loses or gains money!");
				return 0;
			}
			if(userNumCount > oppNumCount){
				System.out.println("The player got a bust before the opponent");
				System.out.println("Therefore, you lose!");
				return -1;
			}
			if(userNumCount < oppNumCount){
				System.out.println("The opponent got a bust before the player");
				System.out.println("Therefore, you win!");
				return 1;
			}
		}
		
		if (opResult > 21){ //if opponent bust in less moves than user, user wins
			System.out.println("Opponent bust!");
			user.betSystem(true);
			return 1;
			
		}
		if (result > 21) { //if user bust in less moves than opp, opp wins
			System.out.println("Player bust!");
			user.betSystem(false);
			return -1;
		}
		
		
		//deals with hands that're less than 21
		if(result < 21 & opResult < 21){
			if (result == opResult){
				System.out.println("You got the same hand as the opponent!");
				System.out.println("Therefore, it's a tie");
				return 0;
			}else if(result > opResult){
				System.out.println("You're closer to 21 than your opponent!");
				user.betSystem(true);
				return 1;
			}else{
				System.out.println("Your opponent is closer to 21 than you!");
				user.betSystem(false);
				return -1;
			}
		}
		
		return -2;
	}
	
}
