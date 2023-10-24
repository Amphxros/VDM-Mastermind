package vdm.mastermind.pc_engine;

import java.awt.image.BufferedImage;

import vdm.mastermind.engine.interfaces.objects.IImage;

public class PCImage implements IImage {
    BufferedImage image;
    public PCImage(BufferedImage image){
        this.image=image;
    }

    public BufferedImage getImage(){
        return this.image;
    }
    @Override
    public int getWidth() {
        return image.getWidth();
    }

    @Override
    public int getHeight() {
        return image.getHeight();
    }
}
