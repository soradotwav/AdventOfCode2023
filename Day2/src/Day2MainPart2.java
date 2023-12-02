import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Day2MainPart2 {

    public static void main(String[] args) {
        try (Scanner input = new Scanner(new File("Day2/input.txt"))) {
            int totalPowerSum = 0;

            while (input.hasNextLine()) {
                totalPowerSum += calculateGamePower(input.nextLine());
            }

            System.out.println(totalPowerSum);

        } catch (FileNotFoundException e) {
            System.out.println("File not found. " + e.getMessage());
            System.exit(1);
        }
    }

    private static int calculateGamePower(String line) {
        String[] parts = line.split(": ");

        String[] differentRounds = parts[1].split("; ");
        Map<String, Integer> minCubes = new HashMap<>(Map.of(
                "red", 0,
                "green", 0,
                "blue", 0
        ));

        for (String round : differentRounds) {
            Map<String, Integer> roundCubes = new HashMap<>();
            String[] games = round.split(", ");

            for (String game : games) {
                String[] gameParts = game.split(" ");
                int num = Integer.parseInt(gameParts[0]);
                String color = gameParts[1];

                roundCubes.put(color, roundCubes.getOrDefault(color, 0) + num);
            }

            for (String color : roundCubes.keySet()) {
                if (roundCubes.get(color) > minCubes.get(color)) {
                    minCubes.put(color, roundCubes.get(color));
                }
            }
        }

        return minCubes.get("red") * minCubes.get("green") * minCubes.get("blue");
    }
}