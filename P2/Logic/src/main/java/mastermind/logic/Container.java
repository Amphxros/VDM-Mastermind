package mastermind.logic;

import mastermind.engine.IGraphics;
import mastermind.engine.IScene;
import mastermind.logic.scene.Scene;

/**
 * Empty game object
 */
public class Container extends GameObject implements ScrollEventListener{
    public Container(IScene scene) {
        super(scene);
        Scene scene_= (Scene) getScene();
        scene_.addScrollListener(this);
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

    @Override
    public void onScroll(int diff) {
        setPosition(getX(), getY() + diff);
        moveChildren(new Vector2D(0,diff));

    }
}
