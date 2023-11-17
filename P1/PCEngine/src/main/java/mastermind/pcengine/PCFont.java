package mastermind.pcengine;

import java.awt.Font;

import mastermind.engine.IFont;

public class PCFont implements IFont {
    private final Font font;

    public PCFont(Font font) {
        this.font = font;
    }

    /**
     * Gets the source size of the font.
     *
     * @return The source size of the font.
     */
    @Override
    public int getSize() {
        return font.getSize();
    }

    /**
     * Gets whether or not the font is bold.
     *
     * @return Whether or not the font is bold.
     */
    @Override
    public boolean isBold() {
        return font.isBold();
    }

    @Override
    public boolean isItalic() {
        return font.isItalic();
    }

    /**
     * Gets the underlying {@link Font} this instance hosts.
     *
     * @return The underlying {@link Font}.
     */
    public Font getUnderlyingFont() {
        return font;
    }
}
