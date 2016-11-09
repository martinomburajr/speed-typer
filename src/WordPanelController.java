
public class WordPanelController {
	
	
	
	public static synchronized boolean isAnime() {
		return WordAppView.isAnime();
	}


	public static synchronized void setAnime(boolean isAnime) {
		WordAppView.setAnime(isAnime);
	}

}
