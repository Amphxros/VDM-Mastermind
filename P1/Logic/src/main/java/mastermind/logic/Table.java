package mastermind.logic;

import java.util.ArrayList;

import mastermind.engine.Color;
import mastermind.engine.IFont;
import mastermind.engine.IGraphics;
import mastermind.engine.IScene;

public class Table extends GameObject implements DaltonicListener{
    int numElems;

    int[] solution;
    ArrayList<Cell> cells;
    IFont font;
    public Table(IScene scene, int numElems, IFont font) {
        super(scene);
        this.numElems=numElems;
        this.solution= new int[this.numElems];
        this.font=font;

    }

    @Override
    public void init() {
        super.init();
        for(int i=0;i<this.numElems;i++){
            Cell c= new Cell(getScene(),font);
        }
    }
    public void addCell(Cell c){
        cells.add(c);
        c.setPosition(0,getHeight()/4);
        addChild(c);
    }

    /**
     *
     * @return if the table is completed
     */
    public boolean isComplete(){
       int i=0;
       while(i< cells.size() && cells.get(i).getState()!=CellState.Empty)
           i++;

       for(int j=0;j<this.numElems;j++){
           solution[j]=cells.get(j).getValue();
       }
       return i==cells.size()-1;
    }

    public void fillCell(Color c, int value){
        int i=0;
        while(i< cells.size() && cells.get(i).getState()!=CellState.Empty)
            i++;

        cells.get(i).fillCell(c,value);
    }

    @Override
    public void render(IGraphics graphics) {
        graphics.setColor(strokeColor);
        graphics.drawRoundRectangle(getX(),getY(),getWidth(), getHeight(),30);
        super.render(graphics);
    }


    @Override
    public void setDaltonicMode(boolean mode) {

    }

    @Override
    public void drawDaltonicInfo(IGraphics graphics) {
        for(Cell c: cells)
            c.drawDaltonicInfo(graphics);
    }
}
