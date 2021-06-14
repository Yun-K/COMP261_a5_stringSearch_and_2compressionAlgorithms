import java.util.*;
import java.io.*;

public class LempelZivDecompress {
    // static String[] args = new String[] { "./data/p3De.txt" };
    // static String[] args = new String[] { "./data/p3C_out.txt" };

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

                System.out.println(decompress(fileText.toString()));
            } catch (FileNotFoundException e) {
                System.out.println("Unable to find file called " + args[0]);
            }
        }
    }

    /**
     * Take compressed input as a text string, decompress it, and return it as a text string.
     */
    public static String decompress(String compressed) {
        // TODO (currently just returns the argument).
        StringBuffer output = new StringBuffer();
        int cursor = 0;
        for (TuplePart3 tuple : getTupleListFromCompressedFile(compressed)) {
            // the case of [0, 0, ch]
            if (tuple.getLength() == 0 && tuple.getOffset() == 0) {
                output.append(tuple.getSymbol());
                cursor++;
            }
            // the case of [offset, length, ch]
            else {
                // get the substring which start at the offset to the length
                String subStr = output.substring(cursor - tuple.getOffset(),
                        cursor - tuple.getOffset() + tuple.getLength());
                output.append(subStr);

                cursor += tuple.getLength();// update the cursor pointer
                // append current tuple symbol
                if (tuple.getSymbol() != null) {
                    output.append(tuple.getSymbol());
                }
                cursor++;
            }
        }

        return output.toString();
    }

    /**
     * Description: <br/>
     * Method for converting txt into the java bean object list , which is <code>Tuple</code>
     * object.
     * 
     * @author Yun Zhou
     * @param compressedString
     *            the str that neeed to be convertedx
     * @return converted Tuple object List
     */
    static List<TuplePart3> getTupleListFromCompressedFile(String compressedString) {
        List<TuplePart3> tupleList = new ArrayList<TuplePart3>();
        char start = '[', delimt = ',', end = ']';
        TuplePart3 tuple = new TuplePart3();
        int index = 0;
        int coount = 0;
        String[] tempString = new String[] { "", "", "" };// string for storing
                                                          // offset,length,symbol
        for (char c : compressedString.toCharArray()) {
            if (c == start) {
                tuple = new TuplePart3();
            } else if (c == end) {
                tuple.setOffset(Integer.parseInt(tempString[0]));
                tuple.setLength(Integer.parseInt(tempString[1]));
                tuple.setSymbol(tempString[2].charAt(0));
                tupleList.add(tuple);
                // if (index < 2) {
                // System.err.println("invalid Tuple object");
                // }
                index = 0;// reset the index
                tempString = new String[] { "", "", "" };// string for storing
                                                         // offset,length,symbol
            } else if (c == delimt) {
                index++;
            }
            // the case of normal character
            else {
                switch (index) {
                case 0:
                    tempString[0] += c;
                    break;
                case 1:
                    tempString[1] += c;
                    break;
                case 2:
                    tempString[2] += c;
                    break;
                default:
                    break;
                }
            }
        }

        return tupleList;

    }
}
