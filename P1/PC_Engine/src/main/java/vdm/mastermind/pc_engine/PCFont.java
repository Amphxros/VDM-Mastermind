package vdm.mastermind.pc_engine;

import java.awt.Font;

import vdm.mastermind.engine.interfaces.objects.IFont;

/**
 * Container of the Java.awt.font to comunicate with the logic
 */
public class PCFont implements IFont {

    private final Font font; //underlying font
    int size;
    public PCFont(Font font, int size){
        this.font=font;
        this.size=size;
    }

    /**
     *
     * @return The underlying font, equivalent of typeface
     */
    public Font getFont() {
        return font;
    }

    /**
     * @return the size of the font
     */
    @Override
    public int getTamFont()
    {
        return size;
    }

    /**
     *
     * @return whether is bold or not
     */
    @Override
    public boolean isBold() {
        return font.isBold();
    }

    /**
     *
     * @return whether is italics or not
     */
    @Override
    public boolean isItalic() {
        return font.isItalic();
    }
}
