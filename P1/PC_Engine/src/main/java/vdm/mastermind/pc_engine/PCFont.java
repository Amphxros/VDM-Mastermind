package vdm.mastermind.pc_engine;

import vdm.mastermind.engine.interfaces.objects.IFont;

public class PCFont implements IFont {
    @Override
    public int getTamFont()
    {
        return 0;
    }

    @Override
    public boolean isBold() {
        return false;
    }

    @Override
    public boolean isItalic() {
        return false;
    }
}
