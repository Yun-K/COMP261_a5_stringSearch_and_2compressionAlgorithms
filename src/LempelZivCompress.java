import java.util.*;
import java.io.*;

public class LempelZivCompress {
    // static String[] args = new String[] { "./data/p3Comp.txt" };

    public static void main(String[] args) {
        if (args.length != 1) {
            System.out.println(
                    "Please call this program with one argument which is the input file name.");
        } else {
            try {
                Scanner s = new Scanner(new File(args[0]));

                // Read the entire file into one String.
                StringBuilder fileText = new StringBuilder();
                while (s.hasNextLine()) {
                    fileText.append(s.nextLine() + "\n");
                }

                System.out.println(compress(fileText.toString()));
            } catch (FileNotFoundException e) {
                System.out.println("Unable to find file called " + args[0]);
            }
        }
    }

    /**
     * Take uncompressed input as a text string, compress it, and return it as a text string.
     */
    public static String compress(String input) {
        // TODO (currently just returns the argument).
        StringBuffer compressedOutput = new StringBuffer();
        int cursor = 0;
        int windowSize = 100;
        while (cursor < input.length()) {
            int length = 0;
            int prevMatch = 0;
            int startPos = 0;
            while (true) {
                startPos = cursor < windowSize ? 0 : cursor - windowSize;
                // This looks for an occurrence of text[cursor..cursor+length] in
                // text[start..cursor-1], for increasing values of length, until none is found,
                // then outputs a triple.
                String subText = input.substring(startPos, cursor);
                String searchPattern = input.substring(cursor, cursor + length);

                // the matched pos of searchPattern in subText
                // the cursor-windowSize should never point before 0
                // And cursor+lengthmustnot go past end of the input text
                int matchPos = cursor + length >= input.length() ? -1
                        : subText.indexOf(searchPattern);

                if (matchPos >= 0) {// match succeeded
                    prevMatch = matchPos;
                    length++;
                } else {// not match

                    // the offset is the count of prevMatch
                    // like 123ed_text,the offset for e is 4
                    int offset = prevMatch < 0 ? 0 : subText.length() - prevMatch;
                    char symbol = input.charAt(cursor + length - 1);
                    TuplePart3 tuple = new TuplePart3(offset, length - 1, symbol);
                    compressedOutput.append(tuple.toString());

                    cursor = cursor + length;// + 1;

                    break;
                }
            }
        }

        return compressedOutput.toString();
    }

}
