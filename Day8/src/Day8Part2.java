import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Day8Part2 {

    private static Map<String, String[]> map = new HashMap<>();
    private static Set<String> endsWithA = new HashSet<>();

    private static Map<String, Integer> stepsToZ = new HashMap<>();

    /**
     * The main method reads input from a file, initializes data structures,
     * and executes the path traversal process. Finally, it computes and
     * prints the LCM of steps taken to reach nodes ending with 'Z'.
     *
     * @param args Command line arguments (not used).
     */
    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(new File("Day8/input.txt"))) {
            String directions = scanner.nextLine();
            scanner.nextLine();
            setup(scanner);

            int steps = 0;
            Set<String> currentNodes = new HashSet<>(endsWithA);

            while (stepsToZ.size() < endsWithA.size()) {
                Set<String> nextNodes = new HashSet<>();

                for (String node : currentNodes) {
                    String nextNode = takeAStep(node, directions.charAt(steps % directions.length()));

                    if (nextNode.endsWith("Z") && !stepsToZ.containsKey(node)) {
                        stepsToZ.put(node, steps + 1);
                    }

                    nextNodes.add(nextNode);
                }

                currentNodes = nextNodes;
                steps++;
            }

            Integer[] stepsArray = stepsToZ.values().toArray(new Integer[0]);
            long lcm = findLCM(stepsArray, stepsArray.length);
            System.out.println(lcm);

        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
            System.exit(1);
        }
    }

    /**
     * Sets up the initial state by parsing the input and populating the maps
     * with nodes and their directions, and initializing the set of nodes
     * that end with 'A'.
     *
     * @param scanner Scanner object to read input.
     */
    private static void setup(Scanner scanner) {
        while (scanner.hasNextLine()) {
            String curr = scanner.nextLine();
            String[] temp = curr.split(" = ");
            map.put(temp[0], temp[1].replaceAll("[^\\w\\s_]", "").split(" "));
            if (temp[0].endsWith("A")) endsWithA.add(temp[0]);
        }
    }

    /**
     * Determines the next node to move to based on the current node and
     * the provided direction.
     *
     * @param key       The current node.
     * @param direction The direction to move in (L/R).
     * @return The next node after taking a step in the given direction.
     */
    public static String takeAStep(String key, char direction) {
        return map.get(key)[direction == 'L' ? 0 : 1];
    }

    /**
     * Calculates the least common multiple (LCM) of an array of integers.
     *
     * @param arr The array of integers.
     * @param n   The number of elements in the array.
     * @return The LCM of the elements in the array.
     */
    private static long findLCM(Integer[] arr, int n) {
        long result = arr[0];
        for (int element : arr) {
            result = lcm(result, element);
            if (result == 1) {
                return 1;
            }
        }
        return result;
    }

    /**
     * Computes the greatest common divisor (GCD) of two long integers.
     *
     * @param a The first integer.
     * @param b The second integer.
     * @return The GCD of a and b.
     */
    static long gcd(long a, long b) {
        if (a == 0)
            return b;
        return gcd(b % a, a);
    }

    /**
     * Calculates the least common multiple (LCM) of two long integers.
     *
     * @param a The first integer.
     * @param b The second integer.
     * @return The LCM of a and b.
     */
    static long lcm(long a, long b) {
        return (a / gcd(a, b)) * b;
    }
}
