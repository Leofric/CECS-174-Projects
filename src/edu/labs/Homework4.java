/*This java class takes a 10 digit number and puts it into phone number format
 * @author Alexander Berthon Alexjberthon@hotmail.com
 * @author Matthew Quevas 	 matthewcuevas@live.com
*/
package edu.labs;

import java.util.Scanner;
public class Homework4 {

	public static void main(String[] args) {
		System.out.println("Please enter a 10 digit number!");
		Scanner input = new Scanner(System.in);
		String phoneNumber = input.next(); // waits for user input
		input.close();
		char letter;
		for (int i = 0; i < 10; i++) {
			letter = phoneNumber.charAt(i);
			if (i == 0) {
				System.out.print("(");
			} else if (i == 3) {
				System.out.print(")");
			} else if (i == 6) {
				System.out.print("-");
			}
			System.out.print(letter);
		}
	}
}