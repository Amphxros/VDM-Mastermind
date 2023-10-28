package vdm.mastermind.pc_engine;

import java.awt.Font;

import vdm.mastermind.engine.interfaces.objects.IFont;

public class PCFont implements IFont {

    private final Font font;
    public PCFont(Font font){
        this.font=font;
    }
    @Override
    public int getTamFont()
    {
        return font.getSize();
    }

    @Override
    public boolean isBold() {
        return font.isBold();
    }

    @Override
    public boolean isItalic() {
        return font.isItalic();
    }
}
