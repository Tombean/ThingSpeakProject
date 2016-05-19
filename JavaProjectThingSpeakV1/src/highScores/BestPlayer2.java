package highScores;


/**
@author Tom Sommerville-Roberts and Erick Taru
@version 2
*/


public class BestPlayer2 {
	String name = new String();
	int score = 0;
	
	public BestPlayer2(String nm, int scr) {
		
		score = scr;
		name = nm;
		
	}
	
	public String getName() {
		
		return name;
		
	}
	
	public int getScore() {
		
		return score;
		
	}

	public void setName(String nm) {
		
		name = nm;
		
	}

	public void setScore(int score) {
		
		this.score = score;
		
	}
	
	public int compareTo(BestPlayer2 p) {
		
		if (this.score < p.getScore()) return -1;
		else if (this.score > p.getScore()) return 1;
		else return 0;
		
	}
}

