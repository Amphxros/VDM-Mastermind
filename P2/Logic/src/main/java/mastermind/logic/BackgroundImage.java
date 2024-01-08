package mastermind.logic;

import mastermind.engine.IGraphics;
import mastermind.engine.IImage;
import mastermind.engine.IScene;

/**
 * GameObject que representa una imagen de fondo hereda de Image
 */
public class BackgroundImage extends Image{
    public BackgroundImage(IScene scene, IImage image) {
        super(scene, image);
    }

    /**
     * Actualizacion del objeto en cada frame, mantiene la imagen de fondo con las proporciones correctas
     * @param delta The number of seconds since the last frame.
     */
    @Override
    public void update(double delta) {
        int w= getEngine().getGraphics().getWidth();
        int h= getEngine().getGraphics().getHeight();
        setPosition(0,0);
        setSize(w,h);

        super.update(delta);
    }
}