import java.util.*;
import java.io.*;

public class BM {

    // static String[] args = new String[] { "./data/war_and_peace.txt", "astronomy" };

    public static void main(String[] args) {
        if (args.length != 2) {
            System.out.println("Please call this program with " +
                               "two arguments which is the input file name " +
                               "and the string to search.");
        } else {
            try {
                Scanner s = new Scanner(new File(args[0]));

                // Read the entire file into one String.
                StringBuilder fileText = new StringBuilder();
                while (s.hasNextLine()) {
                    fileText.append(s.nextLine() + "\n");
                }

                System.out.println(search(fileText.toString(), args[1]));
            } catch (FileNotFoundException e) {
                System.out.println("Unable to find file called " + args[0]);
            }
        }
    }

    /**
     * Perform BM substring search on the given text with the given pattern.
     * 
     * This should return the starting index of the first substring match if it exists, or -1
     * if it doesn't.
     */
    public static int search(String pattern, String text) {
        // TODO
        int[] goodTable = new int[pattern.length()],
                badTable = new int[256];
        // assign the bad table
        for (int i = 0; i < pattern.length(); i++) {
            badTable[pattern.charAt(i)] = pattern.length() - i - 1;// from last to start
        }

        // deal with the goodTable
        int last_prefix = pattern.length();
        for (int i = pattern.length(); i > 0; i--) {
            if (isPrefixMatch(pattern, i)) {
                last_prefix = i;
            }
            goodTable[pattern.length() - i] = last_prefix + pattern.length() - i;
        }
        for (int i = 0; i < pattern.length() - 1; i++) {
            int suffLen = 0;
            while (pattern.charAt(i - suffLen) == pattern
                    .charAt(pattern.length() - suffLen - 1)) {
                suffLen++;
            }
            goodTable[suffLen] = pattern.length() - 1 + i + suffLen;
        }

        for (int i = pattern.length() - 1, j = i; i < text.length(); i += Math.max(
                goodTable[pattern.length() - 1 - j],
                badTable[text.charAt(i)])) {// max is the maximum index to shift
            for (j = pattern.length() - 1; pattern.charAt(j) == text.charAt(i); i--, j--) {
                if (j == 0) {
                    return i;
                }
            }
        }

        return -1;
    }

    /**
     * Description: <br/>
     * Method for chekcing if prefix is matched.
     * 
     * @author Yun Zhou
     * @param pattern
     * @param index
     * @return
     */
    private static boolean isPrefixMatch(String pattern, int index) {
        for (int i = 0; i < pattern.length() - index; i++) {
            //
            if (pattern.charAt(i) != pattern.charAt(index)) {
                i = index;// update i
                return false;
            }
        }
        return true;
    }
}
