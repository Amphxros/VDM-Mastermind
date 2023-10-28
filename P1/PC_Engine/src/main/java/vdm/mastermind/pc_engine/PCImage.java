package vdm.mastermind.pc_engine;

import java.awt.image.BufferedImage;

import vdm.mastermind.engine.interfaces.objects.IImage;

public final class PCImage implements IImage {
    private final BufferedImage image;
    public PCImage(BufferedImage image){
        this.image=image;
    }

    /**
     * @return The image file itself
     */
    public BufferedImage getImage(){
        return this.image;
    }

    /**
     *
     * @return The width of the image file
     */
    @Override
    public int getWidth() {

        return image.getWidth();
    }

    /**
     *
     * @return The height of the image file
     */
    @Override
    public int getHeight()
    {
        return image.getHeight();
    }
}
