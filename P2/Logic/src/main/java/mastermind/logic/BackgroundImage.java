package mastermind.logic;

import mastermind.engine.IGraphics;
import mastermind.engine.IImage;
import mastermind.engine.IScene;

public class BackgroundImage extends Image{
    public BackgroundImage(IScene scene, IImage image) {
        super(scene, image);
    }

    @Override
    public void update(double delta) {
        int w= getEngine().getGraphics().getWidth();
        int h= getEngine().getGraphics().getHeight();
        setPosition(0,0);
        setSize(w,h);

        super.update(delta);
    }

    @Override
    public void render(IGraphics graphics) {
        super.render(graphics);
    }
}
