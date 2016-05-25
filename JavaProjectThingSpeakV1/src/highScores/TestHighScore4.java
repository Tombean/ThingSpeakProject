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
		System.out.println("Entrez votre nom s'il vous plait : ");
		String playerName = sc.nextLine();

		boolean inGame = true;
		
		int server;
			try {
				System.out.println("Entrez votre numero de canal : ");
				server = sc.nextInt();
			}catch (InputMismatchException exception)
			    {
			        System.out.println("Integers only, please.");
			        server = 0;
			    }	
		
			/**
			 * the following is to get a random score
			 */
			
		
		while(inGame)
		{
			//ask for a new game	
			System.out.println("Do you want to play a new game ? (yes/no)");
			String answer = input.nextLine();
			String yes="yes";
			String no = "no";
			if ((answer.charAt(0)==yes.charAt(0))&&(answer.charAt(1)==yes.charAt(1))&&(answer.charAt(2)==yes.charAt(2)))
			{
				HighScore4 h = new HighScore4();
				String[][] tokens = h.getScores(server);         
				Random rand =  new Random();
				int randScore = 0;
				try
				{
					randScore = 1 + rand.nextInt( ( (Integer)tokens.length ) ) ;
				} catch (IllegalArgumentException  e)
				{
					e.printStackTrace();
					randScore = 1;
				}
				System.out.println(playerName + " has reached the score : " + tokens[1][randScore]);

	      
		
			}
			else if ((answer.charAt(0)==no.charAt(0))&&(answer.charAt(1)==no.charAt(1)))
			{
				inGame = false;
			}
			else
			{
				System.err.println("incorrect input, please answer yes or no");
			}
		}
		input.close();
	}
	

	}
