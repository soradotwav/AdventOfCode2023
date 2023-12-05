import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Day4Part2 {

    private static Set<Integer> currCard;
    private static Set<Integer> currWinningNums;
    private static Map<Integer, Integer> duplicatesMap = new HashMap<>();

    /**
     * Main method to read scratchcard data from a file and calculate the total number of scratchcards.
     * It iterates through each scratchcard, determines the number of winning matches, and computes
     * the number of additional scratchcards won. The process accounts for both original and copied
     * scratchcards.
     *
     * @param args Command line arguments (not used).
     */
    public static void main(String[] args) {
        try(Scanner input = new Scanner(new File("Day4/input.txt"))) {

            int scratchcardCount = 0;

            // Iterate through all cards
            for(int i = 1; i < 205; i++) {
                loadLine(input);
                int winningSize = currWinningNums.size();
                currWinningNums.removeAll(currCard);
                int winningAmount = winningSize - currWinningNums.size();

                int currDuplicates = duplicatesMap.getOrDefault(i, 1);
                scratchcardCount += currDuplicates;

                for(int j = i + 1; j < i + 1 + winningAmount; j++) {
                    duplicatesMap.put(j, duplicatesMap.getOrDefault(j, 1) + currDuplicates);
                }

            }

            System.out.println(scratchcardCount);

        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
            System.exit(1);
        }
    }

    /**
     * Loads and processes a single line of scratchcard data from the input file.
     * It parses the line to extract the numbers of the current scratchcard and its winning numbers,
     * updating the static variables currCard and currWinningNums.
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