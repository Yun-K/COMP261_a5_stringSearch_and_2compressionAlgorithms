
public class TuplePart3 {
    private int offset, length;

    /**
     * either be the next char if got repeat
     * 
     * OR the current char if no repeats such as [0,0,symbol]
     */
    private char symbol;

    /**
     * A constructor. It construct a new instance of TuplePart3.
     *
     * @param offset
     * @param length
     * @param symbol
     */

    public TuplePart3(int offset, int length, char symbol) {
        this.offset = offset;
        this.length = length;
        this.symbol = symbol;
    }

    public TuplePart3() {
    }

    @Override
    public String toString() {
        return "[" + offset + "," + length + "," + symbol + "]";
    }

    /**
     * Get the offset.
     *
     * @return the offset
     */
    public int getOffset() {
        return offset;
    }

    /**
     * Set the offset.
     *
     * @param offset
     *            the offset to set
     */
    public void setOffset(int offset) {
        this.offset = offset;
    }

    /**
     * Get the length.
     *
     * @return the length
     */
    public int getLength() {
        return length;
    }

    /**
     * Set the length.
     *
     * @param length
     *            the length to set
     */
    public void setLength(int length) {
        this.length = length;
    }

    /**
     * Get the symbol.
     *
     * @return the symbol
     */
    public Character getSymbol() {
        return symbol;
    }

    /**
     * Set the symbol.
     *
     * @param symbol
     *            the symbol to set
     */
    public void setSymbol(char symbol) {
        this.symbol = symbol;
    }

}
