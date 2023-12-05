import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Day3MainPart1WRONG {

    private static final int SIZE = 140;

    private static Object[][] schematic = new Object[SIZE][SIZE];
    private static String[] schematicList = new String[SIZE];

    public static void main(String[] args) {
        try(Scanner input = new Scanner(new File("input.txt"))) {

            int totalIDs = 0;
            loadMap(input);
            int startIndex = 0;

            for(int i = 0; i < SIZE; i++) {

                while(startIndex < SIZE) {

                    int[] indices = scanForNextNum(startIndex, schematicList[i]);
                    if(surroundedByChar(i, indices[0]) || surroundedByChar(i, indices[1])) {
                        totalIDs += Integer.parseInt(schematicList[i].substring(indices[0], indices[1]));
                    }
                    startIndex = indices[1] + 1;
                }
                startIndex = 0;
            }

            System.out.println(totalIDs);

        } catch (FileNotFoundException e) {
            System.out.println("File not found. " + e.getMessage());
            System.exit(1);
        }
    }

    private static void loadMap(Scanner input) {

        for(int i = 0; i < SIZE; i++) {
            String tempLine = input.nextLine();
            schematicList[i] = tempLine;
            for(int j = 0; j < SIZE; j++) {
                schematic[i][j] = tempLine.charAt(j);
            }
        }
    }
    private static boolean surroundedByChar(int row, int column) {

        // Define the range of coordinates to scan
        int startRow = Math.max(0, row - 1);
        int endRow = Math.min(SIZE - 1, row + 1);
        int startCol = Math.max(0, column - 1);
        int endCol = Math.min(SIZE - 1, column + 1);

        // Scan the surrounding coordinates
        for (int i = startRow; i <= endRow; i++) {
            for (int j = startCol; j <= endCol; j++) {
                if((schematic[i][j] instanceof Character && (char) schematic[i][j] != '.') && !Character.isDigit((char) schematic[i][j])) {
                    System.out.println(schematic[i][j]);
                    return true;
                }
            }
        }
        return false;
    }

    private static int[] scanForNextNum(int startIndex, String currLine) {
        int startPointer = startIndex;
        int endPointer;

        while (startPointer < SIZE && !Character.isDigit(currLine.charAt(startPointer))) {
            startPointer++;
        }

        endPointer = startPointer;

        while (endPointer < SIZE && Character.isDigit(currLine.charAt(endPointer))) {
            endPointer++;
        }

        return new int[] {startPointer, endPointer};
    }
}