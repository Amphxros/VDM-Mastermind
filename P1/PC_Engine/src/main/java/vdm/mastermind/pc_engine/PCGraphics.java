package vdm.mastermind.pc_engine;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.geom.RoundRectangle2D;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.io.File;
import java.nio.Buffer;
import java.util.Map;
import java.util.Scanner;

import javax.imageio.ImageIO;
import javax.swing.JFrame;

import vdm.mastermind.engine.classes.Color;
import vdm.mastermind.engine.classes.GraphicsTransformer;
import vdm.mastermind.engine.interfaces.IGraphics;
import vdm.mastermind.engine.interfaces.objects.IFont;
import vdm.mastermind.engine.interfaces.objects.IImage;

public class PCGraphics implements IGraphics {

    private JFrame window;
    private BufferStrategy bufferStrategy;
    private GraphicsTransformer transformer= new GraphicsTransformer();
    private Graphics2D graphics2D; //equivalent to canvas in android


    Map<String,PCImage> images;
    Map<String, PCFont> fonts;


    public PCGraphics(JFrame window){
        this.window=window;
        int attemps=10;

        do{
            try {
                this.window.createBufferStrategy(2);
            }
            catch (Exception e){
                e.printStackTrace();
            }
        }while (attemps>0);

        this.bufferStrategy=this.window.getBufferStrategy();
        this.graphics2D= (Graphics2D) this.bufferStrategy.getDrawGraphics();

    }

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
        images.put(name,pcImage);

        return pcImage;
    }

    @Override
    public IFont newFont(String filename, int size, boolean isBold, boolean isItalic) {
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
        graphics2D.drawImage(pcImage.getImage(),x,y,w,h,null);
    }

    @Override
    public void drawImage(IImage image, int x, int y) {
        PCImage pcImage=(PCImage) image;
        assert (pcImage!=null);
        graphics2D.drawImage(pcImage.getImage(),x,y,null);
    }

    @Override
    public void setColor(Color color) {

    }

    @Override
    public void fillRectangle(int cx, int cy, int width, int height) {
        graphics2D.fillRect(cx,cy,width,height);
    }

    /**
     * method found in {https://kodejava.org/how-do-i-draw-a-round-rectangle-in-java-2d/} but changed a little so it's filled
     * @param cx pos x
     * @param cy pos y
     * @param width width of the rectangle
     * @param height height of the rectangle
     * @param arc curve of the corners
     */
    @Override
    public void fillRoundRectangle(int cx, int cy, int width, int height, float arc) {
        graphics2D.fill(new RoundRectangle2D.Double(cx, cy, width, height, arc, arc));
    }

    @Override
    public void drawRectangle(int cx, int cy, int width, int height) {
        graphics2D.draw(new Rectangle(cx,cy,width,height));
    }

    /**
     * method found in {https://kodejava.org/how-do-i-draw-a-round-rectangle-in-java-2d/}
     * @param cx pos x
     * @param cy pos y
     * @param width width of the rectangle
     * @param height height of the rectangle
     * @param arc curve of the corners
     */
    @Override
    public void drawRoundRectangle(int cx, int cy, int width, int height, float arc) {
        graphics2D.draw(new RoundRectangle2D.Double(cx, cy, width, height, arc, arc));
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
