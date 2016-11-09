package controller;


import model.Threader;
import model.WordRecord;
import view.*;

//import java.awt.Graphics;

public class ThreaderController
{

	Threader th;
	String typedString = "";
	
	public ThreaderController()
	{
		th = new Threader();
	}
	
	public static void runThreader(WordRecord obj, int xPos)
	{
		Thread th = new Thread(new Threader(obj,xPos));
		th.start();
	}
	
	
	
	
	
	//Getter and setters
	public synchronized static boolean isAnime()
	{
		return WordAppView.isAnime();
	}
	
	public synchronized static void setIsAnime(boolean value)
	{
		WordAppView.setAnime(value);
	}
	
	public synchronized static boolean isPause()
	{
		return WordAppView.isPause();
	}
	
	public synchronized static void setIsPause(boolean value)
	{
		WordAppView.setPause(value);
	}
	
	public synchronized static boolean isStart()
	{
		return WordAppView.isStart();
	}
	
	public synchronized static void setIsStart(boolean value)
	{
		WordAppView.setStart(value);
	}
	
	public synchronized static void setTypedString(String typedString)
	{
		Threader.setGetWord(typedString);
	}
	
	public synchronized static String getTypedString()
	{
		return Threader.getWord();
	}


}
