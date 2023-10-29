package vdm.mastermind.logic.gameobjects;

import vdm.mastermind.engine.classes.Color;
import vdm.mastermind.engine.enums.HorizontalAlignment;
import vdm.mastermind.engine.interfaces.IGraphics;
import vdm.mastermind.engine.interfaces.IScene;
import vdm.mastermind.engine.interfaces.objects.IFont;

public class TextObject extends GameObject {
    private String text;
    private IFont renderingFont;

    public TextObject(IScene scene, IFont font, Color color,String text) {
        super(scene);
        this.text=text;
        this.renderingFont=font;
        this.setStrokeColor(color);
    }

    @Override
    public void render(IGraphics graphics) {
        super.render(graphics);
        graphics.setFont(renderingFont);
        graphics.setColor(strokeColor);

        graphics.drawText(text, getX(),getY(), HorizontalAlignment.CENTER);

    }
}
