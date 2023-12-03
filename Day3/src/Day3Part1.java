import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Day3Part1 {

    private static final int SIZE = 140;
    private static Character[][] inputMap = new Character[SIZE][SIZE];
    private static String[] lineByLine = new String[SIZE];

    public static void main(String[] args) {
        try(Scanner input = new Scanner(new File("input.txt"))) {

            int totalIDS = 0;

            loadMap(input);

            for(int z = 0; z < SIZE; z++) {
                int startIndex = 0;

                while(startIndex < SIZE) {
                    int[] currNumArray = getNextNum(lineByLine[z], startIndex);
                    if(currNumArray[0] == -1) break;

                    int currNum = Integer.parseInt(lineByLine[z].substring(currNumArray[0], currNumArray[1]));
                    for(int i = currNumArray[0]; i <= currNumArray[1]; i++) {
                        if(circleScan(z, i)) {
                            totalIDS += currNum;
                            break;
                        }
                    }
                    startIndex = currNumArray[1];
                }
            }

            System.out.println(totalIDS);


        } catch (FileNotFoundException e) {
            System.out.println("File not found. " + e.getMessage());
            System.exit(1);
        }
    }

    // Loads input into String[] and Character[][] arrays.
    private static void loadMap(Scanner input) {
        for(int i = 0; i < SIZE; i++) {
            String line = input.nextLine();
            lineByLine[i] = line;

            for(int j = 0; j < SIZE; j++) {
                inputMap[i][j] = line.charAt(j);
            }
        }
    }

    // Checks a coordinate in a 3x3 circular pattern to see whether it passes the checks
    private static boolean circleScan(int row, int column) {

        int topRow = Math.max(0, row - 1);
        int bottomRow = Math.min(SIZE - 1, row + 1);
        int leftColumn = Math.max(0, column -1);
        int rightColumn = Math.min(SIZE - 1, column + 1);

        // top to bottom
        for(int i = topRow; i <= bottomRow; i++) {
            // left to right
            for(int j = leftColumn; j <= rightColumn; j++) {
                char currChar = inputMap[i][j];

                if(!Character.isDigit(currChar) && currChar != '.') return true;
            }
        }

        return false;
    }

    //finds the next sequence of digits in the given line and returns their start and end indices
    private static int[] getNextNum(String line, int startIndex) {

        int startPointer = startIndex;
        int endPointer;

        while(startPointer < SIZE) {
            if(Character.isDigit(line.charAt(startPointer))) break;
            startPointer++;
        }

        endPointer = startPointer;

        while(endPointer < SIZE) {
            if(!Character.isDigit(line.charAt(endPointer))) break;
            endPointer++;
        }

        if(startPointer == endPointer) return new int[] {-1, -1};

        return new int[] {startPointer, endPointer};
    }

}