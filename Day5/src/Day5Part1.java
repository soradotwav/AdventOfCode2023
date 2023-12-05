import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

// destination-range-start || source-range-start || range
public class Day5Part1 {
    public static void main(String[] args) {
        try (Scanner input = new Scanner(new File("input.txt"))) {

            ArrayList<Long> seeds = new ArrayList<>();
            long smallestNum = Long.MAX_VALUE;

            for(String num : input.nextLine().substring(7).split(" ")) seeds.add(Long.parseLong(num));

            input.nextLine();

            ArrayList<long[]> seedSoil = new ArrayList<>(); populate(input, seedSoil);
            ArrayList<long[]> soilFertilizer = new ArrayList<>(); populate(input, soilFertilizer);
            ArrayList<long[]> fertilizerWater = new ArrayList<>(); populate(input, fertilizerWater);
            ArrayList<long[]> waterLight = new ArrayList<>(); populate(input, waterLight);
            ArrayList<long[]> lightTemperature = new ArrayList<>(); populate(input, lightTemperature);
            ArrayList<long[]> temperatureHumidity = new ArrayList<>(); populate(input, temperatureHumidity);
            ArrayList<long[]> humidityLocation = new ArrayList<>(); populate(input, humidityLocation);

            for(long seed : seeds) {
                long tempNum = (validateAndReturn(
                        validateAndReturn(
                                validateAndReturn(
                                        validateAndReturn(
                                                validateAndReturn(
                                                        validateAndReturn(
                                                                validateAndReturn(seed, seedSoil), soilFertilizer
                                                        ), fertilizerWater
                                                ), waterLight
                                        ), lightTemperature
                                ), temperatureHumidity
                        ), humidityLocation
                ));

                if(tempNum < smallestNum) smallestNum = tempNum;
            }

            System.out.println(smallestNum);


        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
            System.exit(1);
        }
    }

    /**
     * Populates a given list with number mappings from the almanac. Each mapping is a range conversion
     * from one category to another (e.g., seed to soil, soil to fertilizer).
     *
     * @param input The Scanner to read the almanac data.
     * @param list  The list to populate with the number mappings.
     */
    private static void populate(Scanner input, ArrayList<long[]> list) {
        input.nextLine();

        while(input.hasNextLine()) {
            String line = input.nextLine();
            if(line.isEmpty()) break;

            long[] temp = Arrays.stream(line.split(" ")).mapToLong(Long::parseLong).toArray();
            list.add(temp);
        }
    }

    /**
     * Validates a number against a list of mappings and returns the corresponding number in the next category.
     * If the number does not match any source range in the list, it returns the same number.
     *
     * @param num  The number to be validated and converted.
     * @param list The list of mappings to validate against.
     * @return The converted number or the same number if no match is found.
     */
    private static long validateAndReturn(long num, ArrayList<long[]> list) {

        for(long[] curr : list) {
            long destRangeStart = curr[0];
            long sourceRangeStart = curr[1];
            long range = curr[2];

            if (num >= sourceRangeStart && num < sourceRangeStart + range) {
                num = destRangeStart + (num - sourceRangeStart);
                return num;
            }
        }
        return num;
    }
}