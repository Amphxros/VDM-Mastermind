package vdm.mastermind.logic.gameobjects;

import java.util.ArrayList;
import java.util.Arrays;

import vdm.mastermind.engine.interfaces.IGraphics;
import vdm.mastermind.engine.interfaces.IScene;
import vdm.mastermind.logic.Cell;
import vdm.mastermind.logic.CellState;

public final class Tracks extends GameObject{


    private int index;
    private int numMiniTracks;
    CellState state;
    public Tracks(IScene scene){
        super(scene);
    }

    public void init(int _numMiniTracks){
        this.numMiniTracks = _numMiniTracks;
        state = CellState.EMPTY;
    }
    public void render(IGraphics graphics){
        super.render(graphics);
        graphics.setColor(strokeColor);
        graphics.drawCircle(2*getX(),2*getY(),(getWidth()/2));
    }

}
