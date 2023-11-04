package vdm.mastermind.pc_engine;

import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Insets;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.awt.geom.RoundRectangle2D;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.swing.JFrame;

import vdm.mastermind.engine.classes.Color;
import vdm.mastermind.engine.classes.GraphicsTransformer;
import vdm.mastermind.engine.enums.HorizontalAlignment;
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
        transformer.setSize(300, 600);
        int attemps=2;

        do{
            try {
                this.window.createBufferStrategy(2);
                attemps--;
            }
            catch (Exception e){
                e.printStackTrace();
            }
        }while (attemps>0);

        this.bufferStrategy=this.window.getBufferStrategy();
        this.graphics2D= (Graphics2D) this.bufferStrategy.getDrawGraphics();

        this.images= new HashMap<>();
        this.fonts= new HashMap<>();
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
        Font font;
        try{
            font= Font.createFont(Font.TRUETYPE_FONT,new File("Assets/"+filename));
        }
        catch (Exception e){
            e.printStackTrace();
            return null;
        }

        PCFont pcFont= new PCFont(font,size);
        assert (pcFont!=null);
        this.fonts.put(filename,pcFont);
        return pcFont;
    }

    @Override
    public void clear(Color color) {
        setColor(color);
        graphics2D.fillRect(0,0,window.getWidth(), window.getHeight());
        updateTransformParameters();
    }

    @Override
    public void present() {
        bufferStrategy.show();
        graphics2D.dispose();
        graphics2D= (Graphics2D) bufferStrategy.getDrawGraphics();
    }

    @Override
    public void translate(int x, int y) {
        graphics2D.translate(x,y);
    }

    @Override
    public void scale(float x, float y) {
        graphics2D.scale(x,y);
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
        java.awt.Color col= new java.awt.Color(color.getRed(), color.getGreen(), color.getBlue(),color.getAlpha());
        graphics2D.setColor(col);
    }

    @Override
    public void setFont(IFont font) {
        PCFont pcFont= (PCFont)font;
        assert (pcFont!=null);
        graphics2D.setFont(pcFont.getFont().deriveFont((float) pcFont.getTamFont()));
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
        Shape line= new Line2D.Double(initX,initY,endX,endY);
        graphics2D.draw(line);
    }

    @Override
    public void drawCircle(float cx, float cy, float radius) {
        Shape circle = new Ellipse2D.Double(cx - radius, cy - radius, radius * 2.0, radius * 2.0);
        graphics2D.draw(circle);
    }

    @Override
    public void fillCircle(float cx, float cy, float radius) {
        Shape circle = new Ellipse2D.Double(cx - radius, cy - radius, radius * 2.0, radius * 2.0);
        graphics2D.fill(circle);
    }

    @Override
    public void drawText(String text, int x, int y, HorizontalAlignment alignment) {

        int outX = x;
        if (alignment == HorizontalAlignment.CENTER) {
            outX -= graphics2D.getFontMetrics().stringWidth(text) / 2;
        } else if (alignment == HorizontalAlignment.RIGHT) {
            outX -= graphics2D.getFontMetrics().stringWidth(text);
        }

        graphics2D.drawString(text,outX,y);
    }

    @Override
    public void setResolution(int width, int height) {

    }

    @Override
    public int getWidth() {
        return transformer.getWidth();
    }

    @Override
    public int getHeight() {
        return transformer.getHeight();
    }

    @Override
    public int getLogicPointX(int x) {
        return transformer.getTransformedX(x);
    }

    @Override
    public int getLogicPointY(int y) {
        return transformer.getTransformedY(y);
    }

    private void updateTransformParameters() {
        Insets insets= window.getInsets();
        int contentW= window.getWidth() - insets.left - insets.right;
        int contentH= window.getHeight() - insets.top - insets.bottom;

        transformer.setInset(insets.top,insets.left, insets.bottom, insets.right);
        transformer.update(contentW,contentH);
        transformer.transform(this);

    }
}
