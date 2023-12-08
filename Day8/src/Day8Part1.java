import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Day8Part1 {

    private static Map<String, String[]> map = new HashMap<>();

    public static void main(String[] args) {
        try(Scanner scanner = new Scanner(new File("Day8/input.txt"))) {

            String directions = scanner.nextLine();
            setup(scanner);
            int steps = 0;
            int index = 0;
            String start = "AAA";
            String curr = start;


            while(true) {
                if(directions.charAt(index++) == 'L') curr = map.get(curr)[0];
                else curr = map.get(curr)[1];

                steps++;

                if(curr.equals("ZZZ")) break;
                if(index == directions.length()) index = 0;
            }
            System.out.println(steps);

        } catch(FileNotFoundException e) {
            System.out.println(e.getMessage());
            System.exit(1);
        }
    }

    private static void setup(Scanner scanner) {
        scanner.nextLine();

        while(scanner.hasNextLine()) {
            String curr = scanner.nextLine();

            String[] temp = curr.split(" = ");
            map.put(temp[0], temp[1].replaceAll("[^\\w\\s_]", "").split(" "));
        }
    }
}