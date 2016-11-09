package driver;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Scanner;

import model.Score;
import model.SoundClipTest;
import model.Threader;
import model.WordDictionary;
import model.WordPanel;
import model.WordRecord;
import view.WordAppView;
import controller.ThreaderController;


//model is separate from the view.

public class WordApp {
//shared variables
	static int noWords=4;
	static volatile int totalWords;

   	static int frameX=1000;
	static int frameY=600;
	static int yLimit=480;

	static WordDictionary dict = new WordDictionary(); //use default dictionary, to read from file eventually

	private static WordRecord[] words;
	static WordPanel w;
	Score score = new Score();
	

	
	public static String[] getDictFromFile(String filename) {
		String [] dictStr = null;
		try {
			Scanner dictReader = new Scanner(new FileInputStream(filename));
			int dictLength = dictReader.nextInt();
			//System.out.println("read '" + dictLength+"'");

			dictStr=new String[dictLength];
			for (int i=0;i<dictLength;i++) {
				dictStr[i]=new String(dictReader.next());
				//System.out.println(i+ " read '" + dictStr[i]+"'"); //for checking
			}
			dictReader.close();
		} catch (IOException e) {
	        System.err.println("Problem reading file " + filename + " default dictionary will be used");
	    }
		return dictStr;

	}
	
	
	
	
	

	public static void main(String[] args)
	{
		//deal with command line arguments
		totalWords = Integer.parseInt(args[0]);  //total words to fall
		noWords=Integer.parseInt(args[1]); // total words falling at any point
		assert(totalWords>=noWords); // this could be start more neatly
		

		String[] tmpDict=getDictFromFile(args[2]); //file of words
		if (tmpDict!=null)
			dict= new WordDictionary(tmpDict);
		
		WordRecord.dict=dict; //set the class dictionary for the words.
		
		words = new WordRecord[noWords];  //shared array of current words	
		//[snip]
		WordAppView.setupGUI(frameX, frameY, yLimit);  
    	//Start WordPanel thread - for redrawing animation

		int x_inc=(int)frameX/noWords;
	  	//initialize shared array of current words

		//WordPanel wp = new WordPanel(words,noWords);
		
		//new SoundClipTest();
		
		for (int i=0;i<noWords;i++) {
			words[i]= new WordRecord(dict.getNewWord(),i*x_inc,yLimit);
			ThreaderController.runThreader(words[i],WordAppView.getCurrDifficulty());
		}
			
	}

	
	
	//GETTERS AND SETTERS
	
	public static synchronized WordRecord[] getWords() {
		return words;
	}

	public static synchronized void setWords(WordRecord[] words) {
		WordApp.words = words;
	}


	public static synchronized WordPanel getW() {
		return w;
	}

	public static synchronized void setW(WordPanel w) {
		WordApp.w = w;
	}

	
	
	
	
}
