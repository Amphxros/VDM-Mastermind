package mastermind.androidengine;

import android.content.res.AssetManager;
import android.graphics.Typeface;

import mastermind.engine.IFont;

public class AndroidFont implements IFont {
    private final Typeface font;
    private final int size;

    public AndroidFont(String route, AssetManager assetManager, int size, boolean isBold) {
        this.size = size;
        font = Typeface.create(new Typeface.Builder(assetManager, route).setWeight(size).build(), isBold ? Typeface.NORMAL : Typeface.BOLD);
    }

    /**
     * @return
     */
    public Typeface getFont() {
        return font;
    }

    /**
     *
     * @return
     */
    @Override
    public int getSize() {
        return size;
    }

    /**
     * @return Si la fuente esta en negrita
     */
    @Override
    public boolean isBold() {
        return font.isBold();
    }

    /**
     * @return Si la fuente esta en cursiva
     */
    @Override
    public boolean isItalic() {
        return font.isItalic();
    }

    /**
     *
     * @return El
     */
    public Typeface getUnderlyingFont() {
        return font;
    }
}
