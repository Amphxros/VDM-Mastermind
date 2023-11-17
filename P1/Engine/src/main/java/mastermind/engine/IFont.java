package mastermind.engine;

public interface IFont {
    /**
     * Gets the source size of the font.
     *
     * @return The source size of the font.
     */
    int getSize();

    /**
     * Gets whether or not the font is bold.
     *
     * @return Whether or not the font is bold.
     */
    boolean isBold();
    /**
     * Gets whether or not the font is italics.
     *
     * @return Whether or not the font is italic.
     */
    boolean isItalic();

}
