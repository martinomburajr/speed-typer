package model;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.util.Random;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

import controller.WordPanelController;

public class WordPanel extends JPanel
{

	private static final long serialVersionUID = 1L;
		public static volatile boolean done;
		private WordRecord[] words;
		private int noWords;
		private int maxY;
		
		public static int randInt(int min, int max) {

		    // NOTE: Usually this should be a field rather than a method
		    // variable so that it is not re-seeded every call.
		    Random rand = new Random();

		    // nextInt is normally exclusive of the top value,
		    // so add 1 to make it inclusive
		    int randomNum = rand.nextInt((max - min) + 1) + min;

		    return randomNum;
		}
		
		public void paintComponent(Graphics g) {
		    int width = getWidth();
		    int height = getHeight();
		    g.clearRect(0,0,width,height);
		    g.setColor(Color.red);
		    g.fillRect(0,maxY-10,width,height);

		    if(WordPanelController.isAnime())
		    {
		    if(WordPanel.randInt(0, 19) % 2 == 0)
		    {
		    g.setColor(Color.black);
		    g.setFont(new Font("Arial", Font.ITALIC, 26));
		    }
		    else if(WordPanel.randInt(0, 19) % 2 == 0)
		    {
		    	g.setColor(Color.pink);
			    g.setFont(new Font("Arial", Font.ITALIC, 26));
		    }
		    else if(WordPanel.randInt(0, 19) % 2 == 0)
		    {
		    	g.setColor(Color.blue);
			    g.setFont(new Font("Arial", Font.ITALIC, 26));
		    }
		    else if(WordPanel.randInt(0, 19) % 2 == 0)
		    {
		    	g.setColor(Color.yellow);
			    g.setFont(new Font("Arial", Font.ITALIC, 26));
		    }
		    else
		    {
			    g.setColor(Color.green);
			    g.setFont(new Font("Arial", Font.ITALIC, 26));
		    }
		    }
		    else
		    {
		    	g.setColor(Color.black);
			    g.setFont(new Font("Arial", Font.ITALIC, 26));
		    }
		    
		    //Adds the panel that contains the scores etc
//		    JPanel txt = new JPanel();
//		    txt.setLayout(new BoxLayout(txt, BoxLayout.LINE_AXIS));
//		    
//		    JLabel caught =new JLabel();
//		    caught.setText("Caught: " + Score.getCaught() + "    "); 
//		    
//		    
//		    JLabel missed =new JLabel("Missed:" + Score.getMissed()+ "    ");
//		    missed.setText("Missed: " + Score.getMissed() + "    "); 
//		    
//		    JLabel scr =new JLabel("Score:" + Score.getScore()+ "    ");    
//		    scr.setText("Score: " + Score.getScore() + "    ");
//		    
//		    
//		    txt.add(caught);
//		    txt.add(missed);
//		    txt.add(scr);
		    
		    
		    
		    
		    
		    
		   //draw the words
		   //animation must be added 
		    for (int i=0;i<noWords;i++){	    	
		    	//g.drawString(words[i].getWord(),words[i].getX(),words[i].getY());	
		    	g.drawString(words[i].getWord(),words[i].getX(),words[i].getY()+20);  //y-offset for skeleton so that you can see the words	
		    }
		   
		  }
		
		public WordPanel(WordRecord[] words, int maxY) {
			this.words=words; //will this work?
			noWords = words.length;
			done=false;
			this.maxY=maxY;		
		}
		
	}


