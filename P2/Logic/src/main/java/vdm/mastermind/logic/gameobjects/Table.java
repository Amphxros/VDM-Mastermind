package vdm.mastermind.logic.gameobjects;

import java.util.ArrayList;

import vdm.mastermind.engine.classes.Color;
import vdm.mastermind.engine.enums.HorizontalAlignment;
import vdm.mastermind.engine.interfaces.IGraphics;
import vdm.mastermind.engine.interfaces.IScene;
import vdm.mastermind.engine.interfaces.objects.IFont;
import vdm.mastermind.logic.Cell;
import vdm.mastermind.logic.CellState;
import vdm.mastermind.logic.DaltonicListener;
import vdm.mastermind.logic.Password;

public class Table extends GameObject implements DaltonicListener {

    int index;
    int tamRow;
    int currElem;
    int maxValue;
    Password password;
    ArrayList<Cell> cells;
    ArrayList<Tracks> myTracks;
    IFont font;
    public Table(IScene scene, int index,IFont font, int tamRow, int numColors, Password solution) {
        super(scene);
        this.index=index;
        this.tamRow=tamRow;
        this.currElem=0;
        this.maxValue=numColors;
        this.font=font;
        this.password=solution;

    }

      private Cell createCell(int index){
        Cell c= new Cell(getScene(),font);
        //((index/2) + 1) * (this.getWidth()/(tamRow + 2))
        c.setPosition( this.getX() + ((((this.getWidth()/(tamRow + 2))) * (index + 1))) + ((this.getWidth()/(tamRow + 2)/2)),
                this.getY() + this.getHeight()/2);
        c.setSize(15,15);
        c.setStrokeColor(new Color(180,180,180));
        c.setEnabled(true);

        return c;
    }

    @Override
    public void update(double delta) {
        int i=0;
        while (i<cells.size() && cells.get(i).getState()==CellState.FILLED)
            i++;

        if(i==cells.size()-1){
            //si esta completo
        }
    }

    private Tracks createTrack(int index){
        Tracks t = new Tracks(this.getScene());
        //(this.getWidth()/(tamRow + 2) * ((tamRow + 2)/2))) * index
        int fila = 0;
        int colum = index;
        if((index + 1) > (tamRow)/2)  {
            fila = this.getY() + (this.getHeight()/4);
            colum = index - ((tamRow)/2);
        }
        else fila = this.getY() + (this.getHeight()/4)*3;
        t.setPosition((this.getWidth() - (this.getWidth()/(tamRow + 2)) + (((this.getWidth()/(tamRow + 2))/((tamRow + 1)/2))*colum)), fila);
        t.setSize(5,5);
        t.setStrokeColor(new Color(180,180,180));
        t.setEnabled(true);

        return t;
    }

    public void doTrack(){
        int currentElem = 0;
        ArrayList<Integer> wrong = new ArrayList<Integer>();
        for(int i = 0; i < myTracks.size(); ++i) {
            if(cells.get(i).getIndex() == password.getIntPassword(i)) {
                myTracks.get(currentElem).setState(CellState.CORRECT);
                cells.get(i).setState(CellState.CORRECT);
                myTracks.get(currentElem).setStrokeColor(new Color(0,0,0));
                currentElem++;
            }else{
                wrong.add(i);
            }
        }

        for(int i = 0; i < wrong.size(); i++){
            for(int j = 0; j < cells.size(); ++j){
                if(currentElem < myTracks.size() && j != wrong.get(i) && cells.get(j).getState() != CellState.CORRECT &&
                        cells.get(j).getIndex() == password.getIntPassword(wrong.get(i)) &&
                        myTracks.get(currentElem).getState() != CellState.EMPTY) {

                    myTracks.get(currentElem).setState(CellState.WRONG);
                    cells.get(j).setState(CellState.WRONG);
                    myTracks.get(currentElem).setStrokeColor(new Color(255,255,255));
                    currentElem++;
                }
            }
        }
    }

    @Override
    public void init() {
        this.cells= new ArrayList<>(this.tamRow);
        this.myTracks = new ArrayList<>(this.tamRow);
        for(int i=0;i<this.tamRow;i++){
            //create Cells
            this.cells.add(createCell(i));
            //create Tracks
            this.myTracks.add(createTrack(i));
        }
        super.init();
    }

    @Override
    public void render(IGraphics graphics) {

        graphics.setColor(new Color(200,200,200));
        graphics.drawRoundRectangle(getX(),getY(),getWidth(),getHeight(),10);
        graphics.drawText(String.valueOf(index),getX() + 1*getHeight()/4, getY() + 3*getHeight()/4, getWidth(), HorizontalAlignment.NONE);

        for(int i=0;i<this.tamRow;i++){
            this.cells.get(i).render(graphics);
            this.myTracks.get(i).render(graphics);
        }
    }

    public boolean onColorSelected(Color color, int index){
        for(int i=0; i < cells.size(); ++i){
            if(cells.get(i).getState() == CellState.EMPTY){
                cells.get(i).setState(CellState.FILLED);
                cells.get(i).setStrokeColor(color);
                cells.get(i).setIndex(index);
                return false;
            }
        }
        return true;
    }

    @Override
    public void onDaltonicMode() {
        for(Cell c: cells)
            c.onDaltonicMode();
    }
    @Override
    public void setDaltonicMode(boolean mode){
        for(Cell c: cells)
            c.setDaltonicMode(mode);
    }


}
