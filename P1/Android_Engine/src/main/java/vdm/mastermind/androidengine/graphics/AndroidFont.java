package vdm.mastermind.androidengine.graphics;

import android.content.res.AssetManager;
import android.graphics.Typeface;

import vdm.mastermind.engine.interfaces.objects.IFont;

public final class AndroidFont implements IFont {
    Typeface typeface;
    int tamFont;
    boolean isBold;
    boolean isItalic;
    public AndroidFont(String route, AssetManager manager, int tamFont, boolean isBold, boolean isItalic){
        this.tamFont=tamFont;
        this.isBold=isBold;
        this.isItalic=isItalic;
        this.typeface= Typeface.create(new Typeface.Builder(manager,route).setWeight(this.tamFont).build(), isBold &&isItalic ? Typeface.NORMAL : Typeface.BOLD_ITALIC);

    }

    /**
     * @return The typeface of the font
     */
    public Typeface getTypeface(){
        return this.typeface;
    }

    /**
     * @return the size of the font
     */
    @Override
    public int getTamFont() {
        return this.tamFont;
    }

    /**
     *
     * @return whether is bold or not
     */
    @Override
    public boolean isBold() {
        return this.isBold;
    }

    /**
     * @return whether is italic or not
     */
    @Override
    public boolean isItalic() {
        return this.isItalic;
    }
}
