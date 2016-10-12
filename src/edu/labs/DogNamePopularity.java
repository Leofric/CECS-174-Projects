package edu.labs;

import java.util.Scanner;

/**
 * Program to demonstrate working with arrays, parallel arrays, linear search. 
 * @author Alvaro Monge alvaro.monge@csulb.edu
 *
 */
public class DogNamePopularity {
   public static final int MAX_DOG_NAMES = 50;
   public static Scanner userInput = new Scanner(System.in);
   public static void main(String[] args) {
      String[] dogNames = new String[MAX_DOG_NAMES];
      int[] dogNameFrequency = new int[MAX_DOG_NAMES];
      
      DogDataMethods.displayWelcome();

      int nameCount = readDogNamePopularity(dogNames, dogNameFrequency);
      
      String userChoice;
      do {
         DogDataMethods.displayMenu();
         userChoice = userInput.nextLine();
         switch (userChoice)
         {
         case DogDataMethods.SEARCH_OPTION: 
            System.out.println("Enter dog name to search: ");
            String dogName = userInput.nextLine();
            displayDogData(dogNames, dogNameFrequency, nameCount, dogName);
            break;
         case DogDataMethods.RANK_RANGE_OPTION:
            System.out.println("Enter rank to use for range lower bound: ");
            int lowerBoundRank = userInput.nextInt();
            System.out.println("Enter rank to use for range upper bound: ");
            int upperBoundRank = userInput.nextInt();
            userInput.nextLine(); // eliminates the left-over end-of-line
            displayRankRange(dogNames, dogNameFrequency, nameCount, lowerBoundRank, upperBoundRank);
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
    * Read information on dog names and popularity from the user.
    * @param names the array of dog names, ordered according to frequency 
    * @param frequency the array of how frequently the corresponding dog name has been used, ordered in descending value
    * @return the number of entries provided by the user
    */
   public static int readDogNamePopularity(String[] names, int[] frequency)
   {
      int i = 0;
      boolean done = false;
      System.out.println("Enter the dog names and frequency, one per line separated by comma (e.g.: Lassie,258): ");
      while ( !done && i < names.length && userInput.hasNextLine() )
      {
         String lineOfInput = userInput.nextLine();
         if (lineOfInput.equalsIgnoreCase(DogDataMethods.DOG_NAMES_INPUT_DONE)) 
         {
            done = true;
         }
         else
         {
            String[] fields = lineOfInput.split(",");
            names[i] = fields[0];
            frequency[i] = Integer.parseInt(fields[1]);
            i++;
         }
      }
      
      return i;
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
   public static void displayRankRange(String[] names, int[] frequency, int nameCount, int lowerBoundRank, int upperBoundRank)
   {
      System.out.printf("Dog names ranked %d thru %d:\n", lowerBoundRank, upperBoundRank);
      // TODO: live implementation
   }
   
   /**
    * Finds a dog name in an array of dog names and displays its rank and frequency. 
    * @param names the array of dog names, ordered according to frequency 
    * @param frequency the array of how frequently the corresponding dog name has been used, ordered in descending value, frequency[i] is the frequency for name names[i]
    * @param nameCount the number of valid entries in the names and frequency arrays
    * @param dogName the name of the dog being searched.
    */
   public static void displayDogData(String[] names, int[] frequency, int nameCount, String dogName)
   {
      
      // TODO: live implementation of linear search
      
      // init
      int i=0;
      boolean foundDogName = false;
      while ( i < nameCount && foundDogName ) // test
      {
         if (dogName.equalsIgnoreCase(names[i]))
            foundDogName = true;
         i++;
      }

      if (foundDogName)
      {
         System.out.printf("Found %s ranked %d in the top %d list of dog names", dogName, i+1, nameCount);
         System.out.println("There are " + frequency[i] + " dogs that were licensed with the name " + dogName + ".");
         System.out.println();
      } else
         System.out.println(dogName + " is not in the top " + nameCount + " list of dog names");
   }
   
   /**
    * Displays the data on dog names and popularity.
    * @param names the array of dog names, ordered according to frequency 
    * @param frequency the array of how frequently the corresponding dog name has been used, ordered in descending value, frequency[i] is the frequency for name names[i]
    * @param nameCount the number of valid entries in the names and frequency arrays
    */
   public static void displayDogData(String[] names, int[] frequency, int nameCount)
   {
      for (int i=0; i < nameCount; i++)
      {
         System.out.printf("Rank %d: %s, frequency %d", i+1, names[i], frequency[i]);
      }
   }
}
