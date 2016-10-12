package edu.labs;
/**
*This program takes in a file name and a speaker name, and scans the file for their most frequently used words.
*"filler" words and phrases are removed from the array list where they are stored.
*The top 40 most frequently used words are printed to the console in order from most frequent to least frequent
*The program then makes a HTML file containing a word cloud combination of the top 40 words.
*
*@author Alexander Berthon alexjberthon@hotmail.com
*@author Matthew Cuevas matthewcuevas@live.com
*/

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Scanner;

public class FileReader {

	public static void main(String[] args) throws FileNotFoundException{
		String speechText = args[0];
		String speaker = args[1];
		
		ArrayList<String> stopWords = new ArrayList<String>();
		ArrayList<String> usedWords = new ArrayList<String>();
		ArrayList<Integer> frequency = new ArrayList<Integer>();

		processStopWords(stopWords);
		readFile(usedWords, frequency, stopWords, speechText, speaker);
		ArrayList<Integer> sorted = sortByFrequency(usedWords, frequency);
		
		for(int i=0; i<40; i++){
		System.out.println(usedWords.get(sorted.get(i))+" : " + frequency.get(sorted.get(i)));
		}
		
		String body = "";
		int highCount = frequency.get(sorted.get(0));
		int lowCount = frequency.get(sorted.get(40));	
		for (int i = 0; i < 40; i++) {
			body = body + HTMLWordCloudPrinter.makeHTMLWord(usedWords.get(sorted.get(i)) + " ", frequency.get(sorted.get(i)), highCount, lowCount);
		}
		String box = HTMLWordCloudPrinter.makeHTMLBox(body);
		HTMLWordCloudPrinter.printHTMLFile(speaker+"WordCloud.html", "WordCloud", box);	
	}
	/**
	*This method creates the scanner for stop words and scans the data from the stop words text file
	*and puts it into an array list which is used to sort out the used words array later.
	*/
	public static void processStopWords(ArrayList<String> stopWords) throws FileNotFoundException {
		String stopWordsText = "stopwords.txt";
		File stopWordsFile = new File(stopWordsText);

		Scanner stopWordsScanner = new Scanner(stopWordsFile);
		while (stopWordsScanner.hasNextLine()) {
			stopWords.add(stopWordsScanner.nextLine());
		}
		stopWordsScanner.close();
	}
	
	/**
	 * this controlls what lines in the file will be read and stored in the used words array list
	 * words that are said by others will be ignored
	 * words said by the speaker will be scanned
	 * 
	 * @param usedWords
	 * 			this is the array list that stores the most frequently used words
	 * @param frequency
	 * 			this is the array list PARALLEL to the usedWords array list; it tracks how often a word is said
	 * @param stopWords
	 * 			this is the array list that stores uninteresting and filler words
	 * @param speechText
	 * 			this is a String variable that stores the file name that was input
	 * @param speaker
	 * 			this is a String variable that store the speakers name that was input
	 * @throws FileNotFoundException
	 * 			this catches any errors when reading the file, if file is not found the error will report
	 */
	
	public static void readFile(ArrayList<String> usedWords, ArrayList<Integer> frequency, ArrayList<String> stopWords, String speechText, String speaker) throws FileNotFoundException {
		File speechFile = new File(speechText);
		Scanner speechScanner = new Scanner(speechFile);
		Boolean readLine = false;
		
		while (speechScanner.hasNextLine()){
			String currentLine = speechScanner.nextLine(); //have to put this into a variable or else it doesnt work i dont really know why. overload manybe
			if(currentLine.contains(":") && currentLine.contains(speaker)){
				processWords(usedWords, frequency, stopWords, currentLine);
				readLine = true;
			}
			else if (currentLine.contains(":")){
				readLine = false;
			}
			else if (readLine){
				processWords(usedWords, frequency, stopWords, speechScanner.nextLine());
			}
		}
		speechScanner.close();
		
	}

	/**
	 * This method scans a line sent by readFile, and puts the words into usedWords array list
	 * New words are added
	 * stop words are ignored
	 * repeated words are ignored but the frequency array list is updated respectively
	 * 
	 * @param usedWords
	 * 			Array list containing speakers words
	 * @param frequency
	 * 			Parallel array list to usedWords containing frequency of spoken words
	 * @param stopWords
	 * 			Array list containing useless words
	 * @param wordLine
	 * 			a variable that stores the current line being scanned, it is sent from previous method
	 */
	public static void processWords(ArrayList<String> usedWords, ArrayList<Integer> frequency, ArrayList<String> stopWords, String wordLine){
		String[] fields = wordLine.split(" ");
		for(int i=0; i<fields.length; i++){
			
			//this giant first block of code gets rid of punctuation, whitespace, etc so that it can be read/stored properly
			fields[i]=fields[i].toLowerCase();			
			if (fields[i].endsWith(",") | fields[i].endsWith("?") | fields[i].endsWith(".") | fields[i].endsWith("!") | fields[i].endsWith(";")){
				while(fields[i].endsWith(",") | fields[i].endsWith("?") | fields[i].endsWith(".") | fields[i].endsWith("!"))
				fields[i] = fields[i].substring(0, fields[i].length()-1);
			}
			if (fields[i].startsWith("."))
				while(fields[i].startsWith("."))
				fields[i] = fields[i].substring(fields[i].length()+1-fields[i].length(), fields[i].length());
			fields[i]=fields[i].trim();

			if (stopWords.contains(fields[i]) | fields[i].contains("(") | fields[i].contains(":") | fields[i].length()<2){
			//do nothing
			}
			else if (usedWords.contains(fields[i])){
				int index = usedWords.indexOf(fields[i]);
				int value = frequency.get(index);
				frequency.set(index, value+1);
				
				//find the index in used words where it is stored
				//then add 1 to the value stored at that same index in frequency
			}
			else{
				usedWords.add(fields[i]);
				int index = usedWords.indexOf(fields[i]);
				frequency.add(index, 1);
			}
			
		}
	}
	
	/**
	 * This method creates a new array, which is used as a reference in order to sort the used words and frequency arrays
	 * in the way we want without ruining their parallel nature
	 * 
	 * @param usedWords
	 * 			Array list that stores speakers words
	 * @param frequency
	 * 			Parallel array list that stores the frequency of spoken words
	 * @return 
	 * 			The return statement is used in order to use the indexes array list as a reference to print the 
	 * 			used words array list and the frequency array list in the proper order in the console
	 */
	public static ArrayList<Integer> sortByFrequency(ArrayList<String> usedWords, ArrayList<Integer> frequency){
		ArrayList<Integer> indexes = new ArrayList<Integer>(frequency.size());
		for (int i=0; i < frequency.size(); i++)
	         indexes.add(i);
		
		indexes.sort(new Comparator<Integer>() {
	         @Override
	         public int compare(Integer o1, Integer o2) {
	            return frequency.get(o2) - frequency.get(o1);
	         }
	      });
	return indexes;
	   }	
}