import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

public class Day9Part1 {
    public static void main(String[] args) {
        try(Scanner scanner = new Scanner(new File("input.txt"))) {
            int sum = 0;

            while(scanner.hasNextLine()) {
                int[] curr = Arrays.stream(scanner.nextLine().split("\\s+")).mapToInt(Integer::parseInt).toArray();
                sum += getNextInSequence(curr);
            }

            System.out.println(sum);

        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
            System.exit(1);
        }
    }

    /**
     * Recursively calculates the next number in a sequence based on the input array.
     *
     * @param input The input array representing a sequence.
     * @return The next number in the sequence.
     */
    private static int getNextInSequence(int[] input) {
        int count = 0;
        for(int i : input) if(i == 0) count++;
        if(count == input.length) return 0;

        int[] dif = new int[input.length - 1];

        for(int i = 0; i < dif.length; i++) {
            dif[i] = input[i + 1] - input[i];
        }
        return getNextInSequence(dif) + input[input.length - 1];
    }
}