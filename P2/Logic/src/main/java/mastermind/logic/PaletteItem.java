package mastermind.logic;

import mastermind.engine.Color;

public class PaletteItem {
    Color[]colors= new Color[4];

    public PaletteItem (Color bg, Color buttons, Color tittle, Color font){
        colors[0]= bg;
        colors[1]= buttons;
        colors[2]= tittle;
        colors[3]=font;
    }
}
