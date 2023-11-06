package vdm.mastermind.logic.gameobjects;

import vdm.mastermind.engine.interfaces.IGraphics;
import vdm.mastermind.engine.interfaces.IScene;
import vdm.mastermind.engine.interfaces.objects.IImage;

public class ImageObject extends GameObject {
    IImage image;
    public ImageObject(IScene scene, IImage image) {
        super(scene);
        this.image=image;
    }

    @Override
    public void render(IGraphics graphics) {
        super.render(graphics);
        graphics.drawImage(image,getX(),getY(),getWidth(),getHeight());
    }
}
