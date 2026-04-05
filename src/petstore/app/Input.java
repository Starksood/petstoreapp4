package petstore.app;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

/**
 * Utility class for handling user input from the console.
 * Provides methods for reading lines, strings, integers, and formatted dates.
 * 
 * @author Sanyam Sood
 * @since March 29th, 2026
 */
public class Input {

    public static Scanner sc = new Scanner(System.in);

    /**
     * Reads a full line of text from the console.
     * 
     * @param prompt The prompt to display to the user.
     * @return The text entered by the user.
     */
    public static String getLine(String prompt) {

        System.out.print(prompt);
        return Input.sc.nextLine();

    } // end of getLine

    /**
     * Reads a non-empty string from the console.
     * Trims leading and trailing whitespace.
     * 
     * @param prompt The prompt to display to the user.
     * @return A trimmed, non-empty string entered by the user.
     */
    public static String getString(String prompt) {

        String userInput;

        System.out.print(prompt);

        while (true) {
            userInput = Input.sc.nextLine();

            userInput = userInput.trim();

            if (userInput.isEmpty()) {
                System.out.print("Invalid input! Please enter a value: ");
            } else {
                break;
            }
        }

        return userInput;

    } // end of getString

    /**
     * Reads a valid integer from the console.
     * 
     * @param prompt The prompt to display to the user.
     * @return The integer entered by the user.
     */
    public static int getInt(String prompt) {
        int userInput;

        System.out.print(prompt);

        // if the data in the buffer is a valid integer
        // then break out of the validation loop
        while (!Input.sc.hasNextInt()) {

            System.out.print("Invalid input! Please enter a number: ");
            Input.sc.next(); // clear the data in the input buffer

        } // end of while

        userInput = Input.sc.nextInt();
        Input.sc.nextLine(); // consume newline left-over

        return userInput;

    } // end of getInt

    /**
     * Reads a valid integer within a specified range from the console.
     * 
     * @param prompt The prompt to display to the user.
     * @param low    The minimum acceptable value (inclusive).
     * @param high   The maximum acceptable value (inclusive).
     * @return The validated integer within the specified range.
     */
    public static int getIntRange(String prompt, int low, int high) {
        int userInput;

        System.out.print(prompt);

        while (true) {
            if (Input.sc.hasNextInt()) { // if the data in the buffer is a valid integer
                userInput = Input.sc.nextInt(); // then store the integer in userInput

                // if the userInput is within the valid range
                // then break out of the validation loop
                if (userInput >= low && userInput <= high) {
                    break;
                } // end of if

            } else {
                Input.sc.next(); // clear the data in the input buffer
            } // end of if-else

            System.out.printf("Invalid input! Please enter a number between (%d - %d): ", low, high);

        } // end of while

        Input.sc.nextLine(); // consume newline left-over

        return userInput;

    } // end of getIntRange

    /**
     * Reads a valid date string from the console and validates its format.
     * 
     * @param prompt The prompt to display to the user.
     * @return A valid date string in MM-DD-YYYY format.
     */
    public static String getDate(String prompt) {
        String userInput;

        System.out.print(prompt);

        while (true) {

            userInput = Input.sc.nextLine();

            try {
                LocalDate.parse(userInput, DateTimeFormatter.ofPattern("M-d-yyyy"));
                break;
            } catch (Exception e) {
                System.out.print("Invalid input! Please enter a valid date (MM-D-YYYY): ");
            }
        }

        return userInput;

    } // end of getDate

} // end of library.app.Input class
