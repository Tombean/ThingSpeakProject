package highScores;

public class BestPlayer {
	String player;
	public String getPlayer() {
		return player;
	}

	public void setPlayer(String player) {
		this.player = player;
	}

	public int getScores() {
		return scores;
	}

	public void setScores(int scores) {
		this.scores = scores;
	}

	int scores;
	
	BestPlayer (String player, int scores ){
		this.player  = player;
		this.scores = scores;
	}
	
	public int compareTo(BestPlayer p) {
		if ( this.scores > p.getScores() ){return 1;}
		else if ( this.scores == p.getScores() ){return 0;}
		else {return -1;}
		
	}
}
