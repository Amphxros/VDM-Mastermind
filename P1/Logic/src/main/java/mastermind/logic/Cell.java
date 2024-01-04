package mastermind.logic;

import mastermind.engine.Color;
import mastermind.engine.IFont;
import mastermind.engine.IGraphics;
import mastermind.logic.scene.IScene;
import mastermind.engine.ISound;
import mastermind.engine.TouchEvent;
import mastermind.logic.button.Button;

public class Cell extends Button implements DaltonicListener{
    int value=-1;
    CellState state;
    Color initialColor;
    boolean daltonic_mode;

    IFont font;
    ISound sound;
    public Cell(IScene scene, IFont font, ISound sound) {
        super(scene);
        this.font=font;
        this.state=CellState.Empty;
        this.sound=sound;
    }

    @Override
    public void init() {
        initialColor=strokeColor;
        super.init();
    }

    @Override
    public void render(IGraphics graphics) {

        if(state ==CellState.Empty) {
            graphics.setColor(initialColor);
        }
        else {
            graphics.setColor(strokeColor);
        }
        graphics.fillCircle(getX() + getWidth()/2,getY()+ getHeight()/2, getWidth()/2);
        drawDaltonicInfo(graphics);

        super.render(graphics);
    }

    @Override
    public boolean onTouchDown(TouchEvent event) {
        getEngine().getAudio().stopSound(sound);
        state= CellState.Empty;
        this.value=-1;
        if(sound!=null)
            getEngine().getAudio().playSound(sound);
        return true;
    }

    public void fillCell(Color c, int value){
        getEngine().getAudio().stopSound(sound);
        strokeColor=c;
        this.value=value;
        if(sound!=null) {
            getEngine().getAudio().playSound(sound);
        }
        state=CellState.Filled;
    }

    public CellState getState(){
        return state;
    }
    public int getValue(){
        return value;
    }

    @Override
    public void setDaltonicMode(boolean mode) {
        daltonic_mode=mode;
    }

    @Override
    public void drawDaltonicInfo(IGraphics graphics) {
        if(daltonic_mode && value!=-1){
            graphics.setColor(Color.BLACK);
            graphics.setFont(font);

            graphics.drawText(String.valueOf(value + 1), getX() + getWidth()/2, getY()+ 3*getHeight()/4);
        }
    }
}
