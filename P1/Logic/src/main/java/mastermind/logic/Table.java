package mastermind.logic;

import mastermind.engine.Color;
import mastermind.engine.IFont;
import mastermind.engine.IGraphics;
import mastermind.logic.scene.IScene;
import mastermind.engine.ISound;

public class Table extends GameObject implements DaltonicListener{
    int numElems;

    int[] solution;
    Cell [] cells;
    IFont font;
    HintObject hintObject;
    boolean showHints;
    ISound sound;
    public Table(IScene scene, int numElems, IFont font, boolean showHints, ISound sound) {
        super(scene);
        this.numElems=numElems;
        this.solution= new int[this.numElems];
        this.font=font;
        this.cells= new Cell[this.numElems];
        this.showHints=showHints;
        this.sound=sound;
    }

    @Override
    public void init() {
        int initialPos=getWidth()/numElems;
        for(int i=0;i<this.numElems;i++){
            this.cells[i]=(new Cell(getScene(),font,sound));
            this.cells[i].setSize(2*getHeight()/3,2*getHeight()/3)
                    .setStrokeColor(new Color(150,150,150))
                    .setPosition(initialPos/3 + (3*getHeight()/4)*(i),10);
            this.addChild(cells[i]);
        }
        if(showHints) {
            hintObject = new HintObject(getScene(), this.numElems);
            hintObject.setPosition(getWidth() - 90, 2);
            hintObject.setSize(70, 40);
            hintObject.setStrokeColor(new Color(50, 50, 50));

            this.addChild(hintObject);
        }
        super.init();
    }
    /**
     *
     * @return true if the table is completed
     */
    public boolean isComplete(){
       int i=0;
       while(i< cells.length && cells[i].getState()!=CellState.Empty) {
           i++;
       }

       for(int j=0;j<this.cells.length;j++){
            this.solution[j]=cells[j].getValue();
       }
       return i==cells.length;
    }

    public void fillCell(Color c, int value){
        int i=0;
        while(i< cells.length && cells[i].getState()!=CellState.Empty) {
            i++;
        }
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

    public boolean correctHints(int[] solution){
        return hintObject.showHints(solution, this.solution);
    }
}
