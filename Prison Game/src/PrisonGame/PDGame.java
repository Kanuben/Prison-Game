/**
 *
 * CSCI 470 assignment 3
 *
 * Author: Andy Olivares, Craig Ullrich
 * z-ID: z1860934, z1721852
 * Date: 9/22/2020
 *
 * PDGame Class
 */

package PrisonGame;
import java.io.File;  // Import the File class
import java.io.FileNotFoundException;  // Import this class to handle errors
import java.util.Scanner; // Import the Scanner class to read text files
import java.io.*;
import java.util.Random; 
import java.util.*; 



//PDGame class
class PDGame{

  private int user_input[] = {0,0,0,0,0};
  private int AI_input[] = {0,0,0,0,0};
  private int selected_AI;
  private int user_years;
  private int AI_years;
 
  
/***************************************************************
  Calls generate_stats
  Use: gets the winner of the game
  Parameters: none
  Returns: nothing
***************************************************************/
public GameStat generate_stats(){
	String winner = null;
	String AI = null;
	
	if(user_years > AI_years) winner = "AI won";
	if(user_years < AI_years) winner = "User won";
	if(user_years == AI_years) winner = "Tie";
	
	if(selected_AI == 1) AI = "From File";
	if(selected_AI == 2) AI = "Tit-For-Tat";
	if(selected_AI == 3) AI = "Tit-For-Two-Tats";
	if(selected_AI == 4) AI = "Random";
	
	//passes winner into the gamestat list
	GameStat stat = (new GameStat(winner, AI, user_years, AI_years));
	return stat;
}
  
//getters
public int[] getUser_input() {
	return user_input;
}

public int getUser_years() {
	return user_years;
}

public int getAI_years() {
	return AI_years;
}


/***************************************************************
Calls PDGame
Use: reads input file and determines years
Parameters: 1.int selected_aiL: number for ai to be set.
Returns: result.
***************************************************************/
public PDGame() {
}

public PDGame(int selected_ai) throws FileNotFoundException{
    if(selected_ai == 1){
    	File myObj = new File("C:\\Users\\Kanuben\\Desktop\\CSCI\\470 - Java\\Assignment 3\\Prison Game\\src\\PrisonGame\\ai.txt");
        Scanner myReader = new Scanner(myObj);
        while (myReader.hasNextLine()) {
        	AI_input[0] = myReader.nextInt();
        	AI_input[1] = myReader.nextInt();
        	AI_input[2] = myReader.nextInt();
        	AI_input[3] = myReader.nextInt();
        	AI_input[4] = myReader.nextInt();
        }
        myReader.close();
    }
    
    if(selected_ai == 2)
    {
    	AI_input[0] = 1;
    }
    
    if(selected_ai == 3)
    {
    	AI_input[0] = 1;
    	AI_input[1] = 1;
    }
    
    if(selected_ai == 4) {
    	AI_input[0] = (int)(Math.random() * ((2 - 1) + 1)) + 1;
    	AI_input[1] = (int)(Math.random() * ((2 - 1) + 1)) + 1;
    	AI_input[2] = (int)(Math.random() * ((2 - 1) + 1)) + 1;
    	AI_input[3] = (int)(Math.random() * ((2 - 1) + 1)) + 1;
    	AI_input[4] = (int)(Math.random() * ((2 - 1) + 1)) + 1;
    }
    
    selected_AI = selected_ai;
    user_years = 0;
    AI_years = 0;
  }
  
  public void get_user_input(int input, int round) {
	  user_input[round] = input;
	  
	  if(round > 0 && selected_AI == 2) {
		  AI_input[round] = user_input[round-1];
	  }
	  
	  if(round > 1 && selected_AI == 3) {
		  if(user_input[round] == 2 && user_input[round-1] == 2) {
			  AI_input[round] = 2;
		  }
		  else {
			  AI_input[round] = 1;
		  }
	  }
  }
  
  
  /***************************************************************
  Calls play_round
  Use: gets the winner of the game
  Parameters: 1. int round: determines what happens to the player and ai.
  Returns: the result of the play
***************************************************************/
  public String play_round(int round) {
	  String result = "";
  	if(user_input[round] == 1 && AI_input[round] == 1) {
  		user_years +=2;
  		AI_years +=2;
  		result = "\n You and your partner remained silent and both get 2 years";
  	}
  	
  	if(user_input[round] == 2 && AI_input[round] == 1) {
  		user_years +=1;
  		AI_years +=5;
  		result = "\n You betrayed your partner while they remained silent. You get 1 year and they get 5 years";
  	}
  	
  	if(user_input[round] == 2 && AI_input[round] == 2) {
  		user_years +=3;
  		AI_years +=3;
  		result = "\n You and your partner betrayed each other and both get 3 years";
  	}
  	
  	if(user_input[round] == 1 && AI_input[round] == 2) {
  		user_years +=5;
  		AI_years +=1;
  		result = "\n You remained silent but your partner betrayed you. You get 5 years they get 1";
  	}
  	return result;
  }

  
}

