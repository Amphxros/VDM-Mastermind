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

public class Table extends GameObject implements DaltonicListener {

    int index;
    int tamRow;
    int currElem;

    int maxValue;
    Password password;
    ArrayList<Cell> cells;
    public Table(IScene scene, int index, int tamRow, int numColors) {
        super(scene);
        this.index=index;
        this.tamRow=tamRow;
        this.currElem=0;
        this.maxValue=numColors;
        this.password= new Password(this.tamRow, 1,numColors,false);

        cells= new ArrayList<>(this.tamRow);
        for(int i=0;i<this.tamRow;i++){
            cells.add(createCell(i));
        }

    }

      private Cell createCell(int index){
        Cell c= new Cell(getScene());
        c.setPosition(20*(index+1),2*getY() + getHeight());
        c.setSize(10,10);
        c.setStrokeColor(new Color(0,0,0));
        c.setEnabled(true);

        return c;
    }

    @Override
    public void init() {

    }

    @Override
    public void render(IGraphics graphics) {

        graphics.setColor(new Color(200,200,200));
        graphics.drawRoundRectangle(getX(),getY(),getWidth(),getHeight(),10);
        graphics.setColor(new Color(0,0,0));
        graphics.drawText(String.valueOf(index),getX() + 1*getHeight()/4, getY() + 3*getHeight()/4, getWidth(), HorizontalAlignment.NONE);

        for(int i=0;i<this.tamRow;i++){
            cells.get(i).render(graphics);
        }

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
