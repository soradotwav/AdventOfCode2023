import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class Day4Part1 {

    private static Set<Integer> currCard;
    private static Set<Integer> currWinningNums;

    /**
     * The main method reads from a file containing scratchcard data, where each line represents a card with
     * a list of winning numbers and owned numbers. It calculates the total points for all cards based on
     * the matching numbers and their corresponding point values.
     *
     * @param args Command line arguments (not used).
     */
    public static void main(String[] args) {
        try(Scanner input = new Scanner(new File("Day4/input.txt"))) {

            int totalScore = 0;

            while(input.hasNextLine()) {
                loadLine(input);
                int winningSize = currWinningNums.size();
                currWinningNums.removeAll(currCard);
                int winningAmount = winningSize - currWinningNums.size();

                if(winningAmount == 1) totalScore += 1;
                else if (winningAmount > 1) totalScore += 1 * Math.pow(2, winningAmount - 1);
            }

            System.out.println(totalScore);

        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
            System.exit(1);
        }
    }

    /**
     * Processes a single line from the input file, extracting the set of owned numbers and winning numbers of the current card.
     * The method updates the static variables currCard and currWinningNums based on the line read.
     * Each line represents a card and follows the format: owned numbers | winning numbers.
     *
     * @param input Scanner object used to read the input file.
     */
    private static void loadLine(Scanner input) {
        currCard = new HashSet<>();
        currWinningNums = new HashSet<>();

        String line = input.nextLine().split(": ")[1];
        String[] tempArr = line.split("\\| ");

        for(String temp : tempArr[0].split(" ")) {
            if(!temp.isEmpty()) currCard.add(Integer.parseInt(temp));
        }

        for(String temp : tempArr[1].split(" ")) {
            if(!temp.isEmpty()) currWinningNums.add(Integer.parseInt(temp));
        }
    }
}