package highScores;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;


/**
 * This class handles highscores in our game
 * @author erick.taru, tom.sommerville roberts
 *
 */


public class HighScore4 {
	URLConnection connection;
	//String fileContent;
	ArrayList<String> list;

	/**
	 * Retrieves names and scores from the server
	 * @param s server
	 * @return joueur / score
	 */
	
	public String[][] getScores(int s){
			String server = Integer.toString(s);
			String adresse = "https://api.thingspeak.com/channels/" + server +"/feeds.csv";
			System.out.println("connecting to the url : " + adresse);
			open(adresse);
			this.list = new ArrayList<String>();
			//this.fileContent = "";
			String[][] tokens;     
		     try {
				getFileContent();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		         int taille =  this.list.size();
		         // tokens contient joueur / score 
		         tokens = new String[2][taille];
		         String delims = ",";
		         int indice = 0;
		         for( String l : this.list ){
		        	 // sauve le nom du joueur
		        	 tokens[0][indice] = l.split(delims)[3];
		        	// sauve le score du joueur
		        	 tokens[1][indice] = l.split(delims)[2];
		        	 indice++;
		         }
		      
			 return tokens;
			 
		}
	
	/**
	 * gets the content of the file
	 * @throws IOException error possible during communication with the server
	 */
	public void getFileContent(  ) throws IOException
	 {
	   try{ 
			   BufferedReader br = new BufferedReader( new InputStreamReader(this.connection.getInputStream() ));
			   System.out.println("Pret � lire le buffer : " +br.ready());
			   // lit la 1ere ligne pour enlever les headers
			   br.readLine();
			   String ligne = "";
			   while( ( ligne  = br.readLine() ) != null ){
			       if( !ligne.equals("")){
			    	   this.list.add(ligne);
			       }
		   }
	 } catch(IOException e){
		 e.printStackTrace();
	 }
	 }
	
	/**
	 * Opens the connection to ThingSpeak
	 * @param url URL to connect to
	 */
	public void open(String url){
		try {
			this.connection = new URL(url).openConnection();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
			
		}
	}
	

	/**
	 * function to retrieve the ten best scores
	 *
	 */
	public BestPlayer2[] tenBestScores(String [][] readScores, int s){

		BestPlayer2[] allBest= new BestPlayer2[readScores[0].length];
		System.out.println("Il y a deja "+readScores[0].length+" joueurs ayant mis leur r�sultat en ligne ! ");

		for(int i=0;i<readScores[0].length;i++){
			allBest[i]= new BestPlayer2(readScores[0][i], Integer.parseInt(readScores[1][i]) );
		}

		List<BestPlayer2> resList = new ArrayList<BestPlayer2>();
		BestPlayer2 mem;
		mem= allBest[0];
		
		//sorting algorithm for scores
		for(int i = 0; i<(allBest.length - 1); i++ ){
			int pos = i;
			for (int j = (1+i) ;  j< allBest.length; j++){
				if ( allBest[pos].compareTo(allBest[j])<0 ){
					pos = j;
				}
			}
			mem = allBest[pos];
			allBest[pos] = allBest[i];
			allBest[i] = mem;
			}
			
		BestPlayer2 top[];

		if (allBest.length <10){
			top = new BestPlayer2[allBest.length];
			for (int i = 0; i < allBest.length; i++){
				top[i] = allBest[i];
			}
		}
		else{
			top = new BestPlayer2[10];
			for (int i = 0; i < 10; i++){
				top[i] = allBest[i];
			}
		}

		//condition to place the new best scorein the list of ten best scores		
		for (int a = 0; a< top.length; a++){
			System.out.println((a+1) + " - "+ top[a].getName() + " has reached the score : "+ top[a].getScore());
		}
		return top ;
	}


	/**
	 * function to send to score of the player to ThingSpeak
	 * 
	 */
	public void sendScore(BestPlayer2 p, int s){
        	try {
        
            	String nom = p.getName();
            	int score = p.getScore();
            	String server = Integer.toString(s);
    			String ApiKey = "R1ER0KGLXOGTB4A4";
            	String adresse = " https://api.thingspeak.com/update?api_key=" + ApiKey +"&";
            	URL getURL = new URL(adresse+"field1="+score+ "&field2="+nom); //get right URL
            	getURL.openStream();
            	System.out.println("Score sent to ThingSpeak !");
            
        	} catch (Exception e) { e.printStackTrace(); }
    	}
	

}

