package mastermind.androidengine;

import android.graphics.Bitmap;

import mastermind.engine.IImage;

public class AndroidImage implements IImage {

    private final Bitmap image;

    public AndroidImage(Bitmap image) {
        this.image = image;
    }

    /**
     * Gets the source width of the image.
     *
     * @return The source width of the image.
     */
    @Override
    public int getWidth() {
        return image.getWidth();
    }

    /**
     * Gets the source height of the image.
     *
     * @return The source height of the image.
     */
    @Override
    public int getHeight() {
        return image.getHeight();
    }

    /**
     * Gets the underlying image.
     *
     * @return The buffered image.
     */
    public Bitmap getUnderlyingImage() {
        return image;
    }
}
