package edu.labs;

public class DogDataMethods {
   public static final String SEARCH_OPTION = "1";
   public static final String RANK_RANGE_OPTION = "2";
   public static final String SORT_ALPHABETICALLY = "3";
   public static final String QUIT_OPTION = "QUIT";
   public static final String[] MENU_OPTIONS = {SEARCH_OPTION, RANK_RANGE_OPTION, SORT_ALPHABETICALLY, QUIT_OPTION};
   public static final String[] MENU_PROMPTS = {"Search for name of a dog",
                                                "Display dog names in a rank range",
                                                "Display dog names alphabetically",
                                                "Exit the program"};
   public static final String DOG_NAMES_INPUT_DONE = "Done";
   
   /**
    * Displays the menu of options by using two parallel arrays, one has the option and the other the prompt for the user. 
    */
   public static void displayMenu()
   {
      System.out.println("Choose an option from the menu:");
      for (int i=0; i < MENU_OPTIONS.length; i++)
         System.out.println(MENU_OPTIONS[i] + ": " + MENU_PROMPTS[i]);
   }
   
   /**
    * Displays a welcome message for the user.
    */
   public static void displayWelcome()
   {
      System.out.println("This program allows you to work with data on dog names and how frequently they are used.");
      System.out.println("You'll be asked to supply either the data OR the name of a file that has data.");
      System.out.println("The data must be provided one dog name per line and each line is in the form: Duke,2510");
      System.out.println("In this case, the name Duke has been used 2510 times\n\n");
   }

}
