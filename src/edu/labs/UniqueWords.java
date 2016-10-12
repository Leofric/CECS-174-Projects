package edu.labs;
/**
 * This program takes in all of the words input by the user, sorts them alphabeticaly and determines if they are unique,
 * and prints two arrayLists containing the words that were repeated and not repeated.
 * 
 * @author Alexander Berthon
 * @version 1.0
 * 
 */
import java.util.*;

public class UniqueWords {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		ArrayList<String> uniqueWords = new ArrayList<String>();
		ArrayList<String> repeatedWords = new ArrayList<String>();
		boolean run = true; 
		boolean repeated = false;
		while (run) {	
			String word = in.next();
			word = word.toLowerCase();						//makes all entries lowercase to solve case sensitivity problems
			if (word.equals("end")) {						//stops the loop once the user is done inputing data
				run = false;
			}
			for (int i = 0; i<repeatedWords.size(); i++){	//First check, to see if word has already been repeated
				if (word.equals(repeatedWords.get(i))){
					repeated = true;
				}
			}
			for (int i = 0; i < uniqueWords.size(); i++) {	//Second check, to see if word has been used once already
				if (word.equals(uniqueWords.get(i))) {		//if the word is already on the unique list
					uniqueWords.remove(i);					//it is removed and placed on the repeated list
					repeatedWords.add(word);
					repeated = true;
				}
			}
			if (!repeated && run){							//if the word is not on either list, added to unique list
				uniqueWords.add(word);						//if word = end, it is not added to either list @(&& run)				
			}
			repeated = false;								//refreshes for next word
		}
		uniqueWords.sort(null);								//alphabatizes array lists
		repeatedWords.sort(null);
		in.close();		
		
		System.out.println("Unique Words" +" "+ uniqueWords);
		System.out.println("Repeated Words" +" "+ repeatedWords);
	}

}
