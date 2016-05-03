import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Random;

/**
 * This class handles highscores in our game
 * @author erick.taru, tom.sommerville-roberts
 *
 */
public class HighScore1 {
	
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
		String adresse = "https://api.thingspeak.com/channels/"+server+"/feeds.csv";
		System.out.println("url : " + adresse);
		ouvrir(adresse);
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
			    	   System.out.println("ligne : " + ligne);
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
	public void ouvrir(String url){
		try {
			this.connection = new URL(url).openConnection();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	

}
