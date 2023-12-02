import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Day2MainPart1 {

    private static final Map<String, Integer> AVAILABLE_COLOR_BLOCKS = Map.of(
            "red", 12,
            "green", 13,
            "blue", 14
    );

    public static void main(String[] args) {
        try (Scanner input = new Scanner(new File("Day2/input.txt"))) {

            int totalGameID = 0;

            while(input.hasNextLine()) {
                totalGameID += isValidGame(input.nextLine());
            }

            System.out.println(totalGameID);

        } catch (FileNotFoundException e) {
            System.out.println("File not found. " + e.getMessage());
            System.exit(1);
        }
    }

    private static int isValidGame(String line) {
        String[] list = line.split(": ");

        int gameID = Integer.parseInt(list[0].substring(5));
        String[] differentRounds = list[1].split("; ");

        for (String currRound : differentRounds) {
            String[] individualGames = currRound.split(", ");

            for (String game : individualGames) {
                String[] parts = game.split(" ");

                if (parts.length != 2) {
                    return 0;
                }

                String color = parts[1];
                int num = Integer.parseInt(parts[0]);

                if ((color.equals("blue") && num > AVAILABLE_COLOR_BLOCKS.get("blue"))
                        || (color.equals("red") && num > AVAILABLE_COLOR_BLOCKS.get("red"))
                        || (color.equals("green") && num > AVAILABLE_COLOR_BLOCKS.get("green"))) {
                    return 0;
                }
            }
        }

        return gameID;
    }

}