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

public class TestHighScore1 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.println("Entrez votre nom s'il vous plait : ");
		String playerName = sc.nextLine();
		
		int server;
			try {
				System.out.println("Entrez votre numero de canal : ");
				server = sc.nextInt();
			}catch (InputMismatchException exception)
			    {
			        System.out.println("Integers only, please.");
			        server = 0;
			    }	
		
		HighScore1 h = new HighScore1();
		String[][] tokens = h.getScores(server);         
	         Random rand =  new Random();
	         int randScore = 0;
	         try {
				randScore = 1 + rand.nextInt( ( (Integer)tokens.length ) ) ;
			} catch (IllegalArgumentException  e) {
				e.printStackTrace();
				randScore = 1;
			}
	         for(int i = 0; i < tokens.length; i++){ System.out.println(tokens[0][i]); }
	         System.out.println(playerName + " has reached the score : " + tokens[0][randScore]);

	      
		
		
	}
	

	}