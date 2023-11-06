package vdm.mastermind.logic.gameobjects;

import java.util.ArrayList;

import javax.swing.Renderer;

import vdm.mastermind.engine.classes.Color;
import vdm.mastermind.engine.enums.HorizontalAlignment;
import vdm.mastermind.engine.interfaces.IGraphics;
import vdm.mastermind.engine.interfaces.IScene;
import vdm.mastermind.logic.Cell;
import vdm.mastermind.logic.DaltonicListener;
import vdm.mastermind.logic.Password;
import vdm.mastermind.logic.scenes.Scene;

public class Table extends GameObject implements DaltonicListener {

    int index;
    int tamRow;
    int currElem;
    int maxValue;
    Password password;
    ArrayList<Cell> cells;

    ArrayList<Tracks> myTracks;
    public Table(IScene scene, int index, int tamRow, int numColors) {
        super(scene);
        this.index=index;
        this.tamRow=tamRow;
        this.currElem=0;
        this.maxValue=numColors;
        this.password= new Password(this.tamRow, 1,numColors,false);


    }

      private Cell createCell(int index){
        Cell c= new Cell(getScene());
        //((index/2) + 1) * (this.getWidth()/(tamRow + 2))
        c.setPosition( this.getX() + ((((this.getWidth()/(tamRow + 1)))/2 * (index + 1))),
                this.getY()/2 + this.getHeight()/4);
        c.setSize(20,20);
        c.setStrokeColor(new Color(100,100,100));
        c.setEnabled(true);

        return c;
    }

    private Tracks createTrack(int index){
        Tracks t = new Tracks(this.getScene());
//(this.getWidth()/(tamRow + 2) * ((tamRow + 2)/2))) * index
        int fila = 0;
        int colum = index + 1;
        if((index + 1) > tamRow/2)  {
            fila = this.getY() + (this.getHeight()/4);
            colum -= tamRow/2;
        }
        else fila = this.getY() + (this.getHeight()/4)*3;
        t.setPosition(this.getWidth() - (((this.getWidth()/(tamRow + 2))/2) * (int)(colum)), fila);
        t.setSize(5,5);
        t.setStrokeColor(new Color(255, 0, 0));
        t.setEnabled(true);

        return t;
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



    }

    @Override
    public void render(IGraphics graphics) {

        graphics.setColor(new Color(200,200,200));
        graphics.drawRoundRectangle(getX(),getY(),getWidth(),getHeight(),10);
        graphics.setColor(new Color(0,0,0));
        graphics.drawText(String.valueOf(index),getX() + 1*getHeight()/4, getY() + 3*getHeight()/4, getWidth(), HorizontalAlignment.NONE);

        for(int i=0;i<this.tamRow;i++){
            this.cells.get(i).render(graphics);
            this.myTracks.get(i).render(graphics);
        }

        //super.render(graphics);
    }

    public void onColorSelected(int color){

    }

    @Override
    public void onDaltonicMode() {

    }
    @Override
    public void setDaltonicMode(boolean mode){

    }
}
