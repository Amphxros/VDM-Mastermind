package mastermind.logic;

import java.util.ArrayList;

import mastermind.engine.Color;
import mastermind.engine.IFont;
import mastermind.engine.IGraphics;
import mastermind.engine.IScene;

public class Table extends GameObject implements DaltonicListener{
    int numElems;

    int[] solution;
    Cell [] cells;
    IFont font;
    public Table(IScene scene, int numElems, IFont font) {
        super(scene);
        this.numElems=numElems;
        this.solution= new int[this.numElems];
        this.font=font;
        this.cells= new Cell[this.numElems];

    }

    @Override
    public void init() {
        for(int i=0;i<this.numElems;i++){
            this.addChild(new Cell(getScene(),font)
                    .setSize(30,30)
                    .setStrokeColor(new Color(150,150,150))
                    .setPosition(20 + 40*(i),10)
            );
        }

        this.addChild(new HintObject(getScene())
                .setPosition(getWidth()-90, 2)
                .setSize(70,40)
                .setStrokeColor(new Color(50,50,50))
        );
        super.init();
    }
    /**
     *
     * @return true if the table is completed
     */
    public boolean isComplete(){
       int i=0;
       while(i< cells.length && cells[i].getState()!=CellState.Empty)
           i++;

       for(int j=0;j<this.numElems;j++){
           solution[j]=cells[j].getValue();
       }
       return i==cells.length-1;
    }

    public void fillCell(Color c, int value){
        int i=0;
        while(i< cells.length && cells[i].getState()!=CellState.Empty)
            i++;

        cells[i].fillCell(c,value);
    }

    @Override
    public void render(IGraphics graphics) {
        graphics.setColor(strokeColor);
        graphics.drawRoundRectangle(getX(),getY(),getWidth(), getHeight(),30);
        super.render(graphics);
    }


    @Override
    public void setDaltonicMode(boolean mode) {
        for(Cell c: cells)
            c.setDaltonicMode(mode);

    }

    @Override
    public void drawDaltonicInfo(IGraphics graphics) {
        for(Cell c: cells)
            c.drawDaltonicInfo(graphics);
    }
}
