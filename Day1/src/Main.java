import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * This class provides functionality to compute a calibration number from a file input.
 * Each line of the file is processed to generate a calibration number.
 */
public class Main {

    /**
     * A map to associate strings representing numbers to their numeric values.
     */
    private static final Map<String, Integer> STRING_NUMBER_MAP = new HashMap<>() {{
        put("one", 1);
        put("two", 2);
        put("three", 3);
        put("four", 4);
        put("five", 5);
        put("six", 6);
        put("seven", 7);
        put("eight", 8);
        put("nine", 9);
    }};

    /**
     * The main method to start the program. It reads a file and processes each line to compute a sum of calibration numbers.
     *
     * @param args Command line arguments (not used).
     */
    public static void main(String[] args) {
        try(Scanner input = new Scanner(new File("Day1/input.txt"))) {
            int sum = 0;

            while(input.hasNextLine()) {
                sum += computeCalibrationNum(input.nextLine());
            }
            System.out.println(sum);

        } catch (FileNotFoundException e) {
            System.out.println("File not found. " + e.getMessage());
            System.exit(1);
        }
    }

    /**
     * Computes the calibration number from a given line of text.
     * The method extracts digits or number words from the start and end of the line to form a number.
     *
     * @param line The line of text to process.
     * @return The computed calibration number.
     */
    private static int computeCalibrationNum(String line) {
        StringBuilder sb = new StringBuilder();

        for(int i = 0; i < line.length(); i++) {
            if (Character.isDigit(line.charAt(i))) {
                sb.append(line.charAt(i));
                break;
            } else if (getProperNumber(line.substring(i)) > 0) {
                sb.append(getProperNumber(line.substring(i)));
                break;
            }
        }

        for(int j = line.length() - 1; j >= 0; j--) {
            if (Character.isDigit(line.charAt(j))) {
                sb.append(line.charAt(j));
                break;
            } else if (getProperNumber(line.substring(j)) > 0) {
                sb.append(getProperNumber(line.substring(j)));
                break;
            }
        }
        return Integer.parseInt(sb.toString());
    }

    /**
     * Converts a string representing a number into its numeric value, based on the predefined map.
     * If the string does not represent a number, it returns -1.
     *
     * @param string The string to be converted.
     * @return The numeric value of the string if it represents a number, otherwise -1.
     */
    public static int getProperNumber(String string) {
        for(String number : STRING_NUMBER_MAP.keySet()) {
            if (string.startsWith(number)) {
                return STRING_NUMBER_MAP.get(number);
            }
        }
        return -1;
    }
}