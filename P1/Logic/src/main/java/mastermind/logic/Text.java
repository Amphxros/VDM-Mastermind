package mastermind.logic;

import mastermind.engine.Color;
import mastermind.engine.HorizontalAlignment;
import mastermind.engine.IFont;
import mastermind.engine.IGraphics;
import mastermind.engine.IScene;

public class Text extends GameObject{
    private final IFont font;
    private String text;

    private HorizontalAlignment alignment;

    public Text(IScene scene, String text, IFont font) {
        this(scene, text, font, HorizontalAlignment.CENTRE);
    }

    public Text(IScene scene, String text, IFont font, HorizontalAlignment alignment) {
        super(scene);
        this.text = text;
        this.font = font;
        this.alignment = alignment;
    }

    public Text setText(String text) {
        this.text = text;
        return this;
    }
    public void setAlignment(HorizontalAlignment alignment){
        this.alignment=alignment;
    }

    @Override
    public void render(IGraphics graphics) {
        super.render(graphics);

        graphics.setColor(strokeColor);
        graphics.setFont(font);
        graphics.setTextAlignment(alignment);
        graphics.drawText(text, getX(), getY());
    }
}
