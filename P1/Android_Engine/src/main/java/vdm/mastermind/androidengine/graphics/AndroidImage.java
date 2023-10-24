package vdm.mastermind.androidengine.graphics;

import android.graphics.Bitmap;

import vdm.mastermind.engine.interfaces.objects.IImage;

public final class AndroidImage implements IImage {

    private Bitmap image;
    public AndroidImage(Bitmap image){
        this.image=image;
    }

    /**
     * @return the Bitmap of the image
     */
    public Bitmap getImage() {
        return image;
    }

    /**
     * @return
     */
    @Override
    public int getWidth() {
        return this.image.getWidth();
    }

    @Override
    public int getHeight() {
        return this.image.getHeight();
    }
}
