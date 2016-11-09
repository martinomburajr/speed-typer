package model;


 public class WordDictionary {
	int size;
	static String [] theDict= {"litchi","banana","apple","mango","pear","orange","strawberry",
		"cherry","lemon","apricot","peach","guava","grape","kiwi","quince","plum","prune",
		"cranberry","blueberry","rhubarb","fruit","grapefruit","kumquat","tomato","berry",
		"boysenberry","loquat","avocado"}; //default dictionary
	
	public WordDictionary(String [] tmp) {
		size = tmp.length; // set size to parameter size
		theDict = new String[size]; // initialize theDict array to have the size of the tmp parameter array
		
		for (int i=0;i<size;i++) { //initialize the static theDict array to contain elements from tmp parameter array
			theDict[i] = tmp[i];
		}
		
	}
	
	public WordDictionary() {
		size=theDict.length; //sets the length of the dict array to size
		
	}
	
	public synchronized String getNewWord() //
	{
		int wdPos= (int)(Math.random() * size);
		return theDict[wdPos];
	}
	
}
