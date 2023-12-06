import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

public class Day6Part1 {

    private static int[] time;
    private static int[] recordDistance;

    /**
     * The main method. It reads the race data from "Day6/input.txt" and calculates the total number of ways
     * to beat the record in each race by multiplying the number of winning strategies for each race.
     *
     * @param args Command line arguments (not used).
     */
    public static void main(String[] args) {
        try (Scanner input = new Scanner(new File("Day6/input.txt"))) {

            setArrays(input);

            int total = 1;
            boolean switchFlag = false;

            for(int i = 0; i < time.length; i++) {
                int count = 0;
                for(int j = 0; j < time[i]; j++) {
                    if(beatsRecord(j, time[i], recordDistance[i])) {
                        count++;
                        switchFlag = true;
                    }
                    else switchFlag = false;

                    if(!switchFlag && count > 0) break;
                }
                if(count > 0) total *= count;
            }

            System.out.println(total);

        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
            System.exit(1);
        }
    }

    /**
     * Determines if the given strategy beats the race record.
     *
     * @param hold The time in milliseconds to hold the button at the start of the race.
     * @param time The total time in milliseconds for the race.
     * @param record The record distance in millimeters for the race.
     * @return true if the strategy beats the record; false otherwise.
     */
    private static boolean beatsRecord(int hold, int time, int record) {
        return (time - hold) * hold > record;
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