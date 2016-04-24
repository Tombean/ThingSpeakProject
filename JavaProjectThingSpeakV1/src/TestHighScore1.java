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
		File f = new File("../ressources/scoreFile.csv");
		FileInputStream fis = null;
		 try {
	         // On instancie notre objet :
	         // fis va lire le fichier
	         fis = new FileInputStream( f );
	         String fileContent = "";
	         getFileContent(fis, fileContent);
	         
	         String delims = "[,]+";
	         String[] tokens = fileContent.split(delims);
	         
	         Random rand =  new Random();
	         int randScore = 0;
	         try {
				randScore = 1 + rand.nextInt( ( ( (Integer)tokens.length/2 ) - 1 ) ) ;
			} catch (IllegalArgumentException  e) {
				e.printStackTrace();
				randScore = 1;
			}
	         
	         randScore *= 2;
	         randScore -= 1;
	         System.out.println(playerName + " has reached the score : " + tokens[randScore]);

	      } catch (FileNotFoundException e) {
	         // Cette exception est lev�e si l'objet FileInputStream ne trouve aucun fichier
	         e.printStackTrace();
	      } catch (IOException e) {
	         // Celle-ci se produit lors d'une erreur d'�criture ou de lecture
	         e.printStackTrace();
	      } finally {
	         // On ferme nos flux de donn�es dans un bloc finally pour s'assurer que ces instructions seront ex�cut�es dans tous les cas m�me si une exception est lev�e !
	         try {
	            if (fis != null)
	               fis.close();
	         } catch (IOException e) {
	            e.printStackTrace();
	         }

	     }
		
		
	}
	

	public static String getFileContent( FileInputStream fis, String encoding ) throws IOException
	 {
	   try( 
			   BufferedReader br = new BufferedReader( new InputStreamReader(fis, encoding )))
	   {
		   StringBuilder builder = new StringBuilder();
		   int ch;
		   while((ch = fis.read()) != -1){
		       builder.append((char)ch);
		   }

	      return builder.toString();
	      }
	      
	 }
}