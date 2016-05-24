package highScores;
import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Main class for running the app
 * 
 * @author erick.taru, tom.sommerville-roberts
 *
 */

public class TestHighScore4 {
	
	/**
	 * Main of the application
	 * @param args various arguments
	 */

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String playerName;

		boolean inGame = true;
		
		int server;
			try {
				System.out.println("Enter your channel number : ");
				server = sc.nextInt();
			}catch (InputMismatchException exception)
			    {
			        System.out.println("Integers only, please.");
			        server = 0;
			    }	
		
			/**
			 * the following is to get a random score
			 */
			
		String answer = "yes";
		do
		{
			HighScore4 h = new HighScore4();
			String[][] tokens = h.getScores(server);
			BestPlayer2[] top = h.tenBestScores(tokens, server);
			//ask for a new game	
			System.out.println("Do you want to play a new game ? (yes/no)");
			answer = sc.nextLine();
			String yes="yes";
			String no = "no";
			if (answer.equals("yes"))
			{
				System.out.println("What's your name ? ");
				playerName = sc.nextLine();
				         
				Random rand =  new Random();
				int randScore = 0;
				try
				{
					if (tokens[0].length > 0 ){
							randScore = rand.nextInt( ( tokens[0].length ) ) ;
					}
					else { System.out.println("Rand ne marche pas");}
				} catch (IllegalArgumentException  e)
				{
					e.printStackTrace();
					randScore = 1;
				}
				System.out.println(playerName + " has reached the score : " + tokens[1][randScore]);
				BestPlayer2 bestPlayer = new BestPlayer2(playerName, randScore);
				
				int inTopTen = 0;
				int i  = 0;
				while (inTopTen != 1 && i < 10) {
					if ( top.length < 10){
						inTopTen = 1;
						System.out.println("Welcome in top 10, but you know, they weren't even 10 players anyway :(");
					}
					else{
						inTopTen = bestPlayer.compareTo(top[i]);
						i++;
					}
				}
				if (inTopTen == 1){
					h.sendScore(bestPlayer, server);
					System.out.println(playerName + ", welcome in top 10");
				}
				else{
					System.out.println("Pas encore dans le top 10 ! ");
				}
	      
		
			}
			else if (answer.equals(no))
			{
				inGame = false;
			}
			else
			{
				System.err.println("Incorrect input, please answer yes or no");
			}
		}while(inGame);
		sc.close();
	}
	

	}
