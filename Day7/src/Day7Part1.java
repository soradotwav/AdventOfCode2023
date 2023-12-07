import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Day7Part1 {
    public enum TYPES_OF_HANDS {
        HIGH_CARD,
        ONE_PAIR,
        TWO_PAIR,
        THREE_OF_KIND,
        FULL_HOUSE,
        FOUR_OF_KIND,
        FIVE_OF_KIND
    }

    public static void main(String[] args) {
        try(Scanner input = new Scanner(new File("input.txt"))) {

            Map<TYPES_OF_HANDS, ArrayList<String[]>> mapOfTypes = new TreeMap<>();
            CustomCardComparator comparator = new CustomCardComparator();

            // Sorting everything into map
            while(input.hasNextLine()) {
                String[] temp = input.nextLine().split("\\s+");
                TYPES_OF_HANDS curr = getType(temp[0]);
                if(mapOfTypes.containsKey(curr)) mapOfTypes.get(curr).add(temp);
                else mapOfTypes.put(curr, new ArrayList<>(){{add(temp);}});
            }

            for(ArrayList<String[]> list :mapOfTypes.values()) {
                list.sort(comparator);
            }

            int rank = 1;
            long total = 0;

            for(ArrayList<String[]> list : mapOfTypes.values()) {
                for(String[] arr : list) {
                    total += Long.parseLong(arr[1]) * rank++;
                }
            }

            System.out.println(total);

        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
            System.exit(1);
        }
    }

    public static TYPES_OF_HANDS getType(String hand) {
        Map<Character, Integer> handMap = new HashMap<>();

        for(char c : hand.toCharArray()) {
            handMap.put(c, handMap.getOrDefault(c, 0) + 1);
        }

        if(handMap.keySet().size() == 1) return TYPES_OF_HANDS.FIVE_OF_KIND;
        else if(handMap.containsValue(4)) return TYPES_OF_HANDS.FOUR_OF_KIND;
        else if(handMap.containsValue(3) && handMap.containsValue(2)) return TYPES_OF_HANDS.FULL_HOUSE;
        else if(handMap.containsValue(3) && handMap.containsValue(1)) return TYPES_OF_HANDS.THREE_OF_KIND;
        else if (handMap.values().stream().filter(value -> value == 2).count() == 2) return TYPES_OF_HANDS.TWO_PAIR;
        else if (handMap.containsValue(2)) return TYPES_OF_HANDS.ONE_PAIR;
        else return TYPES_OF_HANDS.HIGH_CARD;
    }
}