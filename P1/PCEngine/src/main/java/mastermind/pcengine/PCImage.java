package mastermind.pcengine;

import java.awt.image.BufferedImage;

import mastermind.engine.IImage;

public class PCImage implements IImage {
    private final BufferedImage image;

    public PCImage(BufferedImage image) {
        this.image = image;
    }
    public BufferedImage getUnderlyingImage() {
        return image;
    }

    /**
     * Gets the source width of the image.
     *
     * @return The source width of the image.
     */
    @Override
    public int getWidth() {
        return image.getWidth(null);
    }

    /**
     * Gets the source height of the image.
     *
     * @return The source height of the image.
     */
    @Override
    public int getHeight() {
        return image.getHeight(null);
    }
}
