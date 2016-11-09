package view;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import model.Difficulty;
import model.Score;
import model.WordPanel;
import controller.ThreaderController;
import driver.WordApp;

public class WordAppView {
	
	private static volatile boolean start = true; //must be volatile so that its current val is viewd by every thread
	private static volatile boolean isPause = false;
	private static volatile int isPauseClicked = 1;
	private static volatile int isAnimeClicked = 1;
	private static volatile boolean isAnime = false;
	private static volatile int currDifficulty = 3;


	static JLabel missed;
	static JLabel caught;
	static JLabel scr;
	
	
	public static void setupGUI(int frameX,int frameY,int yLimit) {
		// Frame init and dimensions

    	final JFrame frame = new JFrame("WordGame"); 
    	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	frame.setSize(frameX, frameY);
    	
      	JPanel g = new JPanel();
        g.setLayout(new BoxLayout(g, BoxLayout.PAGE_AXIS)); 
      	g.setSize(frameX,frameY);
 	
      	WordApp.setW(new WordPanel(WordApp.getWords(),yLimit)); //gets the word panel and sets the limit
      	WordApp.getW().setSize(frameX,yLimit+100);
	    g.add(WordApp.getW()); //adds the words to the panel.
	    
	    //ADDED words
	       
	    //Adds the panel that contains the scores etc
	    JPanel txt = new JPanel();
	    txt.setLayout(new BoxLayout(txt, BoxLayout.LINE_AXIS));
	    
	    caught =new JLabel();
	    caught.setText("Caught: " + Score.getCaught() + "    "); 
	    
	    
	    missed =new JLabel();
	    missed.setText("Missed: " + Score.getMissed() + "    "); 
	    
	    scr =new JLabel();    
	    scr.setText("Score: " + Score.getScore() + "    ");
	    
	    JLabel diffLabel = new JLabel("            Difficulty:  ");
	    JLabel inputLabel = new JLabel("            Type Here:  ");
	    
	    //DIFFICULTY COMBO
	    final JComboBox <String>jcb = new JComboBox<String>();
	    jcb.addItem("EASY");
	    jcb.addItem("INTERMEDIATE");
	    jcb.addItem("HARD");
	    jcb.addItem("ASIAN");
	    jcb.addItem("SUPER SAIYAN");
	    
	    jcb.addActionListener(new ActionListener()
	    {
	    	public void actionPerformed(ActionEvent evt)
	    	{
	    		if(jcb.getSelectedIndex() == 0)
	    		{
	    			currDifficulty = Difficulty.getEasy();
	    		}
	    		else if(jcb.getSelectedIndex() == 1)
	    		{
	    			currDifficulty = Difficulty.getIntermediate();
	    		}
	    		else if(jcb.getSelectedIndex() == 2)
	    		{
	    			currDifficulty = Difficulty.getHard();
	    		}
	    		else if(jcb.getSelectedIndex() == 3)
	    		{
	    			currDifficulty = Difficulty.getAsian();
	    		}
	    		else if(jcb.getSelectedIndex() == 4)
	    		{
	    			currDifficulty = Difficulty.getSuperSaiyan();
	    		}
	    		else
	    		{
	    			currDifficulty = Difficulty.getIntermediate();
	    		}
	    		
	    	}
	    	
	    });
	    
	    
	    txt.add(caught);
	    txt.add(missed);
	    txt.add(scr);
	    txt.add(diffLabel);
	    txt.add(jcb);
	    txt.add(inputLabel);
	   // WordApp.getW().repaint();
	    //[snip]
	    
	    
	    //################# TEXTFIELD ################### //
	    //WORD ENTRY TEXTFIELD
	   final JTextField textEntry = new JTextField("",20);
	   textEntry.addActionListener(new ActionListener()
	    {
	      public void actionPerformed(ActionEvent evt) {
	          String text = textEntry.getText();
	          //sets the word that has been typed so the threads can see it.

	          ThreaderController.setTypedString(text);
	          
	          textEntry.setText("");
	          textEntry.requestFocus();
	      }
	    });  
	   //################# END TEXTFIELD ################### //
	   
	   txt.add(textEntry);
	   txt.setMaximumSize( txt.getPreferredSize() );
	   g.add(txt);
	    
	   //################# BUTTONS ################### //   
	   //START BUTTON
	    JPanel b = new JPanel();
        b.setLayout(new BoxLayout(b, BoxLayout.LINE_AXIS)); 
	   	JButton startB = new JButton("Start");;
		
			// add the listener to the jbutton to handle the "pressed" event
			startB.addActionListener(new ActionListener()
		    {
		      public void actionPerformed(ActionEvent e)
		      {
		    	  ThreaderController.setIsStart(true);
		    	  textEntry.requestFocus();  //return focus to the text entry field
		    	  Score.resetScore();
		      }
		    });
			
			
			//Animate BUTTON
			final JButton animeB = new JButton("Animate");
			animeB.addActionListener(new ActionListener()
			{
			public void actionPerformed(ActionEvent e)
			{
				if(isAnimeClicked % 2 == 0)
				{
					//isPause = false;
					ThreaderController.setIsAnime(false);
					animeB.setText("Animate");
					
				}
				else
				{
					//isPause = true;
					ThreaderController.setIsAnime(true);
					animeB.setText("Un-Animate");
				}
				isAnimeClicked++;
			}	
			});
			
			
			//PAUSE BUTTON
			final JButton pauseB = new JButton("Pause");
			pauseB.addActionListener(new ActionListener()
			{
			public void actionPerformed(ActionEvent e)
			{
				if(isPauseClicked % 2 == 0)
				{
					//isPause = false;
					ThreaderController.setIsPause(false);
					pauseB.setText("Pause");
				}
				else
				{
					//isPause = true;
					ThreaderController.setIsPause(true);
					pauseB.setText("Resume");
				}
				isPauseClicked++;
			}	
			});
			
			
			//END BUTTON
			JButton endB = new JButton("End");;
				endB.addActionListener(new ActionListener()
			    {
			      public void actionPerformed(ActionEvent e)
			      {
			    	 // start = false;
			    	  ThreaderController.setIsStart(false);
			    	  frame.dispose();
			      }
			    });
		
		b.add(startB);
		b.add(pauseB);
		b.add(endB);
		b.add(animeB);
		// #################### END BUTTONS ##############################//
		
		//add panel
		g.add(b);
		
		JPanel diffPanel = new JPanel(new FlowLayout());
		
    	
      	frame.setLocationRelativeTo(null);  // Center window on screen.
      	frame.add(g); //add contents to window
        frame.setContentPane(g);     
       	//frame.pack();  // don't do this - packs it into small space
        frame.setVisible(true);
	}

	public void setScores()
	{
		synchronized(this)
		{
		caught.setText("Caught: " + Score.getCaught() + "    "); 
		missed.setText("Missed: " + Score.getMissed() + "    "); 
		scr.setText("Score: " + Score.getScore() + "    "); 
		}
	}
	
	
	public static int getCurrDifficulty() {
		return currDifficulty;
	}
	
 	public static synchronized boolean isAnime() {
		return isAnime;
	}


	public static synchronized void setAnime(boolean isAnime) {
		WordAppView.isAnime = isAnime;
	}


	public static synchronized boolean isStart() {
		return start;
	}

	public static synchronized void setStart(boolean start) {
		WordAppView.start = start;
	}

	public static synchronized boolean isPause() {
		return isPause;
	}

	public static synchronized void setPause(boolean isPause) {
		WordAppView.isPause = isPause;
	}

	public static synchronized int getIsPauseClicked() {
		return isPauseClicked;
	}

	public static synchronized void setIsPauseClicked(int isPauseClicked) {
		WordAppView.isPauseClicked = isPauseClicked;
	}
	
}
