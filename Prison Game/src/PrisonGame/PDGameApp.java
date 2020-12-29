///**
// *
// * CSCI 470 assignment 3
// *
// * Author: Andy Olivares, Craig Ullrich
// * z-ID: z1860934, z1721852
// * Date: 9/22/2020
// *
// * Main Class(PDGameApp)
// */
//
//package PrisonGame;
//import java.io.FileNotFoundException;
//import java.util.*; 
//
//public class PDGameApp {
//	
//
//	/***************************************************************
//  Calls validate_input
//  Use: checks if input is valid
//  Parameters: 1. int max: input
//  Returns: input (int)
//***************************************************************/
//	public static int validate_input(int max) {
//		Scanner sc = new Scanner(System.in);
//		int input = 0;
//		
//		while(input == 0 || input > max) {
//			try {
//				input = sc.nextInt();
//			}
//			catch(Exception e){
//				sc.skip(".");
//			}
//			if(input <= 0 || input > max)
//				System.out.println("\n***INVALID INPUT PLEASE TRY AGAIN***");
//		}
//		return input;
//	}
//	
//	public static void main(String[] args) throws FileNotFoundException {
//		 Scanner sc=new Scanner(System.in);  
//		 int AI_select = 0;
//		 int user_input = 0;
//		 int round = 0;
//		 int play = 0;
//		 
//		 List<GameStat> stats = new ArrayList<GameStat>();
//		 
//		 //for loop for rounds
//		 while(play != 2)
//		 {
//			System.out.println("\n***Starting A Session of Prisoner's Dilemma ***");
//		    System.out.println("Round: " + (round + 1)  + "/5");
//		    System.out.println("\nSelect a computer AI");
//		    System.out.println("1. Computer Reads Strategy From Input File");
//		    System.out.println("2. Tit-For-Tat");
//		    System.out.println("3. Tit-For-Two-Tats");
//		    System.out.println("4. Random Choice by Computer");
//
//		    AI_select = validate_input(4);
//		    System.out.println("You have chosen AI: " + AI_select);
//		    
//		    PDGame game = new PDGame(AI_select);
//
//		    while(round < 5){
//		    	System.out.println("\nRound: " + (round + 1));
//		    	System.out.println("1. Remain silent.");
//		    	System.out.println("2. Betray and testify against.");
//		    	
//		    	user_input =  validate_input(2);
//		    	
//		    	game.get_user_input(user_input, round);
//		    	System.out.println(game.play_round(round));
//		    	round ++;
//		    }
//		    
//		    if(round == 5)
//		    {
//		    	System.out.println("\n\nGame Over");
//		    	System.out.println("Your prison sentence is: " + game.getUser_years());
//		    	System.out.println("Your partners sentence is: " + game.getAI_years());
//		    }
//		    stats.add(game.generate_stats());
//		    System.out.println("\n\nWould You like to play again?");
//		    System.out.println("1. Yes");
//		    System.out.println("2. No");
//		    play =  sc.nextInt();
//		    round = 0;
//		    
//
//				//prints summary
//		    if(play == 2) {
//		    	 System.out.println("\n\nSummary of games and session times:");
//				 stats.forEach((s)->System.out.println(s.get_date() + "\n" + "Winner: " + s.get_winner() + "\nAI_Type: " + s.get_AI() + "\n\n"));
//		    }
//		 }
//		
//	}
//}
