public class NodeHuffman implements Comparable<NodeHuffman> {

    char char_value;

    /** the occur time in text */
    int freq;

    NodeHuffman leftChild, rightChild, parent;

    /**
     * unique code
     */
    String code = "";

    /**
     * A constructor. It construct a new instance of NodeHuffman.
     *
     * @param char_value
     * @param freq
     */
    public NodeHuffman(char char_value, int freq) {
        this.char_value = char_value;
        this.freq = freq;
    }

    /**
     * Assign 2 childs and add their freq together as freq of this Node obj
     *
     * @param leftChild
     * @param rightChild
     */
    public NodeHuffman(NodeHuffman leftChild, NodeHuffman rightChild) {
        this.leftChild = leftChild;
        this.rightChild = rightChild;
        this.freq = leftChild.getFreq() + rightChild.getFreq();

    }

    @Override
    public int compareTo(NodeHuffman otherNode) {
        NodeHuffman currentNode = this;
        // sort it based on freq(i.e. occur time)
        // e.g. {2,3,7}
        if (currentNode.getFreq() < otherNode.getFreq()) {
            return -1;
        } else if (currentNode.getFreq() > otherNode.getFreq()) {
            return 1;
        }
        // two nodes have the same frequency in the priority queue, pick first the nodes with
        // the smallest characters alphabetically.
        /// Use Character.compare(..., ...) to compare them.
        else {
            char curr_lowest = currentNode.findLowestValueChar();
            char oth_lowest = otherNode.findLowestValueChar();
            int val = Character.compare(curr_lowest, oth_lowest);
            // int val = Character.compare(currentNode.getChar_value(), otherNode.char_value);
            if (val < 0) {
                return -1;
            } else if (val > 0) {
                return 1;
            }
        }
        return 0;
    }

    /**
     * Description: <br/>
     * Used when the freq is the same.
     * <p>
     * the frequencies are the same, search each subtree for the lowest value character (you
     * probably want to use Character.compare(...) as this will handle non-English characters).
     * The sub-tree with the lowest valued character (ie: the first lexograpcically) will have
     * the highest priority. This might result in things that look a bit wierd ('Z' comes
     * before 'a', for example), but it's the easiest way to ensure that your code performs
     * consistently across different character sets.
     * 
     * @author Yun Zhou
     * @return
     */
    public char findLowestValueChar() {
        if (this.getLeftChild() == null && this.getRightChild() == null) {
            return this.getChar_value();
        }
        char left = this.getLeftChild().findLowestValueChar();
        char right = this.getRightChild().findLowestValueChar();
        return Character.compare(left, right) < 0
                ? left
                : right;
    }

    @Override
    public String toString() {
        return this.getChar_value() + this.getCode();
    }
    // public static void main(String[] args) {
    // ArrayList<Character> temp = new ArrayList<>() {
    // {
    // add('y');
    // add('d');
    // add('a');
    // }
    // };
    // Collections.sort(temp, (o1, o2) -> {
    //
    // int val = Character.compare(o1, o2);
    // if (val < 0) {
    // return -1;
    // } else if (val > 0) {
    // return 1;
    // }
    // return 0;
    // });
    // temp.forEach((c) -> {
    // System.out.println(c);
    // });
    // }

    /**
     * Get the char_value.
     *
     * @return the char_value
     */
    public char getChar_value() {
        return char_value;
    }

    /**
     * Set the char_value.
     *
     * @param char_value
     *            the char_value to set
     */
    public void setChar_value(char char_value) {
        this.char_value = char_value;
    }

    /**
     * Get the freq.
     *
     * @return the freq
     */
    public int getFreq() {
        return freq;
    }

    /**
     * Set the freq.
     *
     * @param freq
     *            the freq to set
     */
    public void setFreq(int freq) {
        this.freq = freq;
    }

    /**
     * Get the leftChild.
     *
     * @return the leftChild
     */
    public NodeHuffman getLeftChild() {
        return leftChild;
    }

    /**
     * Set the leftChild.
     *
     * @param leftChild
     *            the leftChild to set
     */
    public void setLeftChild(NodeHuffman leftChild) {
        this.leftChild = leftChild;
    }

    /**
     * Get the rightChild.
     *
     * @return the rightChild
     */
    public NodeHuffman getRightChild() {
        return rightChild;
    }

    /**
     * Set the rightChild.
     *
     * @param rightChild
     *            the rightChild to set
     */
    public void setRightChild(NodeHuffman rightChild) {
        this.rightChild = rightChild;
    }

    /**
     * Get the parent.
     *
     * @return the parent
     */
    public NodeHuffman getParent() {
        return parent;
    }

    /**
     * Set the parent.
     *
     * @param parent
     *            the parent to set
     */
    public void setParent(NodeHuffman parent) {
        this.parent = parent;
    }

    /**
     * Get the code.
     *
     * @return the code
     */
    public String getCode() {
        return code;
    }

    /**
     * Set the code.
     *
     * @param code
     *            the code to set
     */
    public void setCode(String code) {
        this.code = code;
    }

}
