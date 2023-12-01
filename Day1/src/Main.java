import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        try(Scanner input = new Scanner(new File("input.txt"))) {

            int sum = 0;

            while(input.hasNextLine()) {
                sum += computeCalibrationNum(input.nextLine());
            }
            System.out.println(sum);

        } catch (FileNotFoundException e) {
            System.out.println("File not found. " + e.getMessage());
            System.exit(1);
        }
    }

    private static int computeCalibrationNum(String line) {
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < line.length(); i++) {
            if (Character.isDigit(line.charAt(i))) {
                sb.append(line.charAt(i));
                break;
            }
        }

        for(int j = line.length() - 1; j >= 0; j--) {
            if (Character.isDigit(line.charAt(j))) {
                sb.append(line.charAt(j));
                break;
            }
        }

        return Integer.parseInt(sb.toString());
    }
}