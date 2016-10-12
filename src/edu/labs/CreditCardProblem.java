/*this java class takes a 8 digit credit card number and checks it.
 * if the number tested comes out true, it is a valid number
 * if the number is false, it has an invalid check digit. The correct check digit will be calculated and displayed.
 * @author Alexander Berthon Alexjberthon@hotmail.com
 * @author Matthew Quevas 	 matthewcuevas@live.com
*/
package edu.labs;
import java.util.Scanner;
public class CreditCardProblem {

	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		System.out.println("Please enter the last 8 digits of the credit-card you wish to check");
		String creditCardNumber = input.next();
		input.close();
		System.out.print(creditCardChecker(creditCardNumber));
	}

	public static boolean creditCardChecker(String creditCardNumber) {
		int checkDigit = 0;
		int length = creditCardNumber.length();
		int sum = 0;
		int number;
		int secondNumber;
		String calculation;
		boolean check = true;
		for (int i = 0; i < length; i++) {
			number = creditCardNumber.codePointAt(i); //these two assign statements convert a string number into a value 
			number = Character.getNumericValue(number); //that we can use for calculations
			if (i == 1 || i == 3 || i == 5 || i == 7) { //if statement for the first "equation"
				sum = sum + number;
				checkDigit = number;
			} else if (number < 5) { //equation 2 doubles the number, but as long as it is less than 5 it will still be single digit and not cause any problems
				number = number * 2;
				sum = sum + number;
			} else {					//if the number for the second equation is greater than 5 it will be a 2 digit number when doubled 
				number = number * 2;	// so we have to convert it in order to add each of the digits individually.
				calculation = Integer.toString(number);
				for (int j = 0; j <= 1; j++) {			//takes two digit string and breaks it up, so we can add 1+2 rather than +12 to the sum for example
					secondNumber = calculation.codePointAt(j);
					secondNumber = Character.getNumericValue(secondNumber);
					sum = sum + secondNumber;
				}
			}
		}
		if (String.valueOf(sum).contains("0")) { 
			check = true;
		} else {
			check = false;
			checkDigit = ((sum - checkDigit) % 10 - 10) * -1; //we calculated this formula to find the correct check digit.
			System.out.print("The check digit should be " + checkDigit + " : ");
		}
		return check; //returns boolean 
	}
}
