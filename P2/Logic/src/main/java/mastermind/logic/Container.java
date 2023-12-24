package mastermind.logic;

import mastermind.engine.IGraphics;
import mastermind.engine.IScene;

/**
 * Empty game object
 */
public class Container extends GameObject{
    public Container(IScene scene) {
        super(scene);
    }

    public void show(){
        this.setEnabled(true);
        for(GameObject g: getChildren())
            g.setEnabled(true);
    }


    public void hide(){
        this.setEnabled(false);
        for(GameObject g: getChildren())
            g.setEnabled(false);
    }
    @Override
    public void render(IGraphics graphics) {
        graphics.setColor(strokeColor);
        graphics.drawRectangle(getX(),getY(),getWidth(),getHeight());
        super.render(graphics);
    }
}
