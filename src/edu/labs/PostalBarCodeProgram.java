package edu.labs;
import java.util.Scanner;
/**
 * Main program to display a menu.... complete the description
 * @author Alvaro Monge alvaro.monge@csulb.edu
 */

public class PostalBarCodeProgram 
{
   public static final String ZIP_TO_BAR_OPTION = "1";
   public static final String BAR_TO_ZIP_OPTION = "2";
   public static final String QUIT_OPTION = "3";
   
   /**
    * Scanner object to handle the user input.
    */
   public static Scanner userInput = new Scanner(System.in);
   
	public static void main(String[] args) 
	{
	   String userChoice;
		do {
		   displayMenu();
		   userChoice = userInput.next();
		   switch (userChoice)
		   {
		   case ZIP_TO_BAR_OPTION:
		      System.out.println("Converting Zip code to Bar code.");
            System.out.println("Enter a 5-digit Zip code to continue: ");
            String zipCode = userInput.next();
            System.out.println("The zipcode " + zipCode + " has a bar code of:");
            System.out.println(PostalBarCode.zipToBarCode(zipCode));
            System.out.println();
            break;
		   case BAR_TO_ZIP_OPTION:
            System.out.println("Converting Bar code to Zip code.");
            System.out.println("Enter the Bar code to continue: ");
            String barCode = userInput.next();
            System.out.println("The bar code " + barCode + " represent the zip code:");
            System.out.println(PostalBarCode.barToZipCode(barCode));
            System.out.println();
            break;
		   case QUIT_OPTION:
		      System.out.println("Good bye");
		      break;
		   default:
		      System.out.println("Please type one of the options in the menu.");
		   }
		} while (! userChoice.equals(QUIT_OPTION));
	}
	
	/**
	 * Displays a menu with the options for the user. 
	 */
	public static void displayMenu() 
	{
		System.out.println("------------------------------");
		System.out.println(ZIP_TO_BAR_OPTION + ": Zip code --> Bar code");
      System.out.println(BAR_TO_ZIP_OPTION + ": Bar code --> Zip code");
      System.out.println(QUIT_OPTION + ": Quit");
      System.out.println("------------------------------");
	}

}
