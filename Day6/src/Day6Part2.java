import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Day6Part2 {

    private static long time;
    private static long recordDistance;

    /**
     * The main method. It reads the concatenated race data from "Day6/input.txt" and calculates the number of ways
     * to beat the record in this single race.
     *
     * @param args Command line arguments (not used).
     */
    public static void main(String[] args) {
        try (Scanner input = new Scanner(new File("Day6/input.txt"))) {

            setVariables(input);

            boolean switchFlag = false;
            int count = 0;

            for(int i = 0; i < time; i++) {
                if(beatsRecord(i, time, recordDistance)) {
                    count++;
                    switchFlag = true;
                }
                else switchFlag = false;

                if(!switchFlag && count > 0) break;
            }

            System.out.println(count);

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
    private static boolean beatsRecord(int hold, long time, long record) {
        return (time - hold) * hold > record;
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