package edu.labs;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Demonstrates the use of ArrayList and reading input from text files.
 * @author Alvaro Monge alvaro.monge@csulb.edu
 *
 */
public class DogNamePopularityFileArrayList {


   public static Scanner userInput = new Scanner(System.in);

   public static void main(String[] args)  throws FileNotFoundException
   {
      ArrayList<String> dogNames = new ArrayList<String>();
      ArrayList<Integer> dogNameFrequency = new ArrayList<Integer>();
      
      DogDataMethods.displayWelcome();

      readDogNameDataFile(dogNames, dogNameFrequency);

      String userChoice;
      do {
         DogDataMethods.displayMenu();
         userChoice = userInput.nextLine();
         switch (userChoice)
         {
         case DogDataMethods.SEARCH_OPTION:
            System.out.println("Enter dog name to search: ");
            String dogName = userInput.nextLine();
            displayDogData(dogNames, dogNameFrequency, dogName);
            break;
         case DogDataMethods.RANK_RANGE_OPTION:
            System.out.println("Enter rank to use for range lower bound: ");
            int lowerBoundRank = userInput.nextInt();
            System.out.println("Enter rank to use for range upper bound: ");
            int upperBoundRank = userInput.nextInt();
            userInput.nextLine(); // eliminates the left-over end-of-line
            displayRankRange(dogNames, dogNameFrequency, lowerBoundRank, upperBoundRank);
            break;
         case DogDataMethods.SORT_ALPHABETICALLY:
            System.out.println("Not implemented yet"); // TODO: complete this option
            break;
         case DogDataMethods.QUIT_OPTION:
            System.out.println("Good bye...");
            break;
         default: // user didn't enter one of the options
            System.out.println("Please choose from one of the options in the menu");
         }
      } while (! userChoice.equalsIgnoreCase(DogDataMethods.MENU_OPTIONS[3]));

      userInput.close();
   }

   /**
    * Reads dog name popularity from a file that contains, one line per dog, in the form: Lassie,528
    * 
    * @param names the ArrayList where the names of the dogs will be stored
    * @param frequency the ArrayList for frequency of dog names. Parallel to names ArrayList, so 
    * the dog name at index i in names has frequency at index i in frequency. 
    * @throws FileNotFoundException
    */
   public static void readDogNameDataFile(ArrayList<String> names, ArrayList<Integer> frequency) throws FileNotFoundException 
   {
      System.out.println("Enter the name of the data file (CSV format) with data on dog names and frequency: ");
      String fileName = userInput.nextLine();
      File dataFile = new File(fileName);

      Scanner fileScanner = new Scanner(dataFile);  // this is the method that may throw an exception when the fie doesn't exist

      while (fileScanner.hasNextLine() )
         processDataLine(names, frequency, fileScanner.nextLine());

      fileScanner.close();
   }

   /**
    * Process one line of data that contains dog name and frequency in the form: Lassie,528
    * @param names the ArrayList where the names of the dogs will be stored
    * @param frequency the ArrayList for frequency of dog names. Parallel to names ArrayList, so 
    *        the dog name at index i in names has frequency at index i in frequency. 
    * @param dataLine has one piece of data in the form:  Lassie,528
    */
   public static void processDataLine(ArrayList<String> names, ArrayList<Integer> frequency, String dataLine)
   {
      String[] fields = dataLine.split(",");
      names.add(fields[0]);
      frequency.add(Integer.parseInt(fields[1]));
   }

   /**
    * Reads dog name popularity from the user, one line per dog, in the format: Lassie,528.
    * @param names the ArrayList where the names of the dogs will be stored
    * @param frequency the ArrayList for frequency of dog names. Parallel to names ArrayList, so 
    * the dog name at index i in names has frequency at index i in frequency. 
    */
   public static void readDogNamePopularity(ArrayList<String> names, ArrayList<Integer> frequency)
   {
      int i = 0;
      boolean done = false;
      System.out.println("Enter the dog names and frequency, one per line separated by comma (e.g.: Lassie,258): ");
      while ( !done && i < names.size() && userInput.hasNextLine() )
      {
         String lineOfInput = userInput.nextLine();
         if (lineOfInput.equalsIgnoreCase(DogDataMethods.DOG_NAMES_INPUT_DONE)) 
         {
            done = true;
            System.err.println("found done");
         }
         else
         {
            processDataLine(names, frequency, lineOfInput);
            i++;
         }
      }

   }

   /**
    * Display dog names that fall within a range of ranks in a list of the top dog names. The arrays with the
    * list of names and frequencies are assumed to be in order by frequency.
    * @param names the array of dog names, ordered according to frequency 
    * @param frequency the array of how frequently the corresponding dog name has been used, ordered in descending value
    * @param nameCount the number of entries in the names and frequency arrays
    * @param lowerBoundRank the lowerbound of the range of dog names to be displayed
    * @param upperBoundRank the upperbound of the range of dog names to be displayed
    */
   public static void displayRankRange(ArrayList<String> names, ArrayList<Integer> frequency, int lowerBoundRank, int upperBoundRank)
   {
      System.out.printf("Dog names ranked %d thru %d:\n", lowerBoundRank, upperBoundRank);

      for (int i=lowerBoundRank-1; i >= 0 && i < names.size() && i < upperBoundRank; i++)
         System.out.printf("Rank %d: %s %d\n", i+1, names.get(i), frequency.get(i));

      System.out.println();
   }

   /**
    * Finds a dog name in an array of dog names and displays its rank and frequency. 
    * @param names the array of dog names, ordered according to frequency 
    * @param frequency the array of how frequently the corresponding dog name has been used, ordered in descending value
    * @param nameCount the number of entries in the names and frequency arrays
    * @param dogName the name of the dog being searched.
    */
   public static void displayDogData(ArrayList<String> names, ArrayList<Integer> frequency, String dogName)
   {
      int dogIndex = names.indexOf(dogName); // NOTE! No need to implement our own search! Read the JavaDoc 
      if (dogIndex != -1)
      {
         System.out.printf("Found %s ranked %d in the top %d list of dog names.\n", dogName, dogIndex+1, names.size());
         System.out.println("There are " + frequency.get(dogIndex) + " dogs that were licensed with the name " + dogName + ".");
         System.out.println();
      } else
         System.out.println(dogName + " is not in the top " + names.size() + " list of dog names");
   }

   /**
    * Displays the names and corresponding frequencies in the specified range. Overloaded method
    * @param names
    * @param frequency
    */
   public static void displayDogData(ArrayList<String> names, ArrayList<Integer> frequency)
   {
      for (int i=0; i < names.size(); i++)
         System.out.println(names.get(i) + ": frequency=" + frequency.get(i));

      // NOTE: Cannot use enhanced loop here because the index is being used to access two parallel arrays 
   }

}
