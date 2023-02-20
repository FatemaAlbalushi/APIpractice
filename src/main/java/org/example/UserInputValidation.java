package org.example;

import java.util.Scanner;

public class UserInputValidation {
    private Scanner userInput = new Scanner(System.in);

    /**
     * Input: None Output: Integer value Description: This method prompts the user
     * to enter an integer value and returns the input as an integer. In case of an
     * invalid input, it prompts the user to enter a valid integer.
     * @return the input as an integer
     */
    public int getUserChoice() {
        Integer choice = null;
        while (choice == null) {
            try {
                choice = Integer.parseInt(userInput.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid input.");
            }
        }
        return choice;
    }

    /**
     * Input: None Output: String value Description: This method prompts the user to
     * enter a string value and returns the input as a string.
     * @return returns the input as a string
     */

    public String getUserChoiceString() {
        String choice = null;
        while (choice == null) {
           // System.out.println("Invalid input. Please enter a valid input.");
            choice = userInput.nextLine();
        }
        return choice;
    }
}
