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

public class HighScore1 {
	
URLConnection connection;
//String fileContent;
ArrayList<String> list;

public String[][] getScores(int server){
		String adresse = "https://api.thingspeak.com/channels/"+server+"/feeds.csv";
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
	         System.out.println(taille);
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
	
	public void getFileContent(  ) throws IOException
	 {
	   try( 
			   BufferedReader br = new BufferedReader( new InputStreamReader(this.connection.getInputStream() )))
	   {
		   // lit la 1ere ligne pour enlever les headers
		   br.readLine();
		   String ligne = "";
		   while( ( ligne  = br.readLine() ) != null ){
		       if( ligne == ""){
		    	   this.list.add(ligne);
		       }
		   }
	      }
	      
	 }
	
	public void ouvrir(String url){
		try {
			this.connection = new URL(url).openConnection();
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	

}
