import java.util.InputMismatchException;
import java.util.Scanner;

public class SafeInput {

    /**
     * Prompts user for an int in the inclusive range [low, high].
     * Keeps prompting until valid input is entered.
     */
    public static int getRangedInt(Scanner console, String prompt, int low, int high) {
        int value = 0;
        boolean valid = false;
        while (!valid) {
            System.out.print(prompt);
            try {
                value = Integer.parseInt(console.nextLine().trim());
                if (value < low || value > high) {
                    System.out.println("Please enter a number between " + low + " and " + high + ".");
                } else {
                    valid = true;
                }
            } catch (NumberFormatException ex) {
                System.out.println("Invalid integer. Try again.");
            }
        }
        return value;
    }

    /**
     * Prompt user for a yes/no confirmation. Accepts y, yes, n, no (case-insensitive).
     * Returns true for yes, false for no.
     */
    public static boolean getYNConfirm(Scanner console, String prompt) {
        while (true) {
            System.out.print(prompt);
            String line = console.nextLine().trim().toLowerCase();
            if (line.equals("y") || line.equals("yes")) return true;
            if (line.equals("n") || line.equals("no")) return false;
            System.out.println("Please enter Y or N.");
        }
    }

    // (Optional) you can add other SafeInput helpers here if you like.
}
