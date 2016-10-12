/**
 * This class is composed of 4 methods that are called by the main program class "PostalBarCodeProgram"
 * in order to run. This class is used for computation, while the main program does all of the output.
 * @author Alexander Berthon Alexjberthon@hotmail.com
 * @author Matthew Quevas 	 matthewcuevas@live.com
 * @author Dr. Alvaro Monge  alvaro.monge@csulb.edu
 */
package edu.labs;

public class PostalBarCode {
	/**
	 * Converts a bar code into its corresponding zip code.
	 * 
	 * @param barCode
	 *            is a String consisting of characters : and | that is a postal
	 *            bar code.
	 * @return the zip code represented by the given bar code, or null if the
	 *         barCode is invalid
	 */
	public static String barToZipCode(String barCode) {
		String zipCode = "";   //"initial" condition
		int digit;
		if (barCode.length() != 32) { //first error if user input is less than 32 characters
			return zipCode = "Error, bar code must be 32 characters long.";
		}
		for (int i = 0; i < 5; i++) {	    //substring [1,6), [6,11), [11,16), [16,21), [21,26)
			digit = barToDigit(barCode.substring(i * 5 + 1, 6 + i * 5)); 
			if (digit == -1) {  
				zipCode = "Error, invalid this bar code is invalid"; 
			/*if the 5 character substring contains an invalid sequence, ie some combination of
			| and : that does not hold a number representation, this reports the code as invalid*/	
			} else
				zipCode += digit; //otherwise it creates the area code 1 substring at a time
		}
		return zipCode;
	}

	/**
	 * Converts a 5-digit zip code to its corresponding bar code.
	 * 
	 * @param zipCode
	 *            is a 5-digit zip code.
	 * @return the bar code that represents the given 5-digit zip code, or null
	 *         if the zipCode is invalid
	 */
	public static String zipToBarCode(String zipCode) {
		int number;
		String barCode = "|"; //initial | in the 0 slot
		int checkDigit = 0;
		for (int i = 0; i < 5; i++) { //converts string to int value for each individual number
			number = zipCode.codePointAt(i);
			number = Character.getNumericValue(number);
			checkDigit += number; 				//check digit becomes sum
			barCode += digitToBarCode(number); //sends value, returns with barCode value for that number
		}
		checkDigit = checkDigit % 10; //if the sum is divisible by 10, check digit set to 0
		if (checkDigit == 0) {
			barCode += "||:::|";
		} else {					//if the sum has a remainder, this gets the correct value for checkdigit
			checkDigit = 10 - checkDigit;
			barCode += digitToBarCode(checkDigit);
			barCode += "|"; 	
		}
		return barCode;			
	}

	/**
	 * Converts a digit to its corresponding bar code.
	 * 
	 * @param digit
	 *            is an integer in the range [0,9]
	 * @return the String that is the bar code representation of the digit or
	 *         null if the digit is not in the range [0-9]
	 */
	public static String digitToBarCode(int digit) {
		String code;					//checks value one by one, returns Barcode value
		if (digit == 1) {
			code = ":::||";
		} else if (digit == 2) {
			code = "::|:|";
		} else if (digit == 3) {
			code = "::||:";
		} else if (digit == 4) {
			code = ":|::|";
		} else if (digit == 5) {
			code = ":|:|:";
		} else if (digit == 6) {
			code = ":||::";
		} else if (digit == 7) {
			code = "|:::|";
		} else if (digit == 8) {
			code = "|::|:";
		} else if (digit == 9) {
			code = "|:|::";
		} else if (digit == 0) {
			code = "||:::";
		} else {
			code = "Null";		//backup, incase passed value isnt between [0,9]
		}
		return code;
	}

	/**
	 * Converts a String containing a bar code to the single digit the String
	 * represents.
	 * 
	 * @param barCode
	 *            is a bar code representing a single digit
	 * @return a single digit, that is an integer in the range [0,9], or -1 if
	 *         the barCode is invalid
	 */
	public static int barToDigit(String barCode) { 
		int digit = 0;		//checks sequence one by one, probably not the most efficient, but it works.
		if (barCode.equals("||:::")) {
			digit = 0;
		} else if (barCode.equals(":::||")) {
			digit = 1;
		} else if (barCode.equals("::|:|")) {
			digit = 2;
		} else if (barCode.equals("::||:")) {
			digit = 3;
		} else if (barCode.equals(":|::|")) {
			digit = 4;
		} else if (barCode.equals(":|:|:")) {
			digit = 5;
		} else if (barCode.equals(":||::")) {
			digit = 6;
		} else if (barCode.equals("|:::|")) {
			digit = 7;
		} else if (barCode.equals("|::|:")) {
			digit = 8;
		} else if (barCode.equals("|:|::")) {
			digit = 9;
		} else {
			digit = -1; //if the 5 character sequence doesnt match with a value, reports an error
		}
		return digit;
	}
}