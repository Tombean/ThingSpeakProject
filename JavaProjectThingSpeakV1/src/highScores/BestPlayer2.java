package highScores;


/**
@author Tom Sommerville-Roberts and Erick Taru
@version 2
*/


public class BestPlayer2 {
	String name = new String();
	int score = 0;
	
	/**
	 * BestPlayer2 constructor
	 * @param nm : (String) Name
	 * @param scr :  (int) Score
	 */
	public BestPlayer2(String nm, int scr) {
		
		score = scr;
		name = nm;
		
	}
	
	/**
	 * usual getter for Name value of BestPlayer2
	 * @return  name (String)
	 */
	public String getName() {
		
		return name;
		
	}
	
	/**
	 * usual getter for Score value of BestPlayer2
	 * @return score (int)
	 */
	public int getScore() {
		
		return score;
		
	}

	/**
	 * usual setter for Name value of BestPlayer2
	 * @param nm : (String) new Name
	 */
	public void setName(String nm) {
		
		name = nm;
		
	}

	/**
	 * usual setter for Score value of BestPlayer2
	 * @param score : (int) new Score
	 */
	public void setScore(int score) {
		
		this.score = score;
		
	}
	
	/**
	 * compares 2 players (param one with this) to check who has the highest score
	 * @param  p (BestPlayer2) to compare with
	 * @return int
	 */
	public int compareTo(BestPlayer2 p) {
		
		if (this.score < p.getScore()) return -1;
		else if (this.score > p.getScore()) return 1;
		else return 0;
		
	}
}

