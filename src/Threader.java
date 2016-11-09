



public class Threader implements Runnable{

	private static String getWord;
	private WordRecord wr;
	private static int getX;
	private int sleepVal = 0;
	private int diff = 3;
	
	public Threader(WordRecord wra, int difficulty)
	{
		wr =wra;
		sleepVal = wra.getWord().length()*30; //initialize sleeptime based on words length * arbitrary constant
		diff = difficulty;
	}
	
	public Threader()
	{
		
	}
	
	@Override
	public void run() {
		while(ThreaderController.isStart() == true)
		{
				wr.drop(WordAppView.getCurrDifficulty()); // dropsthe word
							
				while(WordAppView.isPause() == true)
				{
					
				}
			
				//Sleep method to better simulate the drop
			try 
			{
				Thread.sleep(sleepVal); //sleepVal is calculated with respect to the size of the word!!!
			} 
			catch (InterruptedException e) 
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
			WordApp.getW().repaint(); // Repaint
			
			
			if(wr.getWord().equalsIgnoreCase(getWord)) // if matches return XPos, increment caught
			{
				Score.caughtWord(wr.getWord().length()); //Set score
				wr.resetWord(); //resets the word
			}
			else if(!wr.getWord().equalsIgnoreCase(getWord))  //if wrong, ignore
			{
				//Haz Nada!
				//Do Nothing
			}
			
			if(wr.dropped()) // if in redzone clear word, increment missed
			{
				wr.resetWord();
				Score.missedWord();
			}	
			
			WordAppView wv = new WordAppView();
			wv.setScores();
			//Score g = new Score();
			//g.scorePanel();
			
		}
	}
	
	//GETTERS N SETTERS
	
	public static int getXPos()
	{
		return getX;
	}
	
	public static synchronized String getWord() {
		return getWord;
	}

	public static synchronized void setGetWord(String getWord) {
		Threader.getWord = getWord;
	}
	
	

}
