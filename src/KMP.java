import java.util.*;
import java.io.*;

public class KMP {
    static String[] args = new String[] { "data/war_and_peace.txt", "astronomy" };

    public static void main(String[] arg) {
        if (args.length != 2) {
            System.out.println("Please call this program with " + "two arguments which is the input file name "
                    + "and the string to search.");
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
     * Perform KMP substring search on the given text with the given pattern.
     * 
     * This should return the starting index of the first substring match if it
     * exists, or -1 if it doesn't.
     */
    public static int search(String text, String pattern) {
        // TODO
        int[] jumpTable = getJumpTable(pattern);

        return -1;
    }

    public static int[] getJumpTable(String pattern) {
        int size = pattern.length();
        int[] jumpTableToReturn = new int[size];
        jumpTableToReturn[0] = -1;
        jumpTableToReturn[1] = 0;
        int pos = 2, j = 0;
        while (pos < size) {
            // substrings ...pos-1 and 0..j match
            if (pattern.charAt(pos - 1) == pattern.charAt(j)) {
                jumpTableToReturn[pos++] = ++j;
            } else if (j > 0) {// mismacth, restart the prefix
                j = jumpTableToReturn[j];
            } else {// j=0 //we have run out of candidate prefixes
                jumpTableToReturn[pos++] = 0;
            }
        }

        return jumpTableToReturn;
    }
}
