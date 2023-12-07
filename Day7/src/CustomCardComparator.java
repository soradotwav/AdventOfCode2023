import java.util.Comparator;

public class CustomCardComparator implements Comparator<String[]> {

    @Override
    public int compare(String[] o1, String[] o2) {

        for(int i = 0; i < o1[0].length(); i++) {
            if(getVal(o1[0].charAt(i)) > getVal(o2[0].charAt(i))) return 1;
            else if (getVal(o1[0].charAt(i)) < getVal(o2[0].charAt(i))) return -1;
        }

        return 0;
    }

    public static int getVal(char c) {
        if(Character.isDigit(c)) return Character.getNumericValue(c);
        else {
            switch(c) {
                case 'T': return 10;
                case 'J': return 11;
                case 'Q': return 12;
                case 'K': return 13;
                case 'A': return 14;
            }
        }
        return -1;
    }
}
