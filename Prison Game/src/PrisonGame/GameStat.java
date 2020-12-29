/**
 *
 * CSCI 470 assignment 3
 *
 * Author: Andy Olivares, Craig Ullrich
 * z-ID: z1860934, z1721852
 * Date: 9/22/2020
 *
 * GameStat Class
 */


package PrisonGame;
import java.time.LocalDateTime;

public class GameStat {
	
	private LocalDateTime date;
	private String winner;
	private String AI;
	private String comp_years;
	private String player_years;

	//constructor for Gamestat
	public GameStat( String winner, String AI, int user_years, int AI_years) {
		this.winner = winner;
		this.AI = AI;
		player_years = Integer.toString(user_years);
		comp_years = Integer.toString(AI_years);
		date = LocalDateTime.now();
	}
	
	
	/***************************************************************
	  Calls generate_stats
	  Use: gets the winner of the game
	  Parameters: 1. String winner: winner score
	  			  2. String AI:		ai score
	  Returns: nothing
	***************************************************************/
	public void update_stats( String winner, String AI) {
		this.winner = winner;
		this.AI = AI;
		date = LocalDateTime.now();
	}
	
	
	
	//getters
	public String get_date() {
		return date.toString();
	}
	
	public String get_winner() {
		return winner;
	}
	
	public String get_AI() {
		return AI;
	}
	
	public String toString() {
		return date.toString();
	}
	
	public String get_comp_years() {
		return comp_years;
	}
	
	public String get_player_years() {
		return player_years;
	}
	
}
