package vdm.mastermind.pc_engine;

import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;

import vdm.mastermind.engine.interfaces.IGraphics;
import vdm.mastermind.engine.interfaces.objects.IFont;
import vdm.mastermind.engine.interfaces.objects.IImage;

public class PCGraphics implements IGraphics {
    @Override
    public PCImage newImage(String name) {
        BufferedImage image;
        try {
            image = ImageIO.read(new File("Assets/" + name));
        } catch (Exception exception) {
            exception.printStackTrace();
            return null;
        }

        PCImage pcImage= new PCImage(image);
        assert (pcImage!=null);
        return pcImage;
    }

    @Override
    public IFont newFont(String filename, int size, boolean isBold) {
        return null;
    }

    @Override
    public void clear(int color) {

    }

    @Override
    public void translate(int x, int y) {

    }

    @Override
    public void scale(float x, float y) {

    }

    @Override
    public void save() {

    }

    @Override
    public void restore() {

    }

    @Override
    public void drawImage(IImage image, int x, int y, int w, int h) {
        PCImage pcImage=(PCImage) image;
        assert (pcImage!=null);
        //graphics2D.drawImage(...)
    }

    @Override
    public void drawImage(IImage image, int x, int y) {
        PCImage pcImage=(PCImage) image;
        assert (pcImage!=null);
        //graphics2D.drawImage(...)
    }

    @Override
    public void setColor(int color) {

    }

    @Override
    public void fillRectangle(int cx, int cy, int width, int height) {

    }

    @Override
    public void fillRoundRectangle(int cx, int cy, int width, int height, float arc) {

    }

    @Override
    public void drawRectangle(int cx, int cy, int width, int height) {

    }

    @Override
    public void drawRoundRectangle(int cx, int cy, int width, int height, float arc) {

    }

    @Override
    public void drawLine(int initX, int initY, int endX, int endY) {

    }

    @Override
    public void drawCircle(float cx, float cy, float radius) {

    }

    @Override
    public void fillCircle(float cx, float cy, float radius) {

    }

    @Override
    public void drawText(String text, int x, int y) {

    }

    @Override
    public int getWidth() {
        return 0;
    }

    @Override
    public int getHeight() {
        return 0;
    }
}
