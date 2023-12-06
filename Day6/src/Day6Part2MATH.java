import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

public class Day6Part2MATH {

    private static long time;
    private static long recordDistance;

    public static void main(String[] args) {
        try (Scanner input = new Scanner(new File("Day6/input.txt"))) {

            setVariables(input);
            int[] solution = mathMagic(time, recordDistance);
            System.out.println(solution[0] - solution[1]);

        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
            System.exit(1);
        }
    }

    /**
     * Calculates the range of values for which the race record can be beaten.
     * It uses a mathematical formula to determine the lower and upper bounds.
     *
     * @param time The total time in milliseconds for the race.
     * @param record The record distance in millimeters for the race.
     * @return An array of two integers, where the first element is the upper bound and the second element is the lower bound.
     */
    private static int[] mathMagic(long time, long record) {
        int x1 = (int)(time + Math.sqrt(time * time - 4 * record)) / 2;
        int x2 = (int)(time - Math.sqrt(time * time - 4 * record)) / 2;

        return new int[] {x1, x2};
    }

    /**
     * Reads the concatenated race data from the input and sets the time and recordDistance.
     * It expects two lines of input, first for concatenated time and second for concatenated record distances.
     *
     * @param input The scanner to read the file input.
     */
    private static void setVariables(Scanner input) {

        String timeInput = input.nextLine().substring(5);
        String distanceInput = input.nextLine().substring(9);

        time = Long.parseLong(timeInput.trim().replaceAll("\\s+", ""));
        recordDistance = Long.parseLong(distanceInput.trim().replaceAll("\\s+", ""));
    }
}