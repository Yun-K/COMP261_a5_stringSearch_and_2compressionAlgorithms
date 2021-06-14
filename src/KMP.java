import java.util.*;
import java.io.*;

public class KMP {
    // static String[] args = new String[] { "./data/war_and_peace.txt", "astronomy" };

    public static void main(String[] args) {
        if (args.length != 2) {
            System.out.println(
                    "Please call this program with " + "two arguments which is the input file name "
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
     * This should return the starting index of the first substring match if it exists, or -1
     * if it doesn't.
     */
    public static int search(String text, String pattern) {
        // TODO
        int[] m_jumpTable = getJumpTable(pattern);// the jump table
        // assert m_jumpTable.length == pattern.length();

        int k = 0;// start of current match in text t
        int i = 0;// pos of current char in pattern
        while (k + i < text.length()) {
            // match at i
            if (pattern.charAt(i) == text.charAt(i + k)) {
                // check if i reaches the end of the searching pattern
                if (++i == pattern.length()) {
                    return k;// find pattern, return it
                }
            }
            // mismatch, no self overlap
            else if (m_jumpTable[i] == -1) {// i is 0 for now
                k = k + i + 1;
                i = 0;
            }
            // mismatch, with self overlap
            else {
                k = k + i - m_jumpTable[i];// match pos, jump forward
                i = m_jumpTable[i];// move jump table index as well
            }
        }

        return -1;// failed to find pattern in texxt, return -1
    }

    public static int[] getJumpTable(String pattern) {
        int pattern_size = pattern.length();
        int[] M_jumpTableToReturn = new int[pattern_size];
        M_jumpTableToReturn[0] = -1;
        M_jumpTableToReturn[1] = 0;
        int mPos = 2;// pos in table
        int j = 0;// pos in prefix
        while (mPos < pattern_size) {
            // substrings ...pos-1 and 0..j match
            if (pattern.charAt(mPos - 1) == pattern.charAt(j)) {
                M_jumpTableToReturn[mPos++] = ++j;

            } else if (j > 0) {// mismacth, restart the prefix
                j = M_jumpTableToReturn[j];

            } else {// j=0 //we have run out of candidate prefixes
                M_jumpTableToReturn[mPos++] = 0;
            }
        }

        return M_jumpTableToReturn;
    }
}
