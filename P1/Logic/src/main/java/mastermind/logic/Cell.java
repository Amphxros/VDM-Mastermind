package mastermind.logic;

import mastermind.engine.Color;
import mastermind.engine.IFont;
import mastermind.engine.IGraphics;
import mastermind.engine.IScene;
import mastermind.engine.TouchEvent;
import mastermind.logic.button.Button;

public class Cell extends Button implements DaltonicListener{
    int value=-1;
    CellState state;
    Color initialColor;
    boolean daltonic_mode;

    IFont font;
    public Cell(IScene scene, IFont font) {
        super(scene);
        this.font=font;

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
        graphics.fillCircle(getX(), getY(), getWidth() / 2);
        drawDaltonicInfo(graphics);

        super.render(graphics);
    }

    @Override
    public boolean onTouchDown(TouchEvent event) {
        state= CellState.Empty;
        return true;
    }

    public void fillCell(Color c, int value){
        strokeColor=c;
        this.value=value;
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

            graphics.drawText(String.valueOf(value), getX(), getY());
        }
    }
}
