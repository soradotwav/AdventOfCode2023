import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

public class Day6Part1MATH {

    private static int[] time;
    private static int[] recordDistance;

    public static void main(String[] args) {
        try (Scanner input = new Scanner(new File("Day6/input.txt"))) {

            setArrays(input);
            int total = 1;

            for(int i = 0; i < time.length; i++) {

                int[] currSolutions = mathMagic(time[i], recordDistance[i]);

                total *= currSolutions[0] - currSolutions[1];
            }

            System.out.println(total);

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
    private static int[] mathMagic(int time, int record) {
        int x1 = (int)(time + Math.sqrt(time * time - 4 * record)) / 2;
        int x2 = (int)(time - Math.sqrt(time * time - 4 * record)) / 2;

        return new int[] {x1, x2};
    }

    /**
     * Reads the race data from the input and sets the time and recordDistance arrays.
     * It expects two lines of input, first for time and second for record distances.
     *
     * @param input The scanner to read the file input.
     */
    private static void setArrays(Scanner input) {

        String timeInput = input.nextLine().substring(5);
        String distanceInput = input.nextLine().substring(9);

        time = Arrays.stream(timeInput.split("\\s+"))
                .filter(s -> !s.isEmpty())
                .mapToInt(Integer::parseInt)
                .toArray();

        recordDistance = Arrays.stream(distanceInput.split("\\s+"))
                .filter(s -> !s.isEmpty())
                .mapToInt(Integer::parseInt)
                .toArray();
    }
}