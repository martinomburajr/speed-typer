package model;


public class Score
{
	private static volatile int missedWords;
	private static volatile int caughtWords;
	private static volatile int gameScore;
	
	public Score() {
		missedWords = 0;
		caughtWords = 0;
		gameScore=0;
	}
		
	// all getters and setters must be synchronized
	
	//######################## READERS #############################//
	public synchronized static int getMissed() {
		return missedWords;
	}

	public synchronized static int getCaught() {
		return caughtWords;
	}

	public synchronized static int getScore() {
		Score.setScore(); // sets the game score
		return gameScore;
	}

	
	//######################## WRITERS #############################//

	
	public synchronized static int missedWord() {
		missedWords++;
		Score.setScore(); // sets the game score
		return gameScore;
		
	}
	public synchronized static int caughtWord(int length) {
		caughtWords++;
		Score.setScore(); // sets the game score
		return gameScore;
	}
	
	public synchronized static void resetScore() {
		caughtWords= 0;
		missedWords= 0;
		gameScore=0;
	}
	
	//Calculates the game score 
	public synchronized static void setScore()
	{
		gameScore = (caughtWords* 7) - (missedWords*4);
	}
}
