import java.util.*;
import java.io.*;

/**
 * A new instance of HuffmanCoding is created for every run. The constructor is passed the full
 * text to be encoded or decoded, so this is a good place to construct the tree. You should
 * store this tree in a field and then use it in the encode and decode methods.
 */
public class HuffmanCoding {
    static String[] args = new String[] { "./data/test1.txt", "0" };

    public static void main(String[] arg) {
        if (args.length != 2) {
            System.out.println("Please call this program with two arguments, which are " +
                               "the input file name and either 0 for constructing tree and printing it, or "
                               +
                               "1 for constructing the tree and encoding the file and printing it, or "
                               +
                               "2 for constructing the tree, encoding the file, and then decoding it and "
                               +
                               "printing the result which should be the same as the input file.");
        } else {
            try {
                Scanner s = new Scanner(new File(args[0]));

                // Read the entire file into one String.
                StringBuilder fileText = new StringBuilder();
                while (s.hasNextLine()) {
                    fileText.append(s.nextLine() + "\n");
                }

                if (args[1].equals("0")) {
                    System.out.println(constructTree(fileText.toString()));
                } else if (args[1].equals("1")) {
                    constructTree(fileText.toString()); // initialises the tree field.
                    System.out.println(encode(fileText.toString()));
                } else if (args[1].equals("2")) {
                    constructTree(fileText.toString()); // initialises the tree field.
                    String codedText = encode(fileText.toString());
                    // DO NOT just change this code to simply print fileText.toString() back.
                    // ;-)
                    System.out.println(decode(codedText));
                } else {
                    System.out.println("Unknown second argument: should be 0 or 1 or 2");
                }
            } catch (FileNotFoundException e) {
                System.out.println("Unable to find file called " + args[0]);
            }
        }
    }

    // TODO add a field with your ACTUAL HuffmanTree implementation.
    private static Map<Character, String> tree; // Change type from Object to HuffmanTree or
                                                // appropriate type you design.

    /**
     * This would be a good place to compute and store the tree.
     */
    public static Map<Character, String> constructTree(String text) {
        // TODO Construct the ACTUAL HuffmanTree here to use with both encode and decode below.
        // TODO fill this in.

        /*
         * first, find the frequency for each char and initialize as the Node objects and offer
         * into priority queue
         */
        Map<Character, Integer> freqMap = getFreqMap(text);
        PriorityQueue<NodeHuffman> priorityQueue = new PriorityQueue<NodeHuffman>();
        freqMap.forEach((key_char, freq_value) -> {
            priorityQueue.offer(new NodeHuffman(key_char, freq_value));
        });
        /*
         * construct the tree
         */
        while (priorityQueue.size() > 1) {
            NodeHuffman leftNodeHuffman = priorityQueue.poll();
            NodeHuffman rightNodeHuffman = priorityQueue.poll();
            NodeHuffman parentNodeHuffman = new NodeHuffman(leftNodeHuffman, rightNodeHuffman);
            leftNodeHuffman.setParent(parentNodeHuffman);
            rightNodeHuffman.setParent(parentNodeHuffman);
            parentNodeHuffman.setFreq(leftNodeHuffman.getFreq() + rightNodeHuffman.getFreq());
            priorityQueue.offer(parentNodeHuffman);
        }
        // final node left in the queue is the root node
        NodeHuffman root = priorityQueue.peek();

        // Traverse this tree to assign codes:
        // if node has code c, assign c0 to left child, c1 to right child

        Map<Character, String> letter_code_map = new HashMap<Character, String>();
        assignCodes(root, letter_code_map);
        assert letter_code_map.isEmpty() == false;
        letter_code_map.forEach((key, value) -> {
            System.out.print(key + ":" + value + ",");
        });
        System.out.println();

        tree = letter_code_map;
        return letter_code_map;
    }

    // static Map<Character, String> letter_code_map = new HashMap<Character, String>();

    public static void assignCodes(NodeHuffman node, Map<Character, String> letter_code_map) {
        // check if it is the leaf node
        if (node.getLeftChild() == null && node.getRightChild() == null) {
            letter_code_map.put(node.getChar_value(), node.getCode());// assign to map
            return;
        }
        NodeHuffman leftNode = node.getLeftChild();
        leftNode.setCode((node.getCode() + '0'));
        assignCodes(leftNode, letter_code_map);

        NodeHuffman rightNode = node.getRightChild();
        rightNode.setCode((node.getCode() + '1'));
        assignCodes(rightNode, letter_code_map);

    }

    public static Map<Character, Integer> getFreqMap(String text) {
        Map<Character, Integer> freqMapToReturn = new HashMap<Character, Integer>();
        for (Character character : text.toCharArray()) {
            if (!freqMapToReturn.containsKey(character)) {
                freqMapToReturn.put(character, 0);
            }
            int oldVal = freqMapToReturn.get(character);
            freqMapToReturn.put(character, oldVal + 1);
        }
        return freqMapToReturn;
    }

    /**
     * Take an input string, text, and encode it with the tree computed from the text. Should
     * return the encoded text as a binary string, that is, a string containing only 1 and 0.
     */
    public static String encode(String text) {
        // TODO fill this in.
        StringBuilder encodeBinText = new StringBuilder();
        for (int i = 0; i < text.length(); i++) {
            Character character = text.charAt(i);
            encodeBinText.append(tree.get(character));
        }

        return encodeBinText.toString();
    }

    /**
     * Take encoded input as a binary string, decode it using the stored tree, and return the
     * decoded text as a text string.
     */
    public static String decode(String encoded) {
        // TODO fill this in.
        StringBuilder decodeText = new StringBuilder();
        for (int i = 0; i < encoded.length(); i++) {
            Character character = encoded.charAt(i);
            decodeText.append(tree.get(character));
        }

        return decodeText.toString();
    }
}
